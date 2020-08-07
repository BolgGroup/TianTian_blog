package com.tiantian.result;


import com.tiantian.enums.ResultCode;

public class FileNullException extends BusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = -5574037708230876835L;

	public FileNullException() {
		super();
	}

	public FileNullException(Object data) {
		super();
		super.setData(data);
	}

	public FileNullException(ResultCode resultCode) {
		super(resultCode);
	}

	public FileNullException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public FileNullException(String msg) {
		super(msg);
	}

	public FileNullException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}

}