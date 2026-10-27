package com.hack.gdclient;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import android.content.res.ColorStateList;

/** UI-only prototype. Gameplay integration is intentionally not wired in yet. */
public class MainActivity extends Activity {
    private static final int BLUE=Color.rgb(0,174,255), PANEL=Color.rgb(2,16,39), CARD=Color.rgb(7,34,69);
    private LinearLayout root;
    private int gems=500, speed=100;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.rgb(0,28,68));
        getWindow().setNavigationBarColor(Color.rgb(0,18,43));
        showMenu();
    }
    private GradientDrawable bg(int color,int stroke,int radius) {
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius);
        if(stroke>0)d.setStroke(2,stroke); return d;
    }
    private TextView text(String s,int size,int color,boolean bold) {
        TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); return t;
    }
    private void showMenu() {
        root=new LinearLayout(this); root.setOrientation(1); root.setPadding(18,14,18,18); root.setBackground(bg(PANEL,BLUE,22));
        TextView title=text("GD CLIENT  •  MOD MENU",22,BLUE,true); title.setGravity(Gravity.CENTER);
        root.addView(title,new LinearLayout.LayoutParams(-1,54));
        Button close=new Button(this); close.setText("CLOSE MENU"); close.setTextColor(Color.WHITE); close.setBackground(bg(CARD,BLUE,14));
        close.setOnClickListener(v->showLauncher()); root.addView(close,new LinearLayout.LayoutParams(-1,46));
        addToggle("Auto Play","Automatically handle level inputs");
        addToggle("Noclip","Collision option • game integration pending");
        addToggle("Fly","Movement option • game integration pending");
        TextView sl=text("Speed: 1.00×",16,Color.WHITE,true); root.addView(sl);
        SeekBar speedBar=new SeekBar(this); speedBar.setMax(300); speedBar.setProgress(100);
        speedBar.setProgressTintList(ColorStateList.valueOf(BLUE));
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar b,int p,boolean u){speed=Math.max(25,p);sl.setText(String.format(java.util.Locale.US,"Speed: %.2f×",speed/100f));}
            public void onStartTrackingTouch(SeekBar b){} public void onStopTrackingTouch(SeekBar b){}
        }); root.addView(speedBar);
        TextView gl=text("Gems to add: 500",16,Color.WHITE,true); root.addView(gl);
        SeekBar gemBar=new SeekBar(this); gemBar.setMax(10000); gemBar.setProgress(500); gemBar.setProgressTintList(ColorStateList.valueOf(BLUE));
        gemBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar b,int p,boolean u){gems=p;gl.setText("Gems to add: "+gems);}
            public void onStartTrackingTouch(SeekBar b){} public void onStopTrackingTouch(SeekBar b){}
        }); root.addView(gemBar);
        Button add=new Button(this); add.setText("+ ADD GEMS"); add.setTextColor(Color.WHITE); add.setBackground(bg(CARD,BLUE,14));
        add.setOnClickListener(v->Toast.makeText(this,"Game integration pending • selected amount: "+gems,Toast.LENGTH_LONG).show());
        root.addView(add,new LinearLayout.LayoutParams(-1,46));
        addToggle("Auto Complete (RobTop)","Level completion integration pending");
        addToggle("Auto Complete Practice","Practice completion integration pending");
        TextView note=text("UI prototype • gameplay integration pending",12,Color.rgb(150,190,235),false); note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-1,-2); np.topMargin=12; root.addView(note,np);
        ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true); scroll.addView(root); setContentView(scroll);
    }
    private void addToggle(String name,String detail) {
        LinearLayout card=new LinearLayout(this); card.setOrientation(1); card.setPadding(12,6,12,6); card.setBackground(bg(CARD,Color.rgb(22,68,125),14));
        LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,-2); cp.topMargin=8; root.addView(card,cp);
        Switch sw=new Switch(this); sw.setText(name); sw.setTextColor(Color.WHITE); sw.setTextSize(16); sw.setTypeface(Typeface.DEFAULT,Typeface.BOLD); card.addView(sw);
        TextView sub=text(detail,12,Color.rgb(184,210,242),false); card.addView(sub);
        sw.setOnCheckedChangeListener((button,checked)->Toast.makeText(this,name+(checked?" selected":" off")+" • gameplay adapter pending",Toast.LENGTH_SHORT).show());
    }
    private void showLauncher() {
        LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setGravity(Gravity.CENTER); l.setPadding(24,24,24,24); l.setBackgroundColor(Color.rgb(0,20,49));
        TextView title=text("GD CLIENT",26,BLUE,true); title.setGravity(Gravity.CENTER); l.addView(title,new LinearLayout.LayoutParams(-1,72));
        Button open=new Button(this); open.setText("M  •  OPEN MOD MENU"); open.setTextColor(Color.WHITE); open.setBackground(bg(CARD,BLUE,18));
        open.setOnClickListener(v->showMenu()); l.addView(open,new LinearLayout.LayoutParams(-1,58));
        TextView note=text("Prototype only: gameplay features are not connected.",13,Color.LTGRAY,false); note.setGravity(Gravity.CENTER); l.addView(note);
        setContentView(l);
    }
}
