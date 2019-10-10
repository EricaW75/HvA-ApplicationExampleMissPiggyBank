package c16.mpb.bankingapp.model;

import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn (name = "userID" )
abstract class Employee extends User {

public Employee() {
  super();
  }

  public Employee(String username, String password, int id, String role, String emailaddress) {
    super(username, password, id, role, emailaddress);
  }
}


