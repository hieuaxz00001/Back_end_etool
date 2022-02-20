package com.rdsic.back_end_e_tool.controller.finder;

import com.rdsic.back_end_e_tool.controller.BaseFinder;
import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.User;
import com.rdsic.back_end_e_tool.repository.UserRepository;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/private-get/user")
public class UserFinderController extends BaseFinder<User> {
    @Autowired
    private UserRepository userRepository;
    public UserFinderController(BaseService<User> service) {
        super(service);
    }


}
