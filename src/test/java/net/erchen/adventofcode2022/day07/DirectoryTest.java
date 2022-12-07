package net.erchen.adventofcode2022.day07;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DirectoryTest {

    @Test
    void shouldInitByInput() {
        var rootDir = new Directory("/", null, new LinkedList<>());
        var directory = Directory.fromInput("dir abcde", rootDir);

        assertThat(directory.name()).isEqualTo("abcde");
        assertThat(directory.parent()).isEqualTo(rootDir);
        assertThat(directory.children()).hasSize(0);
        assertThat(directory.children()).isInstanceOf(LinkedList.class);
    }

    @Test
    void shouldCalculateSize() {
        var directory = new Directory("/", null, List.of(new File("a", 123L), new File("b", 456L), new Directory("tmp", null, List.of(new File("c", 789L)))));

        assertThat(directory.size()).isEqualTo(1368L);
    }

    @Test
    void shouldGetSubdirs() {
        var tmp = new Directory("tmp", null, List.of());
        var directory = new Directory("/", null, List.of(tmp));

        assertThat(directory.subDirs()).containsExactly(tmp);
    }

    @Test
    void shouldGetRecursiveSubdirs() {
        var a = new Directory("a", null, List.of());
        var b = new Directory("b", null, new LinkedList<>());
        var c = new Directory("c", b, List.of());
        b.children().add(c);

        var directory = new Directory("/", null, List.of(a, b));

        assertThat(directory.subDirs()).containsExactly(a, b);
        assertThat(directory.recursiveSubDirs()).containsExactly(a, b, c);
    }

    @Test
    void shouldGetSubdir() {
        var tmp = new Directory("tmp", null, List.of());
        var directory = new Directory("/", null, List.of(tmp));

        assertThat(directory.childDir("tmp")).isEqualTo(tmp);
    }

}