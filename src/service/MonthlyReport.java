package service;


import abstractions.FileContent;
import model.MonthlyData;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

public class MonthlyReport extends FileContent<MonthlyData> {
    List<MonthlyData> data = new ArrayList<>();
    private int valueNameMonth;

    public MonthlyReport(Path path) {
        super(path);
        getNameFileFromFile();
        valueNameMonth = Integer.parseInt(this.getNameFile().substring(4, 6));


    }


    public List<MonthlyData> getListofFiles() {
        return data;
    }

    private void getNameFileFromFile() {
        String name = getPath().getFileName().toString();
        this.setNameFile(name.substring(2, 8));
    }

    @Override
    public void saveFromFileToModel() {
        List<String> lines = readFileContentsOrNull();
        lines.remove(0);
        for (String ln : lines) {
            String[] part = ln.split(",");
            try {
                data.add(new MonthlyData(part[0], Boolean.parseBoolean(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3])));

            } catch (Exception e) {
                System.out.println("Неверный формат данных в файлах");

            }
        }

    }


    public int getSumCostly() {

        return data.stream().filter(i -> i.getIsExpense())
                .mapToInt(i -> i.getSumValue())
                .sum();
    }


    public int getSumRentable() {
        return data.stream().filter(i -> i.getIsExpense() == false)
                .mapToInt(i -> i.getSumValue())
                .sum();
    }


}
