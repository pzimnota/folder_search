package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders != null ? folders : new ArrayList<>();
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        FindFolderByNameVisitor visitor = new FindFolderByNameVisitor(name);
        traverse(folders, visitor);
        return visitor.getFoundFolder();
    }

    @Override
    public List<Folder> findFolderBySize(String size) {
        FindFoldersBySizeVisitor visitor = new FindFoldersBySizeVisitor(size);
        traverse(folders, visitor);
        return visitor.getFolderList();
    }

    @Override
    public int count() {
        CountVisitor visitor = new CountVisitor();
        traverse(folders, visitor);
        return visitor.getFolderCount();
    }

    private boolean traverse(List<Folder> folders, FolderVisitor visitor) {

        for (Folder folder : folders) {
            boolean shouldContinue = visitor.visit(folder);
            if (!shouldContinue) return false;
            if (folder instanceof MultiFolder multiFolder) {
                boolean continueRecursion = traverse(multiFolder.getFolders(), visitor);
                if (!continueRecursion) return false;
            }
        }

        return true;
    }
}

