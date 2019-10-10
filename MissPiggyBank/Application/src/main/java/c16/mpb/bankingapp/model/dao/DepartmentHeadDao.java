package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.DepartmentHead;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentHeadDao extends CrudRepository<DepartmentHead,Integer> {
    public DepartmentHead findByUsername(String username);

    public DepartmentHead findById(int id);
}
