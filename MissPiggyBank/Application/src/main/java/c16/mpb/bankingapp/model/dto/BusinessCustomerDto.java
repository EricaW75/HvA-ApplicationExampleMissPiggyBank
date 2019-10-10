package c16.mpb.bankingapp.model.dto;

import c16.mpb.bankingapp.model.Sector;

public class BusinessCustomerDto {

    private String accountManager;
    private String companyName;
    private int kvkNumber;
    private Sector sector;

    public BusinessCustomerDto(String accountManager, String companyName, int kvkNumber, Sector sector) {
        this.accountManager = accountManager;
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
    }
}
