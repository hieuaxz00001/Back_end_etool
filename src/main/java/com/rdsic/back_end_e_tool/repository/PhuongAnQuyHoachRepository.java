package com.rdsic.back_end_e_tool.repository;

import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhuongAnQuyHoachRepository extends JpaRepository<PhuongAnQuyHoach, Integer>, JpaSpecificationExecutor<PhuongAnQuyHoach> {

}