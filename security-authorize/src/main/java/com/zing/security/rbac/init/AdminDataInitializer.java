package com.zing.security.rbac.init;

import com.zing.security.rbac.domain.*;
import com.zing.security.rbac.repository.AdminRepository;
import com.zing.security.rbac.repository.ResourceRepository;
import com.zing.security.rbac.repository.RoleAdminRepository;
import com.zing.security.rbac.repository.RoleRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 默认的系统数据初始化器，永远在其他数据初始化器之前执行
 */
@Component
public class AdminDataInitializer extends AbstractDataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleAdminRepository roleAdminRepository;

    @Autowired
    protected ResourceRepository resourceRepository;

    @Override
    public Integer getIndex() {
        return Integer.MIN_VALUE;
    }

    @Override
    protected void doInit() throws Exception {
        initResource();
        Role role = initRole();
        initAdmin(role);
    }

    @Override
    protected boolean isNeedInit() {
        return adminRepository.count() == 0;
    }

    /**
     * 初始化用户数据
     *
     * @param role
     */
    private void initAdmin(Role role) {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        adminRepository.save(admin);

        RoleAdmin roleAdmin = new RoleAdmin();
        roleAdmin.setRole(role);
        roleAdmin.setAdmin(admin);
        roleAdminRepository.save(roleAdmin);
    }

    /**
     * 初始化角色数据
     */
    private Role initRole() {
        Role role = new Role();
        role.setName("超级管理员");
        roleRepository.save(role);
        return role;
    }

    /**
     * 初始化菜单数据
     */
    protected void initResource() {
        Resource root = createRoot("根节点");

        createResource("首页", "", "home", root);

        Resource menu = createResource("平台管理", "", "desktop", root);

//		createResource("资源管理", "resource", "", menu);
        createResource("角色管理", "role", "", menu);
        createResource("管理员管理", "admin", "", menu);
    }

    protected Resource createRoot(String name) {
        Resource node = new Resource();
        node.setName(name);
        resourceRepository.save(node);
        return node;
    }

    protected Resource createResource(String name, Resource parent) {
        return createResource(name, null, null, parent);
    }

    protected Resource createResource(String name, String link, String iconName, Resource parent) {
        Resource node = new Resource();
        node.setName(name);
        node.setIcon(iconName);
        node.setParent(parent);
        node.setType(ResourceType.MENU);
        if (StringUtils.isNotBlank(link)) {
            node.setLink(link + "Manage");
            Set<String> urls = new HashSet<>();
            urls.add(link + "Manage");
            urls.add("/" + link + "/**");
            node.setUrls(urls);
        }
        resourceRepository.save(node);
        return node;
    }

}
