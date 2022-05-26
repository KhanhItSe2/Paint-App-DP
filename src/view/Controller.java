package view;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import commandDP.Command;
import commandDP.positions.BringToBack;
import commandDP.positions.BringToFront;
import commandDP.positions.Back;
import commandDP.positions.Front;
import commandDP.select.Deselect;
import commandDP.select.Select;
import commandDP.shapes.Add;
import commandDP.shapes.Remove;
import commandDP.shapes.UpdateCircle;
import commandDP.shapes.UpdateDonut;
import commandDP.shapes.UpdateLine;
import commandDP.shapes.UpdatePoint;
import commandDP.shapes.UpdateRectangle;

import message.msgCircle;
import message.msgDonut;
import message.msgRectangle;
import model.Circle;
import model.Donut;
import model.Line;
import model.Point;
import model.Rectangle;
import model.Shape;

import editBox.CircleEdt;
import editBox.DonutEdt;
import editBox.LineEdt;
import editBox.PointEdt;
import editBox.RectangleEdt;

import observerDP.ShapeObserver;

import strategyDP.PaintSave;
import strategyDP.LogSave;
import strategyDP.SavingManager;

public class Controller implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Stack<Command> unredo = new Stack<>();
    private int unredoPointer = -1;

    private Model m;
    private DrawingFunction f;

    private BufferedReader bR;
    private String line;

    private Point pointOne;

    public Controller(Model m, DrawingFunction f) {
        this.m = m;
        this.f = f;
    }

    public void addShape(MouseEvent e) {

        Point click = new Point(e.getX(), e.getY());
        if (f.getPoint().isSelected()) { //Point button is active 
            Point point = new Point(e.getX(), e.getY(), f.getBorderColor());

            addToCommandStack(new Add(m, point));
            point.addObserver(new ShapeObserver(f, m));

        } else if (f.getRectangle().isSelected()) {//Rectangle button is active
            msgRectangle drawRec = new msgRectangle();
            drawRec.setVisible(true);

            if (drawRec.isMark()) {
                Rectangle r = new Rectangle(click, Integer.parseInt(drawRec.getWidthTxt().getText()),
                        Integer.parseInt(drawRec.getHeightTxt().getText()), f.getBorderColor(), f.getFillColor());

                addToCommandStack(new Add(m, r));

                r.addObserver(new ShapeObserver(f, m));

            }

        } else if (f.getLine().isSelected()) { //Line button is active  
            if (pointOne == null) { //Clicked first time 
                pointOne = new Point(e.getX(), e.getY());
            } else {

                Point pointTwo = new Point(e.getX(), e.getY());
                Line line = new Line(pointOne, pointTwo, f.getBorderColor());
                pointOne = null;
                addToCommandStack(new Add(m, line));
                line.addObserver(new ShapeObserver(f, m));
            }
        } else if (f.getDonut().isSelected()) { //Donut button is active 
            msgDonut drawDonut = new msgDonut();
            drawDonut.setVisible(true);

            if (drawDonut.isMark()) {
                Donut d = new Donut(click, Integer.parseInt(drawDonut.getTextRadius().getText()), Integer.parseInt(drawDonut.getInnerRadiusTxt().getText()), f.getBorderColor(), f.getFillColor());

                addToCommandStack(new Add(m, d));

                d.addObserver(new ShapeObserver(f, m));

            }

        } else if (f.getCircle().isSelected()) { //Circle button is active 
            msgCircle drawCircle = new msgCircle();
            drawCircle.setVisible(true);

            if (drawCircle.isMark()) {
                Circle c = new Circle(click, Integer.parseInt(drawCircle.getTextRadius().getText()), f.getBorderColor(), f.getFillColor());
                addToCommandStack(new Add(m, c));
                c.addObserver(new ShapeObserver(f, m));
            }
        }
    }

    public void deleteShape() {
        ArrayList<Shape> ss = new ArrayList<>();

        if (f.getDelBtn().isEnabled()) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to delete shapes?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {

                for (Shape s : m.getShapes()) {
                    if (s.isSelected()) {
                        ss.add(s);
                    }
                }
                addToCommandStack(new Remove(m, ss));
            }
        }
    }

    //Undo and Redo command pattern 
    public void undo() {
        unredo.get(unredoPointer).exec();
        f.addHistory("Undo:" + unredo.get(unredoPointer).toString());
        unredoPointer--;
        f.repaint();
        f.getRedoBtn().setEnabled(true);
        if (unredoPointer == -1) {
            f.getUndoBtn().setEnabled(false);
        }
    }

    public void redo() {
        unredoPointer++;
        unredo.get(unredoPointer).exec();
        f.addHistory("Redo:" + unredo.get(unredoPointer).toString());
        f.repaint();
        f.getUndoBtn().setEnabled(true);
        if (unredo.size() - 1 <= unredoPointer) {
            f.getRedoBtn().setEnabled(false);
        }
    }

    //Select and Deselect shapes  
    public void selectShape(MouseEvent e) {
        for (int i = m.getShapes().size() - 1; i >= 0; i--) {
            if (m.get(i).contains(e.getX(), e.getY())) {
                if (m.get(i).isSelected() == false) {
                    addToCommandStack(new Select(m.get(i)));
                    break;
                } else {
                    addToCommandStack(new Deselect(m.get(i)));
                    break;
                }
            }
        }
    }

    //Edit Selected Shape
    public void editShape() {
        for (Shape s : m.getShapes()) {
            if (s.isSelected()) {
                if (s instanceof Point) {
                    PointEdt edtPoint = new PointEdt();
                    edtPoint.fillAll((Point) s);
                    edtPoint.setVisible(true);
                    if (edtPoint.isMark()) {
                        Point p = new Point(edtPoint.getX(), edtPoint.getY(), edtPoint.getColor());
                        addToCommandStack(new UpdatePoint((Point) s, p));
                    }
                } else if (s instanceof Circle) {
                    CircleEdt edtCircle = new CircleEdt();
                    edtCircle.fillAll((Circle) s);
                    edtCircle.setVisible(true);
                    if (edtCircle.isMark()) {
                        Circle c = new Circle(new Point(edtCircle.getX(), edtCircle.getY()), edtCircle.getRadius(), edtCircle.getColor(), edtCircle.getInnerColor());
                        addToCommandStack(new UpdateCircle((Circle) s, c));
                    }
                } else if (s instanceof Rectangle) {
                    RectangleEdt edtRectangle = new RectangleEdt();
                    edtRectangle.fillAll((Rectangle) s);
                    edtRectangle.setVisible(true);
                    if (edtRectangle.isMark()) {
                        Rectangle r = new Rectangle(new Point(edtRectangle.getX(), edtRectangle.getY()), edtRectangle.getHeightRec(), edtRectangle.getWidthRec(), edtRectangle.getBorderColor(), edtRectangle.getInnerColor());
                        addToCommandStack(new UpdateRectangle((Rectangle) s, r));
                    }

                } else if (s instanceof Line) {
                    LineEdt edtLine = new LineEdt();
                    edtLine.fillAll((Line) s);
                    edtLine.setVisible(true);
                    if (edtLine.isMark()) {
                        Line l = new Line(new Point(edtLine.getStartX(), edtLine.getStartY()), new Point(edtLine.getEndX(), edtLine.getEndY()), edtLine.getColor());
                        addToCommandStack(new UpdateLine((Line) s, l));
                    }

                } else if (s instanceof Donut) {
                    DonutEdt edtDonut = new DonutEdt();
                    edtDonut.fillAll((Donut) s);
                    edtDonut.setVisible(true);
                    if (edtDonut.isMark()) {
                        Donut don = new Donut(new Point(edtDonut.getX(), edtDonut.getY()), edtDonut.getRadius(), edtDonut.getInnerRadius(), edtDonut.getColor(), edtDonut.getInnerColor());
                        addToCommandStack(new UpdateDonut((Donut) s, don));
                    }

                }
            }
        }
    }

    //Called when finished certain command
    public void addToCommandStack(Command c) {
        clearCommandStack(unredoPointer); //Clearing stack to certain point because when we add new add new shape we can not redo again.
        unredo.push(c);
        c.exec();
        unredoPointer++; //every time we execute action, executed command is added to unodRedoStack so we increment it. 

        f.addHistory(c.toString());// Log all action(command) that user did
        f.repaint(); //Refresh panel on every action
        f.getUndoBtn().setEnabled(true);
        f.getRedoBtn().setEnabled(false);
        f.getSelectBtn().setEnabled(true);

    }

    //Clearing undoRedo stack because of new command 
    private void clearCommandStack(int undoRedoStackPointer) {
        if (unredo.size() < 1) {
            return;
        }
        for (int i = unredo.size() - 1; i > undoRedoStackPointer; i--) {
            unredo.remove(i);
        }
    }

    //Pushing selected shape on top of the frame
    public void toFront() {
        Iterator<Shape> iterator = m.getShapes().iterator();
        while (iterator.hasNext()) {
            Shape S = iterator.next();
            if (S.isSelected()) {
                if (m.getShapes().indexOf(S) == m.getShapes().size() - 1) {
                    JOptionPane.showMessageDialog(null, "Shape is already in front");//Checking if shape is on top already						
                    break;
                } else {
                    if (m.getShapes().indexOf(S) != m.getShapes().size() - 1) {
                        addToCommandStack(new Front(m, S));
                        f.repaint();
                        break;
                    }
                }
            }
        }
    }

    //Pushing selected shape on bottom of the frame
    public void toBack() {
        Iterator<Shape> iterator = m.getShapes().iterator();
        while (iterator.hasNext()) {
            Shape S = iterator.next();
            if (S.isSelected()) {
                //Checking if shape is on bottom already
                if (m.getShapes().indexOf(S) == 0) {
                    JOptionPane.showMessageDialog(null, "Shape is already in back!");
                    break;
                } else {
                    addToCommandStack(new Back(m, S));
                    break;
                }
            }
        }
    }

    //Pushing selected shape on bottom of another shape that was under it
    public void bringToBack() {
        Iterator<Shape> iterator = m.getShapes().iterator();
        while (iterator.hasNext()) {
            Shape s = iterator.next();

            if (s.isSelected()) {

                if (m.getShapes().indexOf(s) == 0) {
                    //Checking if shape is on bottom already	
                    JOptionPane.showMessageDialog(null, "Shape is already in back!");
                    break;
                } else {
                    addToCommandStack(new BringToBack(m, s));
                    break;
                }
            }
        }
    }

    //Pushing selected shape on top of another shape that was above it
    public void bringToFront() {
        Iterator<Shape> iterator = m.getShapes().iterator();
        while (iterator.hasNext()) {
            Shape s = iterator.next();
            if (s.isSelected()) {
                //Checking if shape is on top already
                if (m.getShapes().indexOf(s) == m.getShapes().size() - 1) {
                    JOptionPane.showMessageDialog(null, "Shape is already in front!");
                    break;
                } else {
                    addToCommandStack(new BringToFront(m, s));
                    break;
                }
            }
        }
    }

    //Choosing option from save dialog
    public void save(int option) {
        if (option == JOptionPane.YES_OPTION) {
            SavingManager logManager = new SavingManager(new LogSave(f.getLM()));
            logManager.save();
        } else if (option == JOptionPane.NO_OPTION) {
            SavingManager drawingManager = new SavingManager(new PaintSave(m.getShapes()));
            drawingManager.save();
        }
    }

    //Loading .png or .log file
    public void open(int opt) {
        if (opt == 0) // Log option chosen
        {
            JFileChooser file = new JFileChooser("C:\\Users\\Desktop");
            file.setFileFilter(new FileNameExtensionFilter("Log file (.log)", "log"));
            file.setDialogTitle("Open Log File");
            int chosen = file.showOpenDialog(null);
            if (chosen == JFileChooser.APPROVE_OPTION) {
                File logFile = new File(file.getSelectedFile().getAbsolutePath());
                try {
                    //returning to original state
                    f.getUndoBtn().setEnabled(false);
                    unredo.clear();
                    unredoPointer = -1;
                    m.getShapes().clear();
                    f.getLM().clear();
                    f.getNextBtn().setVisible(true);
                    f.getNextBtn().setEnabled(true);
                    f.getNextBtn().setEnabled(true);
                    bR = new BufferedReader(new FileReader(logFile));
                    f.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while opening log file", "Error!", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (opt == 1) // Drawing option chosen
        {
            JFileChooser file = new JFileChooser("C:\\Users\\Desktop");
            file.setFileFilter(new FileNameExtensionFilter("png file (.png)", "png"));
            file.setDialogTitle("Open PNG File");
            int choosen = file.showOpenDialog(null);
            if (choosen == JFileChooser.APPROVE_OPTION) {
                File serFile = new File(file.getSelectedFile().getAbsolutePath());
                try {
                    FileInputStream fileInputStream = new FileInputStream(serFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    f.getUndoBtn().setEnabled(false);
                    unredo.clear();
                    unredoPointer = -1;
                    m.getShapes().clear();
                    f.getLM().clear();
                    @SuppressWarnings("unchecked")
                    ArrayList<Shape> list = (ArrayList<Shape>) objectInputStream.readObject();

                    for (Shape s : list) {
                        m.add(s);
                        s.addObserver(new ShapeObserver(f, m));
                        if (s.isSelected()) {
                            s.setSelected(true);
                        }
                    }
                    f.getSelectBtn().setEnabled(true);
                    objectInputStream.close();
                    fileInputStream.close();
                    JOptionPane.showMessageDialog(null, "Loaded succesifuly", "Succesful!", JOptionPane.INFORMATION_MESSAGE);
                    f.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while opening the file", "Error!", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    //Load drawing from log, and every time clicks on load next, next command on log file is loaded.
    public void loadNext() {
        try {
            //Checking if next line have command.  
            if ((line = bR.readLine()) != null) {
                bR.mark(1);
                // Checking for content in line
                checkLine();
                //Regex for splitting command line in log file to smaller segments and adding them to array.
                String[] command = line.split("\\W");
                if (command[1].contentEquals("Point")) {
                    String action = command[0];
                    Point point = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]), new Color(Integer.parseInt(command[5]), Integer.parseInt(command[6]), Integer.parseInt(command[7])));
                    switch (action) {
                        case "Added":
                            addToCommandStack(new Add(m, point));
                            point.addObserver(new ShapeObserver(f, m));
                            break;
                        case "Selected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Point) {
                                    if (point.compareTo((Point) s)) {
                                        addToCommandStack(new Select(s));
                                    }
                                }
                            }
                            break;
                        case "Deselected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Point) {
                                    if (point.compareTo((Point) s)) {
                                        addToCommandStack(new Deselect(s));
                                    }
                                }
                            }
                            break;
                        case "Updated":
                            Point newPoint = new Point(Integer.parseInt(command[12]), Integer.parseInt(command[13]), new Color(Integer.parseInt(command[15]), Integer.parseInt(command[16]), Integer.parseInt(command[17])));
                            for (Shape shape : m.getShapes()) {
                                if (shape instanceof Point) {
                                    if (point.compareTo((Point) shape)) {
                                        addToCommandStack(new UpdatePoint((Point) shape, newPoint));
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else if (command[1].contentEquals("Line")) {
                    String action = command[0];
                    Line line = new Line(new Point(Integer.parseInt(command[3]), Integer.parseInt(command[4])), new Point(Integer.parseInt(command[6]), Integer.parseInt(command[7])), new Color(Integer.parseInt(command[9]), Integer.parseInt(command[10]), Integer.parseInt(command[11])));
                    switch (action) {
                        case "Added":
                            addToCommandStack(new Add(m, line));
                            line.addObserver(new ShapeObserver(f, m));
                            break;
                        case "Selected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Line) {
                                    if (line.compareTo((Shape) s)) {
                                        addToCommandStack(new Select(s));
                                    }
                                }
                            }
                            break;
                        case "Deselected":

                            for (Shape s : m.getShapes()) {
                                if (s instanceof Line) {
                                    if (line.compareTo((Shape) s)) {
                                        addToCommandStack(new Deselect(s));
                                    }
                                }
                            }
                            break;
                        case "Updated":
                            Line newLine = new Line(new Point(Integer.parseInt(command[17]), Integer.parseInt(command[18])), new Point(Integer.parseInt(command[20]), Integer.parseInt(command[21])), new Color(Integer.parseInt(command[23]), Integer.parseInt(command[24]), Integer.parseInt(command[25])));
                            for (Shape shape : m.getShapes()) {
                                if (shape instanceof Line) {
                                    if (line.compareTo((Shape) shape)) {
                                        addToCommandStack(new UpdateLine((Line) shape, newLine));
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else if (command[1].contentEquals("Circle")) {
                    String action = command[0];
                    Circle circle = new Circle(new Point(Integer.parseInt(command[3]), Integer.parseInt(command[4])), Integer.parseInt(command[7]), new Color(Integer.parseInt(command[9]), Integer.parseInt(command[10]), Integer.parseInt(command[11])), new Color(Integer.parseInt(command[14]), Integer.parseInt(command[15]), Integer.parseInt(command[16])));
                    switch (action) {
                        case "Added":
                            addToCommandStack(new Add(m, circle));
                            circle.addObserver(new ShapeObserver(f, m));
                            break;
                        case "Selected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Circle) {
                                    if (circle.compareTo((Shape) s)) {
                                        addToCommandStack(new Select(s));
                                    }
                                }
                            }
                            break;
                        case "Deselected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Circle) {
                                    if (circle.compareTo((Shape) s)) {
                                        addToCommandStack(new Deselect(s));
                                    }
                                }
                            }
                            break;
                        case "Updated":
                            Circle newCircle = new Circle(new Point(Integer.parseInt(command[22]), Integer.parseInt(command[23])), Integer.parseInt(command[26]), new Color(Integer.parseInt(command[28]), Integer.parseInt(command[29]), Integer.parseInt(command[30])), new Color(Integer.parseInt(command[33]), Integer.parseInt(command[34]), Integer.parseInt(command[35])));
                            for (Shape shape : m.getShapes()) {
                                if (shape instanceof Circle) {
                                    if (circle.compareTo((Shape) shape)) {
                                        addToCommandStack(new UpdateCircle((Circle) shape, newCircle));
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else if (command[1].contentEquals("Rectangle")) {
                    String action = command[0];
                    Rectangle rectangle = new Rectangle(new Point(Integer.parseInt(command[5]), Integer.parseInt(command[6])), Integer.parseInt(command[10]), Integer.parseInt(command[14]), new Color(Integer.parseInt(command[16]), Integer.parseInt(command[17]), Integer.parseInt(command[18])), new Color(Integer.parseInt(command[21]), Integer.parseInt(command[22]), Integer.parseInt(command[23])));
                    switch (action) {
                        case "Added":
                            addToCommandStack(new Add(m, rectangle));
                            rectangle.addObserver(new ShapeObserver(f, m));
                            break;
                        case "Selected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Rectangle) {
                                    if (rectangle.compareTo((Shape) s)) {
                                        addToCommandStack(new Select(s));
                                    }
                                }
                            }
                            break;

                        case "Deselected":

                            for (Shape s : m.getShapes()) {
                                if (s instanceof Rectangle) {
                                    if (rectangle.compareTo((Shape) s)) {

                                        addToCommandStack(new Deselect(s));
                                    }
                                }
                            }
                            break;
                        case "Updated":
                            Rectangle newRectangle = new Rectangle(new Point(Integer.parseInt(command[33]), Integer.parseInt(command[34])), Integer.parseInt(command[38]), Integer.parseInt(command[42]), new Color(Integer.parseInt(command[44]), Integer.parseInt(command[45]), Integer.parseInt(command[46])), new Color(Integer.parseInt(command[49]), Integer.parseInt(command[50]), Integer.parseInt(command[51])));
                            for (Shape shape : m.getShapes()) {
                                if (shape instanceof Rectangle) {
                                    if (rectangle.compareTo((Shape) shape)) {
                                        addToCommandStack(new UpdateRectangle((Rectangle) shape, newRectangle));
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else if (command[1].contentEquals("Donut")) {
                    String action = command[0];
                    Donut donut = new Donut(new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])), Integer.parseInt(command[8]), Integer.parseInt(command[10]), new Color(Integer.parseInt(command[12]), Integer.parseInt(command[13]), Integer.parseInt(command[14])), new Color(Integer.parseInt(command[17]), Integer.parseInt(command[18]), Integer.parseInt(command[19])));
                    switch (action) {
                        case "Added":
                            addToCommandStack(new Add(m, donut));
                            donut.addObserver(new ShapeObserver(f, m));
                            break;
                        case "Selected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Donut) {
                                    if (donut.compareTo((Shape) s)) {
                                        addToCommandStack(new Select(s));
                                    }
                                }
                            }
                            break;
                        case "Deselected":
                            for (Shape s : m.getShapes()) {
                                if (s instanceof Donut) {
                                    if (donut.compareTo((Shape) s)) {
                                        if (s.isSelected()) {
                                            addToCommandStack(new Deselect(s));
                                        }
                                    }
                                }
                            }
                            break;
                        case "Updated":
                            Donut newDonut = new Donut(new Point(Integer.parseInt(command[28]), Integer.parseInt(command[29])), Integer.parseInt(command[32]), Integer.parseInt(command[34]), new Color(Integer.parseInt(command[36]), Integer.parseInt(command[37]), Integer.parseInt(command[38])), new Color(Integer.parseInt(command[41]), Integer.parseInt(command[42]), Integer.parseInt(command[43])));
                            for (Shape shape : m.getShapes()) {
                                if (shape instanceof Donut) {
                                    if (donut.compareTo((Shape) shape)) {
                                        addToCommandStack(new UpdateDonut((Donut) shape, newDonut));
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                // Actions no need to check
                String action = command[0];
                switch (action) {
                    case "undo":
                        undo();
                        break;
                    case "redo":
                        redo();
                        break;
                    case "ToFront":
                        toFront();
                        break;
                    case "ToBack":
                        toBack();
                        break;
                    case "BringToFront":
                        bringToFront();
                        break;
                    case "BringToBack":
                        bringToBack();
                        break;
                    //removing selected shapes, if add shape between load next iterations, deletes it too.
                    case "Removed":
                        ArrayList<Shape> shapes = new ArrayList<>();
                        for (Shape shape : m.getShapes()) {
                            if (shape.isSelected()) {
                                shapes.add(shape);
                            }
                        }
                        addToCommandStack(new Remove(m, shapes));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while opening the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Method that closes bufferReader if there are no content in log file
    private void checkLine() {
        try {
            if (bR.readLine() == null) {
                f.getNextBtn().setEnabled(false);
                f.getNextBtn().setVisible(false);
                bR.close();
            } else {
                bR.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while opening the file", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
