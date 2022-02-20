package com.rdsic.back_end_e_tool.controller.command;

import com.rdsic.back_end_e_tool.controller.BaseCommand;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.entities.PhieuKhaoSat;
import com.rdsic.back_end_e_tool.entities.User;
import com.rdsic.back_end_e_tool.repository.PhuongAnQuyHoachRepository;
import com.rdsic.back_end_e_tool.repository.PhieuKhaoSatRepository;
import com.rdsic.back_end_e_tool.repository.UserRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/private-edit/phieu-khao-sat")
public class PhieuKhaoSatCommandController extends BaseCommand<PhieuKhaoSat> {
    @Autowired
    private PhieuKhaoSatRepository phieuKhaoSatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhuongAnQuyHoachRepository phuongAnQuyHoachRepository;

    public PhieuKhaoSatCommandController(BaseService<PhieuKhaoSat> service) {
        super(service);
    }

    @PostMapping("save-new/{idUser}/{idPhuongAnQuyHoach}")
    public ResponseEntity<JsonResult> saveNew(@PathVariable("idUser") Integer idUser,
                                              @PathVariable("idPhuongAnQuyHoach") Integer idPhuongAnQuyHoach,
                                              @RequestBody PhieuKhaoSat phieuKhaoSat) {
        try {
            User user = userRepository.findById(idUser).get();
            PhuongAnQuyHoach phuongAnQuyHoach = phuongAnQuyHoachRepository.findById(idPhuongAnQuyHoach).get();
            phieuKhaoSat.setUserId(user);
            phieuKhaoSat.setPhuongAnQuyHoachId(phuongAnQuyHoach);
            return JsonResult.success(phieuKhaoSatRepository.save(phieuKhaoSat));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
