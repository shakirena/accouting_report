package api;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Интерфейс для поиска файлов в директории
 * Реализации определяют логику поиска (например, по регулярному выражению)
 */
public interface FindFiles {

    /** Ищет файлы в директории
     * @return коллекцию путей, если файлы найдены, иначе {@code emptyList},
     *                          если указанная директория не существует
     * @throws IOException, если ошибка чтения директории
     */
    public abstract List<Path> findInDirectory() throws IOException;
}
