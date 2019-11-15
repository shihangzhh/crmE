package xynu.shihang.action.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Role;
import xynu.shihang.service.manager.RoleService;
import xynu.shihang.utils.OAResult;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleAction {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/{page}")
    public String forwardManagerResourcePage(@PathVariable(value = "page") String page,
                                             @RequestParam(value = "roleid",defaultValue = "0") int roleid,Model model){
model.addAttribute("roleid",roleid);

        return "manager/"+page;
    }
     @ResponseBody
    @RequestMapping(value = "/addRole")
     public OAResult addRole(Role role,String sourcesid){

        return  roleService.addRole(role,sourcesid);
     }

  @ResponseBody
     @RequestMapping(value = "/getAllRole")
      public List<Role> getAllRole(){

        return  roleService.getAllRole();
     }


     @ResponseBody
     @RequestMapping(value = "/showOneRole")
     public Map showOneRoleByRoleid(int roleid){
         Map map = roleService.showOneRoleByRoleid(roleid);
         return  map;
     }

     @ResponseBody
     @RequestMapping(value = "/updateRole")
     public OAResult updateRole(Role role,String sourcesid){


         OAResult oaResult = roleService.updateRole(role, sourcesid);
         return  oaResult;
     }

    /**
     * 删除角色
     *
     * @param roleid
     * @return
     */

      @ResponseBody
     @RequestMapping(value = "/deleteRoleById")
     public OAResult deleteRoleById(int roleid){

        return roleService.deleteRoleById(roleid);
     }

     @ResponseBody
@RequestMapping(value = "/getAllRoleByStatus")
    public List<Role> getAllRoleByStatus(int status){
        List<Role> roleList = roleService.getAllRoleByStatus(status);
        return roleList;
    }

}
