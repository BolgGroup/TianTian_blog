package com.tiantian.result;


import com.tiantian.enums.ResultCode;

public class FileUploadException extends BusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = -5574037708230876835L;

	public FileUploadException() {
		super();
	}

	public FileUploadException(Object data) {
		super();
		super.setData(data);
	}

	public FileUploadException(ResultCode resultCode) {
		super(resultCode);
	}

	public FileUploadException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public FileUploadException(String msg) {
		super(msg);
	}

	public FileUploadException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}

}