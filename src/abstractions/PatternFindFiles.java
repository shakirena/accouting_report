package abstractions;
import api.FindFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Абстрактный базовый класс для поиска файлов в директории по шаблону названии
 * <p>
 * Реализует общий алгоритм поиска и оставляет конкретный шаблон регулярного выражение на усмотрение наследников
 */
public abstract class PatternFindFiles implements FindFiles {
    /**
     * Директория, в которой происходит поиск файлов
     */
    private  final Path path ;

    /**
     * Возвращает регулярное выражение для фильтрации файлов по имени
     * @return регулярное выражение для фильтрации файлов
     */
    public abstract Pattern getPattern();


    public PatternFindFiles(String paths) {
       path = Paths.get(paths);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<Path> findInDirectory() throws IOException {

        if (!Files.isDirectory(path))
        {
            System.out.println(path + " такой директории не существует");
            return Collections.emptyList();
        }
        Pattern pattern  = getPattern(); //;
        List<Path> fileNames = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, p ->
                pattern.matcher(p.getFileName().toString()).matches())) {
                for (Path file : stream)
                {
                    Path pathFull = Paths.get( path.toString() , "//" , file.getFileName().toString());
                    fileNames.add(pathFull);
                }
        }
        return fileNames;
    }
}
