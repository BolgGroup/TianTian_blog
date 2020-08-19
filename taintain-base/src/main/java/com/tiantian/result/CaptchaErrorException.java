package com.tiantian.result;


import com.tiantian.enums.ResultCode;

/**
 * @author qi_bingo
 */
public class CaptchaErrorException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = -5574037708230876835L;

    public CaptchaErrorException() {
		super();
	}

	public CaptchaErrorException(Object data) {
        super();
        super.setData(data);
	}

	public CaptchaErrorException(ResultCode resultCode) {
		super(resultCode);
	}

	public CaptchaErrorException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public CaptchaErrorException(String msg) {
		super(msg);
	}

	public CaptchaErrorException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}


}