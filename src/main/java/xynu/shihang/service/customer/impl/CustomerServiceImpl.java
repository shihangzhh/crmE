package xynu.shihang.service.customer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Customer;
import xynu.shihang.entity.CustomerExample;
import xynu.shihang.mapper.CustomerMapper;
import xynu.shihang.service.customer.CustomerService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired    private CustomerMapper customerMapper;

    /**
     *
     * @param customer
     * @return
     */
    @Override
    public OAResult addCustomer(Customer customer) {
        Date addTime = new Date();

        customer.setAddtime(addTime);
       int row=  customerMapper.insert(customer);

       if (row == 1){
           return OAResult.ok(200,"添加成功");
       }

        return OAResult.build(400,"添加失败");
    }

    /**
     * ��ѯ���еĿͻ�
     * @return
     */

    @Override
    public List<Customer> findAllCustomers() {
        CustomerExample example = new CustomerExample();
        List<Customer> customerList = customerMapper.selectByExample(example);
        return customerList;
    }

    /**
     * ���ݿͻ�id��ѯ�ͻ���ϸ��Ϣ
     * @param id
     * @return
     */

    @Override
    public Customer selectCustomerById(int id) {
       CustomerExample example = new CustomerExample();


        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public OAResult modityCustomerInfor(Customer customer) {

        int row = customerMapper.updateByPrimaryKey(customer);
        if (row == 1){
        return     OAResult.ok(200,"修改数据成功");
        }
        return OAResult.ok(400,"修改数据失败");
    }

    @Override
    public PageView<Customer> findACustomersList(int currentPage, int pageSize) {
        //创建pageView对象并设置当前页和当前页显示多少条数据
        PageView<Customer> pageView = new PageView<Customer>(currentPage,pageSize);

        //开始分页
        PageHelper.startPage(currentPage, pageSize);
        CustomerExample customerExample = new CustomerExample();
        List<Customer> customerList = customerMapper.selectByExample(customerExample);
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(customerList);
        pageView.setTotalRecoreds( pageInfo.getTotal());
        ;pageView.setDataList(pageInfo.getList());//保存查询出来的数据


        return pageView;
    }

    @Override
    public List<Customer> getSearchCustomer(int searchContextType, String searchContext, int orderby) {
        CustomerExample customerExample =  new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();

        //当搜索类型为空时，默认搜索公司名称    当搜索类型为1时，即按照公司名称进行搜索
        if (searchContextType == 0 ||searchContextType == 1){

            //当搜索类型不为空时，按照公司名称进行搜索
            if (StringUtil.isNotEmpty(searchContext)){

                criteria.andComnameLike("%"+searchContext+"%");
            }
        }else if (searchContextType == 2){
            //按照姓名进行搜索
            if (StringUtil.isNotEmpty(searchContext)){

                criteria.andCompanypersonLike("%"+searchContext+"%");
            }


        }

        //搜索内容的排列顺序设置
        //按照id的降序顺序进行排列
        if (orderby == 0){
            customerExample.setOrderByClause("id DESC");
        }else if(orderby == 1){ //按照添加的时候进行排序
            customerExample.setOrderByClause("addtime ASC");
        }

        return customerMapper.selectByExample(customerExample);
    }


}
