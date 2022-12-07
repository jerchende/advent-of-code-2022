package net.erchen.adventofcode2022.day07;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;


public record Directory(String name, Directory parent, List<Handle> children) implements Handle {

    public static Directory fromInput(String input, Directory parent) {
        return new Directory(input.substring(4), parent, new LinkedList<>());
    }

    @Override
    public Long size() {
        return children.stream().mapToLong(Handle::size).sum();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    public Directory childDir(String name) {
        return subDirs().filter(dir -> dir.name().equals(name)).findFirst().orElseThrow();
    }

    public Stream<Directory> subDirs() {
        return children.stream().filter(Handle::isDirectory).map(h -> (Directory) h);
    }

    public Stream<Directory> recursiveSubDirs() {
        return Stream.concat(subDirs(), subDirs().flatMap(Directory::recursiveSubDirs));
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Directory.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("children=" + children)
                .toString();
    }
}
