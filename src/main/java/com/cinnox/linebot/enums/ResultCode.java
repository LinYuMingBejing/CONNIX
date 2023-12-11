package com.cinnox.linebot.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    SUCCESS(200),
    ERROR(500);
    private int code;
    public int getCode(){
        return this.code;
    }
}
