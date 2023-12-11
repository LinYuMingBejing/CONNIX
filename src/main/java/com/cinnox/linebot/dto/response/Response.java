package com.cinnox.linebot.dto.response;

import com.cinnox.linebot.enums.ResultCode;
import lombok.Data;

@Data
public class Response {
    private int statusCode;
    private Object data;

    public static Response success(){
        Response response = new Response();
        response.setStatusCode(ResultCode.SUCCESS.getCode());
        return response;
    }
}
