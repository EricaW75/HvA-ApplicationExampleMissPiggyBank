package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.*;
import c16.mpb.bankingapp.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Nathan - service class om Hibernate database-connectie werkend te krijgen
@Service
public class HibernateLab {

    @Autowired
    AddAccount addAccount;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    BusinessCustomerDao businessCustomerDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    TransactionDao transactionDao;

    //Erica Addition for employee
    @Autowired
    DepartmentHeadDao departmentHeadDao;

    @Autowired
    PaymentAuthorizer paymentAuthorizer;

  /*  @Autowired
    AccountManagerDao accountManagerDao;
*/
    // Nathan - Constructor
    public HibernateLab() {
        super();
    }


    // Nathan - methode die test-content in de database zet
    public void dbinit() {




        // Nathan - Maak twee particuliere Customers aan zonder bankrekeningen
        Customer kermit = new Customer("Kermit",
                "1234",
                0,
                "Customer",
                "kermit@test.nl",
                "Kermit",
                "the",
                "Frog",
                1234567,
                "Koos Postemalaan",
                23,
                "a",
                "Hilversum",
                "1234AA",
                "06-12345678");

        Customer gabor = new Customer("gaborroozen",
                "1234",
                0,
                "Customer",
                "gabor@test.nl",
                "Gabor",
                "",
                "Roozen",
                7654321,
                "Stationsstraat",
                128,
                "",
                "Amsterdam",
                "9999ZZ",
                "06-87654321");

        // Erica - Add 1 Employee with role "hoofd particlieren"




    DepartmentHead hoofdPrivate = new DepartmentHead("Boss",
                "boss",
                0,
                "Employee",
                "boss@misspiggybank.nl", "Particulieren");



        // Nathan - Save alle customers naar de database
        customerDao.save(kermit);
        customerDao.save(gabor);

        // Erica - Save Employee to database
        departmentHeadDao.save(hoofdPrivate);

        // Nathan - Maak zes bankrekeningen aan voor de testklanten en wijs ze toe aan de klant
        addAccount.makeNewAccount(kermit);
        addAccount.makeNewAccount(kermit);
        addAccount.makeNewAccount(kermit);
        addAccount.makeNewAccount(gabor);
        addAccount.makeNewAccount(gabor);
        addAccount.makeNewAccount(gabor);

        // Nathan - Geeft de zes bankrekeningen allemaal wat cash
        // Haal de accounts op
        List<BankAccount> kermitaccounts = kermit.getBankAccounts();
        List<BankAccount> gaboraccounts = gabor.getBankAccounts();

        // Zet money bitch!
        kermitaccounts.get(0).setBalance(123.25);
        kermitaccounts.get(1).setBalance(4096.66);
        kermitaccounts.get(2).setBalance(1080.24);
        gaboraccounts.get(0).setBalance(555.77);
        gaboraccounts.get(1).setBalance(19.22);
        gaboraccounts.get(2).setBalance(763.84);
        // Nathan - Klaar met Sinterpiggy spelen :)


        // Nathan - Maak een heleboel test-transacties aan
        Transaction tr01 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                15.00,
                "2019-08-31 10:39",
                "Snoepjes voor Miss Piggy");

