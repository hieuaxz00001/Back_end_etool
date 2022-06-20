package com.rdsic.back_end_e_tool.controller.command;

import com.rdsic.back_end_e_tool.controller.BaseCommand;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PhuongAnQuyHoach;
import com.rdsic.back_end_e_tool.entities.PhieuKhaoSat;
import com.rdsic.back_end_e_tool.entities.User;
import com.rdsic.back_end_e_tool.repository.PhuongAnHasUserRepository;
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
    private PhuongAnHasUserRepository phuongAnHasUserRepository;
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

    @PostMapping("update/{idUser}/{idPhuongAnQuyHoach}")
    public ResponseEntity<JsonResult> update(@PathVariable("idUser") Integer idUser,
                                             @PathVariable("idPhuongAnQuyHoach") Integer idPhuongAnQuyHoach,
                                             @RequestBody PhieuKhaoSat phieuKhaoSat) {
        try {
            User user = userRepository.findById(idUser).get();
            PhuongAnQuyHoach phuongAnQuyHoach = phuongAnQuyHoachRepository.findById(idPhuongAnQuyHoach).get();
            phieuKhaoSat.setUserId(user);
            phieuKhaoSat.setPhuongAnQuyHoachId(phuongAnQuyHoach);
            return JsonResult.success(phieuKhaoSatRepository.save(phieuKhaoSat),"Update thành công");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    //    @PostMapping("change-user-phieu/{idPhieu}/{idUserMoi}")
//    public ResponseEntity<JsonResult> changeUser(@PathVariable("idPhieu") Integer idPhieu,
//                                             @PathVariable("idUserMoi") Integer idUserMoi) {
//        try {
//            PhieuKhaoSat phieuKhaoSat = phieuKhaoSatRepository.findById(idPhieu).get();
//            User user = userRepository.findById(idUserMoi).get();
//            phieuKhaoSat.setUserId(user);
//            return JsonResult.success(phieuKhaoSatRepository.save(phieuKhaoSat));
//        } catch (Exception e) {
//            return JsonResult.error(e);
//        }
//    }
    @PostMapping("delete/{idPhieuKhaoSat}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("idPhieuKhaoSat") Integer idPhieuKhaoSat) {
        try {
            PhieuKhaoSat phieuKhaoSat = phieuKhaoSatRepository.findById(idPhieuKhaoSat).get();
            phieuKhaoSat.setDeleted(true);
            return JsonResult.success(phieuKhaoSatRepository.save(phieuKhaoSat),"Đã xóa");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
