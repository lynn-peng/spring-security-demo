package com.lynn.spring.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lynn.userMgmt.facade.model.AddUserParam;
import com.lynn.userMgmt.facade.model.UserDto;
import com.lynn.userMgmt.facade.service.UserMgmtService;

@Controller
@RequestMapping
public class UserMgmtController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMgmtController.class);

    @Autowired
    private UserMgmtService userMgmtService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/users/{name}", method = RequestMethod.GET)
    public UserDto queryUser(@PathVariable String name) {
        LOGGER.info("Querying user {}.", name);

        return userMgmtService.queryUserByName(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/users/{name}", method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserParam param) {
        LOGGER.info("Add user {}.", param.getLoginName());

        userMgmtService.addUser(param);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
