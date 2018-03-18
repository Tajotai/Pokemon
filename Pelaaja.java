
package Pokemon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class Pelaaja{
 protected Pokemon[] tiimi; //array mokeponeista
 protected Pokemon current; //tällä hetkellä tappelussa oleva pokemon
 protected boolean omaVuoro;
 public Pelaaja(Pokemon[] monit){ 
  this.tiimi=monit;
  current=tiimi[0];//ei julkiseen käyttöön, ei ole tarkotus tehdä Pelaaja-oliota vaan ihmis- ja AI-olioita.
 }
 public Pokemon[] getTiimi(){
   return this.tiimi;
 }
 public Pokemon getCurrent(){
   return this.current;
 }
 public void setCurrent(int n){
	 this.current=tiimi[n];
 }
 public void setCurrent(Pokemon p){
	 this.current=p;
 }
 
 public abstract Object valitseSwap();
 public abstract Object valitseAction();
 public void attack(int n, Pelaaja opp){
     Attack[] attackSet=current.getAttacks();
     Attack a = attackSet[n];
     Pokemon enemy = opp.getCurrent();
     this.current.attack(a,enemy);

 }
 //emme anna reppua AI-lle, joten Array itemeista vain ihmis-oliolle.
//valitseAction on myös parempi tehdä erikseen AI-lle ja ihmiselle
 
 
}