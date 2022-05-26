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
public class msgCircle extends JDialog {

    private boolean mark;
    private JTextField RadiusTxt;
    private final JPanel content = new JPanel();

    public JTextField getTextRadius() {
        return RadiusTxt;
    }

    public void setTextRadius(JTextField RadiusTxt) {
        this.RadiusTxt = RadiusTxt;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean flag) {
        this.mark = flag;
    }

    public static void main(String[] args) {
        try {
            msgCircle box = new msgCircle();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate(String radius) {
        String chk = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if (!radius.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public msgCircle() {
        setBounds(100, 100, 270, 176);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        JLabel RadiusLbl = new JLabel("Radius:");
        RadiusLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        RadiusTxt = new JTextField();
        RadiusTxt.setForeground(Color.RED);
        RadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RadiusTxt.setColumns(10);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Circle");

        GroupLayout g_contentField = new GroupLayout(content);
        g_contentField.setVerticalGroup(
                g_contentField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_contentField.createSequentialGroup()
                                .addGap(36)
                                .addGroup(g_contentField.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(RadiusLbl))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        g_contentField.setHorizontalGroup(
                g_contentField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_contentField.createSequentialGroup()
                                .addGap(25)
                                .addComponent(RadiusLbl)
                                .addGap(15)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
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
                        try {
                            validate(RadiusTxt.getText());
                        } catch (NumberFormatException exc) {
                            JOptionPane.showMessageDialog(null, "Data type is wrong", "Error", JOptionPane.ERROR_MESSAGE, null);
                            getToolkit().beep();
                            mark = false;
                            return;
                        }
                        if (Integer.parseInt(RadiusTxt.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Radius must be more than 0!", "Error", JOptionPane.ERROR_MESSAGE, null);
                            getToolkit().beep();
                            mark = false;
                        } else if (RadiusTxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Radius text field is empty", "Error", JOptionPane.ERROR_MESSAGE, null);
                            getToolkit().beep();
                            mark = false;
                        } else {
                            mark = true;
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
                CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
