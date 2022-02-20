package com.rdsic.back_end_e_tool.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "phuong_an_quy_hoach")
public class PhuongAnQuyHoach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_phuong_an", nullable = false)
    private String tenPhuongAn;

    @Column(name = "dia_diem", nullable = false)
    private String diaDiem;

    @Column(name = "ngay_tao", nullable = false)
    private Date ngayTao;

    @Column(name = "thong_tin_chung", nullable = false,columnDefinition = "MEDIUMTEXT")
    private String thongTinChung;

    @Column(name = "dien_tich", nullable = false)
    private String dienTich;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

}
