package com.lynn.userMgmt.facade.service;

import com.lynn.userMgmt.facade.model.AddUserParam;
import com.lynn.userMgmt.facade.model.UserDto;

public interface UserMgmtService {

    UserDto queryUserByName(String name);

    void addUser(AddUserParam user);

}
