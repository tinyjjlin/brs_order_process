package com.brs.idm.service;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/22
 */
public interface UserService {
    /**
     *
     * @param roleName
     * @return
     */
    List<String> getUserIds(String roleName);

    List<String> getUserIdsByRoleId(String roleId);

    boolean exists(String userId);
}
