package de.paesserver.frames.settingframe;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.paesserver.GlobalRegister;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.*;

public class SettingFrame {
    private JButton logButton;
    private JButton settingButton;
    public JPanel mainPane;
    private JTextField journalLogFolderTextField;
    private JLabel journalFolderLabel;
    private JButton saveButton;
    private JSlider journalLogReaderIntervalSlider;
    private JLabel journalLogReaderIntervalLabel;
    private JSlider journalLogUpdaterIntervalSlider;
    private JLabel journalLogUpdaterIntervalLabel;
    private JLabel themeSelectorLabel;
    private JPanel dropDownThemePane;
    private JButton previewButton;
    private JTextField previewTextField;
    private JTree previewTree;
    private JPanel previewPane;
    private JPanel fontPane;
    private JSpinner fontSpinner;
    private JButton aboutButton;

    public SettingFrame() {
        GlobalRegister.initializeMenuBar(logButton, settingButton, aboutButton);

        journalLogFolderTextField.setText(GlobalRegister.properties.getProperty("journalLogPath"));
        journalLogFolderTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setFileHidingEnabled(false);
                fileChooser.setCurrentDirectory(new File(GlobalRegister.properties.getProperty("journalLogPath")));
                int option = fileChooser.showOpenDialog(GlobalRegister.getMainFrame());
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    journalLogFolderTextField.setText(file.getAbsolutePath());
                    GlobalRegister.properties.setProperty("journalLogPath", file.getAbsolutePath());
                }
            }
        });
        saveButton.addActionListener(actionEvent -> Settings.saveToPropertiesFile(GlobalRegister.properties));

        journalLogReaderIntervalSlider.setValue(Integer.parseInt(GlobalRegister.properties.getProperty("journalLogReaderInterval")));
        journalLogUpdaterIntervalSlider.setValue(Integer.parseInt(GlobalRegister.properties.getProperty("journalLogUpdaterInterval")));

        journalLogReaderIntervalSlider.addChangeListener(changeEvent -> GlobalRegister.properties.setProperty("journalLogReaderInterval", String.valueOf(journalLogReaderIntervalSlider.getValue())));
        journalLogUpdaterIntervalSlider.addChangeListener(changeEvent -> GlobalRegister.properties.setProperty("journalLogUpdaterInterval", String.valueOf(journalLogUpdaterIntervalSlider.getValue())));

        previewTextField.setText("Example text to test look and feel");

        ArrayList<String> themes = new ArrayList<>();
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeel : lookAndFeelInfos) {
            themes.add(lookAndFeel.getClassName());
        }
        themes.add("com.formdev.flatlaf.FlatDarculaLaf");
        themes.add("com.formdev.flatlaf.FlatDarkLaf");
        themes.add("com.formdev.flatlaf.FlatIntelliJLaf");
        themes.add("com.formdev.flatlaf.FlatLightLaf");
        themes.add("javax.swing.plaf.metal.MetalLookAndFeel");
        themes.add("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        JComboBox<String> themeComboBox = new JComboBox<>(themes.toArray(String[]::new));
        themeComboBox.setSelectedItem(GlobalRegister.properties.getProperty("theme"));
        GridConstraints constraints = new GridConstraints();
        dropDownThemePane.add(themeComboBox, constraints);

        themeComboBox.addActionListener(event -> {
            GlobalRegister.properties.setProperty("theme", Objects.requireNonNull(themeComboBox.getSelectedItem()).toString());
            try {
                UIManager.setLookAndFeel(themeComboBox.getSelectedItem().toString());
                previewPane.updateUI();
                previewButton.updateUI();
                previewTextField.updateUI();
                previewTree.updateUI();

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        });


        JComboBox<String> fontComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

        fontComboBox.setSelectedItem(GlobalRegister.properties.getProperty("font"));
        constraints = new GridConstraints();
        fontPane.add(fontComboBox, constraints);

        fontComboBox.addActionListener(event -> {
            GlobalRegister.properties.setProperty("font", Objects.requireNonNull(fontComboBox.getSelectedItem()).toString());

            Hashtable<TextAttribute, Object> m = new Hashtable<>();
            m.put(TextAttribute.FAMILY, fontComboBox.getSelectedItem().toString());
            m.put(TextAttribute.SIZE, Integer.parseInt(GlobalRegister.properties.getProperty("fontSize")));
            m.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            Font font = Font.getFont(m);

            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", font);

            previewPane.setFont(font);
            previewPane.updateUI();
            previewButton.setFont(font);
            previewButton.updateUI();
            previewTextField.setFont(font);
            previewTextField.updateUI();
            previewTree.setFont(font);
            previewTree.updateUI();
        });

        fontSpinner.setValue(Integer.parseInt(GlobalRegister.properties.getProperty("fontSize")));
        fontSpinner.addChangeListener(changeListener -> {
            GlobalRegister.properties.setProperty("fontSize", fontSpinner.getValue().toString());
            Hashtable<TextAttribute, Object> m = new Hashtable<>();
            m.put(TextAttribute.FAMILY, GlobalRegister.properties.getProperty("font"));
            m.put(TextAttribute.SIZE, Integer.parseInt(GlobalRegister.properties.getProperty("fontSize")));
            m.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            Font font = Font.getFont(m);

            UIManager.getLookAndFeelDefaults().put("defaultFont", font);
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPane = new JPanel();
        mainPane.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JToolBar toolBar1 = new JToolBar();
        mainPane.add(toolBar1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        logButton = new JButton();
        logButton.setText("Log");
        toolBar1.add(logButton);
        settingButton = new JButton();
        settingButton.setText("Settings");
        toolBar1.add(settingButton);
        aboutButton = new JButton();
        aboutButton.setText("About");
        toolBar1.add(aboutButton);
        final Spacer spacer1 = new Spacer();
        mainPane.add(spacer1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPane.add(panel1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        journalLogUpdaterIntervalLabel = new JLabel();
        journalLogUpdaterIntervalLabel.setText("Update-Interval for Updater in ms[1-100]:");
        panel2.add(journalLogUpdaterIntervalLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        journalLogUpdaterIntervalSlider = new JSlider();
        journalLogUpdaterIntervalSlider.setMinimum(1);
        journalLogUpdaterIntervalSlider.setPaintLabels(true);
        journalLogUpdaterIntervalSlider.setPaintTicks(true);
        journalLogUpdaterIntervalSlider.setSnapToTicks(true);
        panel2.add(journalLogUpdaterIntervalSlider, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        journalLogReaderIntervalLabel = new JLabel();
        journalLogReaderIntervalLabel.setHorizontalTextPosition(10);
        journalLogReaderIntervalLabel.setText("Update-Interval for Reader in ms[1-100]:");
        journalLogReaderIntervalLabel.setVerticalAlignment(0);
        journalLogReaderIntervalLabel.setVerticalTextPosition(0);
        panel3.add(journalLogReaderIntervalLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        journalLogReaderIntervalSlider = new JSlider();
        journalLogReaderIntervalSlider.setMinimum(1);
        journalLogReaderIntervalSlider.setPaintLabels(true);
        journalLogReaderIntervalSlider.setPaintTicks(true);
        journalLogReaderIntervalSlider.setSnapToTicks(true);
        journalLogReaderIntervalSlider.setValue(50);
        journalLogReaderIntervalSlider.setValueIsAdjusting(false);
        journalLogReaderIntervalSlider.putClientProperty("JSlider.isFilled", Boolean.FALSE);
        journalLogReaderIntervalSlider.putClientProperty("Slider.paintThumbArrowShape", Boolean.FALSE);
        panel3.add(journalLogReaderIntervalSlider, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        journalFolderLabel = new JLabel();
        journalFolderLabel.setHorizontalTextPosition(10);
        journalFolderLabel.setText("Path to journal logs:");
        journalFolderLabel.setVerticalAlignment(0);
        panel4.add(journalFolderLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        journalLogFolderTextField = new JTextField();
        panel4.add(journalLogFolderTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        themeSelectorLabel = new JLabel();
        themeSelectorLabel.setText("Theme:");
        panel5.add(themeSelectorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dropDownThemePane = new JPanel();
        dropDownThemePane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(dropDownThemePane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel6, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Preview:");
        panel6.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        previewPane = new JPanel();
        previewPane.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(previewPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        previewButton = new JButton();
        previewButton.setText("Button");
        previewPane.add(previewButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        previewTextField = new JTextField();
        previewPane.add(previewTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        previewTree = new JTree();
        previewPane.add(previewTree, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel7, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Font:");
        panel7.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fontPane = new JPanel();
        fontPane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(fontPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel8, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Font size:");
        panel8.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fontSpinner = new JSpinner();
        panel8.add(fontSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setHideActionText(false);
        saveButton.setText("Save");
        mainPane.add(saveButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPane;
    }

}
