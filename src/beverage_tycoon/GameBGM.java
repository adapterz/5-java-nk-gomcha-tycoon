package beverage_tycoon;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class GameBGM extends Thread{

    @Override
    public void run(){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/bgm/tycoonBgm.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audio);

                //소리 설정
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                //볼륨 조정
                gainControl.setValue(-10.0f);

                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
