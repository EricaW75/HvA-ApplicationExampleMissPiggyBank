package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.*;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerViewer.class})
@MockBeans({@MockBean(BankAccountDao.class), @MockBean(CustomerDao.class)})
public class CustomerViewerTest {

    @Autowired
    CustomerViewer customerViewer;

    @MockBean
    BankAccountDao bankAccountDao;

    @MockBean
    CustomerDao customerDao;

    @Before
    public void setupMock() {
        //Setup Mockdata for getTopBankAccounts
        ArrayList<BankAccount> topBankAccounts = new ArrayList<>();
        topBankAccounts.add(new BankAccount(1,"1",null, 20));
        topBankAccounts.add(new BankAccount(2,"2",null, 1000));
        topBankAccounts.add(new BankAccount(3,"3",null, 400));
        topBankAccounts.add(new BankAccount(4,"4",null, 20000));
        topBankAccounts.add(new BankAccount(5,"5",null, 20));
        topBankAccounts.add(new BankAccount(6,"6",null, -10));
        when(bankAccountDao.findAll()).thenReturn(topBankAccounts);

        //Setup Mockdata for getAverageBalancePerSector & for getMostActiveBusinessCustomers
        ArrayList<BankAccount> businessAccounts = new ArrayList<>();
        int amount = 100;
        for (Sector sector : Sector.values()) {
            for (int i = 1; i <= 3; i++) {
                BusinessCustomer businessCustomer = new BusinessCustomer("username"+i, "password"+i,i,
                        "","", "","","",i,"",i,"","",
                        "","",null,null,i,sector.toString(), "FOZZIE");
                businessAccounts.add(new BankAccount(i,""+i, businessCustomer, amount));
            }
            amount += 100;
        }
        when(bankAccountDao.findByAccountType(AccountType.BUSINESS)).thenReturn(businessAccounts);

    }

    @Test
    public void getMostActiveBusinessCustomersWhenItReturnsEmptyList() {
        List<Customer> customerList = customerViewer.getMostActiveBusinessCustomers(0);
        assertNotEquals(null, customerList);
        assertEquals(0, customerList.size());
    }

    @Test
    public void getMostActiveBusinessCustomersWhenAsking3Items() {
        List<Customer> customerList = customerViewer.getMostActiveBusinessCustomers(3);
        assertNotEquals(null, customerList);
        assertEquals(3, customerList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getMostActiveBusinessCustomersWhenAskingTooManyItems() {
        customerViewer.getMostActiveBusinessCustomers(20);
    }

    @Test
    public void getTopBankAccountsWhenAskingTop5() {
        List<BankAccount> topBankAccounts = customerViewer.getTopBankAccounts(5);
        assertNotEquals(null, topBankAccounts);
        assertEquals(20000, topBankAccounts.get(0).getBalance(), 0.1);
        assertEquals(20, topBankAccounts.get(topBankAccounts.size()-1).getBalance(), 0.1);
    }

    @Test
    public void getTopBankAccountsWhenResultContainsNegativeBalance() {
        List<BankAccount> topBankAccounts = customerViewer.getTopBankAccounts(6);
        assertNotEquals(null, topBankAccounts);
        assertEquals(-10, topBankAccounts.get(topBankAccounts.size()-1).getBalance(), 0.1);
    }

    @Test
    public void getAverageBalancePerSector() {
        double[] averageBalances = customerViewer.getAverageBalancePerSector();
        assertNotEquals(null, averageBalances);
        assertEquals(100, averageBalances[0], 0.1);
        assertEquals(600, averageBalances[averageBalances.length-1], 0.1);
    }

    @Test
    public void filterBySectorAgriculture() {
        List<BankAccount> bankAccounts = customerViewer.filterBySector((ArrayList<BankAccount>)bankAccountDao.findByAccountType(AccountType.BUSINESS), Sector.AGRICULTURE);
        assertNotEquals(null, bankAccounts);
        assertEquals(Sector.AGRICULTURE, ((BusinessCustomer) (bankAccounts.get(0).getOwner())).getSector());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void filterBySectorAgricultureWhenAskingTooManyItems() {
        List<BankAccount> bankAccounts = customerViewer.filterBySector((ArrayList<BankAccount>)bankAccountDao.findByAccountType(AccountType.BUSINESS), Sector.AGRICULTURE);
        assertEquals(Sector.AGRICULTURE, ((BusinessCustomer) (bankAccounts.get(3).getOwner())).getSector());
    }

    @Test
    public void filterBySectorHealthCare() {
        List<BankAccount> bankAccounts = customerViewer.filterBySector((ArrayList<BankAccount>)bankAccountDao.findByAccountType(AccountType.BUSINESS), Sector.HEALTHCARE);
        assertNotEquals(null, bankAccounts);
        assertEquals(Sector.HEALTHCARE, ((BusinessCustomer) (bankAccounts.get(0).getOwner())).getSector());
    }
}