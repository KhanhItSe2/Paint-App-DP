package strategyDP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Shape;

public class PaintSave implements SavingMethod {

    private ArrayList<Shape> shapes;

    public PaintSave(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void save() {

        JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Desktop");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("png file (.png)", "png"));
        jFileChooser.setDialogTitle("Location has to be chosen!");

        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            File product = new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".png");

            if (product.exists()) {
                JOptionPane.showMessageDialog(null, "Filename is existed", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                FileOutputStream fileOutputStream;
                ObjectOutputStream objectOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(product);
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);

                    objectOutputStream.writeObject(shapes);
                    objectOutputStream.close();
                    fileOutputStream.close();

                    JOptionPane.showMessageDialog(null, " Saved Successfully ", "Message",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while saving the file", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

}
