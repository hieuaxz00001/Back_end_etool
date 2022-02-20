package com.rdsic.back_end_e_tool.service_impl;

import com.rdsic.back_end_e_tool.entities.PhieuKhaoSat;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
@Service
public class PhieuKhaoSatService_Impl extends BaseService<PhieuKhaoSat> {
    public PhieuKhaoSatService_Impl(JpaSpecificationExecutor<PhieuKhaoSat> repo, EntityManager entityManager) {
        super(repo, entityManager);
    }
}
