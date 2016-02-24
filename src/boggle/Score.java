// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

public class Score implements Comparable<Score> {

    private int score;
    private String theNames;

    public int getScore() {
        return score;
    }

    public String getTheNames() {
        return theNames;
    }

    public Score(String theNames, int score) {
        this.score = score;
        this.theNames = theNames;
    }

    @Override
    public int compareTo(Score score1) {
        return ((Integer) (score1.getScore())).compareTo(getScore());
    }

    @Override
    public String toString() {
        return theNames + "\t" + score;
    }
}
