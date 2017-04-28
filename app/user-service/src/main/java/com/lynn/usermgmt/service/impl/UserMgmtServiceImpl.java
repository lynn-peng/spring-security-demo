package com.lynn.usermgmt.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lynn.userMgmt.facade.exception.UserAlreadyExistsException;
import com.lynn.userMgmt.facade.exception.UserNotFoundException;
import com.lynn.userMgmt.facade.model.AddUserParam;
import com.lynn.userMgmt.facade.model.UserDto;
import com.lynn.userMgmt.facade.service.UserMgmtService;
import com.lynn.usermgmt.service.model.UserPo;

public class UserMgmtServiceImpl implements UserMgmtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMgmtServiceImpl.class);

    private static final Map<String, UserPo> USERS = new ConcurrentHashMap();

    static {
        populateUser();
    }

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String prop1;

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    private String prop2;

    public UserMgmtServiceImpl() {

    }

    public UserMgmtServiceImpl(String prop1, String prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;

    }

    @Override
    public UserDto queryUserByName(String name) {
        UserPo userPo = USERS.get(name);
        if (userPo == null) {
            LOGGER.error("User {} not found.", name);
            throw new UserNotFoundException(String.format("User %s not found.", name));
        }

        return toUserDto(userPo);
    }

    @Override
    public void addUser(AddUserParam user) {
        UserPo po = toUserPo(user);
        boolean isExist = USERS.containsKey(po.getLoginName());
        if (isExist) {
            LOGGER.error("User {} already exists.", user.getLoginName());
            throw new UserAlreadyExistsException(
                    String.format("User %s already exists.", user.getLoginName()));
        }

        USERS.put(user.getLoginName(), po);
    }

    private UserDto toUserDto(UserPo po) {
        UserDto dto = new UserDto();
        dto.setLoginName(po.getLoginName());
        dto.setDisplayName(po.getDisplayName());
        dto.setAddress(po.getAddress());

        return dto;
    }

    private UserPo toUserPo(AddUserParam param) {
        UserPo po = new UserPo();
        po.setLoginName(param.getLoginName());
        po.setDisplayName(param.getDisplayName());
        po.setAddress(param.getAddress());
        po.setPassword(passwordEncoder.encode(param.getPassword()));

        return po;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static void populateUser() {
        UserPo po = new UserPo();
        po.setLoginName("lynn001");
        po.setPassword("lynn001");
        USERS.put(po.getLoginName(), po);
        po.setLoginName("lynn002");
        po.setPassword("lynn002");
        USERS.put(po.getLoginName(), po);
        po.setLoginName("lynn003");
        po.setPassword("lynn003");
        USERS.put(po.getLoginName(), po);

    }

}
