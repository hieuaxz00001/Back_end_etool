package com.rdsic.back_end_e_tool.controller.finder;

import com.rdsic.back_end_e_tool.controller.BaseFinder;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhuongAnHasUser;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.repository.PhuongAnHasUserRepository;
import com.rdsic.back_end_e_tool.repository.PhuongAnQuyHoachRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/private-get/phuong-an-quy-hoach")
public class PhuongAnQuyHoachFinderController extends BaseFinder<PhuongAnQuyHoach> {
    public PhuongAnQuyHoachFinderController(BaseService<PhuongAnQuyHoach> service) {
        super(service);
    }

    @Autowired
    private PhuongAnHasUserRepository phuongAnHasUserRepository;
    @Autowired
    private PhuongAnQuyHoachRepository phuongAnQuyHoachRepository;

    @GetMapping("get-list-phuong-an")
    public ResponseEntity<JsonResult> getList(@RequestParam(value = "queries", required = false) List<String> queries,
                                              @RequestParam(value = "sort", required = false) String sort) {
        try {
            List<PhuongAnQuyHoach> phuongAnQuyHoachList = super.service.filterWithSort(queries, sort);
            return JsonResult.success(phuongAnQuyHoachList);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

//    @GetMapping("get-list-phuong-an-quy-hoach")
//    public ResponseEntity<JsonResult> ghetPhuongAnQuyHoachByUserId(@RequestParam(value = "queries", required = false) List<String> queries,
//                                                                   @RequestParam(value = "sort", required = false) String sort) {
//        try {
//            List<PhuongAnHasUser> phuongAnHasUserList = phuongAnHasUserRepository.findByUserId_Id(idUser);
//            List<PhuongAnQuyHoach> phuongAnQuyHoachList = new ArrayList<>();
//            phuongAnHasUserList.parallelStream().forEach(item -> {
//                phuongAnQuyHoachList.add(item.getPhuongAnQuyHoachId());
//            });
//            List<PhuongAnQuyHoach> phuongAnQuyHoachList = super.service.filterWithSort(queries, sort);
//            return JsonResult.success(phuongAnQuyHoachList);
//        } catch (Exception e) {
//            return JsonResult.error(e);
//        }
//    }
}
