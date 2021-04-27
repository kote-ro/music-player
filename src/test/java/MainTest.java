package test.java;

import jaco.mp3.player.MP3Player;
import main.java.controller.PlaylistController;
import main.java.controller.TrackController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    private TrackController trackController;
    private PlaylistController playlist;
    private File currentTrack;
    // download predefined playlist
    @BeforeAll
    void config(){
        this.playlist = new PlaylistController();
    }
    // play current track (5 sec) -> pause it (for 2 sec) -> continue playing (10 sec)
    @Test
    void test1(){
        currentTrack = playlist.getPlaylist().get(0);
        trackController = new TrackController(new MP3Player(currentTrack));

        trackController.playTrack();
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(5000)); // these code snippets are analogous to Thread.sleep() and make the music play
        trackController.pauseTrack();
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(2000));
        trackController.playTrack();
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10000));
        trackController.stopTrack();
    }
    // check if getNextTrack() and getPreviousTrack() are working
    @Test
    void test2(){
        currentTrack = playlist.getPlaylist().get(0);

        if (playlist.getNextTrack(currentTrack).isPresent()) {
            currentTrack = playlist.getNextTrack(currentTrack).get();
            trackController = new TrackController(new MP3Player(currentTrack));
        }

        trackController.playTrack();
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(5000));
        trackController.stopTrack();

        if (playlist.getPreviousTrack(currentTrack).isPresent()) {
            currentTrack = playlist.getPreviousTrack(currentTrack).get();
            trackController = new TrackController(new MP3Player(currentTrack));
        }

        trackController.playTrack();
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(5000));
        trackController.stopTrack();
    }
    // test addTrack(), deleteTrack(), shuffleList() and showTracksInPlaylist from PlaylistController
    @Test
    void test3(){
        File newTrack = new File("resources\\music\\Slipknot-Duality.mp3");
        
        playlist.showTracksInPlaylist();
        playlist.shuffleList();
        playlist.showTracksInPlaylist();

        playlist.addTrack(newTrack);
        playlist.showTracksInPlaylist();

        playlist.deleteTrack(3);
        playlist.showTracksInPlaylist();

        playlist.deleteTrack(200);
    }

}
