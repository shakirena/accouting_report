package service;

import abstractions.FileContent;
import abstractions.ReportAnalyzer;
import model.MonthName;
import model.MonthlyData;
import model.YearlyData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class YearReportAnalyzer extends ReportAnalyzer {
    List<YearlyData> data = new ArrayList<>();

    public YearReportAnalyzer(FileContent<YearlyData> report) {
        this.data = report.getListofFiles();
    }

    @Override
    public int getSumCostly() {
        return data.stream().filter(i -> i.getIsExpense()).mapToInt(i -> i.getSum()).sum();

    }

    @Override
    public int getSumRentable() {
        return data.stream().filter(i -> i.getIsExpense()==false).mapToInt(i -> i.getSum()).sum();
    }

    @Override
    public String getRentable() {
        return data.stream().filter(i -> i.getIsExpense() == false)
                .max(Comparator.comparing(i -> i.getSum()))
                .map( i -> i.getMonth() + ":" + i.getSum()).orElse("No data");

    }

    @Override
    public String getCostly() {
        return data.stream().filter(i -> i.getIsExpense() == true)
                .max(Comparator.comparing(i -> i.getSum()))
                .map( i -> i.getMonth() + ":" + i.getSum()).orElse("No data");
    }

    public Double getAveCosts() {
        double count = data.stream().filter(i -> i.getIsExpense()).count();
        return data.stream().filter(i -> i.getIsExpense()).mapToInt(i -> i.getSum()).sum() / count;

    }

    public Double getAveIncoming() {
        double count = data.stream().filter(i -> i.getIsExpense() == false).count();
        return data.stream().filter(i -> i.getIsExpense() == false).mapToInt(i -> i.getSum()).sum() / count;
    }

    public String getProfitMonth() {

        return data.stream().collect(Collectors.groupingBy(
                        YearlyData::getMonth,
                        LinkedHashMap::new,
                        Collectors.summingInt(i -> i.getIsExpense() ? -i.getSum() : i.getSum())
                ))
                .entrySet().stream()
                .map(entry -> "\n" + MonthName.getMonthName(entry.getKey()) + " : " + entry.getValue())
                .collect(Collectors.joining());

    }
}
