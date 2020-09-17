package com.pacman.service;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;

public class Audio {

    private String track;
    private Clip clip = null;
    private FloatControl volumeC;
    private double wt;
    private boolean play_audio;

    public Audio(String track, double wt) {
        this.track = track;
        this.wt = wt;
        this.play_audio = false;
    }

    public void sound() {
        File file = new File(track);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume();
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setVolume () {
        if (wt < 0)
            wt = 0;
        if (wt > 1)
            wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min) * (float)wt + min);
    }

}
