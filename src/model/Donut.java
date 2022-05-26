package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import model.Line;
import model.Point;

public class Donut extends Circle implements Cloneable {

    private static final long serialVersionUID = 1L;
    private int innerRadius;

    public Donut() {

    }

    public Donut(Point center, int radius, int innerRadius) {
        super(center, radius);
        this.innerRadius = innerRadius;
    }

    public Donut(Point center, int radius, int innerRadius, Color color) {
        this(center, radius, innerRadius);
        super.setColor(color);
    }

    public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
        this(center, radius, innerRadius, color);
        super.setInnerColor(innerColor);
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    @Override
    public Donut clone() {
        Donut donutClone = new Donut();
        donutClone.setRadius(getRadius());
        donutClone.setCenter(new Point(getCenter().getX(), getCenter().getY()));
        donutClone.setInnerRadius(getInnerRadius());
        donutClone.setInnerColor(getInnerColor());
        donutClone.setColor(getColor());

        return donutClone;

    }

    @Override
    public void fill(Graphics graphic) {
        graphic.setColor(getInnerColor());
        super.fill(graphic);
        graphic.setColor(Color.WHITE);
        graphic.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2,
                getInnerRadius() * 2);
    }

    @Override
    public boolean contains(int x, int y) {
        double dCenter = this.getCenter().distance(x, y);
        return dCenter > innerRadius && super.contains(x, y);
    }

    @Override
    public void draw(Graphics graphic) {

        Graphics2D d = (Graphics2D) graphic.create();

        Shape donut = createDonut();

        d.setColor(getInnerColor());
        d.fill(donut);
        d.setColor(getColor());

        d.draw(donut);

        if (isSelected()) {
            selected(d);
        }

        d.dispose();

    }

    public Shape createDonut() {

        Shape outer = new Ellipse2D.Double(
                getCenter().getX() - getRadius(),
                getCenter().getY() - getRadius(),
                getRadius() * 2,
                getRadius() * 2);

        Shape inner = new Ellipse2D.Double(
                getCenter().getX() - innerRadius,
                getCenter().getY() - innerRadius,
                innerRadius * 2,
                innerRadius * 2);

        Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
    }

    @Override
    public boolean compareTo(Object object) {
        if (object instanceof Donut) {
            return (getCenter().compareTo(((Donut) object).getCenter()) && getRadius() == ((Donut) object).getRadius() && getInnerRadius() == ((Donut) object).getInnerRadius());

        } else {
            return false;
        }
    }

    @Override
    public void selected(Graphics graphic) {
        graphic.setColor(Color.green);
        super.selected(graphic);
        new Line(new Point(super.getCenter().getX() - innerRadius, super.getCenter().getY()),
                new Point(super.getCenter().getX() + innerRadius, super.getCenter().getY())).selected(graphic);
        new Line(new Point(super.getCenter().getX(), super.getCenter().getY() - innerRadius),
                new Point(super.getCenter().getX(), super.getCenter().getY() + innerRadius)).selected(graphic);
    }

    @Override
    public String toString() {
        return "Donut: center" + super.getCenter().getCordinatesText() + " radius:" + getRadius() + " InnerRadius:" + getInnerRadius() + " " + getColorText() + getInnerColorText();
    }

}
