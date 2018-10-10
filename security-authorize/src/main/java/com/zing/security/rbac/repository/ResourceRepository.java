package com.zing.security.rbac.repository;

import com.zing.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends SimpleRepository<Resource> {

    Resource findByName(String name);

}
