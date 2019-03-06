package com.brs.idm.persistence.dao;

import com.brs.idm.api.Role;
import com.brs.idm.api.dto.RoleDTO;
import com.brs.idm.api.dto.RolePrivilegeDTO;
import com.brs.idm.api.model.PrivItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
public interface RoleDao {

    @Select("select ID_ as roleId,NAME_ as roleName from act_id_group")
    List<RoleDTO> selectRoleAll();

    /**
     * add new role
     * @param roleEntity
     */
    @Insert("insert into act_id_group(ID_,NAME_,TYPE_)values(#{role.roleId},#{role.roleName},#{role.roleType})")
    void insertRole(@Param("role") Role roleEntity);

    @Delete("delete from act_id_group where ID_ = #{roleId}")
    void deleteRole(@Param("roleId") String roleId);

    @Update("update act_id_group set NAME_ = #{role.roleName} where ID_ = #{role.roleId}")
    void updateRole(@Param("role") Role role);


    @Insert({
            "<script>",
            "insert into act_id_membership(USER_ID_,GROUP_ID_)values",
            "<foreach collection='userIds' item='userId' index='index' separator=','>",
            "(#{userId},#{roleId})",
            "</foreach>",
            "</script>"
    })
    void bindUsers(@Param("roleId") String roleId, @Param("userIds") List <String> userIds);


    @Insert({
            "<script>",
            "insert into act_id_priv_mapping(ID_,GROUP_ID_,PRIV_ID_)values",
            "<foreach collection='privilegeIds' item='item' index='index' separator=','>",
            "(#{item.id},#{roleId},#{item.privId})",
            "</foreach>",
            "</script>"
    })
    void bindRolePrivilege(@Param("roleId") String roleId,@Param("privilegeIds") List<PrivItem>privilegeIds);

    @Delete("delete from act_id_priv_mapping where GROUP_ID_ = #{roleId}")
    void unbindRoleAllPrivilege(@Param("roleId") String roleId);


    List<RolePrivilegeDTO> selectRolePrivilegeList(@Param("roleId") String roleId);
}
