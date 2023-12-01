package Days;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    protected List<String> inputFile;

    public abstract String exec();

    public void getInput(String path) {
        try {
            this.inputFile = Files.readAllLines(Paths.get(getClass().getResource(path).toURI()));
        } catch (IOException e) {
            System.out.println("Couldn't read file in path: " + path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