        Transaction tr02 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                30.00,
                "2019-09-03 16:12",
                "Kipkluifjes voor Animal");

        Transaction tr03 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                67.53,
                "2019-01-17 08:14",
                "Jumbo Supermarkten");

        Transaction tr04 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                121.30,
                "2019-01-19 19:43",
                "MediaMarkt Utrecht");

        Transaction tr05 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                88.00,
                "2019-01-22 22:12",
                "Coolblue");

        Transaction tr06 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                321.89,
                "2019-01-27 11:11",
                "IKEA Groningen");

        Transaction tr07 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                13.99,
                "2019-02-05 08:37",
                "Albert Heijn Supermarkten");

        Transaction tr08 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                31.25,
                "2019-02-07 19:22",
                "Tankstation Hilversum Noord");

        Transaction tr09 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                649.99,
                "2019-02-16 23:16",
                "Een enorm lange omschrijving die ingekort moet gaan worden op het transactieoverzicht, omdat er anders hele nare dingen gaan gebeuren met de layout.");

        Transaction tr10 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                45.52,
                "2019-02-17 07:31",
                "Nederlandse Spoorwegen");

        Transaction tr11 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                73.22,
                "2019-03-01 19:54",
                "Oude ladenkast Miss Piggy");

        Transaction tr12 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                1354.19,
                "2019-03-27 14:19",
                "Antieke Sita brommer van Gonzo");

        Transaction tr13 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                14.99,
                "2019-04-08 16:44",
                "Kiosk Station Amsterdam-Noord");

        Transaction tr14 = new Transaction(
                0,
                bankAccountDao.findByOwnerId(gabor.getUserId()).get(0),
                bankAccountDao.findByOwnerId(kermit.getUserId()).get(0),
                6.49,
                "2019-04-15 09:23",
                "Kiosk Station Hilversum Sportpark");

        // Nathan - Save transacties naar database
        transactionDao.save(tr01);
        transactionDao.save(tr02);
        transactionDao.save(tr03);
        transactionDao.save(tr04);
        transactionDao.save(tr05);
        transactionDao.save(tr06);
        transactionDao.save(tr07);
        transactionDao.save(tr08);
        transactionDao.save(tr09);
        transactionDao.save(tr10);
        transactionDao.save(tr11);
        transactionDao.save(tr12);
        transactionDao.save(tr13);
        transactionDao.save(tr14);

        // Nathan - Voeg transacties toe aan transactiehistories van Kermit en Gabor
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr01);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr01);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr02);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr02);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr03);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr03);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr04);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr04);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr05);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr05);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr06);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr06);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr07);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr07);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr08);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr08);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr09);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr09);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr10);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr10);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr11);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr11);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr12);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr12);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr13);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr13);
        bankAccountDao.findByOwnerId(kermit.getUserId()).get(0).addToTransactionhistory(tr14);
        bankAccountDao.findByOwnerId(gabor.getUserId()).get(0).addToTransactionhistory(tr14);

        customerDao.save(kermit);
        customerDao.save(gabor);

        //voor paymentscherm controle
        Customer c1 = new Customer();
        c1.setFirstName("John");
        c1.setLastName("Poopyman");
        customerDao.save(c1);

        BankAccount testAccount01 = new BankAccount(0, "NL09INGB0008580769", c1,
                3251.43);
        bankAccountDao.save(testAccount01);
        c1.addBankAccount(testAccount01);

        customerDao.save(c1);


        DepartmentHead dh1 = new DepartmentHead("DaddyMan", "baws", 0, "Employee", "cream@yahoo.de", "Business");
        departmentHeadDao.save(dh1);


        //aanmaken businessaccounts & business Customers voor test business head overview
        Customer ce = new Customer();
        BankAccount b1 = new BankAccount(0, addAccount.createIban(), ce, 300.0);
        b1.getAccountType();
        b1.setAccountType(AccountType.CURRENT);

        BusinessCustomer bc = new BusinessCustomer();
        bc.setSector(Sector.HOSPITALITY);
        bc.setFirstName("Sjakie");
        bc.setLastName("Rollebol");
        businessCustomerDao.save(bc);

        BankAccount b2 = new BankAccount(0, addAccount.createIban(), bc, 3000000.0);
        b2.getAccountType();
        b2.setAccountType(AccountType.BUSINESS);
        bankAccountDao.save(b2);


