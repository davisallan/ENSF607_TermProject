package Client.View;

import javax.swing.*;

public class ClientMgmtGUI extends JFrame{

    public ClientMgmtGUI() {
        JFrame frame = new JFrame("Tool Shop App");
        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        frame.setSize(500, 500);
        frame.setLocation(300,100);

        // Left panel
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        // Title label "Search Clients
        JLabel search = new JLabel("Search Clients");
        search.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        search.setFont(search.getFont().deriveFont(20f));
        leftPanel.add(search);
        // Select type label
        JLabel type = new JLabel("Select type of search to be performed:");
        leftPanel.add(type);
        // Client ID option
        JCheckBox id = new JCheckBox("Client ID");
        leftPanel.add(id);
        // Last Name option
        JCheckBox name = new JCheckBox("Last Name");
        leftPanel.add(name);
        // Client Type option
        JCheckBox clientType = new JCheckBox("Client Type");
        leftPanel.add(clientType);
        // Search parameters label
        JLabel param = new JLabel("Enter the search parameters below:");
        leftPanel.add(param);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JLabel client = new JLabel("Client Information");
        client.setHorizontalTextPosition(JLabel.CENTER);
        client.setFont(client.getFont().deriveFont(20f));
        rightPanel.add(client);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientMgmtGUI();
    }
}
