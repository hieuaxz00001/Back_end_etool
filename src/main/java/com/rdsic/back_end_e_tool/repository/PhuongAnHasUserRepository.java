package com.rdsic.back_end_e_tool.repository;

import com.rdsic.back_end_e_tool.entities.PhuongAnHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Repository
public interface PhuongAnHasUserRepository extends JpaRepository<PhuongAnHasUser, Integer>, JpaSpecificationExecutor<PhuongAnHasUser> {
    List<PhuongAnHasUser> findByUserId_Id(Integer idUser);

    @Modifying
    @Query("delete from PhuongAnHasUser h where h.userId.id = :idUser and h.phuongAnQuyHoachId.id = :idPhuongAn")
    void deleteUserTrongPhuongAn(@Param("idUser") Integer idUser, @Param("idPhuongAn") Integer idPhuongAn);
}