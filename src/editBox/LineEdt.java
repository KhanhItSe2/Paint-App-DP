package editBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Line;

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
public class LineEdt extends JDialog {

    private final JPanel content = new JPanel();
    private Color color;
    private Line line;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private JTextField StartXtxt;
    private JTextField StartYtxt;
    private JTextField EndXTxt;
    private JTextField EndYtxt;
    private JTextField ColorTxt;

    private Boolean mark = false;

    public static void main(String[] args) {
        try {
            LineEdt box = new LineEdt();
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public Boolean isMark() {
        return mark;
    }

    public void fillAll(Line l) {
        this.line = l;
        StartXtxt.setText(String.valueOf(line.getStart().getX()));
        StartYtxt.setText(String.valueOf(line.getStart().getY()));
        EndXTxt.setText(String.valueOf(line.getEnd().getX()));
        EndYtxt.setText(String.valueOf(line.getEnd().getY()));
        color = line.getColor();
        ColorTxt.setBackground(line.getColor());
    }

    public LineEdt() {
        setBounds(100, 100, 390, 350);
        getContentPane().setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content, BorderLayout.CENTER);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Line");

        JLabel StartXLbl = new JLabel("Start point X coordinate: ");
        StartXLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel StartYLbl = new JLabel("Start point Y coordinate: ");
        StartYLbl.setFont(new Font("Tahoma", Font.PLAIN, 14
        ));

        JLabel EndXLbl = new JLabel("End point X coordinate: ");
        EndXLbl.setFont(new Font("Tahoma", Font.PLAIN, 14
        ));

        JLabel EndYLbl = new JLabel("End point Y coordinate: ");
        EndYLbl.setFont(new Font("Tahoma", Font.PLAIN, 14
        ));

        StartXtxt = new JTextField();
        StartXtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        StartXtxt.setColumns(10);

        StartYtxt = new JTextField();
        StartYtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        StartYtxt.setColumns(10);

        EndXTxt = new JTextField();
        EndXTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EndXTxt.setColumns(10);

        EndYtxt = new JTextField();
        EndYtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EndYtxt.setColumns(10);

        JButton ColorBtn = new JButton("Color: ");
        ColorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14
        ));
        ColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null, "Select Line color", line.getColor());
                if (color == null) {
                    color = line.getColor();
                }
                ColorTxt.setBackground(color);
            }
        });

        ColorTxt = new JTextField();
        ColorTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ColorTxt.setEditable(false);
        ColorTxt.setColumns(10);
        GroupLayout g_content = new GroupLayout(content);
        g_content.setHorizontalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(g_content.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(StartXLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EndXLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EndYLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(StartYLbl)
                                .addComponent(ColorBtn))
                        .addGap(35)
                        .addGroup(g_content.createParallelGroup(Alignment.TRAILING)
                                .addComponent(StartYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(StartXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(EndXTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(EndYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30))
        );
        g_content.setVerticalGroup(g_content.createParallelGroup(Alignment.LEADING)
                .addGroup(g_content.createSequentialGroup()
                        .addGap(30)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(StartXLbl)
                                .addComponent(StartXtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20
                        )
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(StartYLbl)
                                .addComponent(StartYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(40)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(EndXLbl)
                                .addComponent(EndXTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20
                        )
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(EndYLbl)
                                .addComponent(EndYtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 14,
                                Short.MAX_VALUE)
                        .addGroup(g_content.createParallelGroup(Alignment.BASELINE)
                                .addComponent(ColorBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ColorTxt, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                        .addGap(21))
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
                        if (StartXtxt.getText().trim().equals("") || StartYtxt.getText().trim().equals("") || EndXTxt.getText().trim().equals("") || EndYtxt.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Fields are empty", "Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        try {
                            startX = Integer.parseInt(StartXtxt.getText());
                            startY = Integer.parseInt(StartYtxt.getText());
                            endX = Integer.parseInt(EndXTxt.getText());
                            endY = Integer.parseInt(EndYtxt.getText());

                            mark = true;

                            dispose();
                        } catch (NumberFormatException ec) {
                            JOptionPane.showMessageDialog(null, "Data type is invalid", "Error", JOptionPane.ERROR_MESSAGE, null);
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
