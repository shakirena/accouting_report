package model;

public class YearlyData  extends AccountingData{
    private int month;


    public int getMonth() {
        return month;
    }

    public YearlyData(int month, int sum, boolean is_expense) {
        super(is_expense, sum);
        this.month = month;

    }
}
