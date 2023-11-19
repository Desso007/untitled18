package edu;

import java.io.*;
import java.util.*;

public class task1 implements Map<String, String> {
    private File file;

    public task1(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int size() {
        return loadMap().size();
    }

    @Override
    public boolean isEmpty() {
        return loadMap().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return loadMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return loadMap().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return loadMap().get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> map = loadMap();
        String oldValue = map.put(key, value);
        saveMap(map);
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }

    // Implement other methods of the Map interface

    private Map<String, String> loadMap() {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int separatorIndex = line.indexOf(':');
                if (separatorIndex != -1) {
                    String key = line.substring(0, separatorIndex).trim();
                    String value = line.substring(separatorIndex + 1).trim();
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private void saveMap(Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test the DiskMap class
    public static void main(String[] args) {
        task1 diskMap = new task1("data.txt");
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        System.out.println(diskMap.get("key1")); // Output: value1
        System.out.println(diskMap.get("key2")); // Output: value2
    }
}