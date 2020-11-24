package Client.Controller.ViewController;

import Client.View.CardLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryViewController {

    private JFrame gui;
    public InventoryViewController(JFrame gui) {
        setGui(gui);
    }



    public void setGui(JFrame gui) {
        this.gui = gui;
    }
}
