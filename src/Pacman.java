import java.util.* ;
import javax.swing.* ;
import java.awt.* ;

public class Pacman extends JPanel{
    private int rowNum = 21;  //from 0 to 20
    private int colubmnNum = 19;
    private int tileSize = 32;
    private int tableWidth = colubmnNum * tileSize;
    private int tableHeigth = rowNum * tileSize ;

    class Block{
        int x;
        int y;
        int width;
        int heigth;
        Image image;

        int startX;
        int startY;

        Block(int x,int y,int width,int heigth,Image image){
            this.x=x;
            this.y=y;
            this.image =image;
            this.startX=x;
            this.startY=y;
            this.heigth=heigth;
            this.width=width;
        }
    }

    HashSet<Block> background;
    HashSet<Block> ghosts;
    HashSet<Block> foods;
    Block pacman;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"};

    private Image wallImage;
    private Image blueGhostI;
    private Image orangeGhostI;
    private Image pinkGhostI;
    private Image redGhostI;
    private Image darkverGhostI;

    private Image leftPacmanI;
    private Image rigthPacmanI;
    private Image upPacmanI;
    private Image downPacmanI;



    Pacman(){
        setPreferredSize(new Dimension(tableWidth,tableHeigth));
        setBackground(Color.orange);

        wallImage =new ImageIcon(getClass().getResource("./wall.png")).getImage() ;

        blueGhostI=new ImageIcon(getClass().getResource("./blueGhost.png")).getImage() ;
        orangeGhostI =new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage() ;
        pinkGhostI =new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage() ;
        redGhostI =new ImageIcon(getClass().getResource("./redGhost.png")).getImage() ;
        darkverGhostI =new ImageIcon(getClass().getResource("./darkGhost.png")).getImage() ;

        upPacmanI =new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage() ;
        downPacmanI =new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage() ;
        leftPacmanI =new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage() ;
        rigthPacmanI =new ImageIcon(getClass().getResource("./pacmanRigth.png")).getImage() ;

        loadMap();
        System.out.println(background.size());
        System.out.println(foods.size());
        System.out.println(ghosts.size());



    }

    public void loadMap(){
    background= new HashSet<Block>();
    ghosts = new HashSet<Block>();
    foods =new HashSet<Block>();

    for (int r =0 ;r< rowNum ;r++){
        for(int c =0 ;c< colubmnNum ;c++){
            String row = tileMap[r];
            char tileMapChar =row.charAt(c);

            int x =c*tileSize;
            int y =r*tileSize;

            if (tileMapChar =='X') {
                Block wall = new Block(x, y, tileSize, tileSize, wallImage);
            }
            else if (tileMapChar =='b') {
                Block blueGhost = new Block(x, y, tileSize, tileSize,blueGhostI);
                ghosts.add(blueGhost);
            }
            else if (tileMapChar =='o') {
                Block orangeGhost = new Block(x, y, tileSize, tileSize,orangeGhostI);
                ghosts.add(orangeGhost);
            }
            else if (tileMapChar =='p') {
                Block pinkGhost = new Block(x, y, tileSize, tileSize,pinkGhostI);
                ghosts.add(pinkGhost);
            }
            else if (tileMapChar =='r') {
                Block redGhost = new Block(x, y, tileSize, tileSize,redGhostI);
                ghosts.add(redGhost);
            }
            else if (tileMapChar =='P') {
                 pacman = new Block(x, y, tileSize, tileSize,rigthPacmanI);
            }
            else if (tileMapChar ==' ') {
                Block food = new Block(14, 14, 4, 4,null); //with 32px
                foods.add(food);
            }
        }
    }

    }
}










