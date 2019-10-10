package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.ExtraAccountHolderAuthorisationData;
import c16.mpb.bankingapp.model.dao.ExtraAccountHolderAuthorisationDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddAccountHolderController {

    @Autowired
    ExtraAccountHolderAuthorisationDataDao extraAccountHolderAuthorisationDataDao;

    @PostMapping(value = "verificationcode_saved")
    public String addAccountHolderFormHandler(@ModelAttribute ExtraAccountHolderAuthorisationData data, Model model) {
        extraAccountHolderAuthorisationDataDao.save(data);
        return "add_account_holder_code_succes";
    }
}


