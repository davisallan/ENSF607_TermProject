package Client.View;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class ManagementGUI {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel searchLabel;
    private JLabel clientLabel;
    private JCheckBox clientIDCheckBox;
    private JCheckBox lastNameCheckBox;
    private JCheckBox clientTypeCheckBox;
    private JLabel clientIdLabel;
    private JLabel fNameLabel;
    private JLabel addressLabel;
    private JLabel postalCodeLabel;
    private JLabel numberLabel;
    private JLabel LNameLabel;
    private JLabel clientTypeLabel;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel parameterLabel;
    private JTextField textField8;
    private JButton searchButton;
    private JButton clearButton1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagementGUI");
        frame.setContentPane(new ManagementGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}