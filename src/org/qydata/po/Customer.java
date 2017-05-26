package org.qydata.po;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/3/8.
 */
public class Customer implements Serializable {

    private Integer customerId;
    private String companyName;
    private List<CustomerApiTypeConsume> customerApiTypeConsumeList;
    private List<CustomerApiTypeConsumeDetail> customerApiTypeConsumeDetailList;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CustomerApiTypeConsume> getCustomerApiTypeConsumeList() {
        return customerApiTypeConsumeList;
    }

    public void setCustomerApiTypeConsumeList(List<CustomerApiTypeConsume> customerApiTypeConsumeList) {
        this.customerApiTypeConsumeList = customerApiTypeConsumeList;
    }

    public List<CustomerApiTypeConsumeDetail> getCustomerApiTypeConsumeDetailList() {
        return customerApiTypeConsumeDetailList;
    }

    public void setCustomerApiTypeConsumeDetailList(List<CustomerApiTypeConsumeDetail> customerApiTypeConsumeDetailList) {
        this.customerApiTypeConsumeDetailList = customerApiTypeConsumeDetailList;
    }
}
