package edu;

import java.io.IOException;
import java.nio.file.*;

public class task3 {

    public static void main(String[] args) {
        Path dir = Paths.get("path/to/your/directory");

        DirectoryStream.Filter<Path> filter = AbstractFilter.regularFile
                .and(AbstractFilter.readable)
                .and(AbstractFilter.sizeGreaterThan(100_000))
                .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))
                .and(AbstractFilter.globMatches("*.png"))
                .and(AbstractFilter.regexMatches(".*\\.png"))
                .and(AbstractFilter.startsWith("prefix"))
                .or(AbstractFilter.directory);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {

        default AbstractFilter and(AbstractFilter other) {
            return (path) -> this.accept(path) && other.accept(path);
        }

        default AbstractFilter or(AbstractFilter other) {
            return (path) -> this.accept(path) || other.accept(path);
        }

        default AbstractFilter negate() {
            return (path) -> !this.accept(path);
        }

        static AbstractFilter regularFile = Files::isRegularFile;
        static AbstractFilter directory = Files::isDirectory;
        static AbstractFilter readable = Files::isReadable;

        static AbstractFilter sizeGreaterThan(long size) {
            return (path) -> {
                try {
                    return Files.size(path) > size;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            };
        }

        static AbstractFilter globMatches(String glob) {
            return (path) -> {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
                return matcher.matches(path.getFileName());
            };
        }

        static AbstractFilter regexMatches(String regex) {
            return (path) -> {
                String fileName = path.getFileName().toString();
                return fileName.matches(regex);
            };
        }

        static AbstractFilter startsWith(String prefix) {
            return (path) -> {
                String fileName = path.getFileName().toString();
                return fileName.startsWith(prefix);
            };
        }

        static AbstractFilter magicNumber(int... magicBytes) {
            return (path) -> {
                try {
                    byte[] fileBytes = Files.readAllBytes(path);
                    if (fileBytes.length >= magicBytes.length) {
                        for (int i = 0; i < magicBytes.length; i++) {
                            if ((fileBytes[i] & 0xFF) != magicBytes[i]) {
                                return false;
                            }
                        }
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            };
        }

        static AbstractFilter and(DirectoryStream.Filter<? super Path>... filters) {
            return (path) -> {
                for (DirectoryStream.Filter<? super Path> filter : filters) {
                    if (!filter.accept(path)) {
                        return false;
                    }
                }
                return true;
            };
        }

        static AbstractFilter or(DirectoryStream.Filter<? super Path>... filters) {
            return (path) -> {
                for (DirectoryStream.Filter<? super Path> filter : filters) {
                    if (filter.accept(path)) {
                        return true;
                    }
                }
                return false;
            };
        }

        static AbstractFilter not(DirectoryStream.Filter<? super Path> filter) {
            return (path) -> !filter.accept(path);
        }
    }
}
