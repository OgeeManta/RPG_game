package model;
import view.TalentDisplay;

import javax.swing.*;
import java.util.TreeMap;

public class TalentTree {

    Talent damageMastery = new Talent("Damage Mastery","Your damage is increased by ",2);

    public TalentTree(TalentDisplay talentDisplay){
        talentDisplay.getOffenseButton0().setToolTipText( damageMastery.getToolTipText() + " " + damageMastery.getLevel() * damageMastery.getValue());
    }



}
