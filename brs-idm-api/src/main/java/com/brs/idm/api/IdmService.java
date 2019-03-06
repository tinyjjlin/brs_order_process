package com.brs.idm.api;
import com.brs.idm.api.dto.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
public interface IdmService {
    void saveUser(User user);
    void saveRole(Role role);
    void savePrivilege(Privilege privilege);

    void updateUser(User user);
    void updateRole(Role role);
    void updatePrivilege(Privilege privilege);

    void deleteUser(String userId);
    void deleteRole(String roleId);
    void deletePrivilege(String privilegeId);

    void addUserRoleMapping(String userId, String roleId);
    void addUserRoleMapping(List <String> userIds, String roleId);
    void deleteUserRoleMapping(String userId, String roleId);
    void addUserPrivilegeMapping(List <String> userIds, String privilegeId);
    void addUserPrivilegeMapping(String userId, String privilegeId);
    void deleteUserPrivilegeMapping(String userId, String privilegeId);
    void addRolePrivilegeMapping(List <String> roleIds, String privilegeId);
    void addRolePrivilegeMapping(String roleId, String privilegeId);
    void deleteRolePrivilegeMapping(String roleId, String privilegeId);

    List<User> getUserWithRole(String roleId);
    List<User> getUserWithPrivilege(String privilegeId);
    List<Role> getRoleWithPrivilege(String privilegeId);

    User getUser(String userId);
    Role getRole(String roleId);
    Privilege getPrivilege(String privilegeId);

    /**
     * get login user info
     * @param userId
     * @return
     */
    UserInfoDTO getLoginUserInfo(String userId);
    List<LoginUserRepresentation> getUserAll();
    List<RoleDTO> getRoleAll();
    List<PrivilegeDTO> getPrivilegeAll();

    /**
     * get user  privilege
     * @param userId
     * @return
     */
    List<PrivilegeDTO> getPrivilegeByUserId(String userId);

    /**
     *获取用户对应的角色列表
     * @param userId
     * @return
     */
    List<RoleDTO> getUserBindRole(String userId);

    /**
     * 为用户绑定角色
     * @param userId
     * @param roleIds
     * @return
     */
    void bindUserRole(String userId, List<String> roleIds);

    /**
     * 删除用户绑定的所有角色
     * @param userId
     */
    void unbindUserAllRole(String userId);

    void bindRolePrivilege(String roleId,List<String>privilegeIds);
    void unbindRoleAllPrivilege(String roleId);

    List<UserRoleDTO> getUserRoleList(String userId);
    List<RolePrivilegeDTO> getRolePrivilegeList(String roleId);
  }
