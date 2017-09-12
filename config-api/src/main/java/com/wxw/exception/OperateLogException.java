package com.wxw.exception;

/**
 * @Author:Created by wanxiaowei
 * @Description:
 * @Date:Created in 16:08 2017/7/27
 * @Modified By :
 */
public class OperateLogException  extends BaseException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public OperateLogException() {
        super();
    }

    public OperateLogException(String arg0) {
        super(arg0);
    }

    public OperateLogException(Throwable arg0) {
        super(arg0);
    }

    public OperateLogException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public OperateLogException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
