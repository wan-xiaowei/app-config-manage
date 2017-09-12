package com.wxw.exception;

/**
 * @Author:Created by wanxiaowei
 * @Description: 异常基类
 * @Date:Created in 18:38 2017/7/19
 * @Modified By :
 */
public class BaseException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8376555234967628678L;

	public BaseException() {
		super();
	}

	public BaseException(String arg0) {
		super(arg0);
	}

	public BaseException(Throwable arg0) {
		super(arg0);
	}

	public BaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BaseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
