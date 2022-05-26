package model;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Surface implements Cloneable {

    private static final long serialVersionUID = 1L;
    private Point upLeftP;
    private int height;
    private int width;

    public Rectangle() {

    }

    public Point getUpLeftP() {
        return upLeftP;
    }

    public void setUpLeftP(Point upLeftP) {
        this.upLeftP = upLeftP;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int area() {
        return this.height * this.width;
    }

    public Rectangle(Point upLeftP, int height, int width) {
        this.upLeftP = upLeftP;
        this.height = height;
        this.width = width;
    }

    public Rectangle(Point upLeftP, int height, int width, Color borderColor, Color fillColor) {
        this.upLeftP = upLeftP;
        this.height = height;
        this.width = width;
        this.setColor(borderColor);
        this.setInnerColor(fillColor);

    }

    @Override
    public Rectangle clone() {

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(getWidth());
        rectangle.setInnerColor(getInnerColor());
        rectangle.setHeight(getHeight());
        rectangle.setColor(getColor());
        rectangle.setUpLeftP(new Point(getUpLeftP().getX(), getUpLeftP().getY()));

        return rectangle;
    }

    @Override
    public boolean contains(int x, int y) {
        if (this.getUpLeftP().getX() <= x && upLeftP.getY() <= y && x <= upLeftP.getX() + width
                && y <= this.getUpLeftP().getY() + height) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics graphic) {
        fill(graphic);
        graphic.setColor(getColor());
        graphic.drawRect(this.upLeftP.getX(), this.upLeftP.getY(), this.width, this.height);
        this.fill(graphic);
        if (isSelected()) {
            selected(graphic);
        }

    }

    @Override
    public boolean compareTo(Object object) {
        if (object instanceof Rectangle) {
            return (getUpLeftP().compareTo(((Rectangle) object).getUpLeftP()) && getHeight() == ((Rectangle) object).getHeight() && getWidth() == ((Rectangle) object).getWidth());
        } else {
            return false;
        }
    }

    @Override
    public void selected(Graphics graphic) {
        graphic.setColor(Color.green);
        graphic.drawRect(getUpLeftP().getX() - 3, getUpLeftP().getY() - 3, 6, 6);
        graphic.drawRect(getUpLeftP().getX() + getWidth() - 3, getUpLeftP().getY() - 3, 6, 6);
        graphic.drawRect(getUpLeftP().getX() - 3, getUpLeftP().getY() + getHeight() - 3, 6, 6);
        graphic.drawRect(getUpLeftP().getX() + getWidth() - 3, getUpLeftP().getY() + getHeight() - 3, 6, 6);

    }

    @Override
    public void fill(Graphics graphic) {
        graphic.setColor(getInnerColor());
        graphic.fillRect(this.upLeftP.getX() + 1, this.getUpLeftP().getY() + 1, width - 1, height - 1);

    }

    @Override
    public String toString() {
        return "Rectangle:UperLeftPoint->" + upLeftP.getCordinatesText() + " Height->" + this.height + " Width->" + " " + this.width + " " + getColorText() + getInnerColorText();
    }

}
