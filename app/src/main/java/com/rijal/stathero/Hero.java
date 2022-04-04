package com.rijal.stathero;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.checkerframework.checker.signature.qual.ClassGetName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hero {
    private Class dumphero;
    interface Class {

    }
    enum HeroEnum {
        WARRIOR,
        MAGE,
        ARCHER
    }
    enum ClassWarrior implements Class {
        CHAMPION,
        PALADIN
    }
    enum ClassMage implements Class{
        WIZARD,
        SORCERER
    }
    enum ClassArcher implements Class{
        HUNTER,
        SNIPER
    }
    enum RateItem {
        ATTACK,
        DEFENSE,
        ASPD,
        HP
    }
    private HashMap<HeroEnum, Integer> dictHero = new HashMap<HeroEnum, Integer>() {{
        put(HeroEnum.WARRIOR, 12000);
        put(HeroEnum.MAGE, 10000);
        put(HeroEnum.ARCHER, 10000);
    }};
    private HashMap<ClassWarrior, Integer> dictClassWarrior = new HashMap<ClassWarrior, Integer>() {{
        put(ClassWarrior.CHAMPION, 5000);
        put(ClassWarrior.PALADIN, 7000);
    }};

    private HashMap<ClassArcher, Integer> dictClassArcher = new HashMap<ClassArcher, Integer>() {{
        put(ClassArcher.HUNTER, 4000);
        put(ClassArcher.SNIPER, 7000);
    }};
    private HashMap<ClassMage, Integer> dictClassMage = new HashMap<ClassMage, Integer>() {{
        put(ClassMage.WIZARD, 3500);
        put(ClassMage.SORCERER, 5000);
    }};
    private HashMap<RateItem, Integer> dictRateItem = new HashMap<RateItem, Integer>() {{
        put(RateItem.ATTACK, 10000);
        put(RateItem.DEFENSE, 8000);
        put(RateItem.ASPD, 6000);
        put(RateItem.HP, 15000);
    }};

    public HashMap<String, ClassWarrior> dictClassWarriorName = new HashMap<String, ClassWarrior>() {{
        put("Champion", ClassWarrior.CHAMPION);
        put("Paladin", ClassWarrior.CHAMPION);
    }};
    public HashMap<String, ClassArcher> dictClassArcherName = new HashMap<String, ClassArcher>() {{
        put("Sniper", ClassArcher.SNIPER);
        put("Hunter", ClassArcher.HUNTER);
    }};
    public HashMap<String, ClassMage> dictClassMageName = new HashMap<String, ClassMage>() {{
        put("Wizard", ClassMage.WIZARD);
        put("Sorcerer", ClassMage.SORCERER);
    }};
    public HashMap<String, RateItem> dictRateItemName = new HashMap<String, RateItem>() {{
        put("Attack", RateItem.ATTACK);
        put("Defense", RateItem.DEFENSE);
        put("ASPD", RateItem.ASPD);
        put("HP", RateItem.HP);
    }};

    public List<RateItem> listRareItem = new ArrayList<>();

    private HeroEnum hero;
    private ClassWarrior classWarrior;
    private ClassArcher classArcher;
    private ClassMage classMage;
    private RateItem rateItem;

    public void setHero(HeroEnum hero) {
        this.hero = hero;
    }
    public void setClassWarrior(ClassWarrior classWarrior) {
        this.classWarrior = classWarrior;
    }
    public void setClassArcher(ClassArcher classArcher) {

        this.classArcher = classArcher;
        Log.d("ARCHER", classArcher.toString());
    }
    public void setClassMage(ClassMage classMage) {
        this.classMage = classMage;
    }
    public void setRateItem(RateItem rateItem) {
        this.rateItem = rateItem;
    }

    public HeroEnum getHero() {
        return hero;
    }

    public int calculateHero() {
        int poin = 0;
        switch (hero) {
            case WARRIOR:
                poin += dictHero.get(HeroEnum.WARRIOR);
                break;
            case ARCHER:
                poin += dictHero.get(HeroEnum.ARCHER);
                break;
            case MAGE:
                poin += dictHero.get(HeroEnum.MAGE);
                break;
            default:
                break;
        }
        return poin;
    }
    public int calculateClass() {
        int poin = 0;
        switch (hero) {
            case WARRIOR:
                switch (classWarrior) {
                    case CHAMPION:
                        poin += dictClassWarrior.get(ClassWarrior.CHAMPION);
                        break;
                    case PALADIN:
                        poin += dictClassWarrior.get(ClassWarrior.PALADIN);
                        break;
                    default:
                        break;
                }
                break;
            case ARCHER:
                switch (this.classArcher) {
                    case HUNTER:
                        poin += dictClassArcher.get(ClassArcher.HUNTER);
                        break;
                    case SNIPER:
                        poin += dictClassArcher.get(ClassArcher.SNIPER);
                        break;
                    default:
                        break;
                }
                break;
            case MAGE:
                switch (classMage) {
                    case WIZARD:
                        poin += dictClassMage.get(ClassMage.WIZARD);
                        break;
                    case SORCERER:
                        poin += dictClassMage.get(ClassMage.SORCERER);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return poin;
    }
    public int calculateRareItem() {
        int poin = 0;
        for(RateItem rateItem : listRareItem) {
            poin += dictRateItem.get(rateItem);
        }
        return poin;
    }
    public int calculatePoin() {
        int poin = 0;
        poin += calculateHero();
        poin += calculateClass();
        poin += calculateRareItem();
        return poin;
    }
}
