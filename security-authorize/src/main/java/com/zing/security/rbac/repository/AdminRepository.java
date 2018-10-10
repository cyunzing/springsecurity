package com.zing.security.rbac.repository;

import com.zing.security.rbac.domain.Admin;

public interface AdminRepository extends SimpleRepository<Admin> {

    Admin findByUsername(String username);

}
