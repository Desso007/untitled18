import edu.task3;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class task3test {




    @Test
    public void testFilter_globMatches() throws IOException {
        task3.AbstractFilter filter = task3.AbstractFilter.globMatches("*.txt");
        assertTrue(filter.accept(Paths.get("test_files/file.txt")));
        assertFalse(filter.accept(Paths.get("test_files/image.png")));
    }

    @Test
    public void testFilter_regexMatches() throws IOException {
        task3.AbstractFilter filter = task3.AbstractFilter.regexMatches(".*\\.txt");
        assertTrue(filter.accept(Paths.get("test_files/file.txt")));
        assertFalse(filter.accept(Paths.get("test_files/image.png")));
    }

    @Test
    public void testFilter_startsWith() throws IOException {
        task3.AbstractFilter filter = task3.AbstractFilter.startsWith("prefix");
        assertTrue(filter.accept(Paths.get("test_files/prefix_file.txt")));
        assertFalse(filter.accept(Paths.get("test_files/file.txt")));
    }
}
