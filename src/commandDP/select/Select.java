package commandDP.select;

import commandDP.Command;
import model.Shape;

public class Select implements Command {

    private Shape s;

    public Select(Shape s) {
        this.s = s;
    }

    @Override
    public void exec() {
        s.setSelected(true);

    }

    @Override
    public void undo() {
        s.setSelected(false);

    }

    @Override
    public String toString() {
        return "Selected:" + s.toString();
    }

}
