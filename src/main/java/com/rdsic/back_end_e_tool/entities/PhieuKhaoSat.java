package com.rdsic.back_end_e_tool.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "phieu_khao_sat")
public class PhieuKhaoSat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dia_diem", nullable = false)
    private String diaDiem;

    @Column(name = "kinh_do", nullable = false)
    private String kinhDo;

    /**
     * giờ bắt đầu khảo sát
     */
    @Column(name = "gio_bat_dau", nullable = false)
    private String gioBatDau;

    /**
     * giờ kết thúc  khảo sát
     */
    @Column(name = "gio_ket_thuc", nullable = false)
    private String gioKetThuc;

    /**
     * tên người hỏi khảo sat này
     */
    @Column(name = "ten_nguoi_phong_van", nullable = false)
    private String tenNguoiPhongVan;

    @Column(name = "cau_hoi", nullable = false,columnDefinition = "MEDIUMTEXT")
    private String cauHoi;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "phuong_quy_hoach_id")
    private PhuongAnQuyHoach phuongAnQuyHoachId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;


    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "vy_do", nullable = false)
    private String vyDo;

}
