package de.paesserver.frames.settingframe;

import com.intellij.uiDesigner.core.GridConstraints;
import de.paesserver.GlobalRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

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

    public SettingFrame() {
        GlobalRegister.initializeMenuBar(logButton,settingButton);

        journalLogFolderTextField.setText(GlobalRegister.properties.getProperty("journalLogPath"));
        journalLogFolderTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setCurrentDirectory(new File("./"));
                int option = fileChooser.showOpenDialog(GlobalRegister.getMainFrame());
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    journalLogFolderTextField.setText(file.getAbsolutePath());
                    GlobalRegister.properties.setProperty("journalLogPath",file.getAbsolutePath());
                }
            }
        });
        saveButton.addActionListener(actionEvent -> Settings.saveToPropertiesFile(GlobalRegister.properties));

        journalLogReaderIntervalSlider.setValue(Integer.parseInt(GlobalRegister.properties.getProperty("journalLogReaderInterval")));
        journalLogUpdaterIntervalSlider.setValue(Integer.parseInt(GlobalRegister.properties.getProperty("journalLogUpdaterInterval")));

        journalLogReaderIntervalSlider.addChangeListener(changeEvent -> GlobalRegister.properties.setProperty("journalLogReaderInterval", String.valueOf(journalLogReaderIntervalSlider.getValue())));
        journalLogUpdaterIntervalSlider.addChangeListener(changeEvent -> GlobalRegister.properties.setProperty("journalLogUpdaterInterval", String.valueOf(journalLogUpdaterIntervalSlider.getValue())));

        String[] themes;
        themes = Arrays.stream(UIManager.getInstalledLookAndFeels()).map(UIManager.LookAndFeelInfo::getClassName).toArray(String[]::new);

        JComboBox<String> themeComboBox = new JComboBox<>(themes);
        themeComboBox.setSelectedItem(GlobalRegister.properties.getProperty("theme"));
        GridConstraints constraints = new GridConstraints();
        dropDownThemePane.add(themeComboBox,constraints);

        themeComboBox.addActionListener(event -> {
            GlobalRegister.properties.setProperty("theme",themeComboBox.getSelectedItem().toString());
            try {
                UIManager.setLookAndFeel(themeComboBox.getSelectedItem().toString());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
