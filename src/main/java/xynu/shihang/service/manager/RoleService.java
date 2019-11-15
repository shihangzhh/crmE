package xynu.shihang.service.manager;

import xynu.shihang.entity.Role;
import xynu.shihang.utils.OAResult;

import java.util.List;
import java.util.Map;

public interface RoleService {

    public OAResult addRole(Role role, String sourcesid);

    public List<Role> getAllRole();

    public Map showOneRoleByRoleid(int roleid);

    public OAResult updateRole(Role role,String sourcesid);

    public OAResult deleteRoleById(int roleid);
    public List<Role> getAllRoleByStatus(int status);
}
