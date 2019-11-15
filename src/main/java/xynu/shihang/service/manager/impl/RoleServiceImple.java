package xynu.shihang.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.*;
import xynu.shihang.mapper.EmpRoleMapper;
import xynu.shihang.mapper.RoleMapper;
import xynu.shihang.mapper.RoleSourcesMapper;
import xynu.shihang.mapper.SourcesMapper;
import xynu.shihang.service.manager.RoleService;
import xynu.shihang.utils.OAResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImple implements RoleService {

    @Autowired
   private RoleMapper roleMapper;
    @Autowired
    private RoleSourcesMapper roleSourcesMapper;

    @Autowired
    private SourcesMapper sourcesMapper;

    @Autowired
    private EmpRoleMapper empRoleMapper;

    /**
     * 把role插入到数据库之后需要立即取出roleid，以便可以将roleid存储到中间表roleSource中
     * @param role
     * @param sourcesid
     * @return
     */


    @Override
    public OAResult addRole(Role role, String sourcesid) {

        int row = roleMapper.insert(role);

        //接收过来的是一个数组
        String[] sids = sourcesid.split(",");

        int count = 0;

      for(int i= 0;i< sids.length;i++){

          RoleSources roleSources = new RoleSources();
          //设置中间表role_source中的sid
          roleSources.setSid(Integer.parseInt(sids[i]));

          roleSources.setRoleid(role.getRoleid());
          roleSourcesMapper.insert(roleSources);
          count++;

      }
              //如果role和roleSource数据能够成功插入
      if (row ==1 && count==sids.length){
      return  OAResult.ok(200,"插入角色成功");
      }

        return  OAResult.ok(200,"插入角色失败");
    }

    @Override
    public List<Role> getAllRole() {
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);

        return roleList;
    }

    @Override
    public Map showOneRoleByRoleid(int roleid) {


        Role role=roleMapper.selectByPrimaryKey(roleid);
        RoleSourcesExample roleSourcesExample=new RoleSourcesExample();
        RoleSourcesExample.Criteria criteria = roleSourcesExample.createCriteria();
        criteria.andRoleidEqualTo(roleid);
        List<RoleSources> roleSourcesList= roleSourcesMapper.selectByExample(roleSourcesExample);
        List<Sources> sourcesList=new ArrayList<Sources>();
        for(RoleSources roleSources : roleSourcesList){
            sourcesList.add(sourcesMapper.selectByPrimaryKey(roleSources.getSid()));
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("role",role);
        map.put("sourcesId",sourcesList);

        return  map;
    }

    @Override
    public OAResult updateRole(Role role, String sourcesid) {


        //删除中间表
        
        RoleSourcesExample roleSourcesExample = new RoleSourcesExample();
        RoleSourcesExample.Criteria criteria = roleSourcesExample.createCriteria();
       criteria.andRoleidEqualTo(role.getRoleid());
       
       roleSourcesMapper.deleteByExample(roleSourcesExample);

        String[] sourcesids = sourcesid.split(",");

        int count = 0;
            for (String sid:sourcesids){

                RoleSources roleSources = new RoleSources();
                //设置角色id
                roleSources.setRoleid(role.getRoleid());
                //设置资源id
                roleSources.setSid(Integer.parseInt(sid));
                roleSourcesMapper.insert(roleSources);
                count++;
        }

        int row = roleMapper.updateByPrimaryKey(role);

            if (row == 1 && count== sourcesids.length){

                return OAResult.ok(200,"角色更新成功");
            }


     return    OAResult.ok(200,"角色更新失败");
    }

    /**
     * 因为角色表对应资源表以及员工表是多对多的关系  ，所以要
     * 删除角色表的话，还要删除中间表中的数据
     * @param roleid
     * @return
     */

    @Override
    public OAResult deleteRoleById(int roleid) {


        //根据id删除资源角色表
        RoleSourcesExample roleSourcesExample = new RoleSourcesExample();
        RoleSourcesExample.Criteria criteria = roleSourcesExample.createCriteria();
        criteria.andRoleidEqualTo(roleid);

        roleSourcesMapper.deleteByExample(roleSourcesExample);

        //根据id删除角色员工表
            EmpRoleExample empRoleExample = new EmpRoleExample();
        EmpRoleExample.Criteria criteria1 = empRoleExample.createCriteria();
        criteria1.andRoleFkEqualTo(roleid);

        empRoleMapper.deleteByExample(empRoleExample);

        int row = roleMapper.deleteByPrimaryKey(roleid);

        if(row == 1){

            return  OAResult.ok(200,"角色删除成功");
        }


        return  OAResult.ok(400,"角色删除失败");
    }

    @Override
    public List<Role> getAllRoleByStatus(int status){

        RoleExample roleExample=new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andStatusEqualTo(status);
        return  roleMapper.selectByExample(roleExample);
    }


}
