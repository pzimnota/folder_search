package org.example;

import java.util.List;
import java.util.Optional;

public interface Cabinet {
    Optional<Folder> findFolderByName(String name);

    List<Folder> findFolderBySize(String size);

    int count();
}
