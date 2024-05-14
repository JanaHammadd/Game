package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class GameOpen extends JPanel implements ActionListener  {
	private MainFrame mainview;
	private ImageIcon imageicon = new ImageIcon ("Mama.gif");
	private JLabel backImage;
	private JButton startButtom ;
	JMenuItem miRed;
	
	
	public GameOpen(MainFrame mainview) {
		this.mainview = mainview;
		this.setLayout(null);
		backImage = new JLabel();
		backImage.setSize(1500 ,800);
		backImage.setIcon(imageicon);
		this.add(backImage);
		
		startButtom= new JButton("Start Game");
		startButtom.setBounds(450,600,300,50);
		startButtom.addActionListener(this);
		backImage.add(startButtom);
		startButtom.setFont(new Font("SansSerif",Font.BOLD,18));
		miRed = new JMenuItem("Red", 'r');
		startButtom.setForeground(Color.RED);
		startButtom.setBackground(Color.black);
		startButtom.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLUE.darker(),Color.BLACK),  	
	BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
	startButtom.setFont(new Font("Arial", Font.BOLD, 20));
	startButtom.setOpaque(false);
	 GameOpen.audio();
	 
	
	}
	
	
	public static void audio()  { 
		
		    try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Marvel Opening Theme (mp3cut.net).wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
	
	

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== startButtom) {
			
				try {
					mainview.switchtoGui(startButtom.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		
			}
	}
	
	
	}