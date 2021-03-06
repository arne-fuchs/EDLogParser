package de.paesserver.frames.aboutframe;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.paesserver.GlobalRegister;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class AboutFrame {
    private JButton logButton;
    private JButton settingButton;
    private JButton aboutButton;
    public JPanel mainPane;
    private JLabel textLabel;
    private JButton gitHubButton;
    private JTextArea textArea;

    public AboutFrame() {
        GlobalRegister.initializeMenuBar(logButton, settingButton, aboutButton);

        textArea.setText("EDLogParser\n" +
                "Version: 0.17\n" +
                "Author: Arne Fuchs\n" +
                "Aka. Cmdr Fexx");

        gitHubButton.addActionListener(action -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://github.com/arne-fuchs/EDLogParser"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
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
        mainPane.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JToolBar toolBar1 = new JToolBar();
        mainPane.add(toolBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        logButton = new JButton();
        logButton.setText("Log");
        toolBar1.add(logButton);
        settingButton = new JButton();
        settingButton.setText("Settings");
        toolBar1.add(settingButton);
        aboutButton = new JButton();
        aboutButton.setText("About");
        toolBar1.add(aboutButton);
        textArea = new JTextArea();
        textArea.setEditable(false);
        mainPane.add(textArea, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        gitHubButton = new JButton();
        gitHubButton.setText("GitHub");
        mainPane.add(gitHubButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPane.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPane;
    }

}
