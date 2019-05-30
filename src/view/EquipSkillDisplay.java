package view;

import javax.swing.*;

public class EquipSkillDisplay {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JLabel skillSlot1Label;
    private JLabel skillSlot3Label;
    private JLabel skillSlot2Label;
    private JLabel skillSlot4Label;
    private JPanel equipSkillRootPanel;
    private JButton backButton;
    private JButton equipButton;

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JButton getEquipButton() {
        return equipButton;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public JComboBox getComboBox3() {
        return comboBox3;
    }

    public JComboBox getComboBox4() {
        return comboBox4;
    }

    public JLabel getSkillSlot1Label() {
        return skillSlot1Label;
    }

    public JLabel getSkillSlot3Label() {
        return skillSlot3Label;
    }

    public JLabel getSkillSlot2Label() {
        return skillSlot2Label;
    }

    public JLabel getSkillSlot4Label() {
        return skillSlot4Label;
    }

    public JPanel getEquipSkillRootPanel() {
        return equipSkillRootPanel;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
