package view;

import java.util.ArrayList;

import model.Shape;

public class Model {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public Shape get(int shape) {
        return shapes.get(shape);
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

}
