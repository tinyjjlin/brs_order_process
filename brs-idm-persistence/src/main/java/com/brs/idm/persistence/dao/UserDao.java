package com.brs.idm.persistence.dao;

import com.brs.idm.api.User;
import com.brs.idm.api.dto.RoleDTO;
import com.brs.idm.api.dto.UserInfoDTO;
import com.brs.idm.api.dto.LoginUserRepresentation;
import com.brs.idm.api.dto.UserRoleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author tiny lin
 * @date 2019/2/20
 */
public interface UserDao {
    /**
     * add new user
     * @param user
     */
     void insertUser(@Param("user") User user);

    /**
     * loing user info
     * @param userId
     * @return
     */
     UserInfoDTO selectUserInfo(@Param("userId") String userId);


     List<UserRoleDTO> selectUserRoleList(@Param("userId") String userId);

    /**
     * get user list by role id
     * @param roleId
     * @return
     */
     List<User> selectUserWithRoleList(@Param("roleId")String roleId);

    /**
     * where or not exists user
     * @param userId
     * @return
     */
     int exists(@Param("userId") String userId);

    /**
     * idm all user
     * @return
     */
     List<LoginUserRepresentation> selectUserAll();


     List<String>selectUserListByRoleName(@Param("roleName")String roleName);
    List<String>selectUserListByRoleId(@Param("roleId")String roleId);

    /**
     * user bind role list
     * @param userId
     * @return
     */
     List<RoleDTO> selectUserBindRole(@Param("userId") String userId);

    /**
     * 绑定角色
     * @param userId
     * @param roleIdList
     * @return
     */
    int bindUserRole(@Param("userId")String userId, @Param("roleIdList") List<String> roleIdList);

    /**
     * 删除用户对应的所以角色
     * @param userId
     * @return
     */
    int unbindUserAllRole(@Param("userId")String userId);

}
