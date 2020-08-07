package com.tiantian.result;

import com.tiantian.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultResult implements Result {

    /**
     *
     */
    private static final long serialVersionUID = -475779915021896428L;

    private Integer code;

    private String message;

    private Object data;

    public DefaultResult(ResultCode resultCode, Object data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.message = resultCode.message(); 
    }

    public static DefaultResult success(){
        DefaultResult result = new DefaultResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static DefaultResult success(Object data){
        DefaultResult result = new DefaultResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static DefaultResult failure(ResultCode resultCode){
        DefaultResult result = new DefaultResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static DefaultResult failure(ResultCode resultCode, Object data){
        DefaultResult result = new DefaultResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
