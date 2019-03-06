package com.brs.idm.service;

import com.brs.idm.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public List<String> getUserIds(String roleName) {
        return userDao.selectUserListByRoleName(roleName);
    }

    @Override
    public List <String> getUserIdsByRoleId(String roleId) {
        return userDao.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean exists(String userId) {
        return userDao.exists(userId)>0?true:false;
    }
}
