package model;

import view.Display;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapOfMainLand{

    private Map<Point, JLabel> map = new HashMap<>();

    public MapOfMainLand(Display display) throws IOException {
        for(int i=0;i<6;++i){
            for(int j=0;j<6;++j){
                Point tmp = new Point(i,j);
                switch(i){
                    case 0: switch(j){
                        case 0:
                            map.put(tmp,display.getMap00());
                            break;
                        case 1:
                            map.put(tmp,display.getMap01());
                            break;
                        case 2: map.put(tmp,display.getMap02());
                            break;
                        case 3: map.put(tmp,display.getMap03());
                            break;
                        case 4: map.put(tmp,display.getMap04());
                            break;
                        case 5: map.put(tmp,display.getMap05());
                            break;
                    }
                    break;
                    case 1: switch(j){
                        case 0:
                            map.put(tmp,display.getMap10());
                            break;
                        case 1: map.put(tmp,display.getMap11());
                            break;
                        case 2: map.put(tmp,display.getMap12());
                            break;
                        case 3: map.put(tmp,display.getMap13());
                            break;
                        case 4:
                            map.put(tmp,display.getMap14());
                            break;
                        case 5: map.put(tmp,display.getMap15());
                            break;
                    }
                    break;
                    case 2: switch(j){
                        case 0: map.put(tmp,display.getMap20());
                            break;
                        case 1: map.put(tmp,display.getMap21());
                            break;
                        case 2: map.put(tmp,display.getMap22());
                            break;
                        case 3: map.put(tmp,display.getMap23());
                            break;
                        case 4: map.put(tmp,display.getMap24());
                            break;
                        case 5: map.put(tmp,display.getMap25());
                            break;
                    }
                    break;
                    case 3: switch(j){
                        case 0: map.put(tmp,display.getMap30());
                            break;
                        case 1: map.put(tmp,display.getMap31());
                            break;
                        case 2: map.put(tmp,display.getMap32());
                            break;
                        case 3: map.put(tmp,display.getMap33());
                            break;
                        case 4: map.put(tmp,display.getMap34());
                            break;
                        case 5: map.put(tmp,display.getMap35());
                            break;
                    }
                    break;
                    case 4: switch(j){
                        case 0: map.put(tmp,display.getMap40());
                            break;
                        case 1: map.put(tmp,display.getMap41());
                            break;
                        case 2: map.put(tmp,display.getMap42());
                            break;
                        case 3: map.put(tmp,display.getMap43());
                            break;
                        case 4: map.put(tmp,display.getMap44());
                            break;
                        case 5: map.put(tmp,display.getMap45());
                            break;
                    }
                    break;
                    case 5: switch(j){
                        case 0: map.put(tmp,display.getMap50());
                            break;
                        case 1: map.put(tmp,display.getMap51());
                            break;
                        case 2: map.put(tmp,display.getMap52());
                            break;
                        case 3: map.put(tmp,display.getMap53());
                            break;
                        case 4: map.put(tmp,display.getMap54());
                            break;
                        case 5: map.put(tmp,display.getMap55());
                            break;
                    }
                    break;
                }
            }
        }
    }

    public Map<Point, JLabel> getMap() {
        return map;
    }
}
