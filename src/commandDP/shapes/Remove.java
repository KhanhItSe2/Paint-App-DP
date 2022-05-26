package commandDP.shapes;

import java.util.ArrayList;
import java.util.Hashtable;

import commandDP.Command;
import model.Shape;
import view.Model;

public class Remove implements Command {

    private Model m;
    private Hashtable<Integer, Shape> shapes;

    public Remove(Model m, ArrayList<Shape> shapes) {
        this.m = m;
        this.shapes = new Hashtable<>();

        for (Shape s : shapes) {
            this.shapes.put(m.getShapes().indexOf(s), s);
        }
    }

    @Override
    public void undo() {
        for (int key : shapes.keySet()) {
            Shape shape = shapes.get(key);
            if (key >= m.getShapes().size()) {
                m.add(shape);
                shape.setSelected(true);
            } else {
                m.getShapes().add(key, shape);
                shape.setSelected(true);

            }
        }
    }

    @Override
    public void exec() {
        for (Shape s : shapes.values()) {
            s.setSelected(false);
            m.getShapes().remove(s);
        }
    }

    @Override
    public String toString() {
        String removeTxt = "Removed: ";
        for (int key : shapes.keySet()) {
            Shape s = shapes.get(key);
            removeTxt = removeTxt + s.toString() + ", ";
        }
        return removeTxt;
    }
}
