// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

import javax.swing.JFrame;

public class BoggleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoggleFiles bf = new BoggleFiles();
    	BoggleFrame frame = new BoggleFrame();
        frame.setTitle("BOGGLE");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

}
