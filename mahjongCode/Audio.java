package mahjongCode;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;



public class Audio
{
	private AudioClip clip1; // play tile
	private AudioClip clip2; // Gong
	private AudioClip clip3; // win John Cena
	private AudioClip clip4; // Pong
	private AudioClip clip5; // Song
	private AudioClip clip6; // Draw
	
	public Audio()
	{
		clip1 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundPlay.wav"));
		clip2 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundGong.wav"));
		clip3 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundWin.wav"));
		clip4 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundPong.wav"));
		clip5 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundSong.wav"));
		clip6 = Applet.newAudioClip(this.getClass().getResource("/res/sound/soundDraw.wav"));
	}
	
	public void playClip(String cond) throws MalformedURLException
	{
		if(cond.equals("play")) clip1.play();
		else if(cond.equals("gong")) clip2.play();
		else if(cond.equals("win")) clip3.loop();
		else if(cond.equals("pong")) clip4.play();
		else if(cond.equals("song")) clip5.play();
		else if(cond.equals("draw")) clip6.play();
		
	}
	
	public void stopClip()
	{
		clip3.stop();
	}
}