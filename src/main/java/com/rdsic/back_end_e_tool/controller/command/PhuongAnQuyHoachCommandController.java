package com.rdsic.back_end_e_tool.controller.command;

import com.rdsic.back_end_e_tool.controller.BaseCommand;
import com.rdsic.back_end_e_tool.entities.FormLogin;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.repository.PhuongAnQuyHoachRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/private-edit/phuong-an-quy-hoach")
public class PhuongAnQuyHoachCommandController extends BaseCommand<PhuongAnQuyHoach> {
    @Autowired
    private PhuongAnQuyHoachRepository phuongAnQuyHoachRepository;


    public PhuongAnQuyHoachCommandController(BaseService<PhuongAnQuyHoach> service) {
        super(service);
    }
    @PostMapping("save-new")
    public ResponseEntity<JsonResult> login(@RequestBody PhuongAnQuyHoach phuongAnQuyHoach) {
        try {
            return JsonResult.success(phuongAnQuyHoachRepository.save(phuongAnQuyHoach));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
