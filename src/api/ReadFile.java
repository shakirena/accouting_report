package api;

import java.util.List;

/**
 * Интерфейс для чтения содержимого файлов.
 */
public interface ReadFile {

    /**
     * Читает файл и возвращает список строк
     * @return список строк файлов или {@code null},если не удалось прочитать файл
     */
    public abstract List<String> readFileContentsOrNull();


}
