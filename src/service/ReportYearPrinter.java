package service;

import abstractions.FileContent;
import api.ReportPrinter;
import model.YearlyData;

public class ReportYearPrinter implements ReportPrinter {
    FileContent<YearlyData> report;

    public ReportYearPrinter(FileContent<YearlyData> reportYear) {
        report = reportYear;
    }

    @Override
    public void print() {
        YearReportAnalyzer analyzer = new YearReportAnalyzer(report);
        System.out.println("Год:" + report.getNameFile());
        System.out.println("Прибыль по каждому месяцу: " + analyzer.getProfitMonth());
        System.out.printf("Средний расход за год: %.2f %n", analyzer.getAveCosts());
        System.out.printf("Средний доход за год: %.2f %n", analyzer.getAveIncoming());
        System.out.println("--------------------------------------------");
    }
}
