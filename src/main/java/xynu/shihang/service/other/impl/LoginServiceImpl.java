package xynu.shihang.service.other.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.EmployeeExample;
import xynu.shihang.mapper.EmployeeMapper;
import xynu.shihang.service.other.LoginService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

     @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public OAResult modifyPassword(String username,String password) {

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();

        criteria.andUsernameEqualTo(username);
         List<Employee> employees = employeeMapper.selectByExample(employeeExample);
         if (employees!= null && employees.size() == 1){
             employees.get(0).setPassword(password);
         }

        int row = employeeMapper.updateByPrimaryKey(employees.get(0));
         if (row == 1){
             return  OAResult.build(200,"密码修改成功");
         }
        return OAResult.ok(400,"密码修改失败");
    }
}
