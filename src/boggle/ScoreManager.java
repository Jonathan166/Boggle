// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

import java.util.*;

import javax.swing.JTextArea;

import java.io.*;

public class ScoreManager {

    private ArrayList<Score> scores;

    //private static final String HIGHSCORE_FILE = "highscores.txt";
    private static final String HIGHSCORE_FILE = "c:\\temp2\\boggle\\highscores.txt";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ScoreManager() {
        this.scores = new ArrayList<Score>();
        loadScoreFile();
        sort();
    }

    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    private void sort() {
        Collections.sort(scores);
    }
    public void sortHighScore(ScoreManager myScoresMan, JTextArea txtrHighScore) {
		Collections.sort(scores);
		myScoresMan.setScoreFile(scores);
		for (Score userScore : scores) {
			txtrHighScore.append(userScore.toString());
		}
	}

    public void addScore(String name, int score) {
        scores.add(new Score(name, score));
    }

    public void loadScoreFile()
    {
    	String name;
    	int score;
    	//try (Scanner in = new Scanner(ScoreManager.class.getResourceAsStream(HIGHSCORE_FILE)))
    	try (Scanner in = new Scanner(new File(HIGHSCORE_FILE)))
    	{
    		//in = new Scanner(HIGHSCORE_FILE);
    		while(in.hasNextLine())
    		{
    			name = in.next();
    			if (!(name == null | name == "" ))
    			{
    				try
    				{
    					score = in.nextInt();
    					scores.add(new Score(name,score));
    					in.nextLine();
    				}catch (InputMismatchException e)
    				{
    					//System.out.println("Could not read in number skipping line");
    				}
    				
    			}
    			else
    			{
    				in.nextLine();
    			}		
    		}
    			
    	} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
    
    private void saveScoreFile(ArrayList<Score> scores)
    {
    	
    	//try(PrintWriter pw = new PrintWriter(new FileWriter("src/boggle/highscores.txt")))
    	try(PrintWriter pw = new PrintWriter(new FileWriter("c:\\temp2\\boggle\\highscores.txt")))
    	{
    		Collections.sort(scores);
    		for (Score s: scores)
    		{
    			pw.printf("%s%n",s.toString());
    		}
    		pw.close();
    	} catch (IOException e) {
			System.out.println("Cannot write to file");
		} 
    }
    
    public void setScoreFile(ArrayList<Score> scores)
    {
    	saveScoreFile(scores);
    }
    
    public ArrayList<Score> getAllScores()
    {
    	return scores;
    }
    public String getScoreName() {
		String temp = "";
		for (Score userScores : scores) {
			temp += userScores.toString() + "\n";
		}

		return temp;
	}

}
