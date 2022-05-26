package view;

import javax.swing.JFrame;

public class Application {
    //Start point of application

    public static void main(String[] args) {
        Model m = new Model();
        DrawingFunction paint = new DrawingFunction();

        paint.getView().setModel(m);
        Controller controller = new Controller(m, paint);
        paint.setController(controller);

        paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint.setVisible(true);
        paint.setLocationRelativeTo(null);

    }

}
