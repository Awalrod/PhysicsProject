package Physics;
import javax.swing.*;
/**
 * Created by alex on 11/1/15.
 */
public class Physics  {



    public static void main(String[] args)  throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Space space = new Space();
                Board board = new Board(space);
                new ControlWindow(board,space);
            }
        });
    }
}
