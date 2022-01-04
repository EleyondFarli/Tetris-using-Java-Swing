package tetris;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private String soundsFolder = "sounds" + File.separator;
    private String clearLinePath = soundsFolder + "line.wav";
    private String gameoverPath = soundsFolder + "success.wav";

    private Clip clearLineSound;
    private Clip gameoverSound;


    public AudioPlayer() {
        try {
            clearLineSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            gameoverSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        try {
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameoverPath).getAbsoluteFile()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playClearLine() {
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }

    public void playGameover() {
        gameoverSound.setFramePosition(0);
        gameoverSound.start();
    }
}
