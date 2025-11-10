package service;


import abstractions.FileContent;
import model.YearlyData;
import java.nio.file.Path;
import java.util.*;

public class YearlyReport extends FileContent<YearlyData> {
    private List<YearlyData> data = new ArrayList<>();

    public YearlyReport(Path path) {
        super(path);
        getNameFileFromFile();
    }

    public List<YearlyData> getListofFiles() {
        return data;
    }

    private void getNameFileFromFile() {
        String name = getPath().getFileName().toString();
        this.setNameFile(name.substring(2, 6));
    }

    @Override
    public void saveFromFileToModel() {
        List<String> lines = readFileContentsOrNull();
        lines.remove(0);
        for (String ln : lines) {
            String[] part = ln.split(",");
            try {
                data.add(new YearlyData(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Boolean.parseBoolean(part[2])));

            } catch (Exception e) {
                System.out.println("Неверный формат данных в файлах");

            }
        }
    }


    public String getCostly() {
        return "";
    }
}
