package com.wxw.admin.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.wxw.config.ConfigConstant;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Configuration
@MapperScan(basePackages = {ConfigConstant.MAPPER_BASE_PACKAGES, ConfigConstant.DAO_BASE_PACKAGES})
@ComponentScan(basePackages = {ConfigConstant.SCAN_BASE_PACKAGES}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
        @Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @Filter(type = FilterType.ANNOTATION, value = RestController.class),
        @Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class)})
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
@ConfigurationProperties(prefix="jedis")
public class SpringContextConfig implements AsyncConfigurer, TransactionManagementConfigurer {
    private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

    @SuppressWarnings("unused")
    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    /**
     * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
     * @return restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name= "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal}")int maxTotal,
                                            @Value("${jedis.pool.config.maxIdle}")int maxIdle,
                                            @Value("${jedis.pool.config.testOnBorrow}")Boolean testOnBorrow,
                                            @Value("${jedis.pool.config.testOnReturn}")Boolean testOnReturn,
                                            @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        return config;
    }

    @Bean(name= "jedisPool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig config,
                               @Value("${jedis.pool.host}")String host,
                               @Value("${jedis.pool.password}")String password,
                               @Value("${jedis.pool.timeOut}")int timeOut,
                               @Value("${jedis.pool.port}")int port) {
        JedisPool pool = null;
        if (StringUtils.isNotBlank(password)) {
            pool = new JedisPool(config, host, port, timeOut, password);
        } else {
            pool = new JedisPool(config, host, port, timeOut);
        }
        return pool;
    }


    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     * @return
     */
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }
    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }
    //引用该bean时  需要指定@Transactional的value值
    @Bean(name = "userTransactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        JtaTransactionManager manager = new JtaTransactionManager(userTransaction,atomikosTransactionManager());
        return manager;
    }

    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        //默认DataSourceTransactionManager为PlatformTransactionManager实现类
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public Executor getAsyncExecutor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // TODO Auto-generated method stub
        return null;
    }

}
