package c16.mpb.bankingapp.model;

public enum Sector {
    SERVICES ("Services"),
    RETAIL ("Retail"),
    HEALTHCARE ("Healthcare"),
    TECH ("Tech"),
    HOSPITALITY ("Hospitality"),
    AGRICULTURE( "Agriculture");


    public final String label;

    private Sector (String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}

