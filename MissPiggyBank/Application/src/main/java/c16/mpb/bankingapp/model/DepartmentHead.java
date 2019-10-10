
package c16.mpb.bankingapp.model;
//Created by Erica

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn (name = "departmentHeadId" )
public class DepartmentHead extends Employee {
    private String department;

    public DepartmentHead() {
        super();
    }

    public DepartmentHead(String username, String password, int id, String role, String emailaddress) {
        super(username, password, id, role, emailaddress);
    }

    public DepartmentHead(String username, String password, int id, String role, String emailaddress, String department) {
        super(username, password, id, role, emailaddress);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
