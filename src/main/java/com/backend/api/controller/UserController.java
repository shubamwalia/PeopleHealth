package com.backend.api.controller;

import com.backend.api.helper.miscellaneous.UserConstants;
import com.backend.api.model.service.IuserService;
import com.backend.api.model.sro.UserSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * dependency injection for user service
     */
    @Autowired
    IuserService iuserService;

    /**
     * for activation of user
     * @param userId
     * @return true/false based on successful operation
     */
    @PutMapping("/users/activate/{userId}")
    public Object userEnable(@PathVariable("userId") int userId) {
        return iuserService.userEnable(userId, UserConstants.ENABLE);
    }


    /**
     * for deactivation of user
     * @param userId
     * @return true/false based on successful operation
     */
    @PutMapping("/users/deactivate/{userId}")
    public Object userDisable(@PathVariable("userId") int userId) {
        return iuserService.userEnable(userId, UserConstants.DISABLE);
    }
}
