package main.java.util;

import java.io.File;
import java.util.*;

/**
 * Util download class
 */
public class FileDownloader {

    /**
     * Downloads mp3 files
     * @param folder the folder with mp3 files
     * @return the playlist
     */
    public ArrayList<File> downloadMP3Files(final File folder){
        try {
            return new ArrayList<>(Arrays.asList(folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"))));
        }catch (RuntimeException exception){ // handling the exception related to the lack of access rights to the folder
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }
}


