package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.*;
import c16.mpb.bankingapp.model.dao.BusinessCustomerDao;
import c16.mpb.bankingapp.service.CustomerViewer;
import c16.mpb.bankingapp.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("sessionUser")
public class LoginController {

    @Autowired
    CustomerViewer customerViewer;

    @Autowired 
    LoginService login;

    @PostMapping(value = "mypiggybank") // Nathan - Fix voor postrequest
    public String loginHandler(@ModelAttribute User user, Model model, HttpSession session) {
        User dbUser = login.loginUser(user);



        if (dbUser == null) {
            model.addAttribute("loginError", "error");
            return "login";
        } else if (dbUser.getRole().equals("Employee")) {
            session.setAttribute("sessionUser", dbUser);
            switch (login.getDepartmentHeadPosition(dbUser)) {
                case "Particulieren":
                  ArrayList<BankAccount> topAccounts= customerViewer.getTopBankAccounts(5);
                  model.addAttribute("topAccounts", topAccounts);
                  return "employee/overview_private";
                case "Business":
                  ArrayList<Customer> topCustomers = customerViewer.getTopBusinessAccounts(5);
                  double[] accountBalances = customerViewer.getTotalAccountBalance(topCustomers);
                  model.addAttribute("customerBalances", accountBalances);
                  model.addAttribute("topCustomers", topCustomers);
                
                  ArrayList<Customer> mostActiveCustomers = customerViewer.getMostActiveBusinessCustomers(5);
                  double[] totalBalances = new double[mostActiveCustomers.size()];
                  for (int i = 0; i < mostActiveCustomers.size(); i++) {
                    totalBalances[i] = customerViewer.getTotalBalanceOfCustomer(mostActiveCustomers.get(i));
                  }
                  model.addAttribute("totalBalances", totalBalances);
                  model.addAttribute("topActives", mostActiveCustomers);

                  double[] averages = customerViewer.getAverageBalancePerSector();
                  model.addAttribute("averages", averages);
                  ArrayList<Sector> sectors = new ArrayList<Sector>();
                  for (Sector s : Sector.values()) {
                      sectors.add(s);
                  }
                  model.addAttribute("sectors", sectors);
                  return "employee/overview_business";
                default:
                  return "login";}
        } else {
            List<BankAccount> bankAccounts = login.getUserBankAccounts(dbUser);
            BankAccount selectedAccount = bankAccounts.get(0);

            StringBuilder fullname = new StringBuilder();
            fullname.append(selectedAccount.getOwner().getFirstName() + " ");
            if (selectedAccount.getOwner().getPrefix() != null) {
                fullname.append(selectedAccount.getOwner().getPrefix() + " ");
            }
            fullname.append(selectedAccount.getOwner().getLastName());

            model.addAttribute("pendingAuthorization", login.checkForPendingAuthorization(dbUser.getUsername()));

            //Erica - addition to display business customer
            boolean isBusinessCustomer = login.isBusinessCustomer(dbUser.getUsername());
            model.addAttribute("isBusiness", isBusinessCustomer);


            if (isBusinessCustomer) {

                model.addAttribute("businessname", login.getBusinessName(dbUser.getUserId()));
                model.addAttribute("kvkNummer", login.getBusinessKvk(dbUser.getUserId()));
                model.addAttribute("sector", login.getSector(dbUser.getUserId()));
                model.addAttribute("accountMan", login.getAccountMan(dbUser.getUserId()));

            }
            //Erica - addition to display customer details
            model.addAttribute("email", dbUser.getEmailaddress());
            model.addAttribute("streetName", selectedAccount.getOwner().getStreetName());
            model.addAttribute("number", selectedAccount.getOwner().getNumber());
            model.addAttribute("numberSuffix", selectedAccount.getOwner().getNumberSuffix());
            model.addAttribute("city", selectedAccount.getOwner().getCity());
            model.addAttribute("postalCode", selectedAccount.getOwner().getPostalCode());
            model.addAttribute("phonenumber", selectedAccount.getOwner().getPhonenumber());
            model.addAttribute("bsn", selectedAccount.getOwner().getBsn());
            session.setAttribute("sessionUser", dbUser);
            session.setAttribute("sessionUserName", dbUser.getUsername());
            model.addAttribute("bankAccounts", bankAccounts);
            session.setAttribute("fullname", fullname);
            model.addAttribute("fullname", fullname);
            session.setAttribute("firstName", selectedAccount.getOwner().getFirstName());
            model.addAttribute("selectedAccount", selectedAccount);
            System.out.println(session.getAttribute("sessionUser"));
            return "mypiggybank";
        }
    }

    @GetMapping("logOut")
    public String logOutHandler (HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("user", new User());
     return "login";
    }
}
