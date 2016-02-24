// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BoggleUtility 
{
    public static Set<String> get_words() 
    {
        Set<String> words = new HashSet<String>();
		// read one word at a time
            try (Scanner s = new Scanner(BoggleUtility.class.getResourceAsStream("Lexicon.csv")))
            {
            	while (s.hasNextLine()) 
            	{
            		words.add(s.nextLine().toUpperCase());
            	}
            }
        // add into words

        return words;

    }

}
