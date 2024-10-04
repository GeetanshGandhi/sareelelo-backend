package com.vastrika.backend.DeliveryEmployee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryEmployee {
     @Id
    private String mailID;
    private String EmployeeName;

    public DeliveryEmployee(){}

    public String getmailID() {
        return mailID;
    }

    public void setmailID(String mailID) {
        this.mailID = mailID;
    }
    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName){
        this.EmployeeName = EmployeeName;
    }
}
