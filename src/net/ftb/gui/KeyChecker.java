package net.ftb.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class KeyChecker extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JTextField key;
	private JButton button;
	
	public KeyChecker() {
		setBounds(100, 100, 200, 200);
		setResizable(false);
		
		key = new JTextField("Enter key here");
		key.setBounds(50,50,100,30);
		key.setVisible(true);
		add(key);
		button = new JButton("Go!!!");
		button.setBounds(50, 100, 100, 30);
		button.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rightKey(key.getText())) {
						LaunchFrame frame = new LaunchFrame();
						frame.setVisible(true);
						setVisible(false);
					} else {
						System.exit(0);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(button);
		
	}
	
	public boolean rightKey(String key) throws IOException {
		
		URL url = new URL("http://feed-the-beast.com/validate/index.php?code=" + key);
		InputStream inputStream = url.openStream();
		InputStreamReader r = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(r);
		
		String line = reader.readLine();
		System.out.println(line);
		reader.close();
		char character = line.charAt(4);
		System.out.println(character);
		
		if(character == 'a') {
			return true;
		} else {
			return false;
		}
	}
}