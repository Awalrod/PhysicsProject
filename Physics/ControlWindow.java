package Physics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by alex on 11/1/15.
 */
public class ControlWindow extends JFrame implements ActionListener {
    JTabbedPane tabPane;
    JPanel objectControls;
    JPanel windowControls;
    JPanel simulControls;
    Board board;

    //gui components for object controls
    JLabel v;
    JLabel vd;
    JLabel a;
    JLabel ad;
    JLabel posX;
    JLabel posY;


    JTextField velocity;
    JTextField velocityDirection;
    JTextField acceleration;
    JTextField accelerationDirection;
    JTextField positionY;
    JTextField positionX;

    JButton addObject;

    // end object controls

    //gui components for window controls
    JLabel lowerX;
    JLabel upperX;
    JLabel lowerY;
    JLabel upperY;

    JTextField lowerXText;
    JTextField upperXText;
    JTextField lowerYText;
    JTextField upperYText;

    JButton set;
    //end window controls

    //gui components for simulation controls
    JButton clear;
    JButton simulate;
    JButton stop;
    //end simulation controls


    Space space;


    public ControlWindow(Board b, Space s){
        space = s;
        board = b;

        setSize(200,700);

        objectControls = new JPanel();
        GridLayout objectControlsLayout = new GridLayout(7,2,10,0);
        objectControls.setLayout(objectControlsLayout);
        initObjControls();

        windowControls = new JPanel();
        GridLayout windowControlsLayout = new GridLayout(5,2,10,0);
        windowControls.setLayout(windowControlsLayout);
        initWinControls();

        simulControls = new JPanel();
        GridLayout simulControlsLayout = new GridLayout(2,2,10,0);
        simulControls.setLayout(simulControlsLayout);
        initSimulControls();

        tabPane = new JTabbedPane();
        tabPane.addTab("Object Controls",objectControls);
        tabPane.addTab("Window Controls",windowControls);
        tabPane.addTab("Simulation Controls",simulControls);

        add(tabPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Controls");
        setResizable(true);
        setVisible(true);
    }

    public void initSimulControls(){
        simulate = new JButton("Start");
        simulate.addActionListener(this);
        simulControls.add(simulate);

        stop = new JButton("Stop");
        stop.addActionListener(this);
        simulControls.add(stop);

        clear = new JButton("Clear");
        clear.addActionListener(this);
        simulControls.add(clear);

    }

    public void initWinControls(){
        int fieldSize = 3;

        lowerX = new JLabel("X lower bound:");
        lowerXText = new JTextField(fieldSize);
        windowControls.add(lowerX);
        windowControls.add(lowerXText);

        upperX = new JLabel("X upper bound");
        upperXText = new JTextField(fieldSize);
        windowControls.add(upperX);
        windowControls.add(upperXText);

        lowerY = new JLabel("Y lower bound");
        lowerYText = new JTextField(fieldSize);
        windowControls.add(lowerY);
        windowControls.add(lowerYText);

        upperY = new JLabel("Y upper bound");
        upperYText = new JTextField(fieldSize);
        windowControls.add(upperY);
        windowControls.add(upperYText);

        set = new JButton("Set");
        set.addActionListener(this);
        windowControls.add(set);




    }
    public void initObjControls(){
        int fieldSize = 3;
        v = new JLabel("Velocity");
        velocity = new JTextField(fieldSize);
        objectControls.add(v);
        objectControls.add(velocity);

        vd = new JLabel("Velocity Direction");
        velocityDirection = new JTextField(fieldSize);
        objectControls.add(vd);
        objectControls.add(velocityDirection);

        a = new JLabel("Acceleration");
        acceleration = new JTextField(fieldSize);
        objectControls.add(a);
        objectControls.add(acceleration);

        ad = new JLabel("Acceleration Direction");
        accelerationDirection = new JTextField(fieldSize);
        objectControls.add(ad);
        objectControls.add(accelerationDirection);

        posX = new JLabel("X:");
        positionX = new JTextField(fieldSize);
        objectControls.add(posX);
        objectControls.add(positionX);

        posY = new JLabel("Y:");
        positionY = new JTextField(fieldSize);
        objectControls.add(posY);
        objectControls.add(positionY);

        addObject = new JButton("Add Object");
        addObject.addActionListener(this);
        objectControls.add(addObject);



        add(objectControls);
        pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == set){
            double lx = Double.parseDouble(lowerXText.getText());
            double ux = Double.parseDouble(upperXText.getText());
            double ly = Double.parseDouble(lowerYText.getText());
            double uy = Double.parseDouble(upperYText.getText());

            board.updateBounds(lx,ux,ly,uy);
        }
        if(e.getSource() == addObject){
            float objV = Float.parseFloat(velocity.getText());
            float objVD = Float.parseFloat(velocityDirection.getText());
            float objA = Float.parseFloat(acceleration.getText());
            float objAD = Float.parseFloat(accelerationDirection.getText());
            float objX = Float.parseFloat(positionX.getText());
            float objY = Float.parseFloat(positionY.getText());

            float objVX = (float)Math.cos(Math.toRadians(objVD))*objV;
            float objVY = (float)Math.sin(Math.toRadians(objVD))*objV;

            space.addObject(new SimpleObject(objVX, objVY, objA, objAD, objX, objY));
        }
        if(e.getSource()== simulate){
            space.start();
        }
        if(e.getSource()==stop){
            space.stop();
        }
        if(e.getSource()==clear){
            space.clear();
        }
    }
}
