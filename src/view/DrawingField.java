package view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import model.Shape;

@SuppressWarnings("serial")
public class DrawingField extends JPanel {

    Model model = new Model();

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics graphic) {
        super.paint(graphic);
        if (model != null) {
            Iterator<Shape> shape = model.getShapes().iterator();
            while (shape.hasNext()) {
                shape.next().draw(graphic);
            }
        }

    }

}
