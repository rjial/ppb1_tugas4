package com.rijal.stathero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Hero.HeroEnum heroEnum;
    RadioGroup rgWarrior, rgArcher, rgMage;
    CheckBox cbAttack, cbDefense, cbASPD, cbHP;
    TextView txtNameChar, txtHeroPoin, txtClassPoin, txtRareItem, txtTotalPoin;
    TextInputEditText txtChar;
    Button btnHitung;
    CircleImageView imgHero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rgHero = (RadioGroup) findViewById(R.id.rgHero);
        rgWarrior = (RadioGroup) findViewById(R.id.rgClassWarrior);
        rgArcher = (RadioGroup) findViewById(R.id.rgClassArcher);
        rgMage = (RadioGroup) findViewById(R.id.rgClassMage);
        cbAttack = (CheckBox) findViewById(R.id.cbAttack);
        cbDefense = (CheckBox) findViewById(R.id.cbDefense);
        cbASPD = (CheckBox) findViewById(R.id.cbASPD);
        cbHP = (CheckBox) findViewById(R.id.cbHP);
        btnHitung = (Button) findViewById(R.id.btnHitung);
        txtNameChar = (TextView) findViewById(R.id.txtNameChar);
        txtHeroPoin = (TextView) findViewById(R.id.txtHeroPoin);
        txtClassPoin = (TextView) findViewById(R.id.txtClassPoin);
        txtRareItem = (TextView) findViewById(R.id.txtRareItem);
        txtTotalPoin = (TextView) findViewById(R.id.txtTotalPoin);
        txtChar = (TextInputEditText) findViewById(R.id.txtChar);
        imgHero = (CircleImageView) findViewById(R.id.imgHero);
        Hero hero = new Hero();
        rgHero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) findViewById(i);
                String heroName = rb.getText().toString();
                switch (heroName){
                    case "Warrior":
                        imgHero.setImageDrawable(getDrawable(R.drawable.warrior));
                        heroEnum = Hero.HeroEnum.WARRIOR;
                        hero.setHero(Hero.HeroEnum.WARRIOR);
                        rgWarrior.setVisibility(View.VISIBLE);
                        rgArcher.setVisibility(View.GONE);
                        rgMage.setVisibility(View.GONE);

                        break;
                    case "Mage":
                        imgHero.setImageDrawable(getDrawable(R.drawable.mage));
                        heroEnum = Hero.HeroEnum.MAGE;
                        hero.setHero(Hero.HeroEnum.MAGE);
                        rgWarrior.setVisibility(View.GONE);
                        rgArcher.setVisibility(View.GONE);
                        rgMage.setVisibility(View.VISIBLE);

                        break;
                    case "Archer":
                        imgHero.setImageDrawable(getDrawable(R.drawable.archer));
                        heroEnum = Hero.HeroEnum.ARCHER;
                        hero.setHero(Hero.HeroEnum.ARCHER);
                        rgWarrior.setVisibility(View.GONE);
                        rgArcher.setVisibility(View.VISIBLE);
                        rgMage.setVisibility(View.GONE);

                        break;
                    default:
                        imgHero.setImageDrawable(getDrawable(R.drawable.ic_baseline_account_circle_24));
                        break;
                }
//                hero.setRadioGroupClass(rgClass, heroEnum);
            }
        });
        rgWarrior.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) findViewById(i);
                hero.setClassWarrior(hero.dictClassWarriorName.get(rb.getText().toString()));
            }
        });
        rgArcher.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) findViewById(i);
                hero.setClassArcher(hero.dictClassArcherName.get(rb.getText().toString()));
            }
        });
        rgMage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) findViewById(i);
                hero.setClassMage(hero.dictClassMageName.get(rb.getText()));
            }
        });
        cbAttack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hero.listRareItem.add(Hero.RateItem.ATTACK);
                } else {
                    hero.listRareItem.remove(Hero.RateItem.ATTACK);
                }
            }
        });
        cbDefense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hero.listRareItem.add(Hero.RateItem.DEFENSE);
                } else {
                    hero.listRareItem.remove(Hero.RateItem.DEFENSE);
                }
            }
        });
        cbASPD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hero.listRareItem.add(Hero.RateItem.ASPD);
                } else {
                    hero.listRareItem.remove(Hero.RateItem.ASPD);
                }
            }
        });
        cbHP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hero.listRareItem.add(Hero.RateItem.HP);
                } else {
                    hero.listRareItem.add(Hero.RateItem.HP);
                }
            }
        });
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean lanjut = true;
                switch (hero.getHero()) {
                    case WARRIOR:
                        if (rgWarrior.getCheckedRadioButtonId() == -1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Please select one of classes", Toast.LENGTH_SHORT).show();
                                }
                            });
                            lanjut = false;
                        }
                        break;
                    case ARCHER:
                        if (rgArcher.getCheckedRadioButtonId() == -1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Please select one of classes", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        lanjut = false;
                        break;
                    case MAGE:
                        if (rgMage.getCheckedRadioButtonId() == -1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Please select one of classes", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        lanjut = false;
                        break;
                    default:

                        break;
                }
                if (lanjut) {
                    txtNameChar.setText("Name : " + txtChar.getText());
                    txtClassPoin.setText("Class : " + String.valueOf(hero.calculateClass()));
                    txtHeroPoin.setText("Hero : " + String.valueOf(hero.calculateHero()));
                    txtRareItem.setText("Item : " + String.valueOf(hero.calculateRareItem()));
                    txtTotalPoin.setText("Total Poin : " + String.valueOf(hero.calculatePoin()));
                    Toast.makeText(MainActivity.this, String.valueOf(hero.calculatePoin()), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}