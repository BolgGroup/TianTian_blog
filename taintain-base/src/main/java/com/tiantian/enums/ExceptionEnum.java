package com.tiantian.enums;

import com.tiantian.result.*;
import org.springframework.http.HttpStatus;


public enum ExceptionEnum {

	/**
	 * 无效参数
	 */
	PARAMETER_INVALID(ParameterInvalidException.class, HttpStatus.BAD_REQUEST, ResultCode.PARAM_IS_INVALID),
	CAPTCHA_ERROR(CaptchaErrorException.class, HttpStatus.BAD_REQUEST, ResultCode.CAPTCHA_ERROR),
	FILE_IS_NULL_ERROR(FileNullException.class, HttpStatus.BAD_REQUEST, ResultCode.FILE_IS_NULL_ERROR),
	FILE_UPLOAD_ERROR(FileUploadException.class, HttpStatus.BAD_REQUEST, ResultCode.FILE_UPLOAD_ERROR);

	private Class<? extends BusinessException> eClass;

	private HttpStatus httpStatus;

	private ResultCode resultCode;

	ExceptionEnum(Class<? extends BusinessException> eClass, HttpStatus httpStatus, ResultCode resultCode) {
		this.eClass = eClass;
		this.httpStatus = httpStatus;
		this.resultCode = resultCode;
	}

	public Class<? extends BusinessException> getEClass() {
		return eClass;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public static boolean isSupportHttpStatus(int httpStatus) {
		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (exceptionEnum.httpStatus.value() == httpStatus) {
				return true;
			}
		}

		return false;
	}

	public static boolean isSupportException(Class<?> z) {
		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (exceptionEnum.eClass.equals(z)) {
				return true;
			}
		}

		return false;
	}

	public static ExceptionEnum getByHttpStatus(HttpStatus httpStatus) {
		if (httpStatus == null) {
			return null;
		}

		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (httpStatus.equals(exceptionEnum.httpStatus)) {
				return exceptionEnum;
			}
		}

		return null;
	}

	public static ExceptionEnum getByEClass(Class<? extends BusinessException> eClass) {
		if (eClass == null) {
			return null;
		}
		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (eClass.equals(exceptionEnum.eClass)) {
				return exceptionEnum;
			}
		}
		return null;
	}
}