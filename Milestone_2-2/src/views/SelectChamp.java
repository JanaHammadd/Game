package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class SelectChamp extends JPanel implements ItemListener, ActionListener {

	private MainFrame mainview;
	private JLabel firstP;
	private JLabel secondP;
	
	private JComboBox<String> ch1;
	private JComboBox<String> ch2;
	private JComboBox<String> ch3;
	private JComboBox<String> ch4;
	private JComboBox<String> ch5;
	private JComboBox<String> ch6;
	
	private JLabel leader1;
	private JLabel leader2;
	
	private JTextArea descr;
	private JButton gamestart ;
	private JLabel backImage;
	
    private ImageIcon imageicon = new ImageIcon ("rsz_2hekayat.jpg");


	public SelectChamp(MainFrame mainview) {
		this.mainview=mainview;
		this.setLayout(null);

		backImage = new JLabel();
		backImage.setSize(1280 ,800);
		backImage.setIcon(imageicon);
		this.add(backImage);
	
		
		firstP= new JLabel("First Player");
		firstP.setBounds(200,30,150,25);
		backImage.add(firstP);
		firstP.setForeground(Color.WHITE);
		firstP.setBackground(Color.BLACK);
		firstP.setFont(new Font("SansSerif", Font.BOLD ,17));
		
		
		secondP= new JLabel("Second Player");
		secondP.setBounds(600,30,150,25);
		backImage.add(secondP);
		secondP.setForeground(Color.WHITE);
		secondP.setBackground(Color.BLACK);
		secondP.setFont(new Font("SansSerif", Font.BOLD ,17));
		
		
		
		ch1= new JComboBox<String>(mainview.ChampionsList());
		ch1.setBounds(150,100,150,25);
		ch1.addItemListener(this);
		backImage.add(ch1);
		//UIManager.put("ch1.background", Color.RED);
		
		
		
		
		
		
		
		ch2= new JComboBox<String>(mainview.ChampionsList());
		ch2.setBounds(150,170,150,25);
		ch2.addItemListener(this);
		backImage.add(ch2);
		
		ch3= new JComboBox<String>(mainview.ChampionsList());
		ch3.setBounds(150,240,150,25);
		ch3.addItemListener(this);
		backImage.add(ch3);
		
		ch4= new JComboBox<String>(mainview.ChampionsList());
		ch4.setBounds(590,100,150,25);
		ch4.addItemListener(this);
		backImage.add(ch4);
		
		ch5= new JComboBox<String>(mainview.ChampionsList());
		ch5.setBounds(590,170,150,25);
		ch5.addItemListener(this);
		backImage.add(ch5);
		
		ch6= new JComboBox<String>(mainview.ChampionsList());
		ch6.setBounds(590,240,150,25);
		ch6.addItemListener(this);
		backImage.add(ch6);
		
		leader1= new JLabel("is leader");
		leader1.setBounds(320,100,150,25);
		backImage.add(leader1);
		leader1.setFont(new Font("SansSerif",Font.BOLD,14));
		//miRed = new JMenuItem("Red", 'r');
		leader1.setForeground(Color.RED);
		leader1.setBackground(Color.BLACK);
		
		
		leader2= new JLabel("is leader");
		leader2.setBounds(750,100,150,25);
		backImage.add(leader2);
		leader2.setFont(new Font("SansSerif",Font.BOLD,14));
		//miRed = new JMenuItem("Red", 'r');
		leader2.setForeground(Color.RED);
		leader2.setBackground(Color.BLACK);
		
		
		descr = new JTextArea();
		descr.setBounds(900,140,200,245);
		descr.setEditable(false);
		backImage.add(descr);
		descr.setFont(new Font("SansSerif",Font.BOLD,14));
		//miRed = new JMenuItem("Red", 'r');
		descr.setForeground(Color.RED);
		descr.setBackground(Color.BLACK);
		
		
		
		
		gamestart= new JButton("Start Game");
		gamestart.setBounds(400,400,300,50);
		backImage.add(gamestart);
		gamestart.addActionListener(this);
		gamestart.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLUE.darker(),Color.BLACK),  	
	BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
	gamestart.setFont(new Font("Arial", Font.BOLD, 20));
	gamestart.setForeground(Color.RED);
		gamestart.setOpaque(false);
		
		
	}

	 
	
	public void itemStateChanged(ItemEvent e) {
	if (e.getSource()==ch1)
		descr.setText(mainview.getInfo(ch1.getSelectedIndex()));
	if (e.getSource()==ch2)
		descr.setText(mainview.getInfo(ch2.getSelectedIndex()));
	if (e.getSource()==ch3)
		descr.setText(mainview.getInfo(ch3.getSelectedIndex()));
	if (e.getSource()==ch4)
		descr.setText(mainview.getInfo(ch4.getSelectedIndex()));
	if (e.getSource()==ch5)
		descr.setText(mainview.getInfo(ch5.getSelectedIndex()));
	if (e.getSource()==ch6)
		descr.setText(mainview.getInfo(ch6.getSelectedIndex()));
	
	this.validate();
	this.repaint();
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
//	ChampionIndex[]= {ch1.getSelectedIndex(),ch2.getSelectedIndex(),ch3.getSelectedIndex(),
	//			ch4.getSelectedIndex(), ch5.getSelectedIndex(), ch6.getSelectedIndex();
		
		
		if(e.getSource()== gamestart) {
			if(ch1.getSelectedIndex()==ch2.getSelectedIndex()||ch1.getSelectedIndex()==ch3.getSelectedIndex()||ch1.getSelectedIndex()==ch4.getSelectedIndex()||ch1.getSelectedIndex()==ch5.getSelectedIndex()
					||ch1.getSelectedIndex()==ch6.getSelectedIndex()) {
				this.popUp("hathareb nafsak wala eh hewarak");
			}
			if (ch2.getSelectedIndex()== ch1.getSelectedIndex() || ch2.getSelectedIndex()==ch3.getSelectedIndex()|| ch2.getSelectedIndex()==ch4.getSelectedIndex()|| ch2.getSelectedIndex()==ch5.getSelectedIndex() || ch2.getSelectedIndex()==ch6.getSelectedIndex()) {
				this.popUp("hathareb nafsak wala eh hewarak");

			}
			if (ch3.getSelectedIndex()== ch1.getSelectedIndex() || ch3.getSelectedIndex()==ch2.getSelectedIndex() || ch3.getSelectedIndex()== ch4.getSelectedIndex() || ch3.getSelectedIndex()==ch5.getSelectedIndex() || ch3.getSelectedIndex()==ch6.getSelectedIndex()) {
				this.popUp("hathareb nafsak wala eh hewarak");
				
			}
			if (ch4.getSelectedIndex()== ch1.getSelectedIndex() || ch4.getSelectedIndex()== ch2.getSelectedIndex() || ch4.getSelectedIndex()==ch3.getSelectedIndex() || ch4.getSelectedIndex()==ch5.getSelectedIndex() || ch4.getSelectedIndex()==ch6.getSelectedIndex()) {
				this.popUp("hathareb nafsak wala eh hewarak");

			}
			if (ch5.getSelectedIndex()==ch1.getSelectedIndex()|| ch5.getSelectedIndex()==ch2.getSelectedIndex()|| ch5.getSelectedIndex()== ch3.getSelectedIndex() || ch5.getSelectedIndex() == ch4.getSelectedIndex()|| ch5.getSelectedIndex()==ch6.getSelectedIndex() ) {
				this.popUp("hathareb nafsak wala eh hewarak");
			}
			if (ch6.getSelectedIndex()==ch1.getSelectedIndex()|| ch6.getSelectedIndex()==ch2.getSelectedIndex() ||ch6.getSelectedIndex()==ch3.getSelectedIndex() || ch6.getSelectedIndex()== ch4.getSelectedIndex() || ch6.getSelectedIndex()== ch5.getSelectedIndex()) {
				this.popUp("hathareb nafsak wala eh hewarak");

			}
			else if (e.getSource()==gamestart ) {

				mainview.switchtoboard(ch1.getSelectedIndex(),ch2.getSelectedIndex(),ch3.getSelectedIndex(),ch1.getSelectedIndex(), ch4.getSelectedIndex(), ch5.getSelectedIndex(), ch6.getSelectedIndex(), ch4.getSelectedIndex());
			
		}
			
		this.revalidate();
		this.repaint();
					
	}
	}
	public void popUp(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

	

}