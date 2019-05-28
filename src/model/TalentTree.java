package model;
import view.TalentDisplay;

import java.util.ArrayList;


public class TalentTree {

    Talent damageMastery = new Talent("Damage Mastery","Your damage is increased by ",2);
    Talent critMastery = new Talent("Critical Mastery","Your critical chance is increased by ",1);
    Talent flameBringer = new Talent("Flamebringer","Your attacks deal an additional ", 5);
    Talent poisonedBlades = new Talent("Poisoned Blades","Your attacks deal an additional ", 3);
    Talent lethality = new Talent("Lethality","Your damage is increased against targets with lower than 30% Hp by ",2);

    ArrayList<Talent> talentList;

    public TalentTree(TalentDisplay talentDisplay){

        talentList = new ArrayList<Talent>();

        talentDisplay.getOffenseButton0().setToolTipText(damageMastery.getToolTipText() + " " +  damageMastery.getValue());
        talentList.add(damageMastery);
        talentDisplay.getOffenseButton00().setToolTipText(critMastery.getToolTipText() + " " +  critMastery.getValue());
        talentList.add(critMastery);
        talentDisplay.getOffenseButton1().setToolTipText(flameBringer.getToolTipText() + flameBringer.getValue() + " fire damage");
        talentList.add(flameBringer);
        talentDisplay.getOffenseButton01().setToolTipText(poisonedBlades.getToolTipText() + poisonedBlades.getValue() + " poison damage");
        talentList.add(poisonedBlades);
        talentDisplay.getOffenseButton2().setToolTipText(lethality.getToolTipText() + lethality.getValue() + "x times.");
        talentList.add(lethality);
    }

    public ArrayList<Talent> getTalentList(){
        return this.talentList;
    }

}
