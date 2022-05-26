package commandDP.positions;

import commandDP.Command;
import model.Shape;
import view.Model;

public class BringToFront implements Command {

    private Model m;
    private Shape s;
    private int order;

    public BringToFront(Model m, Shape s) {
        this.m = m;
        this.s = s;
        this.order = m.getShapes().indexOf(s);
    }

    @Override
    public void undo() {
        m.remove(s);
        m.getShapes().add(order, s);
    }

    @Override
    public void exec() {
        m.remove(s);
        m.add(s);

    }

    @Override
    public String toString() {
        return "Bring To Front:" + s.toString();
    }

}
