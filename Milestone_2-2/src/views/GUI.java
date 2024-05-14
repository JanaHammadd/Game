package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class GUI extends JPanel implements ActionListener{
private MainFrame mainview;
 private JLabel P1Name;
 private JTextField p1field;
 private JLabel P2Name;
 private JTextField p2field;
 private JButton nextbutton; 
 private JButton startButtom ;
 private JLabel backImage;
 private ImageIcon imageicon = new ImageIcon ("ezgif.com-gif-maker.jpg");
 
 
	public GUI(MainFrame mainview) {
		this.mainview = mainview;
		this.setLayout(null);
		
		backImage = new JLabel();
		backImage.setSize(1280 ,800);
		backImage.setIcon(imageicon);
		this.add(backImage);
		
		
		P1Name =new JLabel ("Player1 Name:");
		P1Name.setBounds(70,150,130,100);
		backImage.add(P1Name);
		P1Name.setFont(new Font("SansSerif",Font.BOLD,15));
		P1Name.setForeground(Color.red);
		
		p1field= new JTextField();
		p1field.setBounds(200,180,130,35);
		backImage.add(p1field);
		p1field.setFont(new Font("SansSerif",Font.BOLD,15));
		p1field.setForeground(Color.red);
		
		P2Name =new JLabel ("Player2 Name:");
		P2Name.setBounds(440,150,130,100);
		backImage.add(P2Name);
		P2Name.setFont(new Font("SansSerif",Font.BOLD,15));
		P2Name.setForeground(Color.red);
		
		p2field= new JTextField();
		p2field.setBounds(570,180,130,35);
		backImage.add(p2field);
		p2field.setFont(new Font("SansSerif",Font.BOLD,15));
		p2field.setForeground(Color.red);
		
		nextbutton= new JButton("Next");
		nextbutton.setBounds(300,280,300,50);
		nextbutton.addActionListener(this);
		backImage.add(nextbutton);
		//nextbutton.setFont(new Font("SansSerif",Font.BOLD,15));
		//nextbutton.setForeground(Color.red);
		nextbutton.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLUE.darker(),Color.BLACK),  	
	BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
	nextbutton.setFont(new Font("Arial", Font.BOLD, 20));
	nextbutton.setForeground(Color.RED);
		
	}
	
	//ACTION LISTENER LE NEXT BUTTON
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== nextbutton) {
			if(p1field.getText().equals("")) {
				displayErrorMessage(" player 1 fadya ya ahwal");
			}
			else if(p2field.getText().equals("")) {
				displayErrorMessage("player 2 fadya ya ahwal");
				
			}
			else {
				mainview.switchtoSelectChamp(p1field.getText(),p2field.getText());
			}
		}
	}
	public void displayErrorMessage(String string) {
		JOptionPane.showMessageDialog(this, string, "error", JOptionPane.ERROR_MESSAGE);
	}

		
	
	
	
	
	
	
	
}
