import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.* ;
import javax.swing.* ;
import javax.swing.Timer;
import java.awt.* ;

public class Pacman extends JPanel implements ActionListener, KeyListener {

    private int rowNum = 21;  //from 0 to 20
    private int colubmnNum = 19;
    private int tileSize = 32;
    private int tableWidth = colubmnNum * tileSize;
    private int tableHeigth = rowNum * tileSize ;

    public  void move(){
        pacman.x += pacman.veloX;
        pacman.y += pacman.veloY;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {
        //System.out.println("Key event :"+ e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.updateDirection('U');
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.updateDirection('D');
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.updateDirection ('L');
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            pacman.updateDirection ('R');
    }

    class Block{
        int x;
        int y;
        int width;
        int heigth;
        Image image;

        int startX;
        int startY;
        char direction ='U';
        int veloX=0;
        int veloY =0;

        Block(int x,int y,int width,int heigth,Image image){
            this.x=x;
            this.y=y;
            this.image =image;
            this.startX=x;
            this.startY=y;
            this.heigth=heigth;
            this.width=width;
        }

        void updateDirection(char direction){
            this.direction= direction;
            updateVelo();
        }
        void updateVelo(){
            if (this.direction=='U'){
                this.veloX=0;
                this.veloY =-tileSize/4;
            }
            else if(this.direction=='D'){
                this.veloX=0;
                this.veloY =tileSize/4;
            }
            else if (this.direction=='L'){
                this.veloX=-tileSize/4;
                this.veloY=0;
            }
            else if(this.direction=='R'){
                this.veloX=tileSize/4;
                this.veloY=0;
            }

        }

    }

    HashSet<Block> background;
    HashSet<Block> ghosts;
    HashSet<Block> foods;
    Block pacman;

    Timer gameLoop;

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
            "X  X      P    X  X",
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
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        wallImage = new ImageIcon("src/wall.png").getImage();

        blueGhostI = new ImageIcon("src/blueGhost.png").getImage();
        orangeGhostI = new ImageIcon("src/orangeGhost.png").getImage();
        pinkGhostI = new ImageIcon("src/pinkGhost.png").getImage();
        redGhostI = new ImageIcon("src/redGhost.png").getImage();
        darkverGhostI = new ImageIcon("src/darkGhost.png").getImage();

        upPacmanI = new ImageIcon("src/pacmanUp.png").getImage();
        downPacmanI = new ImageIcon("src/pacmanDown.png").getImage();
        leftPacmanI = new ImageIcon("src/pacmanLeft.png").getImage();
        rigthPacmanI = new ImageIcon("src/pacmanRigth.png").getImage();

        loadMap();
        gameLoop = new Timer(60,this);
        gameLoop.start();
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
                background.add(wall);
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
                Block food = new Block(x+14, y+14, 4, 4,null); //with 32px
                foods.add(food);
            }
        }
    }
    }
public void draw(Graphics g){
    g.drawImage(rigthPacmanI,pacman.x, pacman.y,pacman.width , pacman.heigth, null);

    for(Block ghost :ghosts){
        g.drawImage(ghost.image,ghost.x,ghost.y,ghost.width,ghost.heigth,null);
    }

    for(Block wall :background){
        g.drawImage(wallImage,wall.x,wall.y,wall.width,wall.heigth,null);

    }
    g.setColor(Color.WHITE );
    for(Block food:foods){
        g.fillRect(food.x, food.y, food.width, food.heigth );

    }
}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
}










