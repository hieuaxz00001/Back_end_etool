package com.rdsic.back_end_e_tool.controller.finder;

import com.rdsic.back_end_e_tool.controller.BaseFinder;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhieuKhaoSat;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.repository.PhieuKhaoSatRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/private-get/phieu-khao-sat")
public class PhieuKhaoSatFinderController extends BaseFinder<PhieuKhaoSat> {
    public PhieuKhaoSatFinderController(BaseService<PhieuKhaoSat> service) {
        super(service);
    }
    @Autowired
    private PhieuKhaoSatRepository phieuKhaoSatRepository;
    @GetMapping("get-list-phieu-khao-sat")
    public ResponseEntity<JsonResult> getList(@RequestParam(value = "queries", required = false) List<String> queries,
                                              @RequestParam(value = "sort", required = false) String sort) {
        try {
            List<PhieuKhaoSat> phieuKhaoSatRepositoryList = super.service.filterWithSort(queries, sort);
            return JsonResult.success(phieuKhaoSatRepositoryList);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
