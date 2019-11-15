package xynu.shihang.service.customer;

import xynu.shihang.entity.Customer;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import java.util.List;

public interface CustomerService {

      public OAResult addCustomer(Customer customer);
    public List<Customer> findAllCustomers();
    public Customer selectCustomerById(int id);

    public OAResult modityCustomerInfor(Customer customer);

  // public PageView<Customer> findACustomersList(int currentPage,int pageSize);

    /**
     * 查询数据并进行分页展示
     * @param currentPage  当前页
     * @param pageSize   一个页面显示多少数据
     * @return
     */
    public PageView<Customer> findACustomersList(int currentPage,int pageSize);

    List<Customer> getSearchCustomer(int searchContextType, String searchContext, int orderby);
}
