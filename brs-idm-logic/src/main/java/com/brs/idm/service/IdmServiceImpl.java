package com.brs.idm.service;

import com.brs.idm.api.*;
import com.brs.idm.api.dto.*;
import com.brs.idm.api.model.PrivItem;
import com.brs.idm.persistence.dao.PrivilegeDao;
import com.brs.idm.persistence.dao.RoleDao;
import com.brs.idm.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/22
 */
@Service
public class IdmServiceImpl implements IdmService {
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private RoleDao roleDao;

    @Override
    public void bindUserRole(String userId, List <String> roleIds) {
        unbindUserAllRole(userId);
        userDao.bindUserRole(userId,roleIds );
    }

    @Override
    public List <UserRoleDTO> getUserRoleList(String userId) {
        return userDao.selectUserRoleList(userId);
    }

    @Override
    public List <RolePrivilegeDTO> getRolePrivilegeList(String roleId) {
        return roleDao.selectRolePrivilegeList(roleId);
    }

    @Override
    public void unbindUserAllRole(String userId) {
        userDao.unbindUserAllRole(userId);
    }



    @Override
    public void bindRolePrivilege(String roleId, List <String> privilegeIds) {
        unbindRoleAllPrivilege(roleId);
        roleDao.bindRolePrivilege(roleId, getPrivIdListWrapper(privilegeIds));
    }


    protected  List<PrivItem> getPrivIdListWrapper(List <String> privilegeIds){
        List<PrivItem> privItemList = new ArrayList<PrivItem>() ;
        for (int i = 0; i < privilegeIds.size(); i++) {
            String id = GenerateId.generate();
            PrivItem privItem = new PrivItem(id,privilegeIds.get(i));
            privItemList.add(privItem);
        }
        return privItemList;
    }
    @Override
    public void unbindRoleAllPrivilege(String roleId) {
            roleDao.unbindRoleAllPrivilege(roleId);

    }

    @Autowired(required = false)
    private PrivilegeDao privilegeDao;

    @Override
    public void saveUser(User user) {
        userDao.insertUser( user);
    }

    @Override
    public void saveRole(Role role) {
        roleDao.insertRole( role);
    }

    @Override
    public void savePrivilege(Privilege privilege) {
        privilegeDao.insertPrivilege( privilege);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void updatePrivilege(Privilege privilege) {
        privilegeDao.updatePrivilege(privilege);
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void deleteRole(String roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void deletePrivilege(String privilegeId) {
        privilegeDao.deletePrivilege(privilegeId);
    }

    @Override
    public void addUserRoleMapping(String userId, String roleId) {

    }

    @Override
    public void deleteUserRoleMapping(String userId, String roleId) {

    }

    @Override
    public void addUserPrivilegeMapping(String userId, String privilegeId) {

    }

    @Override
    public void deleteUserPrivilegeMapping(String userId, String privilegeId) {

    }

    @Override
    public void addRolePrivilegeMapping(String roleId, String privilegeId) {

    }

    @Override
    public void deleteRolePrivilegeMapping(String roleId, String privilegeId) {

    }

    @Override
    public List<User> getUserWithRole(String roleId) {
        return userDao.selectUserWithRoleList(roleId);
    }

    @Override
    public void addUserRoleMapping(List <String> userIds, String roleId) {
        roleDao.bindUsers(roleId,userIds );
    }

    @Override
    public void addUserPrivilegeMapping(List <String> userIds, String privilegeId) {

    }

    @Override
    public List <LoginUserRepresentation> getUserAll() {
        return userDao.selectUserAll();
    }

    @Override
    public List <RoleDTO> getRoleAll() {
        return roleDao.selectRoleAll();
    }

    @Override
    public List <PrivilegeDTO> getPrivilegeByUserId(String userId) {
        return privilegeDao.selectPrivilegeByUserId(userId);
    }

    @Override
    public List <RoleDTO> getUserBindRole(String userId) {
        return userDao.selectUserBindRole(userId);
    }

    @Override
    public List <PrivilegeDTO> getPrivilegeAll() {
        return privilegeDao.selectPrivilegeAll();
    }


    @Override
    public void addRolePrivilegeMapping(List <String> roleIds, String privilegeId) {
        String randomId = GenerateId.generate();
        privilegeDao.bindRoles(randomId,privilegeId,roleIds );
    }

    @Override
    public List <User> getUserWithPrivilege(String privilegeId) {
        return null;
    }

    @Override
    public List <Role> getRoleWithPrivilege(String privilegeId) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public Role getRole(String roleId) {
        return null;
    }

    @Override
    public Privilege getPrivilege(String privilegeId) {
        return null;
    }

    @Override
    public UserInfoDTO getLoginUserInfo(String userId) {
        return userDao.selectUserInfo(userId );
    }
}
