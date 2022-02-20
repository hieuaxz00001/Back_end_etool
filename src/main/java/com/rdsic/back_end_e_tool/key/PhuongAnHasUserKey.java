package com.rdsic.back_end_e_tool.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhuongAnHasUserKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer phuongAnQuyHoachId;
    private Integer userId;
}
