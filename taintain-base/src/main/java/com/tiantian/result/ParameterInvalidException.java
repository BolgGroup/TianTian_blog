package com.tiantian.result;


import com.tiantian.enums.ResultCode;

public class ParameterInvalidException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = -5574037708230876835L;

    public ParameterInvalidException() {
		super();
	}

	public ParameterInvalidException(Object data) {
        super();
        super.setData(data);
	}

	public ParameterInvalidException(ResultCode resultCode) {
		super(resultCode);
	}

	public ParameterInvalidException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public ParameterInvalidException(String msg) {
		super(msg);
	}

	public ParameterInvalidException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}


}