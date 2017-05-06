package com.lynn.catmgmt.security;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lynn.cat.mgmt.dal.DALException;
import com.lynn.cat.mgmt.dal.dao.LoginUserMgmtDao;
import com.lynn.cat.mgmt.dal.dao.PrivilegeDao;
import com.lynn.cat.mgmt.dal.po.LoginUserPo;
import com.lynn.catmgmt.exception.RestException;
import com.lynn.catmgmt.exception.UserNotFoundException;

public class LoginUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserDetailsService.class);

    @Autowired
    private LoginUserMgmtDao loginUserMgmtDao;

    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserPo po;
        try {
            po = loginUserMgmtDao.queryUser(username);
        } catch (DALException ex) {
            LOGGER.error("Error loading user by username {}", username);
            throw new RestException(String.format("Error querying login user %s", username));
        }
        if (po == null) {
            LOGGER.error("User {} not found", username);
            throw new UserNotFoundException(String.format("User %s not found.", username));
        }

        List<String> privileges;
        try {
            privileges = privilegeDao.queryUserPrivileges(username);
        } catch (DALException ex) {
            LOGGER.error("Error loading auths for user {}", username);
            throw new RestException(String.format("Error loading auths for user %s", username));
        }

        User user = new User(po.getLoginName(), po.getPassword(), privileges.stream()
                .map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet()));

        return user;
    }

}
