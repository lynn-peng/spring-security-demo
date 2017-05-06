package com.lynn.cat.mgmt.dal.dao.mybatis;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.lynn.cat.mgmt.dal.DALException;
import com.lynn.cat.mgmt.dal.dao.PrivilegeDao;
import com.lynn.cat.mgmt.dal.dao.mybatis.mapper.PrivilegeMapper;
import com.lynn.cat.mgmt.dal.po.PrivilegePo;

public class PrivilegeDaoImpl implements PrivilegeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeDaoImpl.class);

    @Autowired
    private PrivilegeMapper mapper;

    @Override
    public List<String> queryUserPrivileges(String loginName) {
        List<PrivilegePo> plist;
        try {
            plist = mapper.queryUserPrivileges(loginName);
        } catch (DataAccessException ex) {
            LOGGER.error("Error querying privilege of user {}", loginName, ex);
            throw new DALException(
                    String.format("Error querying privileges of user %s", loginName));
        }
        return plist.stream().map(PrivilegePo::getPrivilege).collect(Collectors.toList());
    }

}
