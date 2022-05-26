package model;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Surface implements Cloneable {

    private static final long serialVersionUID = 1L;
    private Point center;
    private int radius;

    public Circle() {

    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        super.setColor(color);
    }

    public Circle(Point center, int radius, Color color, Color innerColor) {
        this(center, radius, color);
        super.setInnerColor(innerColor);
    }

    @Override
    public Circle clone() {

        Circle circleClone = new Circle();
        circleClone.setCenter(new Point(this.getCenter().getX(), this.getCenter().getY()));
        circleClone.setRadius(getRadius());
        circleClone.setColor(getColor());
        circleClone.setInnerColor(getInnerColor());

        return circleClone;
    }

    @Override
    public boolean contains(int x, int y) {
        return center.distance(x, y) <= radius;
    }

    @Override
    public void draw(Graphics graphic) {
        graphic.setColor(getColor());
        graphic.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2,
                this.radius * 2);
        fill(graphic);
        if (isSelected()) {
            selected(graphic);
        }

    }

    @Override
    public void fill(Graphics graphic) {
        graphic.setColor(getInnerColor());
        graphic.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);

    }

    @Override
    public void selected(Graphics graphic) {
        graphic.setColor(Color.green);
        center.selected(graphic);
        Point left = new Point(center.getX() - radius, center.getY());
        Point right = new Point(center.getX() + radius, center.getY());
        Point up = new Point(center.getX(), center.getY() - radius);
        Point down = new Point(center.getX(), center.getY() + radius);

        left.selected(graphic);
        right.selected(graphic);
        up.selected(graphic);
        down.selected(graphic);

    }

    @Override
    public boolean compareTo(Object object) {
        if (object instanceof Circle) {
            return (getCenter().compareTo(((Circle) object).getCenter()) && getRadius() == ((Circle) object).getRadius());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Circle:center" + center.getCordinatesText() + "radius:(" + this.radius + ")" + getColorText() + getInnerColorText();
    }

}
