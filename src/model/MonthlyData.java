package model;

public class MonthlyData extends AccountingData {
    private String itemName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getSumValue() {
        return quantity * getSum();
    }

    public MonthlyData(String itemName, boolean isExpense, int quantity, int sum) {
        super(isExpense, sum);
        this.itemName = itemName;
        this.quantity = quantity;

    }


}
