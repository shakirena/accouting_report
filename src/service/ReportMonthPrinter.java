package service;

import abstractions.FileContent;
import abstractions.ReportAnalyzer;
import api.ReportPrinter;
import model.MonthName;
import model.MonthlyData;

/**
 *  Вывод Месячного отчета на консоль
 */
public class ReportMonthPrinter  implements ReportPrinter {
    FileContent<MonthlyData> report ;
    public ReportMonthPrinter(FileContent<MonthlyData> reportMonth) {
        report = reportMonth;
    }

    @Override
    public void print() {
        ReportAnalyzer analyzer = new MonthReportAnalyazer(report);
        int month  = Integer.parseInt(String.valueOf(report.getNameFile()).substring(4,6));
        System.out.println("месяц:" + MonthName.getMonthName(month));
        System.out.println("самый прибыльный товар: " + analyzer.getRentable());
        System.out.println("самый большой расход: " + analyzer.getCostly());
        System.out.println("--------------------------------------------");
    }
}
