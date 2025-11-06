package abstractions;
import api.ReadFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @param <T>
 */
public abstract class FileContent<T> implements ReadFile {
    private Path file ;
    private String nameFile;

    public abstract void saveFromFileToModel();


    public abstract List<T> getListofFiles();

    public FileContent(Path path)
    {
        file = path;
    }

    public Path getPath()
    {
        return file;
    }

    public String getNameFile() {
        return this.nameFile;
    }
    public void  setNameFile(String name) {
        this.nameFile = name;
    }
    @Override
    public List<String> readFileContentsOrNull() {
        try {
            List<String> lines = Files.readAllLines(file);
            return lines;

        } catch (IOException e) {
            return null;
        }
    }



}
