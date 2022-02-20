package com.rdsic.back_end_e_tool.service_impl;

import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class PhuongAnQuyHoachService_Impl extends BaseService<PhuongAnQuyHoach> {
    public PhuongAnQuyHoachService_Impl(JpaSpecificationExecutor<PhuongAnQuyHoach> repo, EntityManager entityManager) {
        super(repo, entityManager);
    }
}
