// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class BoggleFrame extends JFrame {

	ScoreManager myScoresMan = new ScoreManager();
	private JPanel gamePanel;
	private JPanel wordListArea;
	private JPanel progressBar;
	private JPanel gameArea;
	private JPanel wordInputArea;
	private JPanel wordListPanel;
	private JPanel startScreenPanel;
	private JButton btnNewClear = new JButton("Clear");
	private JLabel lblScore;
	private JLabel lblEnterWord;
	private JTextArea wordListTextArea;
	private JTextArea txtrHighScore;
	private JTextField textField;
	private JButton btnStartGame;
	private BoggleButton buttons[][];
	private int previousButtonRow;
	private int previousButtonCol;
	private JScrollPane scroll;
	private JProgressBar progressStatusBar;
	private String currentWord = "";
	private int score;
	private final Border clickBorder = new LineBorder(Color.RED, 5);
	private final Border unclickborder = new LineBorder(Color.BLACK, 5);
	private String theNames;
	private Set<String> words = new HashSet<String>();
	private Set<String> wordsDisplay = new HashSet<String>();
	private JLabel lblHighScores;
	private JLabel Logo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel howToScreen;
	private JLabel logo2;
	private BoggleSound boggleSound = new BoggleSound();
	private JButton exitbutton;

	public BoggleFrame() {

		// Card Layout to hold both main screen and game screen and set the size
		// of the screen
		getContentPane().setLayout(new CardLayout(0, 0));
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// Main screen that has start game button, high score and name of the
		// game
		startScreenPanel = new JPanel();
		startScreenPanel.setForeground(Color.BLACK);
		startScreenPanel.setBackground(new Color(30, 144, 255));
		getContentPane().add(startScreenPanel, "name_1406241583084739000");
		startScreenPanel.setLayout(null);

		// Start button to start the game;
		btnStartGame = new JButton("START GAME");
		btnStartGame.setFont(new Font("Riffic", Font.BOLD, 30));
		btnStartGame.setBounds(14, 289, 357, 86);
		startScreenPanel.add(btnStartGame);

		// Text area to display HighScore
		txtrHighScore = new JTextArea();
		txtrHighScore.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtrHighScore.setLineWrap(false);
		txtrHighScore.setEditable(false);
		txtrHighScore.setFont(new Font("American Typewriter", Font.BOLD, 20));
		txtrHighScore.setBounds(400, 68, 184, 352);
		/////////
		myScoresMan.sortHighScore(myScoresMan, txtrHighScore);
		txtrHighScore.setText(myScoresMan.getScoreName());
		startScreenPanel.add(txtrHighScore);

		// label that holds high score
		lblHighScores = new JLabel("HIGH SCORES");
		lblHighScores.setFont(new Font("Riffic", Font.BOLD, 20));
		lblHighScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScores.setBounds(400, 41, 184, 23);
		startScreenPanel.add(lblHighScores);

		Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(BoggleFrame.class.getResource("/boggle/Desktop/GameLogo.png")));
		Logo.setBounds(-119, -48, 618, 281);
		startScreenPanel.add(Logo);

		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		rdbtnEasy.setFont(new Font("Riffic", Font.PLAIN, 11));
		buttonGroup.add(rdbtnEasy);
		rdbtnEasy.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEasy.setBounds(30, 259, 109, 23);
		startScreenPanel.add(rdbtnEasy);

		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		rdbtnMedium.setFont(new Font("Riffic", Font.PLAIN, 11));
		buttonGroup.add(rdbtnMedium);
		rdbtnMedium.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMedium.setBounds(141, 259, 109, 23);
		startScreenPanel.add(rdbtnMedium);

		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		rdbtnHard.setFont(new Font("Riffic", Font.PLAIN, 11));
		buttonGroup.add(rdbtnHard);
		rdbtnHard.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnHard.setBounds(252, 259, 109, 23);
		startScreenPanel.add(rdbtnHard);

		// How to play button
		JButton btnHowToPlay = new JButton("How To Play?");
		btnHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startScreenPanel.setVisible(false);
				howToScreen.setVisible(true);
			}
		});
		btnHowToPlay.setFont(new Font("Riffic", Font.PLAIN, 11));
		btnHowToPlay.setBounds(205, 229, 109, 23);
		startScreenPanel.add(btnHowToPlay);
		
		exitbutton = new JButton("Exit Game");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGme();
			}
		});
		exitbutton.setFont(new Font("Riffic", Font.PLAIN, 11));
		exitbutton.setBounds(86, 229, 109, 23);
		startScreenPanel.add(exitbutton);

		btnStartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				startScreenPanel.setVisible(false);
				gamePanel.setVisible(true);
				score = 0;
				lblScore.setText("SCORE: " + score);
				wordListTextArea.setText("");
				if (rdbtnHard.isSelected()) {
					Task task = new Task(10);
					task.start();
					boggleSound.playSound("gameten");
				}
				if (rdbtnMedium.isSelected()) {
					Task task = new Task(15);
					task.start();
					boggleSound.playSound("gamefive");
				}
				if (rdbtnEasy.isSelected()) {
					boggleSound.playSound("gamethirty");
					Task task = new Task(30);
					task.start();
					
				}		
			}
		});

		// Actual game screen
		gamePanel = new JPanel();
		getContentPane().add(gamePanel, "name_1406249004660584000");
		gamePanel.setLayout(new BorderLayout(0, 0));

		// Holds the panel for list entered by player and score while game is
		// running
		wordListArea = new JPanel();
		wordListArea.setBackground(new Color(30, 144, 255));
		wordListArea.setPreferredSize(new Dimension(200, 350));
		gamePanel.add(wordListArea, BorderLayout.WEST);
		wordListArea.setLayout(null);

		// Label for holding score during gameplay
		lblScore = new JLabel("  SCORE: " + score);
		lblScore.setFont(new Font("Riffic", Font.BOLD, 16));
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblScore.setBounds(6, 30, 184, 50);
		wordListArea.add(lblScore);

		// Actual panel that holds the word list
		wordListPanel = new JPanel();
		wordListPanel.setBorder(
				new TitledBorder(null, "WordList ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		wordListPanel.setBounds(6, 80, 188, 337);
		wordListArea.add(wordListPanel);
		wordListPanel.setLayout(new GridLayout(1, 0, 0, 0));

		// Text area where entered words are saved
		wordListTextArea = new JTextArea();
		wordListTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		wordListPanel.add(wordListTextArea);

		// provides Scrollbar to the wordlist text area
		scroll = new JScrollPane(wordListTextArea);
		wordListPanel.add(scroll);

		// Progress bar panel to hold the status bar
		progressBar = new JPanel();
		progressBar.setBackground(new Color(30, 144, 255));
		progressBar.setPreferredSize(new Dimension(10, 30));
		gamePanel.add(progressBar, BorderLayout.NORTH);

		// Label that displays time left
		JLabel lblTimeLeft = new JLabel("TIME LEFT:");
		lblTimeLeft.setFont(new Font("Riffic", Font.PLAIN, 11));
		progressBar.add(lblTimeLeft);

		// progress bar to keep track of time
		progressStatusBar = new JProgressBar();
		progressStatusBar.setString("100 seconds");
		progressStatusBar.setStringPainted(true);
		progressStatusBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		progressBar.add(progressStatusBar);
		progressStatusBar.setValue(100);

		// Panel for actual game
		gameArea = new JPanel();
		gameArea.setBackground(new Color(30, 144, 255));
		gameArea.setPreferredSize(new Dimension(200, 200));
		gamePanel.add(gameArea, BorderLayout.CENTER);
		gameArea.setLayout(new GridLayout(4, 4, 5, 5));
		gameArea.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// build Grid builds Array of 16 labels , board where the random letter
		// are generated , words holds all the words from lexicon
		words = BoggleUtility.get_words();
		buildGrid();

		// Panel to hold Enter Word label, textfield and done button
		wordInputArea = new JPanel();
		wordInputArea.setPreferredSize(new Dimension(10, 25));
		gamePanel.add(wordInputArea, BorderLayout.SOUTH);
		wordInputArea.setLayout(null);

		// Enterword label
		lblEnterWord = new JLabel("Your Word:");
		lblEnterWord.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnterWord.setFont(new Font("American Typewriter", Font.BOLD, 16));
		lblEnterWord.setBounds(6, 0, 140, 20);
		wordInputArea.add(lblEnterWord);

		// Textfield for players to display clicked word
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(171, 1, 300, 20);
		wordInputArea.add(textField);
		textField.setColumns(10);

		howToScreen = new JPanel();
		howToScreen.setBackground(new Color(30, 144, 255));
		getContentPane().add(howToScreen, "name_456615208119850");
		howToScreen.setLayout(null);

		logo2 = new JLabel("");
		logo2.setBounds(-23, -52, 679, 271);
		logo2.setIcon(new ImageIcon(BoggleFrame.class.getResource("/boggle/Desktop/GameLogo.png")));
		howToScreen.add(logo2);

		JTextPane txtpnMatchYourWits = new JTextPane();
		txtpnMatchYourWits.setBackground(new Color(30, 144, 255));
		txtpnMatchYourWits.setFont(new Font("Riffic", Font.PLAIN, 25));
		txtpnMatchYourWits.setText(
				"Match your wits against the computer by finding "
				+ "words in the random jumble of characters.  "
				+ "You can only link words by selecting adjacent characters.  "
				+ "When you find a word, you get points! ");
		txtpnMatchYourWits.setBounds(76, 218, 433, 200);
		howToScreen.add(txtpnMatchYourWits);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howToScreen.setVisible(false);
				startScreenPanel.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Riffic", Font.PLAIN, 11));
		btnBack.setBounds(245, 438, 89, 23);
		howToScreen.add(btnBack);
	}

	// Creating 16 boards
	private void buildGrid() {
		buttons = new BoggleButton[4][4];
		BoggleButton.init(buttons);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i][j] = new BoggleButton(i, j);
				buttons[i][j].setText(new BoggleDice().rollCube().toUpperCase());
				buttons[i][j].setBorder(unclickborder);
				final int buttonRow = i;
				final int buttonColumn = j;
				new BoggleButtonMouseListener(buttonRow, buttonColumn);
				gameArea.add(buttons[i][j]);
			}
		}
		btnNewClear.setFont(new Font("Riffic", Font.PLAIN, 11));
		btnNewClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAndShuffle(1, 1);
			}
		});
		progressBar.add(btnNewClear);
	}
	private void endGme()
	{
		JOptionPane.showMessageDialog(null, "Exiting Game!");
		System.exit(0);
	}

	private class Task extends Thread {

		int seconds;

		Task(int seconds) {
			this.seconds = seconds;
		}

		public void run() {

			for (int i = seconds; i >= 0; i--) {
				final int progress = i;
				final int counter = i;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						progressStatusBar.setString("" + progress + " seconds");
						progressStatusBar.setValue(progress);
						if (counter <= 0) {
							boggleSound.playSound("AirHorn.wav");
							gameOver();
						}
					}
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private String getwordsDisplay() {
		String return_value = "";
		for (String s : wordsDisplay) {
			return_value += s.toString() + "\n";
		}

		return return_value;
	}

	private void gameOver() {

		theNames = JOptionPane.showInputDialog(null,
				"GameOver.\n You scored: " + score + "\n Please Enter your name: ");
		myScoresMan.addScore(theNames, score);
		startScreenPanel.setVisible(true);
		gamePanel.setVisible(false);
		wordsDisplay.clear();
		currentWord = "";
		myScoresMan.sortHighScore(myScoresMan, txtrHighScore);
		txtrHighScore.setText(myScoresMan.getScoreName());
		textField.setText(currentWord);
		buttons[1][1].shuffle();
	}

	private boolean checkWord(String currentWord) {
		if (words.contains(currentWord)) {
			return true;
		} else {
			return false;
		}
	}

	private void clearAndShuffle(final int buttonRow, final int buttonColumn) {
		boggleSound.playSound("clear");
		currentWord = "";
		textField.setText(currentWord);			
		buttons[buttonRow][buttonColumn].shuffle();
		buttons[buttonRow][buttonColumn].setEnabled(true);
		buttons[buttonRow][buttonColumn].setBorder(unclickborder);
		buttons[buttonRow][buttonColumn].setOpaque(true);
	}
	private void buttonSelected(final int buttonRow, final int buttonColumn) {
		boggleSound.playSound("click");
		currentWord += buttons[buttonRow][buttonColumn].getText();
		textField.setText(currentWord);
		buttons[buttonRow][buttonColumn].setEnabled(false);
		buttons[buttonRow][buttonColumn].setBorder(clickBorder);
		buttons[buttonRow][buttonColumn].setOpaque(false);	
		buttons[buttonRow][buttonColumn].setSelectedButtonTrue();
		previousButtonRow = buttons[buttonRow][buttonColumn].getRow();
		previousButtonCol = buttons[buttonRow][buttonColumn].getCol();
	}
	private class BoggleButtonMouseListener implements MouseListener 
	{
		private int buttonRow;
		private int buttonColumn;
		public BoggleButtonMouseListener(final int buttonRow, final int buttonColumn)
		{
			this.buttonRow = buttonRow;
			this.buttonColumn = buttonColumn;
			buttons[buttonRow][buttonColumn].addMouseListener(this);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (buttons[buttonRow][buttonColumn].isValidButton(previousButtonRow, 
					previousButtonCol)) 
			{
				if (buttons[buttonRow][buttonColumn].getState() == true) 
				{
					buttonSelected(buttonRow, buttonColumn);								
					buttons[buttonRow][buttonColumn].enableButtons();
				}
			}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			buttonSelected(buttonRow, buttonColumn);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (checkWord(currentWord)) {
				if (!(wordsDisplay.contains(currentWord))) {
					//boggleSound.playSound("scream");
					boggleSound.playWordSound(currentWord.length());
					score += currentWord.length();
					lblScore.setText("SCORE: " + score);
					wordsDisplay.add(currentWord);
					wordListTextArea.setText(getwordsDisplay() + "\n");								
					clearAndShuffle(buttonRow, buttonColumn);
				}
				else
				{
					clearAndShuffle(buttonRow, buttonColumn);
				}

			} else {
				clearAndShuffle(buttonRow, buttonColumn);
			}
			
		}
	    
	}
}
