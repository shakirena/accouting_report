package model;

public abstract class  AccountingData {

    private boolean isExpense;
    private int sum;
    private String nameObject;

    public boolean getIsExpense() {
        return isExpense;
    }

    public int getSum() {
        return sum;
    }

    public String getNameObject() {
        return nameObject;
    }

    public AccountingData(boolean isExpense, int sum) {
        this.isExpense = isExpense;
        this.sum = sum;
    }
}
