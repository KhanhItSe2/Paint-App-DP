package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;

public abstract class Shape extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean selected;
    private Color color;

    public abstract boolean compareTo(Object object);

    public abstract void draw(Graphics graphic);

    public abstract void selected(Graphics graphic);

    public abstract boolean contains(int x, int y);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setChanged();
        notifyObservers();

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getColorText() {
        return "BorderColor(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }

}
