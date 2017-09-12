package com.wxw.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpClientTools {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientTools.class);

    private static final CloseableHttpClient httpClient;

    private static final String characterSet = "utf-8";

    private HttpClientTools() {

    }

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        cm.setMaxTotal(200);

        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }


    public static String GetRequst(Map<String, String> Params, String Url) {
        String url = Url;
        if (Params != null) {
            url = "";
            StringBuilder str = new StringBuilder();
            for (Map.Entry<String, String> entry : Params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                str.append(key + "=" + value + "&");
            }
            str.setLength(str.length() - 1);
            url = Url + "?" + str.toString();

            logger.info("url {}", url);
        }
        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(2000).build();

        httpGet.setConfig(requestConfig);

        String result = null;
        try {
            logger.info("URL {}", url);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();

            logger.info("status {}", httpResponse.getStatusLine());

            if (entity != null) {
                logger.info("contentEncoding{} ", entity.getContentEncoding());
                result = EntityUtils.toString(entity, characterSet);
                logger.info("response content  {}", result);
            }
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            httpGet.releaseConnection();
        }
        return result;
    }

    public static String PostRequst(Map<String, String> postParam, String url) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(5000).build();
        httpPost.setConfig(requestConfig);

        if (postParam != null) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : postParam.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                params.add(new BasicNameValuePair(key, value));
                logger.info(value);
            }

            HttpEntity reqEntity = new UrlEncodedFormEntity(params, characterSet);
            httpPost.setEntity(reqEntity);
        }

        String result = null;
        try {

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity entity = httpResponse.getEntity();

            logger.info("status{}", httpResponse.getStatusLine());

            if (entity != null) {

                result = EntityUtils.toString(entity, characterSet);

            } else {
                logger.info("entity is null");
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            httpPost.releaseConnection();
        }
        return result;

    }


    public static Map<String, String> PostRequst(String xmlParam, String url) throws DocumentException {
        //CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);

        if (xmlParam != null) {
            HttpEntity reqEntity = new StringEntity(xmlParam, characterSet);
            httpPost.setEntity(reqEntity);
        }

        String result = null;
        try {

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {

                HttpEntity entity = httpResponse.getEntity();

                logger.info("status{}", httpResponse.getStatusLine());

                if (entity != null) {

                    result = EntityUtils.toString(entity, characterSet);

                } else {
                    logger.info("contententity is null ");
                }
            } else {
                logger.info("url{}", url);
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            httpPost.releaseConnection();
        }

        Map<String, String> map = new HashMap<String, String>();

        // SAXReader reader = new SAXReader();
        if (result != null) {
            if (result.trim().startsWith("{")) {
                JSONObject jsonobj = JSONObject.fromObject(result);
                Iterator keys = jsonobj.keys();
                while (keys.hasNext()) {
                    String key = keys.next().toString();
                    map.put(key, jsonobj.get(key).toString());
                }
            } else {
                Document document = DocumentHelper.parseText(result);

                Element root = document.getRootElement();

                List<Element> elementList = root.elements();


                for (Element e : elementList)
                    map.put(e.getName(), e.getText());
            }
        }
        return map;

    }


}
