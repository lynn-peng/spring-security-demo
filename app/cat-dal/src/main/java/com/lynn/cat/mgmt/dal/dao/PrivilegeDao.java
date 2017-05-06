package com.lynn.cat.mgmt.dal.dao;

import java.util.List;

public interface PrivilegeDao {

    List<String> queryUserPrivileges(String loginName);

}
