package observerDP;

import java.util.Observable;

import model.Shape;
import view.Model;
import view.DrawingFunction;

public class ShapeObserver implements java.util.Observer {

    private DrawingFunction frame;
    private Model model;

    public ShapeObserver(DrawingFunction frame, Model model) {
        this.frame = frame;
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        int flag = getSelectedShapes();
        // None of Shapes is selected
        if (flag == 0) {
            frame.getEdtBtn().setEnabled(false);
            frame.getDelBtn().setEnabled(false);

            frame.getBringFront().setEnabled(false);
            frame.getBringBack().setEnabled(false);
            frame.getFrontBtn().setEnabled(false);
            frame.getBackBtn().setEnabled(false);

        }//One shape is selected
        else if (flag > 1) {

            frame.getEdtBtn().setEnabled(false);
            frame.getDelBtn().setEnabled(true);

            frame.getBringFront().setEnabled(false);
            frame.getBringBack().setEnabled(false);
            frame.getFrontBtn().setEnabled(false);
            frame.getBackBtn().setEnabled(false);

        }//All options are available	 
        else {
            frame.getEdtBtn().setEnabled(true);
            frame.getDelBtn().setEnabled(true);

            frame.getBringFront().setEnabled(true);
            frame.getBringBack().setEnabled(true);
            frame.getFrontBtn().setEnabled(true);
            frame.getBackBtn().setEnabled(true);
        }

    }

    // Mark shape is selected
    public int getSelectedShapes() {
        int count = 0;
        for (Shape s : model.getShapes()) {
            if (s.isSelected()) {
                count++;
            }
        }
        return count;
    }

}
