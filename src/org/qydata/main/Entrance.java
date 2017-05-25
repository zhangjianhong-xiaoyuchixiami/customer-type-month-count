package org.qydata.main;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.qydata.po.CustomerApiTypeConsume;
import org.qydata.tools.CalendarAssistTool;
import org.qydata.tools.ExcelUtil;

import java.io.InputStream;
import java.text.SimpleDateFormat;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //删除
            // Map<String,Object> mapDelete = new HashMap<>();
            //mapDelete.put("years", CalendarAssistTool.getCurrentYear());
            // mapDelete.put("months",CalendarAssistTool.getCurrentMonth());
            // mapDelete.put("days",CalendarAssistTool.getCurrentDay());
            //String statementDelete = "org.qydata.mapper.CustomerApiTypeConsumeMapper.deleteCustomerApiTypeConsume";
            // int flag = session.delete(statementDelete,mapDelete);
            // session.commit();
            //查询
            String statementSelect = "org.qydata.mapper.CustomerApiTypeConsumeMapper.queryCustomerApiTypeConsume";
            Map<String, Object> mapParam = new HashMap<>();
            mapParam.put("firstDay", CalendarAssistTool.getCurrentDateLastMonthFirstDay());
            mapParam.put("lastDay",CalendarAssistTool.getCurrentDateLastMonthEndDay());
            List<CustomerApiTypeConsume> customerApiTypeConsumeList = session.selectList(statementSelect, mapParam);
            ExcelUtil.createExcel(customerApiTypeConsumeList);
            //String statementInsert = "org.qydata.mapper.CustomerApiTypeConsumeMapper.insertCustomerApiTypeConsume";
            // int result = session.insert(statementInsert, customerApiTypeConsumes);
            //增删改操作一定要提交事务
            //session.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
