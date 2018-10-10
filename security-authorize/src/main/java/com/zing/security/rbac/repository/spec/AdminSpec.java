package com.zing.security.rbac.repository.spec;

import com.zing.security.rbac.domain.Admin;
import com.zing.security.rbac.dto.AdminCondition;
import com.zing.security.rbac.repository.support.QueryWraper;
import com.zing.security.rbac.repository.support.SimpleSpecification;

public class AdminSpec extends SimpleSpecification<Admin, AdminCondition> {

    public AdminSpec(AdminCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Admin> queryWraper) {
        addLikeCondition(queryWraper, "username");
    }
}
