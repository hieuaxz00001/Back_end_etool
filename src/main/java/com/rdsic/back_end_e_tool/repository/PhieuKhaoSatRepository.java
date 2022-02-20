package com.rdsic.back_end_e_tool.repository;

import com.rdsic.back_end_e_tool.entities.PhieuKhaoSat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhieuKhaoSatRepository extends JpaRepository<PhieuKhaoSat, Integer>, JpaSpecificationExecutor<PhieuKhaoSat> {

}