package net.erchen.adventofcode2022.day07;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileSystemTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day07/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day07/input.txt"));
    }

    @Test
    void shouldInitFromInput() {

        var fileSystem = FileSystem.fromInput(sampleInput());

        assertThat(fileSystem.rootDir().toString()).isEqualTo("Directory[name='/', children=[Directory[name='a', children=[Directory[name='e', children=[File[name=i, size=584]]], File[name=f, size=29116], File[name=g, size=2557], File[name=h.lst, size=62596]]], File[name=b.txt, size=14848514], File[name=c.dat, size=8504156], Directory[name='d', children=[File[name=j, size=4060174], File[name=d.log, size=8033020], File[name=d.ext, size=5626152], File[name=k, size=7214296]]]]]");
    }

    @Test
    void sizeOfAllDirectoriesWithAtLeast100k_Sample() {
        var fileSystem = FileSystem.fromInput(sampleInput());

        assertThat(fileSystem.sizeOfAllDirectoriesWithAtMost100k()).isEqualTo(95437);
    }

    @Test
    void sizeOfAllDirectoriesWithAtLeast100k_Solution() {
        var fileSystem = FileSystem.fromInput(solutionInput());

        assertThat(fileSystem.sizeOfAllDirectoriesWithAtMost100k()).isEqualTo(1611443);
    }

    @Test
    void freeSpace() {
        var fileSystem = FileSystem.fromInput(sampleInput());

        assertThat(fileSystem.freeSpace()).isEqualTo(21618835);
    }

    @Test
    void sizeOfDirectoryToDelete_Sample() {
        var fileSystem = FileSystem.fromInput(sampleInput());

        assertThat(fileSystem.sizeOfDirectoryToDelete()).isEqualTo(24933642);
    }

    @Test
    void sizeOfDirectoryToDelete_Solution() {
        var fileSystem = FileSystem.fromInput(solutionInput());

        assertThat(fileSystem.sizeOfDirectoryToDelete()).isEqualTo(2086088);
    }
}