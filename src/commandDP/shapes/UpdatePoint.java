package commandDP.shapes;

import commandDP.Command;
import model.Point;

public class UpdatePoint implements Command {

    private Point oldPoint;
    private Point newPoint;

    private Point now = new Point();

    public UpdatePoint(Point oldPoint, Point newPoint) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
    }

    @Override
    public void undo() {
        oldPoint.setX(now.getX());
        oldPoint.setY(now.getY());
        oldPoint.setColor(now.getColor());
    }

    @Override
    public void exec() {
        now = oldPoint.clone();
        oldPoint.setX(newPoint.getX());
        oldPoint.setY(newPoint.getY());
        oldPoint.setColor(newPoint.getColor());
    }

    @Override
    public String toString() {
        return "Updated:" + now.toString() + ", New Point: " + newPoint.toString();
    }

}
