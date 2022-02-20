package com.rdsic.back_end_e_tool.specitication;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria implements Serializable {
    private String key;
    private String operation;
    private Object value;
}
