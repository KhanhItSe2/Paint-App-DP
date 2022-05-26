package message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class msgDonut extends JDialog {

    private JTextField RadiusTxt;
    private JTextField InnerRadiusTxt;

    private final JPanel content = new JPanel();
    private boolean mark;

    public JTextField getInnerRadiusTxt() {
        return InnerRadiusTxt;
    }

    public void setInnerRadiusTxt(JTextField InnerRadiusTxt) {
        this.InnerRadiusTxt = InnerRadiusTxt;
    }

    public JTextField getTextRadius() {
        return RadiusTxt;
    }

    public void setTextradius(JTextField RadiusTxt) {
        this.RadiusTxt = RadiusTxt;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public void validate(String radius, String innerRadius) {
        String chk = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if (!radius.matches(chk) || !innerRadius.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {
        try {
            msgDonut box = new msgDonut();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public msgDonut() {
        setBounds(100, 100, 300, 237);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);

        JLabel RadiusLbl = new JLabel("Radius: ");
        RadiusLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel InnerRaLbl = new JLabel("Inner radius: ");
        InnerRaLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));

        InnerRadiusTxt = new JTextField();
        InnerRadiusTxt.setForeground(Color.RED);
        InnerRadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InnerRadiusTxt.setColumns(10);
        RadiusTxt = new JTextField();
        RadiusTxt.setForeground(Color.RED);
        RadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RadiusTxt.setColumns(10);
        setLocationRelativeTo(null);
        setModal(true);
        setTitle("Donut");
        GroupLayout g_contentField = new GroupLayout(content);
        g_contentField.setHorizontalGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                .addGroup(g_contentField.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                                .addComponent(InnerRaLbl)
                                .addComponent(RadiusLbl))
                        .addGap(20)
                        .addGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                                .addComponent(InnerRadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))
        );
        g_contentField.setVerticalGroup(g_contentField.createParallelGroup(Alignment.LEADING)
                .addGroup(g_contentField.createSequentialGroup()
                        .addGap(25)
                        .addGroup(g_contentField.createParallelGroup(Alignment.BASELINE)
                                .addComponent(RadiusLbl)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addGroup(g_contentField.createParallelGroup(Alignment.BASELINE)
                                .addComponent(InnerRaLbl)
                                .addComponent(InnerRadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))
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
                        if (InnerRadiusTxt.getText().trim().equals("") || RadiusTxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "All fields are empty", "Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        try {
                            validate(RadiusTxt.getText(), InnerRadiusTxt.getText());

                        } catch (NumberFormatException exe) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }

                        if (Integer.parseInt(InnerRadiusTxt.getText()) >= Integer.parseInt(RadiusTxt.getText())) {
                            JOptionPane.showMessageDialog(null, "Inner radius can't be greater than or equal to the outer radius", "Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else if (Integer.parseInt(InnerRadiusTxt.getText()) < 0 || Integer.parseInt(RadiusTxt.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Fields must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
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
