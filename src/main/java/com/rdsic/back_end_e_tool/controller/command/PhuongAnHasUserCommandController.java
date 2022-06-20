package com.rdsic.back_end_e_tool.controller.command;

import com.rdsic.back_end_e_tool.controller.BaseCommand;
import com.rdsic.back_end_e_tool.entities.*;
import com.rdsic.back_end_e_tool.key.PhuongAnHasUserKey;
import com.rdsic.back_end_e_tool.repository.PhuongAnHasUserRepository;
import com.rdsic.back_end_e_tool.repository.PhuongAnQuyHoachRepository;
import com.rdsic.back_end_e_tool.repository.UserRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/private-edit/phuong-an-has-user")
public class PhuongAnHasUserCommandController extends BaseCommand<PhuongAnHasUser> {
    @Autowired
    private PhuongAnHasUserRepository phuongAnHasUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhuongAnQuyHoachRepository phuongAnQuyHoachRepository;

    public PhuongAnHasUserCommandController(BaseService<PhuongAnHasUser> service) {
        super(service);
    }


    @PostMapping("save-new/{idUser}/{idPhuongAnQuyHoach}")
    public ResponseEntity<JsonResult> saveNew(@PathVariable("idUser") Integer idUser,
                                              @PathVariable("idPhuongAnQuyHoach") Integer idPhuongAnQuyHoach) {
        try {
            PhuongAnHasUser phuongAnHasUser = new PhuongAnHasUser();
            PhuongAnHasUserKey phuongAnHasUserKey = new PhuongAnHasUserKey();
            phuongAnHasUserKey.setUserId(idUser);
            phuongAnHasUserKey.setPhuongAnQuyHoachId(idPhuongAnQuyHoach);
            User user = userRepository.findById(idUser).get();
            PhuongAnQuyHoach phuongAnQuyHoach = phuongAnQuyHoachRepository.findById(idPhuongAnQuyHoach).get();
            phuongAnHasUser.setUserId(user);
            phuongAnHasUser.setPhuongAnQuyHoachId(phuongAnQuyHoach);
            phuongAnHasUser.setId(phuongAnHasUserKey);
            return JsonResult.success(phuongAnHasUserRepository.save(phuongAnHasUser));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
    @PostMapping("delete-user/{idUser}/{idPhuongAnQuyHoach}")
    public ResponseEntity<JsonResult> deleteUser(@PathVariable("idUser") Integer idUser,
                                              @PathVariable("idPhuongAnQuyHoach") Integer idPhuongAnQuyHoach) {
        try {
            phuongAnHasUserRepository.deleteUserTrongPhuongAn(idUser,idPhuongAnQuyHoach);
            return JsonResult.success(null,"Đã xóa");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
