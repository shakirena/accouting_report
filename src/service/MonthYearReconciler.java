package service;

import abstractions.FileContent;
import abstractions.ReportAnalyzer;
import api.ReportReconciler;
import model.MonthName;
import model.MonthlyData;
import model.YearlyData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthYearReconciler implements ReportReconciler {
    private Map<String, FileContent<MonthlyData>> monthMap = new HashMap<>();
    private Map<String, FileContent<YearlyData>> yearMap = new HashMap<>();

    public MonthYearReconciler(Map<String, FileContent<MonthlyData>> monthMap, Map<String, FileContent<YearlyData>> yearMap) {
        this.monthMap = monthMap;
        this.yearMap = yearMap;


    }

    @Override
    public List<String> reconcile() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, FileContent<YearlyData>> entry : yearMap.entrySet()) {
            monthMap.entrySet().stream()
                    .filter(e -> e.getKey().substring(0, 4).equals(entry.getKey()))
                    .forEach(e -> {
                        int month = Integer.parseInt(e.getKey().substring(4, 6));
                        String keyNew = MonthName.getMonthName(month) + " " + e.getKey().substring(0, 4);
                        ReportAnalyzer analyzer = new MonthReportAnalyazer(e.getValue());
                        int monthlyCost = analyzer.getSumCostly();

                        int yearlyCostForMonth = entry.getValue()
                                .getListofFiles().stream()
                                .filter(y -> y.getMonth() == month && y.getIsExpense())
                                .mapToInt(y -> y.getSum()).sum();

                        if (monthlyCost == yearlyCostForMonth) {

                            int monthRentable = analyzer.getSumRentable();
                            int yearlyRentableForMonth = entry.getValue()
                                    .getListofFiles().stream()
                                    .filter(y -> y.getMonth() == month && y.getIsExpense() == false)
                                    .mapToInt(y -> y.getSum()).sum();
                            if (monthRentable == yearlyRentableForMonth) result.add(keyNew + ":" + "совпадение");
                            else result.add(keyNew + ":не совпадение");
                        } else result.add(keyNew + ":не совпадение");

                    });
        }
        return result;
    }
}
