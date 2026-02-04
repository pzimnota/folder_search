package org.example;

import java.util.ArrayList;
import java.util.List;

public class FindFoldersBySizeVisitor implements FolderVisitor {
    private final String size;
    private final List<Folder> folderList;

    public FindFoldersBySizeVisitor(String size) {
        this.size = size;
        this.folderList = new ArrayList<>();
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    @Override
    public boolean visit(Folder folder) {
        if (folder.getSize().equalsIgnoreCase(size)) {
            folderList.add(folder);
        }
        return true;
    }
}
