package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
    public List<User> findByUsername(String username);
}
