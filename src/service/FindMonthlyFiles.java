package service;

import abstractions.PatternFindFiles;

import java.util.regex.Pattern;

/**
 * Поиск Месячных отчетов
 * <p>
 *  Имена файлов соответствуют шаблону {@code m.YYYYmm.csv}
 */
public class FindMonthlyFiles extends PatternFindFiles {

    public FindMonthlyFiles(String paths) {
        super(paths);
    }


    @Override
    public Pattern getPattern() {
        return Pattern.compile("^m\\.\\d{6}\\.csv$");
    }
}
