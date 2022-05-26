package message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class msgRectangle extends JDialog {

    private JTextField WidthTxt;
    private JTextField HeightTxt;
    private final JPanel content = new JPanel();
    private boolean Mark;

    public boolean isMark() {
        return Mark;
    }

    public void setMark(boolean mark) {
        this.Mark = mark;
    }

    public JTextField getWidthTxt() {
        return WidthTxt;
    }

    public void setWidthTxt(JTextField WidthTxt) {
        this.WidthTxt = WidthTxt;
    }

    public JTextField getHeightTxt() {
        return HeightTxt;
    }

    public void setHeightTxt(JTextField HeightTxt) {
        this.HeightTxt = HeightTxt;
    }

    public void validate(String width, String height) {
        String chk = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if (!width.matches(chk) || !height.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {
        try {
            msgRectangle box = new msgRectangle();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public msgRectangle() {
        setBounds(100, 100, 300, 210);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setTitle("Rectangle");
        setModal(true);

        JLabel WidthLbl = new JLabel("Width: ");
        WidthLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel HeightLbl = new JLabel("Height: ");
        HeightLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));

        WidthTxt = new JTextField();
        WidthTxt.setForeground(Color.RED);
        WidthTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        WidthTxt.setColumns(10);

        HeightTxt = new JTextField();
        HeightTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        HeightTxt.setForeground(Color.RED);
        HeightTxt.setColumns(10);
        GroupLayout g_contentField = new GroupLayout(content);
        g_contentField.setVerticalGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                .addGroup(g_contentField.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_contentField.createParallelGroup(Alignment.BASELINE)
                                .addComponent(WidthLbl)
                                .addComponent(WidthTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addGroup(g_contentField.createParallelGroup(Alignment.TRAILING)
                                .addComponent(HeightLbl, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                .addComponent(HeightTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(118, Short.MAX_VALUE))
        );
        g_contentField.setHorizontalGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                .addGroup(g_contentField.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                                .addComponent(WidthLbl)
                                .addComponent(HeightLbl))
                        .addGap(45)
                        .addGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                                .addComponent(WidthTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(HeightTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(60, Short.MAX_VALUE))
        );

        content.setLayout(g_contentField);
        {
            JPanel btn = new JPanel();
            btn.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(btn, BorderLayout.SOUTH);
            {
                JButton DrawBtn = new JButton("Draw");
                DrawBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
                DrawBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean number = true;
                        try {
                            validate(getWidthTxt().getText(), getHeightTxt().getText());

                        } catch (NumberFormatException n) {
                            number = false;
                        }

                        if (getWidthTxt().getText().trim().isEmpty() || getHeightTxt().getText().trim().isEmpty()) {
                            Mark = false;
                            getToolkit().beep();
                            JOptionPane.showMessageDialog(null, "All fields are empty", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                        } else if (number == false) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            getToolkit().beep();
                            Mark = false;
                        } else if (Float.parseFloat(getWidthTxt().getText()) < 0
                                || Float.parseFloat(HeightTxt.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Width and Height must be greater than 0", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            getToolkit().beep();
                            Mark = false;

                        } else {
                            Mark = true;
                            dispose();
                        }
                    }
                });
                DrawBtn.setActionCommand("OK");
                btn.add(DrawBtn);
                getRootPane().setDefaultButton(DrawBtn);
            }
            {
                JButton CancelBtn = new JButton("Cancel");
                CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
                CancelBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                CancelBtn.setActionCommand("Cancel");
                btn.add(CancelBtn);
            }
        }
    }

}
