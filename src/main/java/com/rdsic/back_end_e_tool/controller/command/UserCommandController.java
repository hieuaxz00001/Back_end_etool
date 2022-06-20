package com.rdsic.back_end_e_tool.controller.command;

import com.rdsic.back_end_e_tool.controller.BaseCommand;
import com.rdsic.back_end_e_tool.entities.FormLogin;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.User;
import com.rdsic.back_end_e_tool.repository.UserRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/private-edit/user")
public class UserCommandController extends BaseCommand<User> {
    @Autowired
    private UserRepository userRepository;

    public UserCommandController(BaseService<User> service) {
        super(service);
    }

    @PostMapping("login")
    public ResponseEntity<JsonResult> login(@RequestBody FormLogin formLogin) {
        try {
            return JsonResult.success(userRepository.findByUsernameAndPasswordAndDeletedFalse(formLogin.getUsername(), formLogin.getPassword()));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("register")
    public ResponseEntity<JsonResult> login(@RequestBody User user) {
        try {
            return JsonResult.success(userRepository.save(user));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("update")
    public ResponseEntity<JsonResult> update(@RequestBody User user) {
        try {
            return JsonResult.success(userRepository.save(user),"Update thành công");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("delete/{idUser}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("idUser") Integer idUser) {
        try {
            User user = userRepository.findById(idUser).get();
            user.setDeleted(true);
            return JsonResult.success(userRepository.save(user),"Đã xóa");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
