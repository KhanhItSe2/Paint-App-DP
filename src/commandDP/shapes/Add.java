package commandDP.shapes;

import commandDP.Command;
import model.Shape;
import view.Model;

public class Add implements Command {

    private Model m;
    private Shape s;

    public Add(Model m, Shape s) {
        this.m = m;
        this.s = s;
    }

    @Override
    public void exec() {
        m.add(s);
    }

    @Override
    public void undo() {
        m.remove(s);
    }

    @Override
    public String toString() {
        return "Added:" + s.toString();
    }

}
