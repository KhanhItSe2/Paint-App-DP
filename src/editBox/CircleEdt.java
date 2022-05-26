package editBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Circle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

@SuppressWarnings("serial")
public class CircleEdt extends JDialog {

    private final JPanel content = new JPanel();
    private Color color;
    private Color innerColor;
    private JTextField RadiusTxt;
    private JTextField CenterXtxt;
    private JTextField CenterYtxt;
    private JTextField Colortxt;
    private JTextField InnerColortxt;
    private Circle circle;

    private int x, y, radius;
    private boolean mark;

    public Color getColor() {
        return color;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isMark() {
        return mark;
    }

    public void validation(String x, String y, String radius) {
        String chk = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if (x.matches("") || y.matches("") || radius.matches("")) {

        } else if (!x.matches(chk) || !y.matches(chk) || !radius.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {
        try {
            CircleEdt box = new CircleEdt();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillAll(Circle newCircle) {
        this.circle = newCircle;
        CenterXtxt.setText(String.valueOf(circle.getCenter().getX()));
        CenterYtxt.setText(String.valueOf(circle.getCenter().getY()));
        RadiusTxt.setText(String.valueOf(circle.getRadius()));
        Colortxt.setBackground(circle.getColor());
        InnerColortxt.setBackground(circle.getInnerColor());
        color = circle.getColor();
        innerColor = circle.getInnerColor();
    }

    public CircleEdt() {
        setBounds(100, 100, 359, 358);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Circle");
        JLabel CenterXLbl = new JLabel("Center X coordinate: ");
        CenterXLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel CenterYLbl = new JLabel("Center Y coordinate: ");
        CenterYLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        CenterXtxt = new JTextField();
        CenterXtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CenterXtxt.setColumns(10);
        CenterYtxt = new JTextField();
        CenterYtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CenterYtxt.setColumns(10);
        JButton borderColBtn = new JButton("Border Color: ");
        borderColBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        borderColBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null, "Select Border Color", circle.getColor());
                if (color == null) {
                    color = circle.getColor();
                }
                Colortxt.setBackground(color);
            }
        });
        JButton FillColorBtn = new JButton("Fill Color: ");
        FillColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        FillColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                innerColor = JColorChooser.showDialog(null, "Fill Inner Color", circle.getInnerColor());
                if (innerColor == null) {
                    innerColor = circle.getInnerColor();
                }
                InnerColortxt.setBackground(innerColor);
            }
        });
        Colortxt = new JTextField();
        Colortxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Colortxt.setEditable(false);
        Colortxt.setColumns(10);
        InnerColortxt = new JTextField();
        InnerColortxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InnerColortxt.setEditable(false);
        InnerColortxt.setColumns(10);

        JLabel lblRadius = new JLabel("Radius: ");
        lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 14));

        RadiusTxt = new JTextField();
        RadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RadiusTxt.setColumns(10);

        GroupLayout g_content = new GroupLayout(content);
        g_content.setHorizontalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(CenterXLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterYLbl, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addGroup(g_content.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(FillColorBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(borderColBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30)
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(Colortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(InnerColortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
        );
        g_content.setVerticalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addGap(35)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(CenterXLbl)
                                .addComponent(CenterXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(CenterYLbl)
                                .addComponent(CenterYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblRadius)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(g_content.createParallelGroup(Alignment.TRAILING)
                                .addComponent(Colortxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(borderColBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addGroup(g_content.createParallelGroup(Alignment.TRAILING)
                                .addComponent(InnerColortxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(FillColorBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );

        content.setLayout(g_content);
        {
            JPanel btn = new JPanel();
            btn.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(btn, BorderLayout.SOUTH);
            {
                JButton EditBtn = new JButton("Edit");
                EditBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
                EditBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {
                            validation(CenterXtxt.getText(), CenterYtxt.getText(), RadiusTxt.getText());
                        } catch (NumberFormatException exep) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        if (Integer.parseInt(RadiusTxt.getText()) < 1) {
                            JOptionPane.showMessageDialog(null, "Radius must be greater than 0", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else if (CenterXtxt.getText().trim().equals("") || CenterYtxt.getText().trim().equals("") || RadiusTxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Fields are empty", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }

                        x = Integer.parseInt(CenterXtxt.getText());
                        y = Integer.parseInt(CenterYtxt.getText());
                        radius = Integer.parseInt(RadiusTxt.getText());
                        mark = true;
                        dispose();
                    }
                });
                EditBtn.setActionCommand("OK");
                btn.add(EditBtn);
                getRootPane().setDefaultButton(EditBtn);
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
