package com.sysuser.oauth2.exception;

/**
 * @author lijinsheng
 * @date  2018/8/6 15:56
 *  参数不正确
 **/
public class ArgumentsFailureException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2507203569593457083L;

	public ArgumentsFailureException() {
        this("参数错误");
    }

    public ArgumentsFailureException(String message) {
        super(message);
    }
}
