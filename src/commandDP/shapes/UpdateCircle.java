package commandDP.shapes;

import commandDP.Command;
import model.Circle;

public class UpdateCircle implements Command {

    private Circle oldCircle;
    private Circle newCircle;

    private Circle now = new Circle();

    public UpdateCircle(Circle oldCircle, Circle newCircle) {
        this.oldCircle = oldCircle;
        this.newCircle = newCircle;
    }

    @Override
    public void exec() {
        now = (Circle) oldCircle.clone();

        oldCircle.getCenter().setX(newCircle.getCenter().getX());
        oldCircle.getCenter().setY(newCircle.getCenter().getY());

        oldCircle.setRadius(newCircle.getRadius());

        oldCircle.setColor(newCircle.getColor());
        oldCircle.setInnerColor(newCircle.getInnerColor());

    }

    @Override
    public void undo() {
        oldCircle.getCenter().setX(now.getCenter().getX());
        oldCircle.getCenter().setY(now.getCenter().getY());

        oldCircle.setRadius(now.getRadius());

        oldCircle.setColor(now.getColor());
        oldCircle.setInnerColor(now.getInnerColor());

    }

    @Override
    public String toString() {
        return "Updated:" + now.toString() + ",New Circle: " + newCircle.toString();
    }

}
