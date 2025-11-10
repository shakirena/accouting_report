package ui;
import abstractions.FileContent;
import api.FindFiles;
import api.ReportPrinter;
import api.ReportReconciler;
import model.MonthlyData;
import model.YearlyData;
import service.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {

    Map<String, FileContent<MonthlyData>> mapMonth = new HashMap<>();
    Map<String, FileContent<YearlyData>> mapYear = new HashMap<>();

    public void start() {

        while (true) {
            printMenu();
            int choise = readInt();
            switch (choise) {
                case 1:
                    monthlyReportsDownload();
                    break;
                case 2:
                    yearlyReportsDownload();
                    break;
                case 3:
                    reconciliation();
                    break;
                case 4:
                    monthlyReportPrint();
                    break;
                case 5:
                    yearlyReportPrint();
                    break;
                case 6: {
                    System.out.println("Завершение программы");
                    return;
                }

            }
        }

    }

    private void printMenu() {
        System.out.println("Выберите нужную команду");
        System.out.println("-----------MENU-----------\n" +
                "1.Считать все месячные отчеты\n" +
                "2.Считать все годовой отчет\n" +
                "3.Сверить отчеты\n" +
                "4.Вывести информацию о всех месячный отчетах \n" +
                "5.Вывести информацию о годовом отчете \n" +
                "6.Выйти  из программы"
        );
        System.out.println("-----------MENU-----------");
    }

    private void reconciliation() {
        if (mapMonth.isEmpty() || mapYear.isEmpty()) {
            System.out.println("Сначала считайте все отчеты (пункт 1 и 2)");
            return;
        }

        ReportReconciler recon = new MonthYearReconciler(mapMonth, mapYear);
        List<String> result = recon.reconcile();
        for (String res : result) {
            System.out.println(res);
        }


    }

    private void monthlyReportPrint() {
        if (mapMonth.isEmpty()) {
            System.out.println("Выберите '1.Считать все месячные отчеты'");
            return;
        }
        mapMonth.entrySet().stream().forEach(i -> {
            ReportPrinter print = new ReportMonthPrinter(i.getValue());
            print.print();

        });

    }

    private void yearlyReportPrint() {
        if (mapYear.isEmpty()) {
            System.out.println("Выберите '2.Считать все годовые отчеты'");
            return;
        }
        mapYear.entrySet().stream().forEach(i -> {
            ReportPrinter print = new ReportYearPrinter(i.getValue());
            print.print();

        });

    }

    private void monthlyReportsDownload() {

        System.out.println("Введите директорию отчетов (default C:\\Intel\\project)");
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.nextLine();
        if (dir.isEmpty()) dir = "C:\\Intel\\project";

        FindFiles find = new FindMonthlyFiles(dir);
        try {
            List<Path> files = find.findInDirectory();
            for (Path pth : files) {
                FileContent reportMonth = new MonthlyReport(pth);
                System.out.println(reportMonth.getNameFile());

                reportMonth.saveFromFileToModel();
                mapMonth.put(reportMonth.getNameFile(), reportMonth);

            }
            System.out.println("Файлы импортированы");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void yearlyReportsDownload() {
        System.out.println("Введите директорию отчетов (Enter if default C:\\Intel\\project)");
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.nextLine();
        if (dir.isEmpty()) dir = "C:\\Intel\\project";

        FindFiles find = new FindYearlyFiles(dir);
        try {
            List<Path> files = find.findInDirectory();

            for (Path pth : files) {
                FileContent reportYear = new YearlyReport(pth);
                reportYear.saveFromFileToModel();
                mapYear.put(reportYear.getNameFile(), reportYear);

            }
            System.out.println("Файлы импортированы");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int readInt() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.println("Введите число из меню");
            scanner.next();
            printMenu();
        }
        int choise = scanner.nextInt();
        return choise;
    }

}
