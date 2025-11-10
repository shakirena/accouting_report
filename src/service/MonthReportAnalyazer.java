package service;

import abstractions.FileContent;
import abstractions.ReportAnalyzer;
import model.MonthlyData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonthReportAnalyazer extends ReportAnalyzer {
    List<MonthlyData> data = new ArrayList<>();

    public MonthReportAnalyazer(FileContent<MonthlyData> report) {
        this.data = report.getListofFiles();
    }

    @Override
    public int getSumCostly() {

        return data.stream().filter(i -> i.getIsExpense())
                .mapToInt(i -> i.getSumValue())
                .sum();
    }

    @Override
    public int getSumRentable() {
        return data.stream().filter(i -> i.getIsExpense() == false)
                .mapToInt(i -> i.getSumValue())
                .sum();
    }

    @Override
    public String getRentable() {

        String name = data.stream().filter(i -> i.getIsExpense() == false)
                .max(Comparator.comparingInt(i -> i.getSumValue()))
                .map(i -> i.getItemName() + " - " + i.getSumValue()).orElse("No data");

        return name;
    }

    @Override
    public String getCostly() {

        String name = data.stream().filter(i -> i.getIsExpense() == true)
                .max(Comparator.comparingInt(i -> i.getSumValue()))
                .map(i -> i.getItemName() + " - " + i.getSumValue()).orElse("No data");

        return name;
    }
}
