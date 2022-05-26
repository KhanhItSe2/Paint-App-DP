package editBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Point;

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
public class PointEdt extends JDialog {

    private final JPanel content = new JPanel();
    private JTextField XCortxt;
    private JTextField YCortxt;
    private Color color;
    private JTextField ColorTxt;
    private Point point;
    int x, y;

    boolean mark = false;

    public boolean isMark() {
        return mark;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void fillAll(Point p) {
        this.point = p;
        XCortxt.setText(String.valueOf(point.getX()));
        YCortxt.setText(String.valueOf(point.getY()));
        color = point.getColor();
        ColorTxt.setBackground(color);
    }

    public static void main(String[] args) {
        try {
            PointEdt box = new PointEdt();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PointEdt() {
        setBounds(100, 100, 300, 300);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        JLabel lblXCoordinate = new JLabel("X coordinate:");
        lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel lblYCoordinate = new JLabel("Y coordinate: ");
        lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        XCortxt = new JTextField();
        XCortxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        XCortxt.setColumns(10);
        YCortxt = new JTextField();
        YCortxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        YCortxt.setColumns(10);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Point ");

        JButton colorBtn = new JButton("Color: ");
        colorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        colorBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null, "Select Point Color", point.getColor());
                if (color == null) {
                    color = point.getColor();
                }
                ColorTxt.setBackground(color);

            }

        });

        ColorTxt = new JTextField();
        ColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ColorTxt.setColumns(10);
        ColorTxt.setEditable(false);
        GroupLayout g_content = new GroupLayout(content);
        g_content.setHorizontalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblYCoordinate, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(lblXCoordinate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(colorBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING)
                                .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(YCortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(XCortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(50, Short.MAX_VALUE))
        );
        g_content.setVerticalGroup(g_content.createParallelGroup(Alignment.TRAILING)
                .addGroup(g_content.createSequentialGroup()
                        .addGap(29)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(colorBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(g_content.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lblXCoordinate)
                                .addComponent(XCortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblYCoordinate)
                                .addComponent(YCortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(40))
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
                        if (XCortxt.getText().trim().equals("") || YCortxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Fields are empty", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        try {
                            x = Integer.parseInt(XCortxt.getText());
                            y = Integer.parseInt(YCortxt.getText());

                            mark = true;
                            dispose();
                        } catch (NumberFormatException ece) {
                            JOptionPane.showMessageDialog(null, "Data Type is invalid", "ERROR", JOptionPane.ERROR_MESSAGE, null);
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
