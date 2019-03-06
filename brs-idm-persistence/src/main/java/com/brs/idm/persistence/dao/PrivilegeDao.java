package com.brs.idm.persistence.dao;

import com.brs.idm.api.Privilege;
import com.brs.idm.api.dto.PrivilegeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
public interface PrivilegeDao {

    @Select("select ID_ as privId, NAME_ as privName from act_id_priv")
    List<PrivilegeDTO> selectPrivilegeAll();

    @Select("SELECT act_id_priv.ID_ as privId,act_id_priv.NAME_ as privName FROM act_id_priv\n" +
            "LEFT JOIN\n" +
            "act_id_priv_mapping as bpm\n" +
            "on  bpm.PRIV_ID_ = act_id_priv.ID_\n" +
            "LEFT JOIN\n" +
            "act_id_group as bir\n" +
            "on bpm.GROUP_ID_ = bir.ID_\n" +
            "LEFT JOIN\n" +
            "act_id_membership as bim\n" +
            "on bir.ID_ = bim.GROUP_ID_\n" +
            "where bim.USER_ID_ = #{userId}")
    List<PrivilegeDTO> selectPrivilegeByUserId(@Param("userId") String userId);
    /**
     * add new privilege
     * @param privilege
     */
    @Insert("insert into act_id_priv(ID_,NAME_)values(#{privilege.privId},#{privilege.privName})")
    void insertPrivilege(@Param("privilege") Privilege privilege);

    @Delete("delete from act_id_priv where ID_ =#{privId}")
    void deletePrivilege(@Param("privId") String privId);

    @Update("update act_id_priv set NAME_ = #{privilege.privName} where ID_ =#{privilege.privId}")
    void updatePrivilege(@Param("privilege") Privilege privilege);

    @Insert({
            "<script>",
            "insert into act_id_priv_mapping(ID_,PRIV_ID_,GROUP_ID_)values",
            "<foreach collection='roleIds' item='roleId' index='index' separator=','>",
            "(#{id},#{privId},#{roleId})",
            "</foreach>",
            "</script>"
    })
    void bindRoles(@Param("id")String id,@Param("privId") String privId, @Param("roleIds") List <String> roleIds);


}
