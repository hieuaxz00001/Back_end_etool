package com.rdsic.back_end_e_tool.entities;

import com.rdsic.back_end_e_tool.key.PhuongAnHasUserKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "phuong_an_has_user")
@NoArgsConstructor
@AllArgsConstructor
public class PhuongAnHasUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PhuongAnHasUserKey id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("phuongAnQuyHoachId") // tên của khóa ngoại trong embeddedId
    @JoinColumn(name = "phuong_an_quy_hoach_id")// khóa ngoại
    private PhuongAnQuyHoach phuongAnQuyHoachId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("userId") // tên của khóa ngoại trong embeddedId
    @JoinColumn(name = "user_id")// khóa ngoại
    private User userId;

}
