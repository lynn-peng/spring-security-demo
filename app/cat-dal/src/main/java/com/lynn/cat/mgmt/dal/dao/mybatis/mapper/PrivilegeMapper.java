package com.lynn.cat.mgmt.dal.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lynn.cat.mgmt.dal.po.PrivilegePo;

public interface PrivilegeMapper {

    List<PrivilegePo> queryUserPrivileges(@Param("loginName") String loginName);

}
