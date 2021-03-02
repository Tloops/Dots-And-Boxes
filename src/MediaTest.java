
import javafx.embed.swing.*;
import javafx.scene.media.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class MediaTest extends JFrame implements ActionListener {
	MediaPlayer _mediaPlayer;
	JButton pause_play, stop, next;
	JPanel jp;
	ArrayList<File> file;
	int indexOfFile = 0;
	boolean handle;
	public MediaTest(String[] file) throws InterruptedException {
		this.file = new ArrayList<File>();
		for(String s:file) {
			this.file.add(new File(s));
		}
		Media _media = new Media(this.file.get(indexOfFile).toURI().toString());
		indexOfFile = indexOfFile+1 >= this.file.size() ? 0:indexOfFile+1;
		JFXPanel fxPanel = new JFXPanel();
		// ��������һ�У�����Ҫ��MediaPlayer����֮ǰ
		_mediaPlayer = new MediaPlayer(_media);
		pause_play = new JButton();
		pause_play.setText("Pause");
		pause_play.setActionCommand("pause_play");
		pause_play.addActionListener(this);
		pause_play.setBackground(Color.CYAN);
		stop = new JButton();
		stop.setText("Stop");
		stop.setActionCommand("stop");
		stop.addActionListener(this);
		stop.setBackground(new Color(190,110,190));
		next = new JButton();
		next.setText("NextMusic");
		next.setActionCommand("next");
		next.addActionListener(this);
		next.setBackground(Color.GREEN);
		jp = new JPanel();
		jp.setLayout(new GridLayout(1,0));
		jp.add(pause_play);
		jp.add(stop);
		jp.add(next);
		this.add(jp);
		this.pack();
		this.setLocation(600, 250);
		this.setVisible(true);
		_mediaPlayer.setAutoPlay(true);
		handle = false;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("pause_play")) {
			if (_mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED)
					|| _mediaPlayer.getStatus().equals(MediaPlayer.Status.READY)) {
				_mediaPlayer.play();
				handle = false;
				pause_play.setText("Pause");
			}
		if (_mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
			handle = true;
			_mediaPlayer.pause();
			pause_play.setText("Play");
		}
		}
		
		if(e.getActionCommand().equals("stop")) {
			_mediaPlayer.stop();
			handle = true;
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("next")) {
			handle = true;
			_mediaPlayer.dispose();
			_mediaPlayer = new MediaPlayer(new Media(file.get(indexOfFile).toURI().toString()));
			indexOfFile = indexOfFile+1 >= file.size() ? 0:indexOfFile+1;
			_mediaPlayer.play();
			pause_play.setText("Pause");
			handle = false;
		}
	}

	public static void main(String[] args) {
		try {
			MediaTest m = new MediaTest(new String[] {"victory.mp3"});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
