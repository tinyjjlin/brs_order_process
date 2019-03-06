package com.brs.idm.api.dto;

import com.brs.idm.api.User;
import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/28
 */
@Data
public class UserDTO implements User {
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String pictureId;
    private String userType;
}
