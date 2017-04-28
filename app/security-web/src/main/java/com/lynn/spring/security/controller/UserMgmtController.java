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

import com.lynn.userMgmt.facade.model.AddCatParam;
import com.lynn.userMgmt.facade.model.CatDto;
import com.lynn.userMgmt.facade.service.CatMgmtService;

@Controller
@RequestMapping
public class UserMgmtController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMgmtController.class);

    @Autowired
    private CatMgmtService catMgmtService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/users/{name}", method = RequestMethod.GET)
    public CatDto queryUser(@PathVariable String name) {
        LOGGER.info("Querying user {}.", name);

        return catMgmtService.queryCatByName(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/users/{name}", method = RequestMethod.POST)
    public void addUser(@RequestBody AddCatParam param) {
        LOGGER.info("Add user {}.", param.getFormalName());

        catMgmtService.addCat(param);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
