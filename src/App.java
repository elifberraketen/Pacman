import javax.swing.JFrame;                       //for the GUI
import java.util.Dictionary;


public class App {
    static void main(String[] args) {

        int rowNum = 21;  //from 0 to 20
        int colubmnNum = 19;
        int tileSize = 32;
        int tableWidth = colubmnNum * tileSize;
        int tableHeigth = rowNum * tileSize ;

        JFrame frame = new JFrame("PacMan");

        frame.setSize(tableWidth,tableHeigth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);      //center of our screen
        frame.setResizable(false);

    Pacman pacmanGame =new Pacman();
    frame.add(pacmanGame);
    frame.setVisible(true);

    }

}
