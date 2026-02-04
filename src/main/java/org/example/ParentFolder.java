package org.example;

import java.util.List;

public record ParentFolder(String getName, String getSize, List<Folder> getFolders) implements MultiFolder {
}
