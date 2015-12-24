package Physics;

/**
 * Created by alex on 10/27/15.
 */

public class SimpleObject {
    public double velocityX;
    public double velocityY;

    public double acceleration;
    public double accelerationDirection;

    public double posX;
    public double posY;


    public SimpleObject(double vx, double vy, double a, double ad, double x, double y){
        velocityX = vx;
        velocityY = vy;
        acceleration = a;
        accelerationDirection = ad;
        posX = x;
        posY = y;
    }

    public void update(double timePeriod){

        double accelerationX = Math.cos(Math.toRadians(accelerationDirection))*acceleration;
        double accelerationY =Math.sin(Math.toRadians(accelerationDirection))*acceleration;

        posX += velocityX*timePeriod + (.5*accelerationX*timePeriod*timePeriod);
        posY += velocityY*timePeriod + (.5*accelerationY*timePeriod*timePeriod);

        velocityX = (velocityX + (accelerationX*timePeriod));
        velocityY = (velocityY + (accelerationY*timePeriod));
    }

    public Point coords(){
        return new Point(posX,posY);
    }

}



