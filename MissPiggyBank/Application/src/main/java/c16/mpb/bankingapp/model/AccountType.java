package c16.mpb.bankingapp.model;

public enum AccountType {

    CURRENT("Betaalrekening"),
    SAVINGS("Spaarrekening"),
    LOAN("Lening"),
    BUSINESS("Zakelijke rekening");

    public final String label;

    private AccountType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
