import org.example.FileCabinet;
import org.example.Folder;
import org.example.ParentFolder;
import org.example.SimpleFolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileCabinetTest {
    private static FileCabinet simpleCabinet;
    private static FileCabinet nestedCabinet;
    private static Folder original, duplicate;

    @BeforeAll
    static void setup() {
        List<Folder> simpleFolders = new ArrayList<>();
        simpleFolders.add(new SimpleFolder("First", "SMALL"));
        simpleFolders.add(new SimpleFolder("Second", "MEDIUM"));
        simpleFolders.add(new SimpleFolder("Third", "MEDIUM"));
        simpleCabinet = new FileCabinet(simpleFolders);


        List<Folder> grandChildrenFolders = new ArrayList<>();
        grandChildrenFolders.add(new SimpleFolder("First_Child", "SMALL"));
        original = new SimpleFolder("Second_Child", "MEDIUM");
        grandChildrenFolders.add(original);
        duplicate = new SimpleFolder("Second_Child", "MEDIUM");
        grandChildrenFolders.add(duplicate);


        List<Folder> childrenFolders = new ArrayList<>();
        childrenFolders.add(new ParentFolder("Parent", "BIG", grandChildrenFolders));


        List<Folder> nestedFolders = new ArrayList<>();
        nestedFolders.add(new ParentFolder("Grand", "BIG", childrenFolders));
        nestedCabinet = new FileCabinet(nestedFolders);

    }


    @Test
    void count_emptyCabinet_returnsZero() {
        FileCabinet emptyCabinet = new FileCabinet(new ArrayList<>());
        int result = emptyCabinet.count();
        assertEquals(0, result);
    }


    @Test
    void count_nullCabinet_returnsZero() {
        FileCabinet nullCabinet = new FileCabinet(null);
        int result = nullCabinet.count();
        assertEquals(0, result);
    }


    @Test
    void count_onlyRootFolders_countsAll() {
        int result = simpleCabinet.count();
        assertEquals(3, result);

    }


    @Test
    void count_nestedMultiFolder_countsAllLevels() {
        int result = nestedCabinet.count();
        assertEquals(5, result);
    }


    @Test
    void findFolderByName_noMatch_returnsEmptyOptional() {
        Optional<Folder> result = simpleCabinet.findFolderByName("XYZ");
        assertFalse(result.isPresent());
    }


    @Test
    void findFolderByName_rootFolderMatch_returnsRoot() {
        Optional<Folder> result = nestedCabinet.findFolderByName("Grand");
        assertTrue(result.isPresent());
    }


    @Test
    void findFolderByName_nestedFolderMatch_returnsNested() {
        Optional<Folder> result = nestedCabinet.findFolderByName("First_Child");
        assertTrue(result.isPresent());
    }


    @Test
    void findFolderByName_multipleMatch_returnsFirstEncountered() {
        Optional<Folder> result = nestedCabinet.findFolderByName("Second_Child");
        assertTrue(result.isPresent());
        assertNotSame(duplicate, result.get());
        assertSame(original, result.get());
    }


    @Test
    void findFoldersBySize_noMatches_returnsEmptyList() {
        List<Folder> result = simpleCabinet.findFolderBySize("BIG");
        assertEquals(0, result.size());
    }

    @Test
    void findFoldersBySize_singleMatch_returnsListWithOneElement() {
        List<Folder> result = simpleCabinet.findFolderBySize("SMALL");
        assertEquals(1, result.size());
    }

    @Test
    void findFoldersBySize_multipleMatches_returnsListWithAllMatches() {
        List<Folder> result = simpleCabinet.findFolderBySize("MEDIUM");
        assertEquals(2, result.size());

    }

    @Test
    void findFoldersBySize_nestedMatch_returnsListWithAllMatches() {
        List<Folder> result = nestedCabinet.findFolderBySize("MEDIUM");
        assertEquals(2, result.size());
    }

}
