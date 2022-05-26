package commandDP.shapes;

import commandDP.Command;
import model.Donut;

public class UpdateDonut implements Command {

    private Donut oldDonut;
    private Donut newDonut;

    private Donut now = new Donut();

    public UpdateDonut(Donut oldDonut, Donut newDonut) {
        this.oldDonut = oldDonut;
        this.newDonut = newDonut;
    }

    @Override
    public void undo() {
        oldDonut.getCenter().setX(now.getCenter().getX());
        oldDonut.getCenter().setY(now.getCenter().getY());

        oldDonut.setRadius(now.getRadius());
        oldDonut.setInnerRadius(now.getInnerRadius());

        oldDonut.setColor(now.getColor());
        oldDonut.setInnerColor(now.getInnerColor());
    }

    @Override
    public void exec() {
        now = oldDonut.clone();

        oldDonut.getCenter().setX(newDonut.getCenter().getX());
        oldDonut.getCenter().setY(newDonut.getCenter().getY());

        oldDonut.setRadius(newDonut.getRadius());
        oldDonut.setInnerRadius(newDonut.getInnerRadius());

        oldDonut.setColor(newDonut.getColor());
        oldDonut.setInnerColor(newDonut.getInnerColor());
    }

    @Override
    public String toString() {
        return "Updated:" + now.toString() + ", New Donut: " + newDonut.toString();
    }

}
