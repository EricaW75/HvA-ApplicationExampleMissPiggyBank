package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.Customer;

import org.springframework.data.repository.CrudRepository;
    

public interface CustomerDao extends CrudRepository<Customer, Integer> {
    public Customer findByUsername(String username);
}
