package controller;

import model.*;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controller{

    private Player player;
    private Display display;
    private CombatDisplay combatDisplay;
    private TalentDisplay talentDisplay;
    private CharacterDisplay characterDisplay;
    private SkillDisplay skillDisplay;
    private EquipSkillDisplay equipSkillDisplay;
    private ArrayList<Enemy> enemies;
    private ArrayList<Talent> talents;
    private Enemy currentEnemy;
    private MapOfMainLand map;
    private Enemy tmpEnemy;

    private JFrame skillFrame = new JFrame("Skills");

    public Controller(Player player,ArrayList<Enemy> enemies,ArrayList<Talent> talents,Display display,
                      CombatDisplay combatDisplay,TalentDisplay talentDisplay, CharacterDisplay characterDisplay,
                      SkillDisplay skillDisplay,EquipSkillDisplay equipSkillDisplay, MapOfMainLand map){
        this.player = player;
        this.talents = talents;
        this.display = display;
        this.combatDisplay = combatDisplay;
        this.talentDisplay = talentDisplay;
        this.characterDisplay = characterDisplay;
        this.skillDisplay = skillDisplay;
        this.equipSkillDisplay = equipSkillDisplay;
        this.enemies = enemies;
        this.map = map;
        skillFrame.setSize(250,250);
        skillFrame.add(skillDisplay.getSkillRootPanel());
        skillFrame.setLocationRelativeTo(null);
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public void updateStats(){
        display.getStats().setText( "Name: " + player.getName() + "\n" +
                                    "Level: " + player.getLevel()
                                    );
    }

    public void refreshMap(Point p) throws IOException {
        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getMap00().getWidth(), display.getMap00().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);
        map.getMap().get(p).setIcon(playerIcon);
    }

    public void clearMap(Point p){
        map.getMap().get(p).setIcon(null);
    }

    public  void combat(JFrame frame,Player player,Enemy enemy) throws IOException {
        MainFrame.onMapDisplay = false;
        tmpEnemy = new Enemy(getCurrentEnemy());
        combatDisplay.getEndCombatButton().setEnabled(false);
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(combatDisplay.getCombatRootPanel());
        frame.revalidate();
        frame.repaint();

        frame.getRootPane().setDefaultButton(combatDisplay.getAttackButton());
        combatDisplay.getAttackButton().requestFocus();

        combatDisplay.getAttackButton().setEnabled(true);
        combatDisplay.getSkillsButton().setEnabled(true);
        combatDisplay.getItemsButton().setEnabled(true);
        combatDisplay.getRunButton().setEnabled(true);

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(combatDisplay.getPlayerPortraitLabel().getWidth(), combatDisplay.getPlayerPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        BufferedImage enemyImage = ImageIO.read(new File(getCurrentEnemy().getIconPath()));
        Image enemyImg = enemyImage.getScaledInstance(combatDisplay.getEnemyPortraitLabel().getWidth(), combatDisplay.getEnemyPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon enemyIcon = new ImageIcon(enemyImg);

        combatDisplay.getPlayerPortraitLabel().setIcon(playerIcon);
        combatDisplay.getEnemyPortraitLabel().setIcon(enemyIcon);

        combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
        combatDisplay.getPlayerStatsTextArea().setEditable(false);
        combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
        combatDisplay.getEnemyStatsTextArea().setEditable(false);

        combatDisplay.getCombatTextArea().setText("You have encountered " + enemy.getName() + "!");
        combatDisplay.getCombatTextArea().setEditable(false);





    }

    public void showTalentFrame(JFrame frame) throws IOException {

        MainFrame.onMapDisplay = false;
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(talentDisplay.getTalentRootPanel());
        frame.revalidate();
        frame.repaint();

        initIcon("./resources/icons/icon.4_87.png",talentDisplay.getOffenseButton0());

        initIcon("./resources/icons/icon.1_36.png",talentDisplay.getOffenseButton00());

        initIcon("./resources/icons/icons8_79.png",talentDisplay.getOffenseButton1());

        initIcon("./resources/icons/icon.1_68.png",talentDisplay.getOffenseButton01());

        initIcon("./resources/icons/icon.2_23.png",talentDisplay.getOffenseButton2());

        refreshTalentPoints();

        initOffenseTalents();

        turnOffButtonsIfNoPointsToSpend();



    }

    public void initIcon(String path,JButton button) throws IOException {

        BufferedImage Image = ImageIO.read(new File(path));
        Image img = Image.getScaledInstance(button.getWidth(), button.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        button.setIcon(icon);
    }

    private void initIcon(String path,JLabel label) throws IOException {
        BufferedImage Image = ImageIO.read(new File(path));
        Image img = Image.getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        label.setIcon(icon);
    }

    public void initUnlockedIcon(String path,JButton button) throws IOException {

        BufferedImage Image = ImageIO.read(new File(path));
        Image img = Image.getScaledInstance(button.getWidth(), button.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        button.setDisabledIcon(icon);
    }

    public void clickedTalent(JFrame frame, int index){
        if(player.getTalentPoints() > 0) {
            player.getTalents().get(index).setUnlocked(true);
            player.spendTalentPoint();
            player.getTalents().get(index).setLevel(player.getTalents().get(index).getLevel() + 1);
        }else{
            JOptionPane.showMessageDialog(frame,"You've no talent points to spend!");
        }
    }

    public void refreshTalentPoints(){
        talentDisplay.getPointsToSpend().setText("Talent points: " + player.getTalentPoints());
    }

    public void turnOffButtonsIfNoPointsToSpend(){
        if(player.getTalentPoints() <= 0){
            talentDisplay.getOffenseButton0().setEnabled(false);
            talentDisplay.getOffenseButton00().setEnabled(false);
            talentDisplay.getOffenseButton1().setEnabled(false);
            talentDisplay.getOffenseButton01().setEnabled(false);
            talentDisplay.getOffenseButton2().setEnabled(false);
            talentDisplay.getDefenseButton0().setEnabled(false);
            talentDisplay.getDefenseButton1().setEnabled(false);
            talentDisplay.getDefenseButton2().setEnabled(false);
            talentDisplay.getUtilityButton0().setEnabled(false);
            talentDisplay.getUtilityButton1().setEnabled(false);
            talentDisplay.getUtilityButton2().setEnabled(false);
        }
    }

    public void refreshTalentDisplay(JButton button,Talent talent,int nextLevel){
        StringBuilder sb = new StringBuilder();
        sb.append(talent.getToolTipText());
        int previousLevel = talent.getValue();
        talent.setValue(talent.getValue() + nextLevel);
        talent.setToolTipText(talent.getToolTipText() + previousLevel + " --> " + talent.getValue());
        button.setToolTipText(talent.getToolTipText());
        talent.setToolTipText(sb.toString());
    }

    public void initOffenseTalents() throws IOException {

        talentDisplay.getOffenseButton0().setEnabled(true);
        talentDisplay.getOffenseButton00().setEnabled(true);

        for(int i=0;i<player.getTalents().size();++i){
            if(player.getTalents().get(i).isUnlocked()){
                if(player.getTalents().get(0).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.4_87.png",talentDisplay.getOffenseButton0());
                    talentDisplay.getOffenseButton1().setEnabled(true);
                }
                if(player.getTalents().get(1).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.1_36.png",talentDisplay.getOffenseButton00());
                    talentDisplay.getOffenseButton01().setEnabled(true);
                }
                if(player.getTalents().get(2).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icons8_79.png",talentDisplay.getOffenseButton1());
                }
                if(player.getTalents().get(3).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.1_68.png",talentDisplay.getOffenseButton01());
                }
                if(player.getTalents().get(2).isUnlocked() && player.getTalents().get(3).isUnlocked()){
                    talentDisplay.getOffenseButton2().setEnabled(true);
                }
                if(player.getTalents().get(4).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.2_23.png",talentDisplay.getOffenseButton2());
                }
            }
        }
    }

    public void showCharacterFrame(JFrame frame) throws IOException {

        MainFrame.onMapDisplay = false;
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(characterDisplay.getCharRootPanel());
        frame.revalidate();
        frame.repaint();

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        characterDisplay.getPortraitLabel().setIcon(playerIcon);

        characterDisplay.getStats().setText(player.getStats());

    }

    public void initMainLandMap() throws IOException {
        Point volskath = new Point(1,5);
        BufferedImage volskathImage = ImageIO.read(new File("./resources/icons/icon.3_55.png"));
        Image volskathImg = volskathImage.getScaledInstance(display.getMap00().getWidth(), display.getMap00().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon volskathIcon = new ImageIcon(volskathImg);

        map.getMap().get(volskath).setIcon(volskathIcon);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void initComponents(JFrame frame) throws IOException {

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        refreshMap(player.getCurrentLocation());

        initMainLandMap();

        display.getPortraitLabel().setIcon(playerIcon);

        display.getButton4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainFrame.onMapDisplay = false;
                    showCharacterFrame(frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        display.getButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainFrame.onMapDisplay = false;
                    showTalentFrame(frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        talentDisplay.getOffenseButton0().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,0);
                player.decreaseDamage(player.getTalents().get(0).getValue());
                refreshTalentDisplay(talentDisplay.getOffenseButton0(),player.getTalents().get(0),2);
                player.increaseDamage(player.getTalents().get(0).getValue());
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton00().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,1);
                player.decreaseCrit(player.getTalents().get(1).getValue());
                refreshTalentDisplay(talentDisplay.getOffenseButton00(),player.getTalents().get(1),1);
                player.increaseCrit((player.getTalents().get(1).getValue()));
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,2);
                player.decreaseFireDmg(player.getTalents().get(2).getValue());
                refreshTalentDisplay(talentDisplay.getOffenseButton1(),player.getTalents().get(2),5);
                player.increaseFireDmg(player.getTalents().get(2).getValue());
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton01().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,3);
                player.decreasePoisonDmg(player.getTalents().get(3).getValue());
                refreshTalentDisplay(talentDisplay.getOffenseButton01(),player.getTalents().get(3),3);
                player.increasePoisonDmg(player.getTalents().get(3).getValue());
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,4);
                refreshTalentDisplay(talentDisplay.getOffenseButton2(),player.getTalents().get(4),1);
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                frame.getContentPane().remove(talentDisplay.getTalentRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        characterDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                frame.getContentPane().remove(characterDisplay.getCharRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        display.getSkillsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = false;

                refreshSkills();
                fillComboBoxes();

                frame.getContentPane().remove(display.getRootPanel());
                frame.getContentPane().add(equipSkillDisplay.getEquipSkillRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        equipSkillDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                frame.getContentPane().remove(equipSkillDisplay.getEquipSkillRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        combatDisplay.getAttackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.calculateDamage() > 0){
                    String output = player.attackEnemy(getCurrentEnemy());
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    buildString();
                }
            }
        });

        combatDisplay.getEndCombatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                enemies.remove(getCurrentEnemy());
                if(getCurrentEnemy().getName().equals("Dagobert Bácsi") == false) {
                    enemies.add(tmpEnemy);
                }
                frame.getContentPane().remove(combatDisplay.getCombatRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        combatDisplay.getSkillsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skillDisplay.getSkill_1Label().setText(player.getSkillSlot1().getName());
                skillDisplay.getSkill_1().setText("Use (" + player.getSkillSlot1().getManaCost() + ")");
                skillDisplay.getSkill_2Label().setText(player.getSkillSlot2().getName());
                skillDisplay.getSkill_2().setText("Use (" + player.getSkillSlot2().getManaCost() + ")");
                skillDisplay.getSkill_3Label().setText(player.getSkillSlot3().getName());
                skillDisplay.getSkill_3().setText("Use (" + player.getSkillSlot3().getManaCost() + ")");
                skillDisplay.getSkill_4Label().setText(player.getSkillSlot4().getName());
                skillDisplay.getSkill_4().setText("Use (" + player.getSkillSlot4().getManaCost() + ")");
                skillFrame.setVisible(true);

            }
        });

        skillDisplay.getSkill_1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.getSkillSlot1().getDamage() > 0){
                    String output = player.useSkill(getCurrentEnemy(),player.getSkillSlot1(),skillDisplay);
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    buildString();
                }

                skillFrame.setVisible(false);
            }
        });

        skillDisplay.getSkill_2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.getSkillSlot2().getDamage() > 0){
                    String output = player.useSkill(getCurrentEnemy(),player.getSkillSlot2(),skillDisplay);
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    buildString();
                }

                skillFrame.setVisible(false);
            }
        });
        skillDisplay.getSkill_3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.getSkillSlot3().getDamage() > 0){
                    String output = player.useSkill(getCurrentEnemy(),player.getSkillSlot3(),skillDisplay);
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    buildString();
                }

                skillFrame.setVisible(false);
            }
        });

        skillDisplay.getSkill_4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.getSkillSlot4().getDamage() > 0){
                    String output = player.useSkill(getCurrentEnemy(),player.getSkillSlot4(),skillDisplay);
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    buildString();
                }

                skillFrame.setVisible(false);
            }
        });

    }

    private void refreshSkills(){
        equipSkillDisplay.getSkillSlot1Label().setText(player.getSkillSlot1().getName());
        equipSkillDisplay.getSkillSlot2Label().setText(player.getSkillSlot2().getName());
        equipSkillDisplay.getSkillSlot3Label().setText(player.getSkillSlot3().getName());
        equipSkillDisplay.getSkillSlot4Label().setText(player.getSkillSlot4().getName());
    }

    private void fillComboBoxes(){
        ArrayList<String> myList = new ArrayList<String>();
        for(int i=0;i<player.getSkills().size();++i) {
            myList.add(player.getSkills().get(i).getName());
        }

        equipSkillDisplay.getComboBox1().setModel(new javax.swing.DefaultComboBoxModel<>(myList.toArray()));
        equipSkillDisplay.getComboBox1().setSelectedItem(null);
        equipSkillDisplay.getComboBox2().setModel(new javax.swing.DefaultComboBoxModel<>(myList.toArray()));
        equipSkillDisplay.getComboBox2().setSelectedItem(null);
        equipSkillDisplay.getComboBox3().setModel(new javax.swing.DefaultComboBoxModel<>(myList.toArray()));
        equipSkillDisplay.getComboBox3().setSelectedItem(null);
        equipSkillDisplay.getComboBox4().setModel(new javax.swing.DefaultComboBoxModel<>(myList.toArray()));
        equipSkillDisplay.getComboBox4().setSelectedItem(null);

        equipSkillDisplay.getComboBox1().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Skill tmp = null;
                for(int i=0;i<player.getSkills().size();++i){
                    if(player.getSkills().get(i).getName().equals(e.getItem())){
                        tmp = player.getSkills().get(i);
                    }
                }
                equipSkillDisplay.getSkillSlot1Label().setText(tmp.getName());
                player.setSkillSlot1(tmp);
            }
        });
        equipSkillDisplay.getComboBox2().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Skill tmp = null;
                for(int i=0;i<player.getSkills().size();++i){
                    if(player.getSkills().get(i).getName().equals(e.getItem())){
                        tmp = player.getSkills().get(i);
                    }
                }
                equipSkillDisplay.getSkillSlot2Label().setText(tmp.getName());
                player.setSkillSlot2(tmp);
            }
        });
        equipSkillDisplay.getComboBox3().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Skill tmp = null;
                for(int i=0;i<player.getSkills().size();++i){
                    if(player.getSkills().get(i).getName().equals(e.getItem())){
                        tmp = player.getSkills().get(i);
                    }
                }
                equipSkillDisplay.getSkillSlot3Label().setText(tmp.getName());
                player.setSkillSlot3(tmp);
            }
        });
        equipSkillDisplay.getComboBox4().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Skill tmp = null;
                for(int i=0;i<player.getSkills().size();++i){
                    if(player.getSkills().get(i).getName().equals(e.getItem())){
                        tmp = player.getSkills().get(i);
                    }
                }
                equipSkillDisplay.getSkillSlot4Label().setText(tmp.getName());
                player.setSkillSlot4(tmp);
            }
        });
    }

    private void buildString() {
        StringBuilder sb = new StringBuilder();
        sb.append("You have defeated the enemy.\nYou received ");
        sb.append(getCurrentEnemy().getExpWorth() + " experience points." + "\n");
        combatDisplay.getCombatTextArea().setText(sb.toString());
        player.setCurrExp(player.getCurrExp() + getCurrentEnemy().getExpWorth());
        getCurrentEnemy().setHp(0);
        if(player.checkLevelUp()){
            sb.append("Congratulations you've leveled up!" + "\n");
            sb.append("You are now level: " + player.getLevel() + "\n");
            sb.append("You've gained a Talent Point.");
        }
        combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
        combatDisplay.getAttackButton().setEnabled(false);
        combatDisplay.getSkillsButton().setEnabled(false);
        combatDisplay.getItemsButton().setEnabled(false);
        combatDisplay.getRunButton().setEnabled(false);
        combatDisplay.getEndCombatButton().setEnabled(true);
    }
}
