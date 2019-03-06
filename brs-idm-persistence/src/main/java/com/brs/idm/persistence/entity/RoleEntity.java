package com.brs.idm.persistence.entity;

import com.brs.idm.api.Role;
import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
@Data
public class RoleEntity implements Role {
    private String roleId;
    private String roleName;
    private String roleType;
}
