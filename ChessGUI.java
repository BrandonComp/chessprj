/**********************************************************************
 * Creates the chess panel graphic interface for the chess game.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/
import java.awt.Dimension;

import javax.swing.JFrame;

public class ChessGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(790, 637));
        frame.pack();
        frame.setVisible(true);
    }
}
