package editBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Donut;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class DonutEdt extends JDialog {

    private final JPanel content = new JPanel();
    private Color color;
    private Color fillColor;
    private Donut donut;

    private JTextField ColorTxt;
    private JTextField FillColorTxt;
    private JTextField CenterXtxt;
    private JTextField CenterYtxt;
    private JTextField RadiusTxt;
    private JTextField InnerRadiusTxt;

    private boolean mark;
    private int x, y, radius, innerRadius;

    public Color getInnerColor() {
        return fillColor;
    }

    public boolean isMark() {
        return mark;
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

    public Color getColor() {
        return color;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void fillAll(Donut d) {
        this.donut = d;
        CenterXtxt.setText(String.valueOf(donut.getCenter().getX()));
        CenterYtxt.setText(String.valueOf(donut.getCenter().getY()));
        RadiusTxt.setText(String.valueOf(donut.getRadius()));
        InnerRadiusTxt.setText(String.valueOf(donut.getInnerRadius()));
        ColorTxt.setBackground(donut.getColor());
        FillColorTxt.setBackground(donut.getInnerColor());
        color = donut.getColor();
        fillColor = donut.getInnerColor();
    }

    public void validation(String x, String y, String radius, String innerRadius) {

        String chk = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if (x.matches("") || y.matches("") || radius.matches("") || innerRadius.matches("")) {

        } else if (!x.matches(chk) || !y.matches(chk) || !radius.matches(chk) || !innerRadius.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {
        try {
            DonutEdt box = new DonutEdt();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DonutEdt() {
        setBounds(100, 100, 380, 400);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Donut");
        JLabel CenterYLbl = new JLabel("Center Y coordinate: ");
        CenterYLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel CenterXLbl = new JLabel("Center X coordinate:");
        CenterXLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel RadiusLbl = new JLabel("Radius: ");
        RadiusLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel lblInnerRadius = new JLabel("Inner radius: ");
        lblInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton BorderColorBtn = new JButton("Border Color: ");
        BorderColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        BorderColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null, "Select Border Donut Color ", donut.getColor());
                if (color == null) {
                    color = donut.getColor();
                }
                ColorTxt.setBackground(color);
            }
        });
        JButton FillColorBtn = new JButton("Fill Color: ");
        FillColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        FillColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillColor = JColorChooser.showDialog(null, "Select Inner Donut Color", donut.getInnerColor());
                if (fillColor == null) {
                    fillColor = donut.getInnerColor();
                }
                FillColorTxt.setBackground(fillColor);
            }
        });
        ColorTxt = new JTextField();
        ColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ColorTxt.setEditable(false);
        ColorTxt.setColumns(10);
        FillColorTxt = new JTextField();
        FillColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        FillColorTxt.setEditable(false);
        FillColorTxt.setColumns(10);
        CenterXtxt = new JTextField();
        CenterXtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CenterXtxt.setColumns(10);
        CenterYtxt = new JTextField();
        CenterYtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CenterYtxt.setColumns(10);
        RadiusTxt = new JTextField();
        RadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RadiusTxt.setColumns(10);
        InnerRadiusTxt = new JTextField();
        InnerRadiusTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InnerRadiusTxt.setColumns(10);
        GroupLayout g_content = new GroupLayout(content);
        g_content.setHorizontalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addComponent(RadiusLbl, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInnerRadius, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterXLbl, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterYLbl)
                                .addGroup(g_content.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(BorderColorBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(FillColorBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(40)
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addComponent(InnerRadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(FillColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(CenterYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(50, Short.MAX_VALUE))
        );
        g_content.setVerticalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addGap(25)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(CenterXLbl)
                                .addComponent(CenterXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(CenterYLbl)
                                .addComponent(CenterYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(45)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(RadiusLbl)
                                .addComponent(RadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblInnerRadius)
                                .addComponent(InnerRadiusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(40)
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addGroup(g_content.createSequentialGroup()
                                        .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(FillColorTxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGroup(g_content.createSequentialGroup()
                                        .addComponent(BorderColorBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                                        .addComponent(FillColorBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        content.setLayout(g_content);
        setModal(true);
        setLocationRelativeTo(null);
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
                            validation(CenterXtxt.getText(), CenterYtxt.getText(), RadiusTxt.getText(), InnerRadiusTxt.getText());
                        } catch (NumberFormatException exce) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        if (Integer.parseInt(RadiusTxt.getText()) < (Integer.parseInt(InnerRadiusTxt.getText()))) {
                            JOptionPane.showMessageDialog(null, "Inner radius can not be greater than the outer radius!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else if (CenterXtxt.getText().trim().equals("") || CenterYtxt.getText().trim().equals("") || RadiusTxt.getText().trim().equals("") || InnerRadiusTxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Fiels are be empty", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else if ((Integer.parseInt(InnerRadiusTxt.getText())) < 1 || Integer.parseInt(RadiusTxt.getText()) < 1) {
                            JOptionPane.showMessageDialog(null, "Radius(outer,inner) must be greater than 0", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else {
                            x = Integer.parseInt(CenterXtxt.getText());
                            y = Integer.parseInt(CenterYtxt.getText());
                            innerRadius = Integer.parseInt(InnerRadiusTxt.getText());
                            radius = Integer.parseInt(RadiusTxt.getText());
                            mark = true;

                            dispose();
                        }

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
