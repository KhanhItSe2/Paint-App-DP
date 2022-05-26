package model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Surface extends Shape {

    private static final long serialVersionUID = 1L;

    public abstract void fill(Graphics graphic);

    private Color innerColor;

    public Color getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(Color innerColor) {
        this.innerColor = innerColor;
    }

    public String getInnerColorText() {
        return "FillColor:(" + innerColor.getRed() + "," + innerColor.getGreen() + " " + innerColor.getBlue() + ")";
    }

}
