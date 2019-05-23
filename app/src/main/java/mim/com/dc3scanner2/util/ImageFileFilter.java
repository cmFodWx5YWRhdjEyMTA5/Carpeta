package mim.com.dc3scanner2.util;

import java.io.File;

/**
 * Created by marcoisaac on 10/21/2016.
 */

public class ImageFileFilter {

    File file;
    private final String[] okFileExtensions = new String[]{"jpg", "png", "gif", "jpeg"};

    /**
     *
     */
    public ImageFileFilter(File newfile) {
        this.file = newfile;
    }

    public boolean accept(File file) {
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
