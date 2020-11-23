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

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JLabel search = new JLabel("Search Clients");
        search.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        search.setFont(search.getFont().deriveFont(20f));
        leftPanel.add(search);

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
