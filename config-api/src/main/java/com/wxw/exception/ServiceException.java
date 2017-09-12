package com.wxw.exception;

/**
 * @Author:Created by wanxiaowei
 * @Description: service异常
 * @Date:Created in 18:38 2017/7/19
 * @Modified By :
 */
public class ServiceException extends RuntimeException{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
