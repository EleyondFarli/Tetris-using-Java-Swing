package tetris;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private String soundsFolder = "sounds" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav";
    private String gameoverPath = soundsFolder + "gameover.wav";
    private String fallPath = soundsFolder + "fall.wav";
    private String levelUpPath = soundsFolder + "success.wav";

    private Clip clearLineSound;
    private Clip gameoverSound;
    private Clip fallSound;
    private Clip levelUpSound;


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
            fallSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            levelUpSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        try {
            fallSound.open(AudioSystem.getAudioInputStream(new File(fallPath).getAbsoluteFile()));
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            levelUpSound.open(AudioSystem.getAudioInputStream(new File(levelUpPath).getAbsoluteFile()));
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameoverPath).getAbsoluteFile()));
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
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

    public void playFall() {
        fallSound.setFramePosition(0);
        fallSound.start();
    }

    public void playLevelUp() {
        levelUpSound.setFramePosition(0);
        levelUpSound.start();
    }
}
