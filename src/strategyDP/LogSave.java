package strategyDP;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogSave implements SavingMethod { // Class for saving log file

    private DefaultListModel<String> model;

    public LogSave(DefaultListModel<String> defaultListModel) {
        this.model = defaultListModel;
    }

    @Override
    public void save() {
        JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Desktop");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
        jFileChooser.setDialogTitle("Please choose location to save!");

        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File log = new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".log");

            if (log.exists()) {
                JOptionPane.showMessageDialog(null, "Filename is existed!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    PrintWriter printWriter = new PrintWriter(log);
                    for (int i = 0; i < model.size(); i++) {
                        printWriter.println(model.get(i));
                    }
                    printWriter.close();
                    JOptionPane.showMessageDialog(null, "Saved Successfully ", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while saving the file", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

}
