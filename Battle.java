package Pokemon;
import java.awt.*;
import java.awt.event.*;
import java.lang.StringBuffer;
import java.util.*;
import Pokemon.*;
import javax.swing.*;

public class Battle {
 private static Object lol=new Object();
 public static void main(String[] args){
  
  
  AttackSets kek=new AttackSets();
  TypeChart kak=new TypeChart();
   final JFrame f=new JFrame("Testi");
   Pokemon[] thin=new Pokemon[4];
   thin[0]=new Pokemon(Species.PIKACHU);
   thin[1]=new Pokemon(Species.CHARIZARD);
   thin[2]=new Pokemon(Species.PIDGEOT);
   thin[3]=new Pokemon(Species.BLASTOISE);
    Pelaaja p1=new Ihminen(thin);
    Pelaaja p2=generateAI(2, 5);
    boolean b=matsi(p1, p2);
    System.out.println(b);
  
 }
  public static boolean matsi(Pelaaja p1, Pelaaja p2){
   int p=0;
   Object p1action=new Object();
   Object p2action=new Object();
  final JFrame f=new JFrame();
   JLabel b=new JLabel("<html>You are challenged by "+p2.toString()+"<br/>Pelaaja 1 heitti: "+p1.getCurrent().getNimi()+"<br/>"+"Pelaaja 2 heitti: "+p2.getCurrent().getNimi()+"</html>");
   b.setPreferredSize(new Dimension(700, 520));
    boolean pe1=true;
   boolean pe2=true;
   p2.setCurrent(0);
   p1.setCurrent(0);
   StringBuffer log=new StringBuffer();
  JButton a=new JButton("Aloita vuoro");
  
  
  f.getContentPane().add(b,BorderLayout.PAGE_START);
  f.getContentPane().add(a, BorderLayout.CENTER);
  a.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     
     synchronized(lol){
      lol.notify();
     
      }
    }
   });
   
   
   
   
   
   
   while(pe1 && pe2){
    
    
    
    pe1=false; pe2=false;
    for(int i=0; i<p1.getTiimi().length; i++){
    if(p1.getTiimi()[i].getHealth()>0){
     pe1=true;
    }
   }
   for(int j=0; j<p2.getTiimi().length; j++){
    if(p2.getTiimi()[j].getHealth()>0){
     pe2=true;
    }
   }
   if(p1.getCurrent()==null || p2.getCurrent()==null){
    return pe1;
   }
   while(p1.getCurrent().getHealth()>0 && p2.getCurrent().getHealth()>0){
    
    if(p>0){
     
     log.delete(0, log.length()); //oletettavasti stringbuffer s‰‰st‰‰ huomattavasti konetta
     log.append("<html>Pelaaja 1, edellinen vuoro: " +p1action.toString()+ " ja " + p1.getCurrent().getNimi()+ ": " +p1.getCurrent().getHealth()+ "<br/>"+ "Pelaaja 2, edellinen vuoro: "+p2action.toString()+" ja "+p2.getCurrent().getNimi()+": "+p2.getCurrent().getHealth()+"</html>");
     b.setText(log.toString());
    }
    else{p+=1;}
    f.setVisible(true);
    f.pack();
    synchronized(lol){
     try{
      lol.wait();
     }catch(Exception e){}
    }
    f.setVisible(false);
    
   p1action=p1.valitseAction();
   p2action=p2.valitseAction();
   
   if((p1action instanceof Attack) && (p2action instanceof Attack)){
    if(p1.getCurrent().getSpeed()>=p2.getCurrent().getSpeed()){
    p1.getCurrent().attack((Attack) p1action, p2.getCurrent());
    if(p2.getCurrent().getHealth()>0){p2.getCurrent().attack((Attack) p2action, p1.getCurrent());}
   
   }
    else{
     p2.getCurrent().attack((Attack) p2action, p1.getCurrent());
     if(p1.getCurrent().getHealth()>0){p1.getCurrent().attack((Attack) p1action, p2.getCurrent());}
    }
   }
   else{p1.setCurrent((Pokemon) p1action);
   if(p2action instanceof Attack){
    p2.getCurrent().attack((Attack) p2action, p1.getCurrent());
     }
   
   else{
    p2.setCurrent((Pokemon)p2action);
   }
   }
   }
   if(p1.getCurrent().getHealth()<=0){
    try{p1.setCurrent((Pokemon)p1.valitseSwap());}catch(Exception e){break;}
    
   }
   if(p2.getCurrent().getHealth()<=0){
    try{p2.setCurrent((Pokemon)p2.valitseSwap());}catch(Exception e){break;}
   }
   }
   pe1=false;
   for(int i=0; i<p1.getTiimi().length; i++){
     if(p1.getTiimi()[i].getHealth()>0){
       pe1=true;
     }
   }
   
  return pe1;
  }
  
  public static AI generateAI(int diff, int count){
      Random rng= new Random();
      Pokemon[] monit = new Pokemon[count];
      int[][] probChart= new int[][]{{40,30,20,10,0},
                                 {25,30,30,10,5},
                                 {17,30,30,16,7},
                                 {11,21,36,21,11},
                                 {7,16,30,30,27},
                                 {5,10,30,30,25},
                                 {0,10,20,30,40}};
      int[] row = probChart[diff-1];
      for (int i=0; i<count; i++){
        int num=rng.nextInt(100);
        int dc;
        if(num<row[0]){
          dc=1;
          row[0] -= 4;
          row[1] -= 2;
          row[3] += 2;
          row[4] += 4;
        } else if(num<row[0]+row[1]){
          dc=12;
          row[0] -= 2;
          row[1] -= 1;
          row[3] += 1;
          row[4] += 2;
        } else if(num>=row[0]+row[1]+row[2] && num<row[0]+row[1]+row[2]+row[3]){
          dc=4;
          row[0] += 2;
          row[1] += 1;
          row[3] -= 1;
          row[4] -= 2;
        }
        else if(num>row[0]+row[1]+row[2]+row[3]){
          dc=5;
          row[0] += 4;
          row[1] += 2;
          row[3] -= 2;
          row[4] -= 4;
        } else {
          dc=3;
        }
        Species sp = Species.getRand(dc);
        monit[i]=new Pokemon(sp);
      }
      AI ai=new AI(monit);
      return ai;
    }



}


  
