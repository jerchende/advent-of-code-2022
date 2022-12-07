package net.erchen.adventofcode2022.day07;

import java.util.LinkedList;

public record FileSystem(Directory rootDir) {

    private static final long SIZE_NEEDED_FOR_UPDATE = 30_000_000;
    private final static long TOTAL_SIZE = 70_000_000;

    public static FileSystem fromInput(String input) {
        var commands = input.split("\n");

        Directory rootDir = new Directory("/", null, new LinkedList<>());
        Directory currentDir = null;

        for (String command : commands) {
            if (command.charAt(0) == '$') {
                if (command.equals("$ cd /")) {
                    currentDir = rootDir;
                } else if (command.equals("$ cd ..")) {
                    currentDir = currentDir.parent();
                } else if (command.startsWith("$ cd ")) {
                    currentDir = currentDir.childDir(command.substring(5));
                }
                continue;
            }

            currentDir.children().add(Handle.fromInput(command, currentDir));
        }

        return new FileSystem(rootDir);
    }

    public long sizeOfAllDirectoriesWithAtMost100k() {
        return rootDir.recursiveSubDirs().mapToLong(Directory::size).filter(size -> size <= 100000L).sum();
    }

    public long freeSpace() {
        return TOTAL_SIZE - rootDir().size();
    }

    public long sizeOfDirectoryToDelete() {
        var spaceNeeded = SIZE_NEEDED_FOR_UPDATE - freeSpace();
        return rootDir.recursiveSubDirs().mapToLong(Directory::size).filter(size -> size >= spaceNeeded).min().orElseThrow();
    }
}
