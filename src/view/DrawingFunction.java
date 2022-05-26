package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class DrawingFunction extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel content;
    private JTextField BorderColTxt;
    private JTextField FillColTxt;
    public Color borderCol = Color.BLACK;
    private Color fillCol = Color.WHITE;
    private final ButtonGroup btnG = new ButtonGroup();
    private final ButtonGroup btnG1 = new ButtonGroup();

    private Controller controller;
    private DrawingField view = new DrawingField();

    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    //Shape Button
    private JToggleButton PointBtn;
    private JToggleButton LineBtn;
    private JToggleButton CircleBtn;
    private JToggleButton RectangleBtn;
    private JToggleButton DonutBtn;

    // Set Color Button
    private JButton setBorderColBtn;
    private JButton setFillColBtn;

    //Edit and Delete Button
    private JButton EditBtn;
    private JButton DeleteBtn;

    //Draw and Select Button
    private JToggleButton DrawBtn;
    private JToggleButton SelectBtn;

    //Undo and Redo buttons
    private JButton UndoBtn;
    private JButton RedoBtn;

    //Front and Back Button
    private JButton FrontBtn;
    private JButton BackBtn;
    private JButton BringFrontBtn;
    private JButton BringBackBtn;

    //Save Button
    private JButton SaveBtn;

    // components for log part of frame
    private JScrollPane ScrollSlide;
    private JList<String> LogList;
    private JPanel HistoryView;
    private JButton NextBtn;

    /**
     * Create the frame.
     */
    public DrawingFunction() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 731);
        content = new JPanel();
        content.setBackground(Color.GRAY);
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content);
        setResizable(false);
        setTitle("Drawing App Demo by DP");
        view.setBounds(151, 95, 669, 522);

        view.setLayout(null);
        view.setBackground(Color.WHITE);
        content.add(view, BorderLayout.CENTER);

        //Shapes Field
        JPanel ShapesField = new JPanel();
        ShapesField.setBounds(15, 16, 126, 352);
        ShapesField.setBackground(UIManager.getColor("Button.background"));
        ShapesField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Shapes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //Mode Field
        JPanel ModeField = new JPanel();
        ModeField.setBounds(15, 364, 125, 119);
        ModeField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //Border Field
        JPanel BorderField = new JPanel();
        BorderField.setBounds(151, 16, 202, 72);
        BorderField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Border Shape Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        BorderField.setBackground(SystemColor.menu);

        //Select Border Color Button
        setBorderColBtn = new JButton("Select");
        setBorderColBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        setBorderColBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borderCol = JColorChooser.showDialog(null, "Select Border Color", borderCol);
                if (borderCol == null) {
                    borderCol = Color.BLACK;
                    BorderColTxt.setBackground(Color.BLACK);
                } else {
                    BorderColTxt.setBackground(borderCol);
                }

            }
        });

        BorderColTxt = new JTextField();
        BorderColTxt.setColumns(10);
        BorderColTxt.setEditable(false);
        BorderColTxt.setBackground(borderCol);

        //Group BorderColor
        GroupLayout g_borderField = new GroupLayout(BorderField);
        g_borderField.setHorizontalGroup(
                g_borderField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_borderField.createSequentialGroup()
                                .addComponent(setBorderColBtn)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(BorderColTxt, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(19))
        );
        g_borderField.setVerticalGroup(
                g_borderField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_borderField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(g_borderField.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(setBorderColBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BorderColTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BorderField.setLayout(g_borderField);

        //Button for setting fill color 
        JPanel fillField = new JPanel();
        fillField.setBounds(359, 16, 192, 73);
        fillField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Fill Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        fillField.setBackground(SystemColor.menu);

        FillColTxt = new JTextField();
        FillColTxt.setColumns(10);
        FillColTxt.setEditable(false);
        FillColTxt.setBackground(fillCol);

        setFillColBtn = new JButton("Select");
        setFillColBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        setFillColBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillCol = JColorChooser.showDialog(null, "Select Fill Color", fillCol);
                if (fillCol == null) {
                    fillCol = Color.BLACK;
                    FillColTxt.setBackground(Color.WHITE);
                } else {
                    FillColTxt.setBackground(fillCol);
                }

            }
        });

        //Group Fill Color Field
        GroupLayout g_fillField = new GroupLayout(fillField);
        g_fillField.setHorizontalGroup(
                g_fillField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_fillField.createSequentialGroup()
                                .addComponent(setFillColBtn)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(FillColTxt, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        g_fillField.setVerticalGroup(
                g_fillField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_fillField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(g_fillField.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(setFillColBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(FillColTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fillField.setLayout(g_fillField);

        //Action Field
        JPanel ActionField = new JPanel();
        ActionField.setBounds(15, 494, 126, 118);
        ActionField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        ActionField.setBackground(SystemColor.menu);

        //Edit button
        EditBtn = new JButton("Edit");
        EditBtn.setEnabled(false);
        EditBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EditBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (EditBtn.isEnabled()) {
                    controller.editShape();
                }
            }
        });

        //Delete button
        DeleteBtn = new JButton("Delete");
        DeleteBtn.setEnabled(false);
        DeleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        DeleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (DeleteBtn.isEnabled()) {
                    controller.deleteShape();
                }
            }
        });

        GroupLayout g_actionField = new GroupLayout(ActionField);
        g_actionField.setHorizontalGroup(
                g_actionField.createParallelGroup(Alignment.TRAILING)
                        .addGroup(g_actionField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(g_actionField.createParallelGroup(Alignment.LEADING)
                                        .addComponent(DeleteBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(EditBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        g_actionField.setVerticalGroup(
                g_actionField.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, g_actionField.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DeleteBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(EditBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addGap(7))
        );
        ActionField.setLayout(g_actionField);

        //undo and redo button field
        JPanel StepField = new JPanel();
        StepField.setBounds(15, 623, 221, 68);
        StepField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Step", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //Undo button
        UndoBtn = new JButton("Undo");
        UndoBtn.setEnabled(false);
        UndoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (UndoBtn.isEnabled()) {
                    controller.undo();
                }

            }
        });
        UndoBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Redo button
        RedoBtn = new JButton("Redo");
        RedoBtn.setEnabled(false);
        RedoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RedoBtn.isEnabled()) {
                    controller.redo();
                }
            }
        });
        RedoBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        GroupLayout g_StepField = new GroupLayout(StepField);
        g_StepField.setHorizontalGroup(
                g_StepField.createParallelGroup(Alignment.TRAILING)
                        .addGroup(g_StepField.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(UndoBtn, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addGap(35)
                                .addComponent(RedoBtn, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        g_StepField.setVerticalGroup(
                g_StepField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_StepField.createSequentialGroup()
                                .addGroup(g_StepField.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(UndoBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(RedoBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        StepField.setLayout(g_StepField);

        //Pannel for position change button
        JPanel PositionField = new JPanel();
        PositionField.setBounds(561, 16, 518, 73);
        PositionField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Position", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        PositionField.setBackground(SystemColor.menu);

        // Button to move shape one place up
        FrontBtn = new JButton("Set Front");
        FrontBtn.setEnabled(false);
        FrontBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (FrontBtn.isEnabled()) {
                    controller.toFront();

                }

            }
        });
        FrontBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // Button to move shape one place down
        BackBtn = new JButton("Set Back");
        BackBtn.setEnabled(false);
        BackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (BackBtn.isEnabled()) {
                    controller.toBack();

                }

            }
        });
        BackBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Set shape on top
        BringFrontBtn = new JButton("Bring Front");
        BringFrontBtn.setEnabled(false);
        BringFrontBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (BringFrontBtn.isEnabled()) {
                    controller.bringToFront();

                }
            }
        });
        BringFrontBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Set shape on bottom
        BringBackBtn = new JButton("Bring Back");
        BringBackBtn.setEnabled(false);
        BringBackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (BringBackBtn.isEnabled()) {
                    controller.bringToBack();

                }
            }
        });
        BringBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Position Layout
        GroupLayout panel_PositionField = new GroupLayout(PositionField);
        panel_PositionField.setHorizontalGroup(
                panel_PositionField.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, panel_PositionField.createSequentialGroup()
                                .addGap(10)
                                .addComponent(BringBackBtn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addGap(10)
                                .addComponent(BringFrontBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(BackBtn)
                                .addGap(10)
                                .addComponent(FrontBtn)
                                .addContainerGap())
        );
        panel_PositionField.setVerticalGroup(
                panel_PositionField.createParallelGroup(Alignment.LEADING)
                        .addGroup(panel_PositionField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_PositionField.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BackBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(FrontBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BringBackBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BringFrontBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PositionField.setLayout(panel_PositionField);

        //History field setup
        HistoryView = new JPanel();
        HistoryView.setBounds(830, 95, 249, 596);
        HistoryView.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "History", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        HistoryView.setLayout(null);

        //scroll slide
        ScrollSlide = new JScrollPane();
        ScrollSlide.setBounds(0, 12, 249, 584);

        ScrollSlide.setAutoscrolls(true);

        HistoryView.add(ScrollSlide);

        //Log list for all logs 
        LogList = new JList<String>();
        LogList.setBackground(SystemColor.controlLtHighlight);
        LogList.setBounds(16, 450, 148, 479);

        ScrollSlide.setViewportView(LogList);
        LogList.setModel(listModel);

        content.add(StepField);

        DrawBtn = new JToggleButton("Draw");
        DrawBtn.setSelected(true);

        DrawBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        SelectBtn = new JToggleButton("Select");
        SelectBtn.setEnabled(false);
        SelectBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelectBtn.setSelected(true);
            }
        });
        SelectBtn.setSelected(true);

        SelectBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        GroupLayout g_ModeField = new GroupLayout(ModeField);
        g_ModeField.setHorizontalGroup(
                g_ModeField.createParallelGroup(Alignment.TRAILING)
                        .addGroup(g_ModeField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(g_ModeField.createParallelGroup(Alignment.LEADING)
                                        .addComponent(SelectBtn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DrawBtn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        g_ModeField.setVerticalGroup(
                g_ModeField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_ModeField.createSequentialGroup()
                                .addGap(7)
                                .addComponent(SelectBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(DrawBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ModeField.setLayout(g_ModeField);

        //Donut Button
        DonutBtn = new JToggleButton("Donut");
        DonutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawBtn.setSelected(true);
            }
        });
        DonutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Line Button
        LineBtn = new JToggleButton("Line");
        LineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawBtn.setSelected(true);
            }
        });
        LineBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LineBtn.setBackground(UIManager.getColor("Button.darkShadow"));

        //Circle Button
        CircleBtn = new JToggleButton("Circle");
        CircleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawBtn.setSelected(true);
            }
        });
        CircleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //Poin Button
        PointBtn = new JToggleButton("Point");
        PointBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawBtn.setSelected(true);
            }
        });
        PointBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        PointBtn.setForeground(Color.black);
        PointBtn.setBackground(UIManager.getColor("Button.shadow"));

        //Rectangle Button
        RectangleBtn = new JToggleButton("Rectangle");
        RectangleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawBtn.setSelected(true);
            }
        });
        RectangleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        RectangleBtn.setBackground(UIManager.getColor("Button.darkShadow"));

        GroupLayout g_ShapesField = new GroupLayout(ShapesField);
        g_ShapesField.setHorizontalGroup(
                g_ShapesField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_ShapesField.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(g_ShapesField.createParallelGroup(Alignment.LEADING)
                                        .addComponent(DonutBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(CircleBtn, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(RectangleBtn, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(LineBtn, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(PointBtn, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                )
                                .addContainerGap())
        );
        g_ShapesField.setVerticalGroup(
                g_ShapesField.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_ShapesField.createSequentialGroup()
                                .addGap(5)
                                .addComponent(LineBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(CircleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(DonutBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(PointBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(RectangleBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShapesField.setLayout(g_ShapesField);

        //Mode group
        btnG1.add(DrawBtn);
        btnG1.add(SelectBtn);

        //Shapes group
        btnG.add(DonutBtn);
        btnG.add(PointBtn);
        btnG.add(CircleBtn);
        btnG.add(LineBtn);
        btnG.add(RectangleBtn);

        PointBtn.setSelected(true);
        content.setLayout(null);
        content.add(view);
        content.add(ShapesField);
        content.add(ModeField);
        content.add(ActionField);
        content.add(BorderField);
        content.add(fillField);
        content.add(PositionField);
        content.add(HistoryView);

        //Save and Load field
        JPanel saveField = new JPanel();
        saveField.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Save and Open", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        saveField.setBounds(488, 623, 332, 68);
        content.add(saveField);

        //Button for saving log or png
        SaveBtn = new JButton("Save");
        SaveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] choice = {"Save Log", "Save Paint"};
                int optionChosen = JOptionPane.showOptionDialog(null, "Options", "Save File", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choice, choice[0]);
                controller.save(optionChosen);
            }
        });
        SaveBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SaveBtn.setEnabled(true);

        //Open button
        JButton OpenBtn = new JButton("Open");
        OpenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] choice = {"Open Log", "Open Paint"};
                int optionChosen = JOptionPane.showOptionDialog(null, "Choose", "Open", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choice, choice[0]);
                controller.open(optionChosen);
            }
        });
        OpenBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        OpenBtn.setEnabled(true);

        // Button loading log
        NextBtn = new JButton("Load Next");
        NextBtn.setVisible(false);
        NextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (NextBtn.isEnabled()) {
                    controller.loadNext();
                }
            }
        });
        NextBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        NextBtn.setEnabled(false);
        GroupLayout g_saveOpenPanel = new GroupLayout(saveField);
        g_saveOpenPanel.setHorizontalGroup(
                g_saveOpenPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_saveOpenPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(OpenBtn, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(SaveBtn, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(NextBtn, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addContainerGap())
        );
        g_saveOpenPanel.setVerticalGroup(
                g_saveOpenPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(g_saveOpenPanel.createSequentialGroup()
                                .addGroup(g_saveOpenPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(g_saveOpenPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(OpenBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(SaveBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(NextBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveField.setLayout(g_saveOpenPanel);

        //Action when user click 
        view.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SelectBtn.isSelected()) {
                    controller.selectShape(e);
                }
                if (DrawBtn.isSelected()) {
                    controller.addShape(e);
                }
            }
        });
    }

    public void addHistory(String string) {
        this.listModel.addElement(string);
    }

    public JToggleButton getDonut() {
        return DonutBtn;
    }

    public JToggleButton getLine() {
        return LineBtn;
    }

    public JToggleButton getRectangle() {
        return RectangleBtn;
    }

    public JToggleButton getCircle() {
        return CircleBtn;
    }

    public JToggleButton getPoint() {
        return PointBtn;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public DrawingField getView() {
        return view;
    }

    public void setView(DrawingField view) {
        this.view = view;
    }

    public Color getBorderColor() {
        return borderCol;
    }

    public void setBorderColor(Color borderCol) {
        this.borderCol = borderCol;
    }

    public Color getFillColor() {
        return fillCol;
    }

    public void setFillColor(Color fillCol) {
        this.fillCol = fillCol;
    }

    public JButton getEdtBtn() {
        return EditBtn;
    }

    public JButton getDelBtn() {
        return DeleteBtn;
    }

    public JButton getUndoBtn() {
        return UndoBtn;
    }

    public JButton getRedoBtn() {
        return RedoBtn;
    }

    public JToggleButton getSelectBtn() {
        return SelectBtn;
    }

    public JButton getFrontBtn() {
        return FrontBtn;
    }

    public JButton getBackBtn() {
        return BackBtn;
    }

    public JButton getBringFront() {
        return BringFrontBtn;
    }

    public JButton getBringBack() {
        return BringBackBtn;
    }

    public DefaultListModel<String> getLM() {
        return listModel;
    }

    public void setLM(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }

    public JButton getNextBtn() {
        return NextBtn;
    }

}