// test AccountManager
/*
        *//*AccountManager a1 = new AccountManager("Jan Lul", "063526273");
        AccountManager a2 = new AccountManager("Laura Lul", "063226373");*//*
        AccountManager a1 = new AccountManager("Fozzie", "fozzie", 0, "Employee", "fozzie@misspiggybankonline.nl","Fozzie Bear", "06-19740001");
        AccountManager a2 = new AccountManager("Gonzo", "gonzo", 0, "Employee", "gonzo@misspiggybankonline.nl", "Gonzo the Great", "06-19740002");
        AccountManager a3 = new AccountManager("Animal", "animal", 0, "Employee", "animal@misspiggybank.nl", "Animal", "06-19740003");
        AccountManager a4 = new AccountManager("Beaker", "beaker", 0, "Employee", "beaker@misspiggybank.nl","Beaker",  "06-19740004");
        AccountManager a5 = new AccountManager("Chef", "chef", 0, "Employee", "chef@misspiggybank.nl","The Swedish Chef",  "06-19740005");


        accountManagerDao.save(a1);
        accountManagerDao.save(a2);
        accountManagerDao.save(a3);
        accountManagerDao.save(a4);
        accountManagerDao.save(a5);*/

        BusinessCustomer bc3 = new BusinessCustomer();
        bc3.setSector(Sector.TECH);
        bc3.setFirstName("Jopje");
        bc3.setLastName("Pismeneer");
        bc3.setAccountMan(AccountMan.ANIMAL);
        businessCustomerDao.save(bc3);

        BankAccount b3 = new BankAccount(0, addAccount.createIban(), bc3, 50000.0);
        b3.getAccountType();
        b3.setAccountType(AccountType.BUSINESS);
        bankAccountDao.save(b3);

        BusinessCustomer bc4 = new BusinessCustomer();
        bc4.setSector(Sector.AGRICULTURE);
        bc4.setFirstName("Sabakoelie");
        bc4.setLastName("Sambasoeli");
        bc4.setAccountMan(AccountMan.BEAKER);
        businessCustomerDao.save(bc4);

        BankAccount b4 = new BankAccount(0, addAccount.createIban(), bc4, 120000.0);
        b4.getAccountType();
        b4.setAccountType(AccountType.BUSINESS);
        bankAccountDao.save(b4);

        BusinessCustomer bc5 = new BusinessCustomer();
        bc5.setSector(Sector.RETAIL);
        bc5.setFirstName("watra");
        bc5.setLastName("bakman");
        businessCustomerDao.save(bc5);

        BankAccount b5 = new BankAccount(0, addAccount.createIban(), bc5, 3000.0);
        b5.getAccountType();
        b5.setAccountType(AccountType.BUSINESS);
        bankAccountDao.save(b5);

        BusinessCustomer bc6 = new BusinessCustomer();
        bc6.setSector(Sector.SERVICES);
        businessCustomerDao.save(bc6);

        BankAccount b6 = new BankAccount(0, addAccount.createIban(), bc6, 1000000.0);
        b6.getAccountType();
        b6.setAccountType(AccountType.BUSINESS);
        bc6.setFirstName("Boelie");
        bc6.setLastName("Worstman");
        bankAccountDao.save(b6);

        BusinessCustomer bc7 = new BusinessCustomer();
        bc7.setSector(Sector.HEALTHCARE);
        bc7.setFirstName("Loutje");
        bc7.setLastName("Sneaky");
        businessCustomerDao.save(bc7);

        BankAccount b7 = new BankAccount(0, addAccount.createIban(), bc7, 25000.0);
        b7.getAccountType();
        b7.setAccountType(AccountType.BUSINESS);
        bankAccountDao.save(b7);

       /* accountManagerDao.save(a1);
        accountManagerDao.save(a2);*/

        transactionGenerator(50);
    }

