package com.rdsic.back_end_e_tool.service_impl;

import com.rdsic.back_end_e_tool.entities.PhuongAnHasUser;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
@Service
public class PhuongAnHasUserService_Impl extends BaseService<PhuongAnHasUser> {
    public PhuongAnHasUserService_Impl(JpaSpecificationExecutor<PhuongAnHasUser> repo, EntityManager entityManager) {
        super(repo, entityManager);
    }
}
