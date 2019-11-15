package xynu.shihang.action.customer;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xynu.shihang.entity.Customer;
import xynu.shihang.service.customer.CustomerService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerAction {

    @Value("${pageSize}")
    private int  pageSize;
    @Autowired
  private CustomerService customerService;

   //用于页面跳转
    @RequestMapping(value = "/forward/{path}")
    public String  forwardCustomerPage(@PathVariable(value = "path") String path){

        return  "customer/"+path;
    }

    @ResponseBody
@RequestMapping(value = "/addCustomer")
    public OAResult addCustomer(Customer customer){

      return    customerService.addCustomer(customer);
    }

    //根据id查找客户
    @RequestMapping(value = "/findCustomer")
     public  String   findCustomer(int id, Model model){

     Customer customer =   customerService.selectCustomerById(id);
     model.addAttribute("customer",customer);
         return "forward:/WEB-INF/customer/customer-edit.jsp";
    }

  //修改客户信息

    @RequestMapping("/update")
    public String updateCustomer(Customer customer){
     OAResult oaResult =  customerService.modityCustomerInfor(customer);

        return "customer/customer";
    }

    @ResponseBody
    @RequestMapping(value = "/show")
    public  PageView<Customer> getCustomerList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage){

        return customerService.findACustomersList(currentPage,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCustomer")
    public List<Customer> getSearchCustomer(int searchContextType ,String searchContext,int orderby){

        return customerService.getSearchCustomer(searchContextType,searchContext,orderby);
    }


    @ResponseBody
      @RequestMapping(value = "/getAllCustomers")
    public List<Customer>   getAllCustomers(){

    List<Customer>  customerList= customerService.findAllCustomers();

     return customerList;
    }
}
