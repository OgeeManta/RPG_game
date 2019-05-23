package model;
import view.TalentDisplay;

import java.util.ArrayList;


public class TalentTree {

    Talent damageMastery = new Talent("Damage Mastery","Your damage is increased by ",2);
    Talent critMastery = new Talent("Critical Mastery","Your critical chance is increased by ",1);

    ArrayList<Talent> talentList;

    public TalentTree(TalentDisplay talentDisplay){

        talentList = new ArrayList<Talent>();

        talentDisplay.getOffenseButton0().setToolTipText( damageMastery.getToolTipText() + " " + damageMastery.getLevel() * damageMastery.getValue());
        talentList.add(damageMastery);
        talentDisplay.getOffenseButton00().setToolTipText(critMastery.getToolTipText() + " " + critMastery.getLevel() * critMastery.getValue());
        talentList.add(critMastery);
    }

    public ArrayList<Talent> getTalentList(){
        return this.talentList;
    }

}
