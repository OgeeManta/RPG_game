package view;

import javax.swing.*;

public class CombatDisplay {
    private JPanel enemyPortrait;
    private JPanel playerPortrait;
    private JTextArea combatTextArea;
    private JButton runButton;
    private JButton itemsButton;
    private JButton skillsButton;
    private JTextArea enemyStatsTextArea;
    private JButton attackButton;
    private JTextArea playerStatsTextArea;
    private JPanel combatRootPanel;
    private JLabel playerPortraitLabel;
    private JLabel enemyPortraitLabel;
    private JButton endCombatButton;

    public JButton getEndCombatButton() {
        return endCombatButton;
    }

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

    public JButton getRunButton() {
        return runButton;
    }

    public JButton getItemsButton() {
        return itemsButton;
    }

    public JButton getSkillsButton() {
        return skillsButton;
    }

    public JTextArea getEnemyStatsTextArea() {
        return enemyStatsTextArea;
    }

    public JButton getAttackButton() {
        return attackButton;
    }

    public JTextArea getPlayerStatsTextArea() {
        return playerStatsTextArea;
    }

    public JPanel getCombatRootPanel() {
        return combatRootPanel;
    }
}
