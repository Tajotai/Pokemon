package Pokemon;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Ihminen extends Pelaaja{
	/*
	 * Pelaajan attribuutin valinta olemassaolo olisi voitu kiert‰‰, mutta k‰ytet‰‰n valitseactionissa
	 */
 int valinta;
 /*
  * Nappia varten ohjelman pys‰ytt‰j‰
  */
 private final Object waiter=new Object();
 /*
  * K‰ytet‰‰n valitseAction()-metodin palauttajana
  */
 private Object ret;
 /*
  * Ihminen-olio luodaan kutsumalla konstruktoria pokemon-arraylla, jolloin superia kutsutaan samalla arraylla.
  */
 public Ihminen(Pokemon[] monit){
  super(monit);
 }
 /*
  * Getteri current-attribuutille (tallennetaan aina senhetkinen ulkona oleva pokemon
  * Palauttaa pokemon-olion
  */
 public Pokemon getCurrent(){
  return super.current;
 }
/*
 * Toteuttaa yliluokan Pelaaja abstraktin metodin valitseAction().
 * Palauttaa Object-tyypin muuttujan, joka on joko Attack- tai Pokemon-tyyppinen olio.
 * Kokonaisuudessaan metodin suorittamiseen vaaditaan pelaajalta kaksi inputtia.
 * Ensimm‰isess‰ inputissa valitaan, tuleeko valitseActionin tyypist‰ Pokemon, vai Attack
 * Riippuen siit‰, mit‰ pelaaja valitsee ensimm‰isess‰ ikkunassa.
 * Metodissa k‰ytet‰‰n waiteria niin, ett‰ metodi luo ikkunat, ja pys‰ytt‰‰ suorituksen, kunnes pelaaja painaa nappia.
 * Kun nappia painetaan, int valinta muttuu, ja valinnan arvon perusteella kutsutaan joko valitseAttack tai valitseSwap.
 * valitseAttack-metodin tai valitseSwapin palauttama olio palautetaan valitseActionin kutsujaan.
 */
 public Object valitseAction(){
	 /*
	  * luodaan ikkuna ja napit, joihin asetetaan sopivat nimet ja actionit
	  * nappien actionperformed huomauttaa ohjelmalle myˆs, ett‰ nyt saa suoriutua loppuun.
	  */
  JFrame f=new JFrame();
  JLabel b=new JLabel("Valitse action!"); 
  b.setPreferredSize(new Dimension(700, 520));
  f.getContentPane().add(b,BorderLayout.PAGE_START);
  
  
  JPanel panel=new JPanel(new GridLayout(1,3));
  panel.setPreferredSize(new Dimension(150, 50));
  GridBagConstraints[] c=new GridBagConstraints[3];
  JPanel[] buttonpanel=new JPanel[3];
  for(int i=0; i<3; i++){
   buttonpanel[i]=new JPanel((new GridBagLayout()));
   
   c[i]=new GridBagConstraints();
   c[i].anchor = GridBagConstraints.CENTER;
  }
  
  JButton b1=new JButton("Attack");
  JButton b3=new JButton("Swap");
  b1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       valinta=1;
        synchronized (waiter) {
          waiter.notify();
        }}});
  b3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       valinta=3;
       synchronized (waiter) {
          waiter.notify();
        }}});
  
  
  buttonpanel[0].add(b1, c[0]);
  buttonpanel[2].add(b3, c[2]);
  for(int j=0; j<3; j++){
  panel.add(buttonpanel[j]);
  
  }
  f.getContentPane().add(panel);
  
  f.pack();
  f.setVisible(true);
  /*
   * Ohjelma pys‰htyy, kunnes nappia painetaan.
   */
   synchronized(waiter) {
     try {
       waiter.wait();
     } catch (InterruptedException ex) {}
    }
   f.setVisible(false);
   if(valinta==1){return valitseAttack();}
  //else if(valinta==2){return valitseItem();} item-luokkien kirjoitus estyi ajank‰ytˆllisist‰ syist‰.
   return valitseSwap();
   
   
   
   
  }
 
 /*
  * K‰ytet‰‰n m‰‰ritt‰m‰‰n valitseAction()-metodin palauttama olio loppuun.
  * Metodi luo uuden ikkunan, ja odottaa pelaajan inputtia samalla tavalla kuin valitseAction.
  * Kun pelaaja klikkaa nappia, Ihmis-olion attribuutti ret muuttuu vastaamaan pelaajan valintaan.
  * Ohjelma jatkaa suorituksen t‰m‰n j‰lkeen loppuun, ja palauttaa kyseisen ret-olion.
  */
 private Object valitseAttack(){ 
  final JFrame f=new JFrame();
  final Attack[] a=this.getCurrent().getAttacks();
  JLabel b=new JLabel("                                                                                             Valitse attack!");
  b.setPreferredSize(new Dimension(720, 500));
  f.getContentPane().add(b, BorderLayout.PAGE_START);
  JButton b11=new JButton(this.getCurrent().getAttacks()[0].getName());  
  JButton b12=new JButton(this.getCurrent().getAttacks()[1].getName());
  JButton b13=new JButton(this.getCurrent().getAttacks()[2].getName());
  JButton b4=new JButton(this.getCurrent().getAttacks()[3].getName());
  b11.addActionListener(new ActionListener(){ 
   public void actionPerformed(ActionEvent e){  
     ret=a[0];
        f.setVisible(false);
        synchronized (waiter) {
          waiter.notify();
        }}});   
  b12.addActionListener(new ActionListener(){ 
   public void actionPerformed(ActionEvent e){
    ret=a[1];
    f.setVisible(false);
    synchronized (waiter) {
          waiter.notify();
        }}});
  b13.addActionListener(new ActionListener(){ 
   public void actionPerformed(ActionEvent e){
    ret=a[2];
    f.setVisible(false);
    synchronized (waiter) {
          waiter.notify();
        }}});
  b4.addActionListener(new ActionListener(){ 
   public void actionPerformed(ActionEvent e){
    ret=a[3];
        f.setVisible(false);
        synchronized (waiter) {
          waiter.notify();
        }}});
  
  //actionlistenerit nyt muuttavat Object-olion ret arvoa ja kun nappia painetaan, metodi jatkaa suorituksen loppuun (palauttaa siis muutetun objectin)
  
  JPanel panel=new JPanel(new GridLayout(2,2));
  panel.setPreferredSize(new Dimension(300, 100));
  GridBagConstraints[][] c=new GridBagConstraints[2][2];
  JPanel[][] buttonpanel=new JPanel[2][2];
  for(int i=0; i<2; i++){
   for(int j=0; j<2; j++){
    
  
   buttonpanel[i][j]=new JPanel((new GridBagLayout()));
   
   c[i][j]=new GridBagConstraints();
   c[i][j].anchor = GridBagConstraints.CENTER;
  }
 }
  
  buttonpanel[0][0].add(b11, c[0][0]);
  buttonpanel[0][1].add(b12, c[0][1]);
  buttonpanel[1][0].add(b13, c[1][0]);
  buttonpanel[1][1].add(b4, c[1][1]);
  
  for(int k=0; k<2; k++){
   for(int l=0; l<2; l++){
    panel.add(buttonpanel[k][l]);
   }
  }
  f.getContentPane().add(panel);
  f.pack();
  f.setVisible(true);
  
   synchronized(waiter) {
     try {
       waiter.wait();
     } catch (InterruptedException ex) {}
    }
   return ret;
  
 }
 /*
  * K‰ytet‰‰n m‰‰ritt‰m‰‰n valitseAction()-metodin palauttama olio loppuun ja vaihtamaan pokemonia, jos current kuolee.
  * Metodi luo uuden ikkunan, ja odottaa pelaajan inputtia samalla tavalla kuin valitseAction.
  * Kun pelaaja klikkaa nappia, Ihmis-olion attribuutti ret muuttuu vastaamaan pelaajan valintaan.
  * Ohjelma jatkaa suorituksen t‰m‰n j‰lkeen loppuun, ja palauttaa kyseisen ret-olion.
  */
 public Object valitseSwap(){
  final JFrame f=new JFrame();
  int l=0;
  int el=0;
  for(int k=0; k<tiimi.length; k++){
   if(tiimi[k].getHealth()>0){
    el+=1;
   }
  }
  final Pokemon[] a=new Pokemon[el];
  for(int i=0; i<this.tiimi.length; i++){
   if(tiimi[i].getHealth()>0){
    
    a[l]=tiimi[i];
    l+=1;
   }
   
  }
  if(a.length==0){return null;}
  JLabel b=new JLabel("Valitse mihin pokemoniin haluat vaihtaa!");
  b.setPreferredSize(new Dimension (700, 520));
  f.getContentPane().add(b, BorderLayout.PAGE_START);
  JButton[] buts=new JButton[el];
  for(int c=0; c<el; c++){
   final int hh=c;
   buts[c]=new JButton(a[c].getNimi());
   buts[c].addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     ret=a[hh];
     synchronized(waiter){
      waiter.notify();
      
     }
     f.setVisible(false);}
   });
  }
  
  JPanel panel=new JPanel(new GridLayout(1,el));
  panel.setPreferredSize(new Dimension(150, 50));
  GridBagConstraints[] c=new GridBagConstraints[el];
  JPanel[] buttonpanel=new JPanel[a.length];
  for(int j=0; j<el; j++){
   buttonpanel[j]=new JPanel((new GridBagLayout()));
   
   c[j]=new GridBagConstraints();
   c[j].anchor = GridBagConstraints.CENTER;
  
   buttonpanel[j].add(buts[j], c[j]);
   panel.add(buttonpanel[j]);
  
  }
  f.getContentPane().add(panel);
  
  
  f.pack();
  f.setVisible(true);
  synchronized(waiter) {
     try {
       waiter.wait();
     } catch (InterruptedException ex) {}
    }
 return ret;}
} 
/*
*T‰m‰ odottajien k‰yttˆ kiersi sen, ettei tarvitse kantaa ylim‰‰r‰isi‰ olioita mukana metodien kutsuissa.
*valitseActionin palauttamaa oliota tutkitaan matsi-metodissa.
*/