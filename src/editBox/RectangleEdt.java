package editBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class RectangleEdt extends JDialog {

    private Color BorderColor;
    private Color innerColor;
    private Rectangle rectangle;
    private boolean mark = false;

    private int x, y, heightRec, widthRec;
    private final JPanel content = new JPanel();
    private JTextField RecXtxt;
    private JTextField RecYtxt;
    private JTextField WidthTxt;
    private JTextField HeightTxt;
    private JTextField BorderColorTxt;
    private JTextField InnerColorTxt;

    public Color getBorderColor() {
        return BorderColor;
    }

    public Color getInnerColor() {
        return innerColor;
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

    public int getHeightRec() {
        return heightRec;
    }

    public int getWidthRec() {
        return widthRec;
    }

    public static void main(String[] args) {
        try {
            RectangleEdt box = new RectangleEdt();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillAll(Rectangle r) {
        this.rectangle = r;
        RecXtxt.setText(String.valueOf(rectangle.getUpLeftP().getX()));
        RecYtxt.setText(String.valueOf(rectangle.getUpLeftP().getY()));
        WidthTxt.setText(String.valueOf(rectangle.getWidth()));
        HeightTxt.setText(String.valueOf(rectangle.getHeight()));
        BorderColorTxt.setBackground(rectangle.getColor());
        InnerColorTxt.setBackground(rectangle.getInnerColor());
        BorderColor = rectangle.getColor();
        innerColor = rectangle.getInnerColor();
    }

    public void validation(String x, String y, String width, String height) {
        String chk = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
        if (x.matches("") || y.matches("") || width.matches("") || height.matches("")) {

        } else if (!x.matches(chk) || !y.matches(chk) || !width.matches(chk) || !height.matches(chk)) {
            throw new NumberFormatException();
        }
    }

    public RectangleEdt() {
        setBounds(100, 100, 530, 350);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        setModal(true);
        setLocationRelativeTo(null);

        setTitle("Edit Rectangle ");

        JLabel UpperLeftPointXLbl = new JLabel("Upper Left point X coordinate: ");
        UpperLeftPointXLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel UpperLeftPointLbl = new JLabel("Upper Left point Y coordinate: ");
        UpperLeftPointLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel WidthLbl = new JLabel("Width: ");
        WidthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel HeightLbl = new JLabel("Height: ");
        HeightLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        RecXtxt = new JTextField();
        RecXtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RecXtxt.setColumns(10);
        RecYtxt = new JTextField();
        RecYtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RecYtxt.setColumns(10);
        WidthTxt = new JTextField();
        WidthTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        WidthTxt.setColumns(10);
        HeightTxt = new JTextField();
        HeightTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        HeightTxt.setColumns(10);
        JButton BorderColorBtn = new JButton("Border Color: ");
        BorderColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        BorderColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BorderColor = JColorChooser.showDialog(null, "Select Border Rectangle Color", rectangle.getColor());
                if (BorderColor == null) {
                    BorderColor = rectangle.getColor();
                }
                BorderColorTxt.setBackground(BorderColor);
            }
        });
        JButton InnerColorBtn = new JButton("Fill Color:");
        InnerColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InnerColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                innerColor = JColorChooser.showDialog(null, "Select Inner Rectangle Color", rectangle.getInnerColor());
                if (innerColor == null) {
                    innerColor = rectangle.getInnerColor();
                }
                InnerColorTxt.setBackground(innerColor);
            }
        });
        BorderColorTxt = new JTextField();
        BorderColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        BorderColorTxt.setEditable(false);
        BorderColorTxt.setColumns(10);
        InnerColorTxt = new JTextField();
        InnerColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InnerColorTxt.setEditable(false);
        InnerColorTxt.setColumns(10);
        GroupLayout g_content = new GroupLayout(content);
        g_content.setHorizontalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addComponent(UpperLeftPointXLbl)
                                .addGroup(g_content.createSequentialGroup()
                                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                                .addGroup(g_content.createSequentialGroup()
                                                        .addComponent(HeightLbl)
                                                        .addGap(15)
                                                        .addComponent(HeightTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED))
                                                .addGroup(g_content.createSequentialGroup()
                                                        .addComponent(WidthLbl)
                                                        .addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                                        .addComponent(WidthTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(15))
                                                .addComponent(UpperLeftPointLbl))
                                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                                .addGroup(g_content.createSequentialGroup()
                                                        .addGap(5)
                                                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(g_content.createSequentialGroup()
                                                                        .addComponent(InnerColorBtn)
                                                                        .addPreferredGap(ComponentPlacement.RELATED))
                                                                .addComponent(BorderColorBtn, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                                        .addGap(15)
                                                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(BorderColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(InnerColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(g_content.createSequentialGroup()
                                                        .addGap(15)
                                                        .addGroup(g_content.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(RecXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(RecYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
                        .addGap(14))
        );
        g_content.setVerticalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addGap(45)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(UpperLeftPointXLbl)
                                .addComponent(RecXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(UpperLeftPointLbl)
                                .addComponent(RecYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addGroup(g_content.createSequentialGroup()
                                        .addGap(45)
                                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(WidthLbl)
                                                .addComponent(BorderColorBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(WidthTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(BorderColorTxt, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(g_content.createSequentialGroup()
                                        .addGap(98)
                                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(InnerColorBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(InnerColorTxt, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(HeightTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(HeightLbl))))
                        .addContainerGap(12, Short.MAX_VALUE))
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
                            validation(RecXtxt.getText(), RecYtxt.getText(), WidthTxt.getText(), HeightTxt.getText());
                        } catch (NumberFormatException ec) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            mark = false;
                            return;
                        }
                        if (Integer.parseInt(WidthTxt.getText()) < 0 || Integer.parseInt(HeightTxt.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Radius must be greater than 0", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        } else if (RecXtxt.getText().trim().equals("") || RecYtxt.getText().trim().equals("") || WidthTxt.getText().trim().equals("") || HeightTxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Fields are empty", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        mark = true;
                        x = Integer.parseInt(RecXtxt.getText());
                        y = Integer.parseInt(RecYtxt.getText());
                        widthRec = Integer.parseInt(WidthTxt.getText());
                        heightRec = Integer.parseInt(HeightTxt.getText());

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
