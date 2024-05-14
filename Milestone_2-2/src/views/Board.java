package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Board extends JPanel implements ActionListener, ItemListener {
	private MainFrame mainview;
	private ImageIcon imageicon = new ImageIcon ("ezgif.com-gif-maker");
	private JLabel backImage;
	//p1
	
	private JButton T1;

	private JPanel board;
	private JButton[][] buttons;
	
	
	private JPanel header;
	private JLabel pname1;
	private JLabel pname2;
	//private JLabel phealth; 
	private JPanel buttonpanel;
	//private JLabel pname;
	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right; 
	private JButton move;
	private JButton attack;
	private JButton cast; 
	private JButton endturn;
	private JTextArea currentplayerChamp;
	//direction of cast ability
	private Direction dir= Direction.LEFT;
	
	//p3
	private JPanel abilitiespanel;
	private JComboBox<String> abilities;
	private JTextField xcoordinate;
	private JTextField ycoordinate;
	private JLabel Abilityname;
	private JTextArea abil;
	
	
	public Board(MainFrame mainview) {
		this.mainview= mainview;
		this.setLayout(null);
		backImage = new JLabel();
		backImage.setSize(2560,1600);
		backImage.setIcon(imageicon);
	
		//header panel fo2
		header= new JPanel();
		header.setLayout(null);
		header.setBounds(360,0,470,100);
		this.add(header); 

		pname1= new JLabel("First Player: "+ mainview.getGame().getFirstPlayer().getName());
		pname1.setBounds(0,50,160, 50);
		header.add(pname1);
		pname1.setForeground(Color.RED);
		pname1.setBackground(Color.RED);
		pname1.setFont(new Font("SansSerif", Font.CENTER_BASELINE ,17));
		
		pname2= new JLabel("Second Player: "+ mainview.getGame().getSecondPlayer().getName());
		pname2.setBounds(250,50,190, 50);
		header.add(pname2); 
		pname2.setForeground(Color.RED);
		pname2.setBackground(Color.RED);
		pname2.setFont(new Font("SansSerif", Font.CENTER_BASELINE ,17));
		
	
		/*phealth= new JLabel("Current HP: "+ mainview.getGame().getCurrentChampion().getCurrentHP());
		phealth.setBounds(550,50,160, 50);
		phealth.add(phealth);
		*/
		
		//grid for players
		board= new JPanel();
		board.setLayout(new GridLayout(5,5));
		board.setBounds(360,160,500,300);
		this.add(board);
	
		buttons= new JButton[5][5];
		
		for(int i=0; i<buttons.length;i++) {
			for(int j=0; j<buttons.length;j++) {
				buttons[i][j]= new JButton();
				buttons[i][j].addActionListener(this);
				board.add(buttons[i][j]);
			}
		}
		//panel for buttons
		buttonpanel= new JPanel();
		buttonpanel.setLayout(null);
		buttonpanel.setBounds(300,460,1000,600);
		this.add(buttonpanel);
		
		up= new JButton("Up");
		up.addActionListener(this);
		up.setBounds(260,40,50,50);
		buttonpanel.add(up);
		
	
		down= new JButton("Down");
		down.addActionListener(this);
		down.setBounds(250,140,80,50);
		buttonpanel.add(down);
		
		left= new JButton("Right");
		left.addActionListener(this);
		left.setBounds(320,80,80,50);
		buttonpanel.add(left);

		right= new JButton("Left");
		right.addActionListener(this);
		right.setBounds(160,85,90,50);
		buttonpanel.add(right);
		
		
		move= new JButton("Move");
		move.addActionListener(this);
		move.setBounds(450,40,100,30);
		buttonpanel.add(move);
		
		attack= new JButton("Attack");
		attack.addActionListener(this);
		attack.setBounds(450,80,100,30);
		buttonpanel.add(attack);
		
		cast= new JButton("Cast");
		cast.addActionListener(this);
		cast.setBounds(450,120,100,30);
		buttonpanel.add(cast);
		
		endturn= new JButton("ENDTURN");
		endturn.addActionListener(this);
		endturn.setBounds(670,50,100,100);
		endturn.setBackground(Color.RED);
		endturn.setOpaque(true);
		buttonpanel.add(endturn);
		
		
		abilitiespanel= new JPanel();
		abilitiespanel.setLayout(null);
		abilitiespanel.setBounds(0,0,300,800 );
		this.add(abilitiespanel);
	
		
		Abilityname= new JLabel("Ability Name: ");
		Abilityname.setLayout(null);
		Abilityname.setBounds(20,30,90,40);
		abilitiespanel.add(Abilityname);
		
		
		abilities= new JComboBox<String>();
		abilities.setLayout(null);
		abilities.setBounds(20,100,100,70);
		abilities.addItemListener(this);
		abilitiespanel.add(abilities);
		abilities.setForeground(Color.ORANGE);
		abilities.setBackground(Color.DARK_GRAY);
	
	/*	currentplayerChamp= new JTextArea();
		currentplayerChamp.setEditable(false);
		currentplayerChamp.setBounds(5,10,300,500);
		abilitiespanel.add(currentplayerChamp);
		*/
		
		xcoordinate= new JTextField();
		xcoordinate.setBounds(230,30,50,20);
		abilitiespanel.add(xcoordinate);
		
		ycoordinate= new JTextField();
		ycoordinate.setBounds(230,70,50,20);
		abilitiespanel.add(ycoordinate);
		
		abil= new JTextArea();
		abil.setEditable(false);
		abil.setBounds(30,190,300,500);
		abilitiespanel.add(abil);
		abil.setFont(new Font("SansSerif",Font.BOLD,14));
		//miRed = new JMenuItem("Red", 'r');
		abil.setForeground(Color.ORANGE);
		abil.setBackground(Color.DARK_GRAY);
		
		
		//infoForChamp();
		loadChampAbilityInCombo();
		getAbilitiesInfo();
		
		
		Comparable[]e= mainview.getGame().getTurnOrder().getElements();
		for(int i=mainview.getGame().getTurnOrder().size() -1;i>=0;i--) {
			Champion c= ((Champion) mainview.getGame().getTurnOrder().getElements()[i]);
			T1= new JButton(c.getName());
			buttonpanel.add(T1);
		}
		board.revalidate();
		board.repaint();
		showOnGrid();
		this.add(backImage);
		backImage.add(header);
		backImage.add(Abilityname);
		backImage.add(ycoordinate);
		backImage.add(abil);
		backImage.add(abilitiespanel);
		//backImage.add(attack); 
		//backImage.add(backImage); 
		backImage.add(board); 
		backImage.add(buttonpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//grid itself buttons
		for(int i=0; i<buttons.length;i++) {
			for(int j=0; j<buttons.length;j++) {
				if(e.getSource()==buttons[i][j]) {
					if(mainview.getGame().getBoard()[i][j] instanceof Champion) {
						Champion ch= (Champion) mainview.getGame().getBoard()[i][j];
						String info= "Current Champion Info: "+"\n"+"Name: "+ ch.getName()+ "\n"+"Attack Damage: "
						+ ch.getAttackDamage()+"\n"+"Attack Range: "+ch.getAttackRange()+ "\n"+ "Current HP: "+ch.getCurrentHP()+"\n" 
								+"Mana: "+ch.getMana()+"\n"+ "Current Action Points: "
						+ch.getCurrentActionPoints()+"\n"+"Abilities: "+"\n"+ ch.getAbilities() + "\n" +"applied effects:" 
						+ ch.getAppliedEffects() + "\n";
						if (ch instanceof AntiHero) {
							info+= "Type: ANTIHERO" ;
						}
						if ( ch instanceof Hero) {
							info+= "Type: HERO" ;
						}
						if ( ch instanceof Villain) {
							info+= "Type: VILLAIN" ;
						}
						Champion c= mainview.getGame().getFirstPlayer().getLeader();
						if(mainview.getGame().getCurrentChampion().equals(c)) {
						info+="IS LEADER";	
							
						}
						for (int x = 0; x < ch.getAppliedEffects().size(); x++) {
							info +=  ch.getAppliedEffects().get(x).getName();
							info += ch.getAppliedEffects().get(x).getDuration();
						}

						
						
						JOptionPane.showMessageDialog(this, info, "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}

	}
		}
		
		//direction buttons
		if(e.getSource()== up) {
			dir= Direction.DOWN;
		 
		}
		if(e.getSource()== down) {
			dir= Direction.UP;
		}
		if(e.getSource()== right) {
			dir= Direction.LEFT;
		}
		if(e.getSource()== left) {
			dir= Direction.RIGHT;
		}
		//button move 
		if(e.getSource()== move) {
			try {
				mainview.getGame().move(dir);
				mainview.getinfoOfChamp();
				showOnGrid();
				
				
				
				//displayboard&&displaycurrentchampion info
				
			}
			catch(NotEnoughResourcesException | UnallowedMovementException E){
				JOptionPane.showMessageDialog(this, E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				
			
			}
			
			this.revalidate();
			this.repaint();
			
		}
		
		
		//button cast ability
		if(e.getSource()== cast) {
			int index= abilities.getSelectedIndex();
			Ability ab= mainview.getGame().getCurrentChampion().getAbilities().get(index);
			try {
				if(ab.getCastArea()== AreaOfEffect.SINGLETARGET) {
					if(xcoordinate.getText().equals("")||ycoordinate.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Please enter a targetted position", "Error", JOptionPane.ERROR_MESSAGE);
						return;

					}
					int x= Integer.parseInt(xcoordinate.getText());
					int y= Integer.parseInt(ycoordinate.getText());

					mainview.getGame().castAbility(ab, x,y);
				}
				else if(ab.getCastArea()==AreaOfEffect.DIRECTIONAL) {
					mainview.getGame().castAbility(ab,dir);
				}
				else {
					mainview.getGame().castAbility(ab);
				}
				showOnGrid();
				mainview.getinfoOfChamp();
			}
			catch(NotEnoughResourcesException|AbilityUseException|CloneNotSupportedException|NumberFormatException|InvalidTargetException e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		
		//button attack
		if(e.getSource()== attack) {
		try {
			mainview.getGame().attack(dir);
			showOnGrid();
			mainview.getinfoOfChamp();
			
			
			
		}
		catch(NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException except) {
			JOptionPane.showMessageDialog(this, except.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		}
		
		if(e.getSource()==endturn) {
			mainview.getGame().endTurn();
			showOnGrid();
			mainview.getinfoOfChamp();
			//loadChampAbilityInCombo();
			
			
		}
		
		 if(mainview.getGame().checkGameOver()!=null) {
		 JOptionPane.showMessageDialog(this, "Winner: "+
		mainview.getGame().checkGameOver().getName(), "Game Over",
		  JOptionPane.INFORMATION_MESSAGE); mainview.dispose(); }
		 
	}

	
	public void showOnGrid(){
		for(int i=0; i<5;i++) {
			for(int j=0; j<5;j++) {
				if(mainview.getGame().getBoard()[i][j] instanceof Cover) {
					
					Cover cover= (Cover) mainview.getGame().getBoard()[i][j];
					buttons[i][j].setBackground(Color.DARK_GRAY);
					buttons[i][j].setForeground(Color.YELLOW);
					buttons[i][j].setText(cover.getCurrentHP()+" ");
					
				
				}else if(mainview.getGame().getBoard()[i][j] instanceof Champion) {
					
						 Champion c= (Champion) mainview.getGame().getBoard()[i][j];
						 
						if(c==mainview.getGame().getCurrentChampion()) {
							buttons[i][j].setBackground(Color.ORANGE);
							buttons[i][j].setForeground(Color.BLACK);
							buttons[i][j].setText(c.getName()+" ");
						  
						}	
						
						
						// ashelha mn el makan da
						
						else if(mainview.getGame().getFirstPlayer().getTeam().contains(c)) {
							buttons[i][j].setBackground(Color.BLACK);
							buttons[i][j].setForeground(Color.RED);
							buttons[i][j].setText(c.getName()+" ");
						}
						else {
							buttons[i][j].setBackground(Color.LIGHT_GRAY);
							buttons[i][j].setForeground(Color.RED);
							buttons[i][j].setText(c.getName()+" ");
						}
				}
						else {
							buttons [i][j].setBackground(Color.WHITE);
							buttons[i][j].setText(" ");	
						}
							
						
						}	
				
			
			}

		this.revalidate();
		this.repaint();
		
	
		
			}

	public void loadChampAbilityInCombo() {
		//abilities.removeAllItems();
		
		for (int i =0 ; i< mainview.getGame().getCurrentChampion().getAbilities().size() ;  i++) {
			abilities.addItem(mainview.getGame().getCurrentChampion().getAbilities().get(i).getName()+"\n"
		+ mainview.getGame().getCurrentChampion().getAbilities().get(i).getCurrentCooldown() +"\n" );
			}
}
	
	
	
	
	
	public String getAbilitiesInfo() {
		
		String ABinfo = "Ability Names: ";
		
		Champion current = mainview.getGame().getCurrentChampion();
		for(int i=0; i<current.getAbilities().size();i++) {
			ABinfo+= "\n"+ "Ability Name: " +current.getAbilities().get(i).getName()+ "\n"+ "Base Cooldown: "+ current.getAbilities().get(i).getBaseCooldown()+"\n"+ "Current Cooldown: "
		+ current.getAbilities().get(i).getCurrentCooldown()+"\n"+ "Cast Range: "+ current.getAbilities().get(i).getCastRange()+"\n"+ "Mana Cost: "
					+ current.getAbilities().get(i).getManaCost()+"\n"+ "Action Points Required: " +current.getAbilities().get(i).getRequiredActionPoints()+"\n"+
		"Area of Effect: "+ current.getAbilities().get(i).getCastArea()+ "\n";
			
			
		}
		return ABinfo;
		
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()== abilities) {
			for(int i=0; i<=3;i++) {
			
				abil.setText(getAbilitiesInfo());
				break;

				
			}
			revalidate();
			repaint();
		}
	}
	

}

