package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }
}
