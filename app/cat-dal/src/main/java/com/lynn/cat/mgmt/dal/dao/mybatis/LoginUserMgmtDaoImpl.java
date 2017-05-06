package com.lynn.cat.mgmt.dal.dao.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.lynn.cat.mgmt.dal.DALException;
import com.lynn.cat.mgmt.dal.dao.LoginUserMgmtDao;
import com.lynn.cat.mgmt.dal.dao.mybatis.mapper.LoginUserMgmtMapper;
import com.lynn.cat.mgmt.dal.po.LoginUserPo;

public class LoginUserMgmtDaoImpl implements LoginUserMgmtDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserMgmtDaoImpl.class);

    @Autowired
    private LoginUserMgmtMapper mapper;

    @Override
    public LoginUserPo queryUser(String loginName) throws DALException {
        try {
            return mapper.queryUserByName(loginName);
        } catch (DataAccessException ex) {
            LOGGER.error("Error querying login user {}", loginName, ex);
            throw new DALException(String.format("Error querying login user {}", loginName));
        }
    }

}
