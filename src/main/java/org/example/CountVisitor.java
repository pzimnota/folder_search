package org.example;

public class CountVisitor implements FolderVisitor {
    private int folderCount = 0;

    public int getFolderCount() {
        return folderCount;
    }

    @Override
    public boolean visit(Folder folder) {
        folderCount++;
        return true;
    }
}
