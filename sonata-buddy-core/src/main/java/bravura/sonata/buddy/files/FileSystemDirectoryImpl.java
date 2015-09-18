package bravura.sonata.buddy.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by tszymanski on 12/09/2015.
 */
class FileSystemDirectoryImpl implements Directory {

    private final File root;

    FileSystemDirectoryImpl(String root) {
        this.root = new File(root);
        if (!this.root.exists() || !this.root.isDirectory()) {
            this.root.mkdir();
        }
    }

    @Override
    public InputStream getAsInputStream(String name) throws FileNotFoundException {
        File file = new File(root, name);
        return new FileInputStream(file);
    }
}
