package org.example;

import java.util.Optional;

public class FindFolderByNameVisitor implements FolderVisitor {
    private final String name;
    private Optional<Folder> foundFolder;

    public FindFolderByNameVisitor(String name) {
        this.name = name;
        this.foundFolder = Optional.empty();
    }

    public Optional<Folder> getFoundFolder() {
        return foundFolder;
    }

    @Override
    public boolean visit(Folder folder) {
        if (folder.getName().equalsIgnoreCase(name)) {
            foundFolder = Optional.of(folder);
            return false;
        }
        return true;
    }
}
