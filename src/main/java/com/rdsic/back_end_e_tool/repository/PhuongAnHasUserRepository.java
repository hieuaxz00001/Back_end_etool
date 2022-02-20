package com.rdsic.back_end_e_tool.repository;

import com.rdsic.back_end_e_tool.entities.PhuongAnHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PhuongAnHasUserRepository extends JpaRepository<PhuongAnHasUser, Integer>, JpaSpecificationExecutor<PhuongAnHasUser> {
    List<PhuongAnHasUser> findByUserId_Id(Integer idUser);
}