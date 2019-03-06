package com.brs.orderprcoess.api;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/5
 */
public abstract  class AbstractCandidateUserHandler implements TaskListener {

    public abstract  List<String> getUserList();
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.addCandidateUsers(getUserList());
    }
}
