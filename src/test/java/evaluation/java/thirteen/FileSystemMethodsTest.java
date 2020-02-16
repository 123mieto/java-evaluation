package evaluation.java.thirteen;

import org.junit.jupiter.api.Test;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemMethodsTest {

    @Test
    public void createNewFileSystemWFromZipFile() throws Exception {
        List<FileStore> fileStores = new ArrayList<>();

        FileSystem createdFs = FileSystems.newFileSystem(Paths.get(getClass().getResource("/some_dir.zip").toURI()));

        createdFs.getFileStores().iterator().forEachRemaining(fileStores::add);
        //one file store - created out of zip
        assertEquals(1, fileStores.size());
    }
}
