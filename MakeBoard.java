package snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arunima on 4/9/2016.
 */
public class MakeBoard extends JPanel {//Everything will pe painted here----i.e the board

    Color c =new Color(16773298);
    Color c1=new Color(6374762);
    Font f = new Font("Serif", Font.BOLD, 20);
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(c);
        g.fillRect(0,0,800,800);
        Snake snake= Snake.snake;
        for(Point point : snake.snakie)
        {
            g.setColor(c1);
            g.fillRect(point.x*Snake.SCALE, point.y*Snake.SCALE,Snake.SCALE, Snake.SCALE);
        }
        g.setColor(Color.BLACK);
        g.fillRect(snake.head.x*Snake.SCALE,snake.head.y*Snake.SCALE,Snake.SCALE, Snake.SCALE);


        g.setColor(Color.RED);
        g.fillRect(snake.food.x*Snake.SCALE,snake.food.y*Snake.SCALE, Snake.SCALE, Snake.SCALE);

        g.setColor(Color.black);
        g.fillRect(0,800,800,200);
        g.setColor(Color.WHITE);
        g.setFont(f);
        String result="SCORE: "+snake.score;
        if(snake.score==0)
            g.drawString("NEW GAME...",0,820);
        g.drawString(result,0,850 );

    }
}
