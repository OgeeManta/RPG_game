package view;

import javax.swing.*;

public class CombatDisplay {
    private JPanel enemyPortrait;
    private JPanel playerPortrait;
    private JTextArea combatTextArea;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextArea enemyStatsTextArea;
    private JButton button4;
    private JTextArea playerStatsTextArea;
    private JPanel combatRootPanel;
    private JLabel playerPortraitLabel;
    private JLabel enemyPortraitLabel;

    public JPanel getEnemyPortrait() {
        return enemyPortrait;
    }

    public JPanel getPlayerPortrait() {
        return playerPortrait;
    }

    public JLabel getPlayerPortraitLabel() {
        return playerPortraitLabel;
    }

    public JLabel getEnemyPortraitLabel() {
        return enemyPortraitLabel;
    }

    public JTextArea getCombatTextArea() {
        return combatTextArea;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JTextArea getEnemyStatsTextArea() {
        return enemyStatsTextArea;
    }

    public JButton getButton4() {
        return button4;
    }

    public JTextArea getPlayerStatsTextArea() {
        return playerStatsTextArea;
    }

    public JPanel getCombatRootPanel() {
        return combatRootPanel;
    }
}
