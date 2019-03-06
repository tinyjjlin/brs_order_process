package com.brs.orderprocess.service;

import com.brs.orderprcoess.api.AbstractCandidateUserHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑主管候选用户
 * @author tiny lin
 * @date 2019/3/5
 */
public class EditorDirectorCandidateUser extends AbstractCandidateUserHandler {
    @Override
    public List<String> getUserList() {
        List<String>users = new ArrayList<>();
        users.add("tiny");
        users.add("rose");
        return users;
    }
}
