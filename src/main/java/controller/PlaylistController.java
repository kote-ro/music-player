package main.java.controller;

import main.java.util.FileDownloader;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

/**
 * Class contains methods to control playlist
 */
public class PlaylistController {
    private ArrayList<File> playlist; // here is not a polymorphic reference to List because not all List implementations support add/remove operations
    private FileDownloader fileDownloader;
    private final static Logger LOGGER = Logger.getLogger(PlaylistController.class.getName());

    /**
     * Instantiates a new PlaylistController with some used inside objects
     */
    public PlaylistController(){
        this.fileDownloader = new FileDownloader();
        this.playlist = fileDownloader.downloadMP3Files(new File("src/resources/music"));
    }

    /**
     * Gets playlist.
     * @return the playlist
     */
    public List<File> getPlaylist() {
        return playlist;
    }

    /**
     * Sets playlist.
     * @param playlist the playlist
     */
    public void setPlaylist(ArrayList<File> playlist) {
        this.playlist = playlist;
    }

    /**
     * Adds track to playlist.
     * @param track the track
     */
    public void addTrack(File track){
        this.playlist.add(track);
    }

    /**
     * Deletes track from playlist.
     * @param index the index
     */
    public void deleteTrack(int index){
        if(index >= 0 && playlist.size() >= index+1){
            this.playlist.remove(index);
        }else{
            LOGGER.info("You entered wrong index, try again");
        }
    }

    /**
     * Gets next track.
     * Optional used to prevent NullPointerException
     * @param currentTrack the current track
     * @return the next track
     */
    public Optional<File> getNextTrack(File currentTrack){
        int index = playlist.indexOf(currentTrack);
        if (index < 0 || index+1 == playlist.size()) {
            return Optional.empty();
        }
        return Optional.of(playlist.get(index + 1));
    }

    /**
     * Gets previous track optional.
     * @param currentTrack the current track
     * @return the previous track
     */
    public Optional<File> getPreviousTrack(File currentTrack){
        int index = playlist.indexOf(currentTrack);
        if (index < 0 || index+1 == playlist.size()) {
            return Optional.empty();
        }
        return Optional.of(playlist.get(index - 1));
    }

    /**
     * Shuffles list.
     */
    public void shuffleList(){
        Collections.shuffle(this.playlist, new Random());
    }

    /**
     * Shows tracks in playlist.
     */
    public void showTracksInPlaylist(){
        for(File track : playlist){
            LOGGER.info(track.getName());
        }
        LOGGER.info("******");
    }
}
