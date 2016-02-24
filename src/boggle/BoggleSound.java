// Assignment Boggle.update8.UML
// Program BoggleSound
// Author Bryan Fritz
// Date Nov 26, 2015

package boggle;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BoggleSound {
	
	public BoggleSound()
	{
		
	}

	public void playSound(String s) {
		try {
			URL url;
			String file = "";
			if (s == "clear") {
				url = getClass().getResource("Siren.wav");
			} 
			else if (s == "match") {
				url = getClass().getResource("raygun.wav");
			} 			
			else if (s == "click") {
				url = getClass().getResource("Shotgun.wav");
			}
			else if (s == "gamethirty")
			{
				 url = getClass().getResource("wolf30");
			}
			else if (s == "gamefive")
			{
				 url = getClass().getResource("wolf15");
			}
			else if (s == "gameten")
			{
				 url = getClass().getResource("wolf10");
			}
			else if (s == "scream") {
				url = getClass().getResource("Scream.wav");
			} else {
				url = getClass().getResource("AirHorn.wav");
			}
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);			
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public void playWordSound(int i) {
		try {
			URL url;
			String file = "";
			if (i == 2){
				url = getClass().getResource("raygun.wav");
			} else if (i == 3) {
				url = getClass().getResource("laser");
			} else if (i == 4) {
				url = getClass().getResource("electric");
			} else if (i == 5) {
				url = getClass().getResource("Siren.wav");
			} else if (i == 6) {
				url = getClass().getResource("Scream.wav");
			} else {
				url = getClass().getResource("AirHorn.wav");
			}
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
					//AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
}

