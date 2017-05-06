package com.lynn.cat.mgmt.dal.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.lynn.cat.mgmt.dal.po.LoginUserPo;

public interface LoginUserMgmtMapper {

    LoginUserPo queryUserByName(@Param("loginName") String loginName);

}
