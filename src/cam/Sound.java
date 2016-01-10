package cam;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	private String path;
	public Clip clip;
	private AudioInputStream stream;
	

	public Sound(String path){
		this.path = path;
		load();
	}
	
	private void load(){
		try {
			clip = AudioSystem.getClip();
			stream = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
			clip.open(stream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void play(){
		clip.loop(1);
	}
	
	public void stop(){
		clip.stop();
	}

}
