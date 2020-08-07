package com.tiantian.result;

import com.tiantian.enums.ExceptionEnum;
import com.tiantian.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {

    private String code;

    private String message;

    private ResultCode resultCode;

    private Object data;

    /**
     *
     */
    private static final long serialVersionUID = -5574037708230876835L;

    public BusinessException() {
		ExceptionEnum exceptionEnum = ExceptionEnum.getByEClass(this.getClass());
		if (exceptionEnum != null) {
			resultCode = exceptionEnum.getResultCode();
			code = exceptionEnum.getResultCode().code().toString();
			message = exceptionEnum.getResultCode().message();
		}

	}

	public BusinessException(String message) {
		this();
		this.message = message;
	}

	public BusinessException(String format, Object... objects) {
		this();
		format = StringUtils.replace(format, "{}", "%s");
		this.message = String.format(format, objects);
	}

	public BusinessException(String msg, Throwable cause, Object... objects) {
		this();
		String format = StringUtils.replace(msg, "{}", "%s");
		this.message= String.format(format, objects);
	}

	public BusinessException(ResultCode resultCode, Object data) {
		this(resultCode);
		this.data = data;
	}

	public BusinessException(ResultCode resultCode) {
		this.resultCode = resultCode;
		this.code = resultCode.code().toString();
		this.message = resultCode.message();
	}

}