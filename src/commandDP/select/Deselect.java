package commandDP.select;

import commandDP.Command;
import model.Shape;

public class Deselect implements Command {

    private Shape s;

    public Deselect(Shape s) {
        this.s = s;
    }

    @Override
    public void undo() {
        s.setSelected(true);
    }

    @Override
    public void exec() {
        s.setSelected(false);
    }

    @Override
    public String toString() {
        return "Deselected:" + s.toString();
    }

}
