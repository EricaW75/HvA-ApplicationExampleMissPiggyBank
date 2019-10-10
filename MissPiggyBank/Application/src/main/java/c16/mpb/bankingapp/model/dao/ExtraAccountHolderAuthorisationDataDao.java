package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.ExtraAccountHolderAuthorisationData;
import org.springframework.data.repository.CrudRepository;

public interface ExtraAccountHolderAuthorisationDataDao extends CrudRepository<ExtraAccountHolderAuthorisationData, Integer> {
    public ExtraAccountHolderAuthorisationData findByUserName (String userName);
}

