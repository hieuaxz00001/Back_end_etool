package com.rdsic.back_end_e_tool.controller.finder;

import com.rdsic.back_end_e_tool.controller.BaseFinder;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhuongAnHasUser;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/private-get/phuong-an-has-user")
public class PhuongAnHasUserFinderController extends BaseFinder<PhuongAnHasUser> {
    public PhuongAnHasUserFinderController(BaseService<PhuongAnHasUser> service) {
        super(service);
    }

    @GetMapping("get-list-phuong-an")
    public ResponseEntity<JsonResult> findByIdUser(@RequestParam(value = "queries", required = false) List<String> queries,
                                                   @RequestParam(value = "sort", required = false) String sort) {
        try {
            List<PhuongAnHasUser> phuongAnHasUserList = super.service.filterWithSort(queries, sort);
            List<PhuongAnQuyHoach> phuongAnQuyHoachList = new ArrayList<>();
            phuongAnHasUserList.parallelStream().forEach(item -> {
                phuongAnQuyHoachList.add(item.getPhuongAnQuyHoachId());
            });
            return JsonResult.success(phuongAnQuyHoachList);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
