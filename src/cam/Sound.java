package cam;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements LineListener{
	
	private String path;
	public Clip clip;
	private AudioInputStream stream;
	public boolean playing = false;
	

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
		clip.start();
		clip.addLineListener(this);
	}
	
	public void loop(){
		clip.loop(15);
	}
	
	public void stop(){
		clip.stop();
	}

	@Override
	public void update(LineEvent e) {
		LineEvent.Type type = e.getType();
		if(type == LineEvent.Type.OPEN){
			playing = true;
		}else if(type == LineEvent.Type.CLOSE){
			playing = false;
		}else if(type == LineEvent.Type.START){
			playing = true;
		}else if(type == LineEvent.Type.STOP){
			playing = false;
		}
	}

}
