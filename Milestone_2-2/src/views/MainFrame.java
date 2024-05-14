package views;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.world.Champion;

public class MainFrame extends JFrame {
	private Game game;

	private GUI gui;
	private SelectChamp selection;
	private String name1;
	private String name2;
	private GameOpen start;
	private JLabel startImage;
	private Board board;

	public Game getGame() {
		return game;
	}

	public MainFrame() {
		startImage = new JLabel();
		start = new GameOpen(this);
		this.getContentPane().add(start);
		this.setTitle("Marvel");
		this.setSize(2560, 1600);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		start.setOpaque(false);
		start.setBackground(new Color(0, 0, 0, 0));
		startImage = new JLabel("Start Game");
	}

	public void switchtoSelectChamp(String s1, String s2) {
		name1 = s1;
		name2 = s2;
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");

			this.setSize(1280, 800);
			this.remove(gui);
			System.out.println(123);

			selection = new SelectChamp(this);
			this.getContentPane().add(selection);
			selection.setBounds(0, 0, 2560, 1600);
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		MainFrame Mframe = new MainFrame();

	}

	public String[] ChampionsList() {
		ArrayList<Champion> ch = Game.getAvailableChampions();
		String[] names = new String[ch.size() + 1];
		for (int i = 0; i < ch.size(); i++) {
			names[i] = ch.get(i).getName();
		}
		return names;
	}

	public String getInfo(int champ) {

		ArrayList<Champion> champs = Game.getAvailableChampions();
		Champion ch = champs.get(champ);
		String string = "name:" + ch.getName() + "\n" + "attackDamage:" + ch.getAttackDamage() + "\n" + "attackRange:"
				+ ch.getAttackRange() + "\n" + "health:" + ch.getCurrentHP() + "\n" + "Mana:" + ch.getMana() + "\n"
				+ "Abilities:" + ch.getAbilities();
		for (int i = 1; i < ch.getAbilities().size(); i++) {
			string = string + (i + 1) + ")" + ch.getAbilities().get(i).getName() + "\n";
		}
		this.validate();
		this.repaint();
		return string;
	}

	public void switchtoGui(String string) throws IOException {
		name1 = string;
		this.setSize(1280, 800);
		this.remove(start);
		System.out.println(133);
		gui = new GUI(this);
		this.getContentPane().add(gui);
		gui.setBounds(0, 0, 2560, 1600);
		this.revalidate();
		this.repaint();

	}

	public void switchtoboard(int a, int b, int c, int f, int x, int y, int z, int j) {
		this.remove(selection);
		this.setSize(1500, 800);
		Player player1 = new Player(name1);
		Player player2 = new Player(name2);

		getTeamofPlayer1(player1, a, b, c);
		setTeamLeader1(f);

		getTeamofPlayer2(player2, x, y, z);
		setTeamLeader2(j);

		game = new Game(player1, player2);

		board = new Board(this);
		this.getContentPane().add(board);

		board.setBounds(0,0,1500,800);
		this.validate();
		this.repaint();

	}

	public void getTeamofPlayer1(Player player1, int a, int b, int c) {
		Champion Champ1 = Game.getAvailableChampions().get(a);
		Champion Champ2 = Game.getAvailableChampions().get(b);
		Champion Champ3 = Game.getAvailableChampions().get(c);
		player1.getTeam().add(Champ1);
		player1.getTeam().add(Champ2);
		player1.getTeam().add(Champ3);

	}

	public void setTeamLeader1(int f) {
		Player player = new Player(name1);
		Champion leader = Game.getAvailableChampions().get(f);
		player.setLeader(leader);
	}

	public void getTeamofPlayer2(Player player2, int x, int y, int z) {
		Champion Champ1 = Game.getAvailableChampions().get(x);
		Champion Champ2 = Game.getAvailableChampions().get(y);
		Champion Champ3 = Game.getAvailableChampions().get(z);
		player2.getTeam().add(Champ1);
		player2.getTeam().add(Champ2);
		player2.getTeam().add(Champ3);

	}

	public void setTeamLeader2(int j) {
		Player player = new Player(name2);
		Champion leader = Game.getAvailableChampions().get(j);
		player.setLeader(leader);
	}

	public String getinfoOfChamp() {


		String info = "Current Player: ";
		Player first = game.getFirstPlayer();
		Champion current = game.getCurrentChampion();
		if (first.getTeam().contains(current)) {
			info = info + game.getFirstPlayer().getName() + "\n\n";
		} else {
			info = info + game.getSecondPlayer().getName() + "\n\n";
		}
		info = info + "Current Champion Information: " + "\n" + "Name: " + current.getName() + "\n" + "Attack Damage: "
				+ current.getAttackDamage() + "\n" + "Attack Range: " + current.getAttackRange() + "\n"
				+ "Health Points: " + current.getCurrentHP() + "\n" + "Mana: " + current.getMana() + "\n" 
				+ "Current Action Points: " + current.getCurrentActionPoints() + "\n\n" + "Abilities: " + "\n"+
				"applied effecs:" + current.getAppliedEffects() + "\n" + "Type:" + current.getCondition() + "\n" ;

		for (int i = 1; i < current.getAbilities().size(); i++) {
			Ability ability = current.getAbilities().get(i);
			info = info + (i + 1) + ")" + ability.getName() + ": \n";
			info = info + "Mana Cost  :  " + ability.getManaCost() + "\n";
			info = info + "Cool Down  :  " + ability.getRequiredActionPoints() + "\n";
			info = info + "Area of Effect  :  " + ability.getCastArea() + "\n";
			info = info + "action points:" + ability.getRequiredActionPoints() +"\n";
			
			
		}
		return info;
	}

}
