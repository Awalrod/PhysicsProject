package Physics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Created by alex on 11/1/15.
 */
public class Space implements ActionListener {
    Timer timer;
    ArrayList<SimpleObject> objList;
    ArrayList<Point>pointList;
    double timeElapsed;

    public Space(){
        objList = new ArrayList<>();
        pointList = new ArrayList<>();
        timer = new Timer(10,this);
        timeElapsed = 0;
    }



    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    public void clear(){
        objList.clear();
        pointList.clear();
    }

    public void addObject(SimpleObject obj){
        objList.add(obj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(SimpleObject o : objList){
            o.update(.1);
            pointList.add(o.coords());

        }
        timeElapsed += .1;
    }
}
