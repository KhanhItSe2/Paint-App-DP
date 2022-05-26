package commandDP.shapes;

import commandDP.Command;
import model.Line;

public class UpdateLine implements Command {

    private Line oldLine;
    private Line newLine;

    private Line now = new Line();

    public UpdateLine(Line oldLine, Line newLine) {
        this.oldLine = oldLine;
        this.newLine = newLine;
    }

    @Override
    public void exec() {
        now = oldLine.clone();

        oldLine.getStart().setX(newLine.getStart().getX());
        oldLine.getStart().setY(newLine.getStart().getY());
        oldLine.getEnd().setX(newLine.getEnd().getX());
        oldLine.getEnd().setY(newLine.getEnd().getY());
        oldLine.setColor(newLine.getColor());

    }

    @Override
    public void undo() {
        oldLine.getStart().setX(now.getStart().getX());
        oldLine.getStart().setY(now.getStart().getY());
        oldLine.getEnd().setX(now.getEnd().getX());
        oldLine.getEnd().setY(now.getEnd().getY());
        oldLine.setColor(now.getColor());
    }

    @Override
    public String toString() {
        return "Updated:" + now.toString() + ", New line: " + newLine.toString();
    }

}
