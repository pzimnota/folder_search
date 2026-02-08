# File Cabinet — Composite + Internal Traversal

A small, clean Java (23+) project that models a file cabinet structure using the **Composite** design pattern.
The solution supports recursive operations such as searching, filtering, and counting folders, while keeping traversal logic centralized and reusable.

---

## ✅ Features

### Composite hierarchy
- `Folder` — common abstraction for all folders
- `SimpleFolder` — leaf node (no children)
- `ParentFolder` — composite node containing nested folders

Thanks to the Composite pattern, both simple and nested folders are treated uniformly.

### FileCabinet API
- `findFolderByName(String name): Optional<Folder>`
- `findFoldersBySize(String size): List<Folder>`  
  - case-insensitive  
- `count(): int`  
  - counts all folders, including nested ones

### Centralized traversal logic
- Recursive traversal is implemented once and reused across all operations.
- Prevents code duplication and keeps the API easy to extend.

### Immutability & safety
- Folder structures are created once and not modified externally.
- Defensive copying is used where appropriate.

---


## 🚀 Technologies Used

- Java 23 
- JUnit 5

## ⚙️ Setup

### Clone the repository

```bash
git clone https://github.com/pzimnota/folder_search.git
cd folder_search
```


## 🧪 Running Tests

To run tests:

```bash
mvn test
```

## 📌 Notes

Consider replacing String getSize with an enum:
```java
enum Size { SMALL, MEDIUM, LARGE }
```
---

Feel free to submit issues or suggestions via GitHub.
