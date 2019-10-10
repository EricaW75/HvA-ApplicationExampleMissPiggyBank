package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.BusinessCustomer;
import org.springframework.data.repository.CrudRepository;

public interface BusinessCustomerDao extends CrudRepository<BusinessCustomer, Integer> {
    public BusinessCustomer findByUsername(String username);



}
