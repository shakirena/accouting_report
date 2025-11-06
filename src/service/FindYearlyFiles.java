package service;
import abstractions.PatternFindFiles;

import java.util.regex.Pattern;

/**
 * Поиск Годовых отчетов
 * <p>
 * Имена файлов соответствует шаблону {@code y.YYYY.csv}
 */
public class FindYearlyFiles  extends PatternFindFiles {
    public FindYearlyFiles(String path)
    {
        super(path);
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^y\\.\\d{4}\\.csv$");
    }
}
