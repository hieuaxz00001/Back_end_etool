package com.rdsic.back_end_e_tool.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormLogin {
    private String username;
    private String password;

}
