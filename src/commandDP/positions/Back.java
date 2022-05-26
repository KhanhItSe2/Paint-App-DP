package commandDP.positions;

import java.util.Collections;

import commandDP.Command;
import model.Shape;
import view.Model;

public class Back implements Command {

    private Model m;
    private Shape s;
    private int order;

    public Back(Model m, Shape s) {
        this.m = m;
        this.s = s;
    }

    @Override
    public void exec() {
        this.order = m.getShapes().indexOf(s);
        Collections.swap(m.getShapes(), order, order - 1);

    }

    @Override
    public void undo() {
        this.order = m.getShapes().indexOf(s);
        Collections.swap(m.getShapes(), order, order + 1);

    }

    @Override
    public String toString() {
        return "To Back : " + s.toString();
    }

}
