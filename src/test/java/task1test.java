import edu.task1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class task1test {



    @Test
    public void testIsEmpty() {
        task1 emptyDiskMap = new task1("empty_test_data.txt");
        assertTrue(emptyDiskMap.isEmpty());

        task1 diskMap = new task1("non_empty_test_data.txt");
        diskMap.put("key", "value");
        assertFalse(diskMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        task1 diskMap = new task1("test_data.txt");
        diskMap.put("key", "value");
        assertTrue(diskMap.containsKey("key"));
        assertFalse(diskMap.containsKey("nonexistentKey"));
    }

    @Test
    public void testContainsValue() {
        task1 diskMap = new task1("test_data.txt");
        diskMap.put("key", "value");
        assertTrue(diskMap.containsValue("value"));
        assertFalse(diskMap.containsValue("nonexistentValue"));
    }

    @Test
    public void testGet() {
        task1 diskMap = new task1("test_data.txt");
        diskMap.put("key", "value");
        assertEquals("value", diskMap.get("key"));
        assertNull(diskMap.get("nonexistentKey"));
    }

}
