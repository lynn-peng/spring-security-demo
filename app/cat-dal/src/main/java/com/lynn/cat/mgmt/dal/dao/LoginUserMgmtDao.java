package com.lynn.cat.mgmt.dal.dao;

import com.lynn.cat.mgmt.dal.DALException;
import com.lynn.cat.mgmt.dal.po.LoginUserPo;

public interface LoginUserMgmtDao {

    /**
     * @param loginName
     * @return Login user info
     * @throws DALException
     */
    LoginUserPo queryUser(String loginName) throws DALException;

}
