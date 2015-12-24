package Physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * Created by alex on 11/1/15.
 */
public class Board extends JFrame implements ActionListener {
    private Timer timer;

    double lowerX;
    double lowerY;
    double upperX;
    double upperY;
    Space space;

    public Board(Space s){
        timer = new Timer(20,this);
        space = s;

        lowerX = -350;
        lowerY = -350;
        upperX = 350;
        upperY = 350;

        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setTitle("Physics");
        setResizable(false);
        setVisible(true);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.GRAY);
        Point origin = scale(new Point(0,0));
        DecimalFormat df = new DecimalFormat("#.00");
        g2d.drawString(df.format(space.timeElapsed) + " sec", 0, 10);
        g2d.draw(new Line2D.Double(0, origin.y, 700, origin.y));//x-axis
        g2d.draw(new Line2D.Double(origin.x, 0, origin.x, 700));//y-axis

        g2d.setColor(Color.BLACK);
        for(SimpleObject obj : space.objList){
            Point p = scale(obj.coords());
            g2d.fill(new Ellipse2D.Double(p.x,p.y,5,5));
        }
        g2d.setColor(Color.RED);
        for(Point p : space.pointList){
            p = scale(p);
            g2d.fill(new Ellipse2D.Double(p.x,p.y,1,1));
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void updateBounds(double lx, double ux, double ly, double uy){
        lowerX = lx;
        upperX = ux;
        lowerY = ly;
        upperY = uy;
    }

   public Point scale(Point p){
       double newX = (700/(upperX-lowerX))*(p.x-lowerX);
       double newY = 700 - ((700/(upperY-lowerY))*(p.y-lowerY));

       return new Point(newX,newY);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
