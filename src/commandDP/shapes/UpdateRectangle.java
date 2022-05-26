package commandDP.shapes;

import commandDP.Command;
import model.Rectangle;

public class UpdateRectangle implements Command {

    private Rectangle oldRectangle;
    private Rectangle newRectangle;

    private Rectangle now = new Rectangle();

    public UpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
        this.oldRectangle = oldRectangle;
        this.newRectangle = newRectangle;
    }

    @Override
    public void undo() {
        oldRectangle.getUpLeftP().setX(now.getUpLeftP().getX());
        oldRectangle.getUpLeftP().setY(now.getUpLeftP().getY());

        oldRectangle.setHeight(now.getHeight());
        oldRectangle.setWidth(now.getWidth());

        oldRectangle.setColor(now.getColor());
        oldRectangle.setInnerColor(now.getInnerColor());
    }

    @Override
    public void exec() {
        now = oldRectangle.clone();

        oldRectangle.getUpLeftP().setX(newRectangle.getUpLeftP().getX());
        oldRectangle.getUpLeftP().setY(newRectangle.getUpLeftP().getY());

        oldRectangle.setHeight(newRectangle.getHeight());
        oldRectangle.setWidth(newRectangle.getWidth());

        oldRectangle.setColor(newRectangle.getColor());
        oldRectangle.setInnerColor(newRectangle.getInnerColor());
    }

    @Override
    public String toString() {
        return "Updated:" + now.toString() + ", New Rectangle: " + newRectangle.toString();
    }

}
