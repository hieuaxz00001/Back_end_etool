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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/private-edit/phuong-an-quy-hoach")
public class PhuongAnQuyHoachCommandController extends BaseCommand<PhuongAnQuyHoach> {
    @Autowired
    private PhuongAnQuyHoachRepository phuongAnQuyHoachRepository;


    public PhuongAnQuyHoachCommandController(BaseService<PhuongAnQuyHoach> service) {
        super(service);
    }

    @PostMapping("save-new")
    public ResponseEntity<JsonResult> saveNew(@RequestBody PhuongAnQuyHoach phuongAnQuyHoach) {
        try {
            return JsonResult.success(phuongAnQuyHoachRepository.save(phuongAnQuyHoach));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("update")
    public ResponseEntity<JsonResult> update(@RequestBody PhuongAnQuyHoach phuongAnQuyHoach) {
        try {
            return JsonResult.success(phuongAnQuyHoachRepository.save(phuongAnQuyHoach),"Update thành công");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
    @PostMapping("delete/{idPhuongAnQuyHoach}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("idPhuongAnQuyHoach") Integer idPhuongAnQuyHoach) {
        try {
            PhuongAnQuyHoach phuongAnQuyHoach = phuongAnQuyHoachRepository.findById(idPhuongAnQuyHoach).get();
            phuongAnQuyHoach.setDeleted(true);
            return JsonResult.success(phuongAnQuyHoachRepository.save(phuongAnQuyHoach),"Đã xóa");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
