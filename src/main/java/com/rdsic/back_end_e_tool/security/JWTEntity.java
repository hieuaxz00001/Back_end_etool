package com.rdsic.back_end_e_tool.security;

import lombok.Data;

@Data
public class JWTEntity {
    // mã token
    private String code;
    // thời gian sống
    private long duration;
}