/// FILLING OF THE 4000 + DATABASE

    public void dbinit2() {
        //Erica - methods to fill database with CSV files
        importDepartmentHeads();
        importCustomers();
       // importAccountManagers();

        importBusinessCustomers();
        importBankAccounts();
        transactionGenerator(100);

    }

    private void importBankAccounts() {
        Scanner fileReader;
        try {
            File bankAccountDetails = new File("data/bankaccountimport.csv");
            fileReader = new Scanner(bankAccountDetails);

            for (int i = 0; i < bankAccountDetails.length(); i++) {
                while (fileReader.hasNext()) {

                    String[] bankAccSplit = fileReader.nextLine().split(";");
                    String iban = bankAccSplit[0];
                    double balance = Double.parseDouble(bankAccSplit[1]);
                    String accType = bankAccSplit[2];
                    int customerId = Integer.parseInt(bankAccSplit[3]);

                    Customer c = customerDao.findById(customerId).get(); //line1


                    BankAccount newBankAccount = new BankAccount(0, iban, customerDao.findById(customerId).get(), balance, AccountType.valueOf(accType));
                   c.addBankAccount(newBankAccount); //Line2
                    bankAccountDao.save(newBankAccount);
                    customerDao.save(c); //Line3

                }
            }

        } catch (FileNotFoundException nofile) {
            System.out.println("No file available for import");
        }


    }

    private void importBusinessCustomers() {
        Scanner fileReader;
        try {
            File businesscustomerDetails = new File("data/businesscustomerimport.csv");
            fileReader = new Scanner(businesscustomerDetails);

            for (int i = 0; i < businesscustomerDetails.length(); i++) {
                while (fileReader.hasNext()) {
                    String[] customerSplit = fileReader.nextLine().split(";");
                    String username = customerSplit[0];
                    String password = customerSplit[1];
                    String role = customerSplit[2];
                    String emailaddress = customerSplit[3];
                    String firstName = customerSplit[4];
                    String prefix = customerSplit[5];
                    String lastName = customerSplit[6];
                    int bsn = Integer.parseInt(customerSplit[7]);
                    String streetName = customerSplit[8];
                    int number = Integer.parseInt(customerSplit[9]);
                    String numberSuffix = customerSplit[10];
                    String city = customerSplit[11];
                    String postalCode = customerSplit[12];
                    String phonenumber = customerSplit[13];
                    //int accountManagerID = Integer.parseInt(customerSplit[14]);
                    String accountMan = customerSplit[14];
                    String companyName = customerSplit[15];
                    int kvkNnumber = Integer.parseInt(customerSplit[16]);
                    String sector = customerSplit[17];

                   // AccountMan a = accountManagerDao.findById(accountManagerID).get();

                    BusinessCustomer newBusinessCustomer = new BusinessCustomer(username, password, 0, role, emailaddress,
                            firstName, prefix, lastName, bsn, streetName, number, numberSuffix,
                            city, postalCode, phonenumber, companyName, kvkNnumber, Sector.valueOf(sector), AccountMan.valueOf(accountMan));
                   // a.addToClients(newBusinessCustomer);
                    businessCustomerDao.save(newBusinessCustomer);
                   // accountManagerDao.save(a);



                }
            }

        } catch (FileNotFoundException nofile) {
            System.out.println("No file available for import");

    }
    }

    private void importDepartmentHeads() {
        Scanner fileReader;
        try {
            File departmentHeadDetails = new File("data/departmentheadimport.csv");
            fileReader = new Scanner(departmentHeadDetails);

            for (int i = 0; i < departmentHeadDetails.length(); i++) {
                while (fileReader.hasNext()) {
                    String[] AccManSplit = fileReader.nextLine().split(";");
                    String username = AccManSplit[0];
                    String password = AccManSplit[1];
                    String role = AccManSplit[2];
                    String emailaddress = AccManSplit[3];
                    String department = AccManSplit[4];


                    DepartmentHead newDepartmentHead = new DepartmentHead(username, password, 0, role, emailaddress, department);
                    departmentHeadDao.save(newDepartmentHead);
                }
            }

        } catch (FileNotFoundException nofile) {
            System.out.println("No file available for import");
        }
    }

    private void importAccountManagers() {
        Scanner fileReader;
        try {
            File accountManagerDetails = new File("data/accountmanagerimport.csv");
            fileReader = new Scanner(accountManagerDetails);

            for (int i = 0; i < accountManagerDetails.length(); i++) {
                while (fileReader.hasNext()) {
                    String[] AccManSplit = fileReader.nextLine().split(";");
                    String username = AccManSplit[0];
                    String password = AccManSplit[1];
                    String role = AccManSplit[2];
                    String emailaddress = AccManSplit[3];
                    String name = AccManSplit[4];
                    String contactInfo = AccManSplit[5];
                }
            }

            } catch (FileNotFoundException nofile) {
                System.out.println("No file available for import");
            }
    }

    private void importCustomers() {
        Scanner fileReader;
        try {
            File customerDetails = new File("data/customerimport.csv");
            fileReader = new Scanner(customerDetails);

    for (int i = 0; i < customerDetails.length(); i++) {
        while (fileReader.hasNext()) {
        String[] customerSplit = fileReader.nextLine().split(";");
        String username = customerSplit[0];
        String password = customerSplit[1];
        String role = customerSplit[2];
        String emailaddress = customerSplit[3];
        String firstName = customerSplit[4];
        String prefix = customerSplit[5];
        String lastName = customerSplit[6];
        int bsn = Integer.parseInt(customerSplit[7]);
        String streetName = customerSplit[8];
        int number = Integer.parseInt(customerSplit[9]);
        String numberSuffix = customerSplit[10];
        String city = customerSplit[11];
        String postalCode = customerSplit[12];
        String phonenumber = customerSplit[13];

        Customer newCustomer = new Customer(username, password, 0, role, emailaddress, firstName, prefix, lastName, bsn, streetName, number, numberSuffix, city, postalCode, phonenumber);
        customerDao.save(newCustomer);
    }
}
        } catch (FileNotFoundException nofile) {
            System.out.println("No file available for import");
        }
    }

    //random transacton generator, doet een x aantal transacties van en naar random bankaccounts met max 4/5 van het beschiknbare saldo
    public void transactionGenerator(int number) {
        for (int i = 0; i < number; i++) {
            Transaction t = setUpRandomTransaction();
            try {
                paymentAuthorizer.checkValidity(t);
                paymentAuthorizer.execute(t);
            } catch (Exception e) {
                System.out.println("Couldn't generate transaction nr: " + i);
            }
        }
    }

    public Transaction setUpRandomTransaction() {
        ArrayList<BankAccount> allAccounts = (ArrayList<BankAccount>) bankAccountDao.findAll();
        BankAccount debitAccount = allAccounts.get((int) (Math.random() * allAccounts.size()));
        BankAccount creditAccount = allAccounts.get((int) (Math.random() * allAccounts.size()));
        if ((debitAccount == creditAccount)) {
            creditAccount = allAccounts.get((int) (Math.random() * allAccounts.size()));
        }
        double paymentAmount = roundTo2Decimals((debitAccount.getBalance() * 0.80) * Math.random());
        return new Transaction(0, debitAccount, creditAccount, paymentAmount, "2019-04 14:45:00", "Overschrijving");
    }

    private double roundTo2Decimals(double amount) {
        return Math.round(amount * 100) / 100.0;
    }
}
