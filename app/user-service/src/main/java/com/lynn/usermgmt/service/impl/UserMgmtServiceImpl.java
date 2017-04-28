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
import com.lynn.userMgmt.facade.model.UpdateUserParam;
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

    @Override
    public void deleteUser(String name) {
        USERS.remove(name);
    }

    @Override
    public void updateUser(String name, UpdateUserParam param) {
        if (USERS.containsKey(name)) {
            UserPo po = USERS.get(name);
            if (param.getDisplayName() != null)
                po.setDisplayName(param.getDisplayName());
            if (param.getPassword() != null)
                po.setPassword(param.getPassword());
            if (param.getAddress() != null)
                po.setAddress(param.getAddress());
        } else {
            throw new UserNotFoundException(String.format("%s not found.", name));
        }
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

}
