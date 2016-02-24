// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

public class BoggleDice 
{

    private static String[] bigBoggleCubes
            = {"aaafrs", "aaeeee", "aafirs", "adennn", "aeeeem", "aeegmu", "aegmnn",
                    "afirsy", "bjkqxz", "ccnstw", "ceiilt", "ceilpt", "ceipst",
                    "ddlnor", "ddhnot", "dhhlor", "dhlnor", "eiiitt", "emottt",
                    "ensssu", "fiprsy", "gorrvw", "hiprry", "nootuw", "ooottu"};
    private static int numBigBoggleCubes = bigBoggleCubes.length;
    private static int numCubesCreated = 0;
    private String letter;
    private int cubeID;

    public BoggleDice() 
    {
        cubeID = numCubesCreated % numBigBoggleCubes;
        numCubesCreated++;
    }

    //'Roll' the cube, which will generate a random letter for itself.
    public String rollCube() 
    {
        int index = (int) (Math.random() * bigBoggleCubes[cubeID].length());
        letter = bigBoggleCubes[cubeID].substring(index, index + 1);
        if (letter.equals("q")) {
            letter = "qu";
        }
        return letter;
    }

}
