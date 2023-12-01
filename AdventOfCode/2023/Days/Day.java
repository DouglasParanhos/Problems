package Days;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class Day {

    protected List<String> inputLines;

    public abstract String exec();

    public void getInput(String path) {
        try {
            this.inputLines = Files.readAllLines(Paths.get(Objects.requireNonNull(getClass().getResource(path)).toURI()));
        } catch (IOException e) {
            System.out.println("Couldn't read file in path: " + path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
