package de.paesserver.frames.logframe;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import de.paesserver.GlobalRegister;
import de.paesserver.journalLog.DatabaseSingleton;
import de.paesserver.journalLog.JSONInterpreter;
import de.paesserver.journalLog.JournalLogParser;
import de.paesserver.journalLog.JournalLogRunner;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;


public class LogFrame {
    private JButton resetReaderButton;
    public JPanel mainPane;
    private JTextArea bodyInfo;
    private JTextArea systemInfo;
    private JTree systemTree;
    private JTable systemSignalsTable;
    public JTable bodySignalsTable;
    private JButton logButton;
    private JButton settingButton;

    public JournalLogRunner journalLogRunner;
    public JournalLogParser journalLogParser;
    public JSONInterpreter jsonInterpreter;

    public BodyInfo bodyInfoData;
    public SystemInfo systemInfoData;

    public LogFrame() {

        GlobalRegister.initializeMenuBar(logButton,settingButton);

        journalLogParser = new JournalLogParser();
        jsonInterpreter = new JSONInterpreter(this);

        journalLogRunner = new JournalLogRunner(journalLogParser, jsonInterpreter);
        Thread thread = new Thread(journalLogRunner);
        thread.start();


        resetReaderButton.addActionListener(e -> {
            try {
                File log = journalLogRunner.parser.getLatestLogInWorkingDirectory();
                journalLogRunner.setHalt(true);
                journalLogRunner.parser.closeReader();
                if (journalLogRunner.parser.setBufferedReaderForFile(log))
                    journalLogRunner.setHalt(false);
                else
                    throw new Exception("There was an error starting the buffered reader");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        DefaultMutableTreeNode node = (DefaultMutableTreeNode) systemTree.getModel().getRoot();
        node.setUserObject(new SystemTreeUserObject("Milky Way", BodyType.Galaxy, 0));
        node.removeAllChildren();
        systemTree.updateUI();

        systemTree.addTreeSelectionListener(e -> {
            TreePath treePath = e.getNewLeadSelectionPath();
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            SystemTreeUserObject userObject = (SystemTreeUserObject) treeNode.getUserObject();
            bodyInfoData.bodyName = userObject.nodeName;
            bodyInfoData.bodyType = userObject.bodyType;
            updateBodyData();
        });

        systemTree.setCellRenderer(new DefaultTreeCellRenderer() {
            final private ImageIcon galaxyIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Realistic-galaxy-map.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            final private ImageIcon systemIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/orrery_map.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            final private ImageIcon planetIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/planet.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            final private ImageIcon markerIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Map-galaxy-map.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

            final private ImageIcon starIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/star.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

            final private ImageIcon asteroidIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/asteroids.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                SystemTreeUserObject userObject = (SystemTreeUserObject) node.getUserObject();
                ;

                ImageIcon icon = markerIcon;
                switch (userObject.bodyType) {
                    case Galaxy:
                        icon = galaxyIcon;
                        break;
                    case System:
                        icon = systemIcon;
                        break;
                    case Planet:
                        icon = planetIcon;
                        break;
                    case Star:
                        icon = starIcon;
                        break;
                    case BeltCluster:
                    case Ring:
                        icon = asteroidIcon;
                        break;
                }
                setIcon(icon);

                return this;
            }
        });
        bodySignalsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.DARK_GRAY.brighter() : Color.DARK_GRAY);
                return c;
            }
        });
        systemSignalsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.DARK_GRAY.brighter() : Color.DARK_GRAY);
                return c;
            }
        });

        updateBodySingalsTable(0);
        updateSystemSignalsTable(0);
    }

    public void setBodyData(BodyInfo data) {
        bodyInfoData = data;
        bodyInfo.setText(data.getText());
    }

    public void updateBodyData() {
        if (bodyInfoData != null) {
            bodyInfoData.updateText();
            bodyInfo.setText(bodyInfoData.getText());
        }

    }

    public void setSystemData(SystemInfo data) {
        systemInfoData = data;
        systemInfo.setText(data.getText());
    }

    public void updateSystemData() {
        systemInfo.setText(systemInfoData.getText());
    }

    public void replaceSystem(String systemName) {
        SystemTreeUserObject node = new SystemTreeUserObject(systemName, BodyType.System, 0);

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) systemTree.getModel().getRoot();
        root.setUserObject(node);
        root.removeAllChildren();
        systemTree.updateUI();
    }

    public void addNode(SystemTreeUserObject userObject) {
        String query = "SELECT ParentBodyID FROM PARENT WHERE BodyName = ? ORDER BY ParentBodyID DESC";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)) {
            statement.setString(1, userObject.nodeName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long parentID = resultSet.getLong("ParentBodyID");
                DefaultMutableTreeNode parent = findNodeRecursive(((DefaultMutableTreeNode) systemTree.getModel().getRoot()), parentID);
                if (parent != null) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(userObject);
                    parent.add(newNode);
                    TreePath path = new TreePath(newNode.getPath());
                    systemTree.collapsePath(path);
                    insertPossibleChildren(newNode);
                    sortTree();
                    systemTree.updateUI();
                    return;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }


    private DefaultMutableTreeNode findNodeRecursive(DefaultMutableTreeNode node, long id) {

        SystemTreeUserObject userObject = (SystemTreeUserObject) node.getUserObject();
        if (userObject.bodyID == id)
            return node;
        if (userObject.bodyID > id)
            return null;

        Iterator<TreeNode> iterator = node.children().asIterator();
        while (iterator.hasNext()) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) iterator.next();
            DefaultMutableTreeNode possibleParent = findNodeRecursive(treeNode, id);
            if (possibleParent != null)
                return possibleParent;
        }
        return null;
    }

    private void insertPossibleChildren(DefaultMutableTreeNode parent) {
        //TODO Implement sort insertPossibleChildren
    }

    private void sortTree() {
        //TODO Implement sorting nodes
    }

    public void updateBodySingalsTable(long systemAddress) {
        //bodySignalsTable.removeColumnSelectionInterval(0,bodySignalsTable.getColumnCount()-1);

        TableModel tableModel = new DefaultTableModel(new String[]{"Body Name", "Signal Type", "Count"}, 50);
        bodySignalsTable.setModel(tableModel);
        bodySignalsTable.getTableHeader().setVisible(true);
        bodySignalsTable.getTableHeader().updateUI();

        bodySignalsTable.getColumn("Count").setPreferredWidth(50);
        bodySignalsTable.getColumn("Count").setMaxWidth(50);
        bodySignalsTable.getColumn("Count").setMinWidth(50);

        bodySignalsTable.getColumn("Signal Type").setPreferredWidth(120);
        bodySignalsTable.getColumn("Signal Type").setMaxWidth(120);
        bodySignalsTable.getColumn("Signal Type").setMinWidth(120);

        String query = "SELECT * FROM BODYSIGNAL WHERE SystemAddress = ? ORDER BY Count desc,BodyName";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)) {
            statement.setLong(1, systemAddress);

            ResultSet resultSet = statement.executeQuery();
            int lastRow = 0;
            while (resultSet.next() && resultSet.getRow() < bodySignalsTable.getRowCount()) {
                tableModel.setValueAt(resultSet.getString("BodyName"), resultSet.getRow() - 1, 0);
                tableModel.setValueAt(resultSet.getString("Type_Localised"), resultSet.getRow() - 1, 1);
                tableModel.setValueAt(resultSet.getString("Count"), resultSet.getRow() - 1, 2);
                lastRow = resultSet.getRow();
            }
            for (int i = lastRow; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(" ", lastRow, 0);
                tableModel.setValueAt(" ", lastRow, 1);
                tableModel.setValueAt(" ", lastRow, 2);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void updateSystemSignalsTable(long systemAddress) {
        //bodySignalsTable.removeColumnSelectionInterval(0,bodySignalsTable.getColumnCount()-1);

        TableModel tableModel = new DefaultTableModel(new String[]{"Signal Name", "Threat Level"}, 50);
        systemSignalsTable.setModel(tableModel);
        systemSignalsTable.getTableHeader().setVisible(true);
        systemSignalsTable.getTableHeader().updateUI();

        systemSignalsTable.getColumn("Threat Level").setPreferredWidth(150);
        systemSignalsTable.getColumn("Threat Level").setMaxWidth(150);
        systemSignalsTable.getColumn("Threat Level").setMinWidth(150);


        String query = "SELECT SignalName,SignalName_Localised,ThreatLevel FROM SYSTEMSIGNAL WHERE SystemAddress = ? ORDER BY SignalName,ThreatLevel desc";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)) {
            statement.setLong(1, systemAddress);

            ResultSet resultSet = statement.executeQuery();
            int lastRow = 0;
            while (resultSet.next() && resultSet.getRow() < systemSignalsTable.getRowCount()) {
                String name = resultSet.getString("SignalName_Localised");
                if (name == null)
                    name = resultSet.getString("SignalName");

                tableModel.setValueAt(name, resultSet.getRow() - 1, 0);
                tableModel.setValueAt(resultSet.getString("ThreatLevel"), resultSet.getRow() - 1, 1);
                lastRow = resultSet.getRow();
            }
            for (int i = lastRow; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(" ", lastRow, 0);
                tableModel.setValueAt(" ", lastRow, 1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
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
        mainPane.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        resetReaderButton = new JButton();
        resetReaderButton.setText("Reset Reader");
        mainPane.add(resetReaderButton, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bodyInfo = new JTextArea();
        mainPane.add(bodyInfo, new GridConstraints(0, 2, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        systemInfo = new JTextArea();
        systemInfo.setText("");
        mainPane.add(systemInfo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(130, 50), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPane.add(scrollPane1, new GridConstraints(0, 1, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, -1), null, null, 0, false));
        systemTree = new JTree();
        scrollPane1.setViewportView(systemTree);
        final JScrollPane scrollPane2 = new JScrollPane();
        mainPane.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bodySignalsTable = new JTable();
        scrollPane2.setViewportView(bodySignalsTable);
        final JScrollPane scrollPane3 = new JScrollPane();
        mainPane.add(scrollPane3, new GridConstraints(2, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        systemSignalsTable = new JTable();
        scrollPane3.setViewportView(systemSignalsTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPane;
    }

}
