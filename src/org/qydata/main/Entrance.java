package org.qydata.main;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.qydata.po.Customer;
import org.qydata.po.CustomerConsumeExcel;
import org.qydata.tools.CalendarAssistTool;
import org.qydata.tools.ExcelUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/8.
 */
public class Entrance {


    public static void main(String[] args) {
        String resource = "mybatis.xml";
        InputStream is = Entrance.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        try {

            //增删改操作一定要提交事务
            //删除
            String statementDelete = "org.qydata.mapper.CustomerApiTypeConsumeMapper.deleteCustomerConsumeExcel";
            int flag = session.delete(statementDelete,CalendarAssistTool.getCurrentDateLastMonth());
            session.commit();

            //查询
            String statementSelect = "org.qydata.mapper.CustomerApiTypeConsumeMapper.queryCustomerLastMonthConsume";
            Map<String, Object> mapParam = new HashMap<>();
            mapParam.put("firstDay", CalendarAssistTool.getCurrentDateLastMonthFirstDay());
            mapParam.put("lastDay",CalendarAssistTool.getCurrentDateLastMonthEndDay());
            List<Customer> customerList = session.selectList(statementSelect, mapParam);

            String statementDetailSelect = "org.qydata.mapper.CustomerApiTypeConsumeMapper.queryCustomerLastMonthConsumeDetail";
            Map<String, Object> mapDetailParam = new HashMap<>();
            mapDetailParam.put("firstDay", CalendarAssistTool.getCurrentDateLastMonthFirstDay()+" "+"00:00:00");
            mapDetailParam.put("lastDay",CalendarAssistTool.getCurrentDateLastMonthEndDay()+" "+"23:59:59");
            List<Customer> customerDetailList = session.selectList(statementDetailSelect, mapDetailParam);

            if (customerList != null){
                for (int i=0;i<customerList.size(); i++){
                    Customer customer = customerList.get(i);
                    if (customerDetailList != null){
                        for (int j=0;j<customerDetailList.size(); j++){
                            Customer customerDetail = customerDetailList.get(j);
                            if (customer.getCustomerId() == customerDetail.getCustomerId()){
                                customer.setCustomerApiTypeConsumeDetailList(customerDetail.getCustomerApiTypeConsumeDetailList());
                            }
                        }
                    }
                }
            }


            //添加
            List<CustomerConsumeExcel> customerConsumeExcelList = ExcelUtil.createExcel(customerList);
            if(customerConsumeExcelList.size() > 0) {
                String statementInsert = "org.qydata.mapper.CustomerApiTypeConsumeMapper.insertCustomerConsumeExcel";
                int result = session.insert(statementInsert, customerConsumeExcelList);
                session.commit();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
