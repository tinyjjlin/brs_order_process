package com.brs.orderprocess.service;

import com.brs.orderprcoess.api.AbstractCandidateUserHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 投稿人候选用户组
 * @author tiny lin
 * @date 2019/3/5
 */
public class SubmitterCandidateUser extends AbstractCandidateUserHandler {
    @Override
    public List <String> getUserList() {
        List <String> users = new ArrayList <>();
        users.add("tom");
        users.add("jack");
        return users;
    }
}