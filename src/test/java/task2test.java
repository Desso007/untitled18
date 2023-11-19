import edu.task2;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class task2test {

    @Test
    public void testCloneFile_Failure() throws IOException {
        Path nonExistentFilePath = Paths.get("test_files/nonexistent.txt");

        task2.cloneFile(nonExistentFilePath);

        assertFalse(Files.exists(Paths.get("test_files/nonexistent - копия.txt")));
    }


}
