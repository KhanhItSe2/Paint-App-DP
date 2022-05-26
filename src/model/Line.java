package model;

import java.awt.Color;
import java.awt.Graphics;

import model.Point;

public class Line extends Shape implements Cloneable {

    private static final long serialVersionUID = 1L;
    private Point start;
    private Point end;

    public Line() {

    }

    public Line(Point startPoint, Point endPoint) {
        this.start = startPoint;
        this.end = endPoint;
    }

    public Line(Point startPoint, Point endPoint, Color color) {
        this(startPoint, endPoint);
        super.setColor(color);
    }

    public Point middleOfLine() {
        int middleX = (this.start.getX() + this.end.getX()) / 2;
        int middleY = (this.start.getY() + this.end.getY()) / 2;
        Point point = new Point(middleX, middleY);
        return point;
    }

    public double length() {
        return start.distance(end.getX(), end.getY());
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public Line clone() {
        Line lineClone = new Line();
        lineClone.setStart(new Point(start.getX(), start.getY()));
        lineClone.setEnd(new Point(end.getX(), end.getY()));
        lineClone.setColor(getColor());

        return lineClone;
    }

    @Override
    public boolean contains(int x, int y) {
        if ((start.distance(x, y) + end.distance(x, y)) - length() <= 0.05) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics graphic) {
        graphic.setColor(getColor());
        graphic.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
        if (isSelected()) {
            selected(graphic);
        }
    }

    @Override
    public boolean compareTo(Object object) {
        if (object instanceof Line) {
            return (start.compareTo(((Line) object).getStart()) && end.compareTo(((Line) object).getEnd()));
        } else {
            return false;
        }
    }

    @Override
    public void selected(Graphics graphic) {
        graphic.setColor(Color.green);
        start.selected(graphic);
        end.selected(graphic);
        middleOfLine().selected(graphic);

    }

    @Override
    public String toString() {
        return "Line: start" + start.getCordinatesText() + "end" + end.getCordinatesText() + getColorText();
    }

}
