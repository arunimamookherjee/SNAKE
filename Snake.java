package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Arunima on 4/7/2016.
 */

public class Snake implements ActionListener, KeyListener {

    public static Snake snake;
    public  JFrame jFrame;
    public MakeBoard myBoard;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> snakie=new ArrayList<Point>();
    public static final int UP=0,DOWN=1, LEFT=2, RIGHT=3, SCALE=10;
    public int SnakeMoves=0, dir=DOWN, score=0, tailLength=10;
    public float speed=5;
    public Point head, food;

    public boolean over=false;

    public Random random;

    public Dimension dim;



    public Snake()
    {

        System.out.println("Snake Game Starts noww!");
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame=new JFrame("FLASH the NAAGIN"); //Will name applet screen Snake
        jFrame.setVisible(true);
        jFrame.setSize(800,1000);
        jFrame.setLocation(dim.width / 2 - jFrame.getWidth() / 2, dim.height / 2 - jFrame.getHeight() / 2);
        jFrame.add(myBoard=new MakeBoard());//adding component
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame();


    }

    public void startGame()
    {

        over=false;
        score=0;
        tailLength=5;
        dir=DOWN;

        head=new Point(2,-1);//initializing the head of the snake
        random=new Random();
        snakie.clear();
        food=new Point(random.nextInt(80),random.nextInt(80));
        jFrame.addKeyListener(this);

        for(int i=0; i<tailLength; i++)
            snakie.add(new Point(head.x, head.y));
        timer.start();

    }




    @Override
    public void actionPerformed(ActionEvent e)
    {
        //System.out.println(food.x+" , "+food.y);
        //System.out.println(head.x+" , "+head.y);
        //System.out.println(" "+tailLength);
        myBoard.repaint();
        SnakeMoves++;
        if(SnakeMoves%speed==0 && head!=null && over !=true)
        {

            if(dir==DOWN)
                if(head.y+1<80 && noTailFound(head.x, head.y + 1))
                head=new Point(head.x, head.y+1);
                else
                over=true;

            if(dir==UP)
                if(head.y-1>=0 && noTailFound(head.x, head.y - 1))
                head=new Point(head.x, head.y - 1);
                else
                    over=true;

            if(dir==LEFT && noTailFound(head.x - 1, head.y))
                if(head.x-1>=0)
                head=new Point(head.x-1, head.y);
                else
                over=true;

            if(dir==RIGHT)
                if(head.x+1<80 && noTailFound(head.x + 1, head.y))
                head=new Point(head.x+1, head.y);
                else
                over=true;
            if(snakie.size()>tailLength)
                snakie.remove(0);

            snakie.add(new Point(head.x, head.y));
            /*for( int i=0;i<tailLength;i++)
            snakie.remove(snakie.size()-i); //removes the last part of the snake or the first part that was added
            */
            if(food!=null){
                //System.out.print("Snakeee Food");
                if(head.equals(food))
                {
                    score+=10;
                    tailLength++;
                    food.setLocation(random.nextInt(80),random.nextInt(80));
                    speed*=0.25;

                    //random=new Random();
                    //food.setLocation(random.nextInt(dim.width/SCALE),random.nextInt( dim.height/SCALE));

                }

            }

        }

    }
    public boolean noTailFound(int x, int y)
    {
        for(Point point : snakie) {
            if (point.equals(head))
            {
                System.out.println("Accident ho gaya");
                //startGame();
                return true;


            }
        }

        return false;
    }

    public static void main(String[] args)
    {
        snake=new Snake();

    }


    @Override
    public void keyPressed(KeyEvent e) {

        int i = e.getKeyCode();

        if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)
        {
            dir = LEFT;
        }

        if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT)
        {
            dir= RIGHT;
        }

        if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP)
        {
            dir = UP;
        }

        if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN)
        {
            dir = DOWN;
        }

        if (i == KeyEvent.VK_SPACE || over  )
        {
           startGame();
        }




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
