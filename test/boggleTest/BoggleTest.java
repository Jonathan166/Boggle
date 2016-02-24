// Assignment Boggle.update7.UML
// Program BoggleTest
// Author Bryan Fritz
// Date Nov 25, 2015

package boggleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import boggle.BoggleButton;
import boggle.BoggleDice;
import boggle.Score;
import boggle.ScoreManager;

public class BoggleTest {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	@Test
	public void testBoggleButtonConstructor() 
	{
		//Creates a Boggle button we then test the int value for the row and the col to make sure they match what was input in the
		//constructor.
		BoggleButton bbtest = new BoggleButton(1, 2);
		int expectedRow = 1;
		int expectedCol = 2;
		assertEquals(expectedRow,bbtest.getRow());
		assertEquals(expectedCol,bbtest.getCol());
	}
	@Test
	public void testScoreConstructor()
	{
		//Test the creation of a Score class. We will compare what is created with a String and and int value that is what should have 
		//been created.
		Score testScore = new Score("James", 12);
		assertEquals("James", testScore.getTheNames());
		assertEquals(12,testScore.getScore());
	}
	@Test
	public void testScoreCompareTo()
	{
		//Overwritten method 
		/*public int compareTo(Score score1) {
	        return ((Integer) (score1.getScore())).compareTo(getScore());
	    }*/
		//notice that it compares the Score passed in to the object. 
		Score testScore = new Score("James", 2);
		Score testScore2 = new Score("Jim", 2);
		Score testScore3 = new Score("John", 1);
		Score testScore4 = new Score("Chery", 200);
		//Score is equal
		assertEquals(0,testScore.compareTo(testScore2));
		//testScore3 is less than testscore
		assertEquals(-1,testScore.compareTo(testScore3));
		//testScore4 is greater than testscore
		assertEquals(1,testScore.compareTo(testScore4));
	}
	@Test
	public void testScoreManagerConstructorandUpdateScore()
	{
		//Tests the score in the highest spot and makes sure that it is the score added. It would be very hard to score 2000 in the 
		//game so I feel this is a safe test
		ScoreManager sm = new ScoreManager();
		sm.addScore("SuperTest", 20000);		
		assertTrue(sm.getScores().get(0).getScore() == 20000 && sm.getScores().get(0).getTheNames() == "SuperTest");
	}
	@Test
	public void testBoggleDice()
	{
		//Test to make sure that letters are generated from a - z. They are lower case and in a different method when we add them 
		//to our cubes we then convert them to upper case. We are also testing that only a single letter is created with no additional
		//letters unless the letter is a q. A q will fail the test as the method should return only a qu.
		BoggleDice bd = new BoggleDice();
		String regex = "^\\b[a-p]\\b|^\\b[r-z]\\b|^\\bqu\\b";
		for (int i = 0; i < 1000; i++)
		{
			String test = bd.rollCube();
			assertTrue(test.matches(regex));
		}
		String qu = "qu";
		assertTrue(qu.matches(qu));
		String qtest = "q";
		assertFalse(qtest.matches(regex));
		String wtest = "as";
		assertFalse(wtest.matches(regex));
	}
	
}
