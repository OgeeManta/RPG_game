package view;

import javax.swing.*;

public class CharacterDisplay {
    private JTextArea stats;
    private JPanel portrait;
    private JLabel portraitLabel;
    private JButton backButton;
    private JPanel charRootPanel;

    public JPanel getCharRootPanel() {
        return charRootPanel;
    }

    public JTextArea getStats() {
        return stats;
    }

    public JPanel getPortrait() {
        return portrait;
    }

    public JLabel getPortraitLabel() {
        return portraitLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
