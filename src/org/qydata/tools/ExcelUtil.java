package org.qydata.tools;

import org.apache.poi.hssf.usermodel.*;
import org.qydata.po.CustomerApiTypeConsume;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
public class ExcelUtil {

    public static void createExcel(List<CustomerApiTypeConsume> list) {
        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet("消费账单");

        //第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中


        //第四步创建单元格
        HSSFCell cell = row.createCell(0);         //第一个单元格
        cell.setCellValue("日期");                  //设定值
        cell.setCellStyle(style);                   //内容居中

        cell = row.createCell(1);                   //第二个单元格
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);

        cell = row.createCell(2);                   //第三个单元格
        cell.setCellValue("消费金额（单位：元）");
        cell.setCellStyle(style);

        cell = row.createCell(3);                   //第四个单元格
        cell.setCellValue("扣费次数");
        cell.setCellStyle(style);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //第五步插入数据
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                CustomerApiTypeConsume customerApiTypeConsume = list.get(i);
                //创建行
                row = sheet.createRow(i+1);
                //创建单元格并且添加数据
                row.createCell(0).setCellValue(sdf.format(customerApiTypeConsume.getConsuTime()));
                row.createCell(1).setCellValue(customerApiTypeConsume.getApiTypeName());
                if (customerApiTypeConsume.getSumAmount() == null){
                    row.createCell(2).setCellValue(0);
                }else {
                    row.createCell(2).setCellValue(customerApiTypeConsume.getSumAmount());
                }

                row.createCell(3).setCellValue(customerApiTypeConsume.getCountSuccess());
            }
            //第六步将生成excel文件保存到指定路径下
            try {
                FileOutputStream fout = new FileOutputStream("D:\\errorCondition.xls");
                wb.write(fout);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
