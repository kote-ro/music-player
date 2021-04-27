package main.java.controller;

import jaco.mp3.player.MP3Player;

/**
 * Class contains methods to control current track
 */
public class TrackController {
    private MP3Player player;

    /**
     * Instantiates a new TrackController.
     * @param player the player
     */
    public TrackController(MP3Player player){
        this.player = player;
    }

    /**
     * Plays track.
     */
    public void playTrack() {
        player.play();
    }

    /**
     * Stops track.
     */
    public void stopTrack() {
        player.stop();
    }

    /**
     * Pauses track.
     */
    public void pauseTrack() {
        player.pause();
    }

}
