package br.quizeducativo.util;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SomUtils {
    public static void tocarSom(String caminho) {
        try{
            URL url = SomUtils.class.getResource(caminho);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
         } catch (UnsupportedAudioFileException |
             IOException |
             LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void tocarAcerto() {
        tocarSom("/sons/acerto.wav");
    }

    public static void tocarErro() {
        tocarSom("/sons/erro.wav");
    }

    public static void tocarVitoria() {
        tocarSom("/sons/vitoria.wav");
    }

    public static void tocarDerrota() {
        tocarSom("/sons/derrota.wav");
    }
}
