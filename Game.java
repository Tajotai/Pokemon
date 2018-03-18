package Pokemon;
import java.io.File;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;
import Pokemon.Pokemon;
import Pokemon.Species;
import Pokemon.Battle;
import Pokemon.Pelaaja;
import Pokemon.AI;
import Pokemon.Ihminen;
import Pokemon.TypeChart;
import Pokemon.AttackSets;
import Pokemon.PokemonPointer;
import Pokemon.BooleanPointer;
public class Game{
  static final Object waiter=new Object();
  private Pelaaja player;
  private int victories;
  private int diff;
  private boolean vic;
  public Game(int diff, Pokemon[] monit){
    this.diff=diff;
    this.player= new Ihminen(monit);
    this.victories=0;
    this.vic=true;
  }
  public static void main (String[] args){
    
    AttackSets ae = new AttackSets();
    TypeChart tc = new TypeChart();
    Pokemon lol = new Pokemon(Species.PIKACHU);
    Pokemon[] lolol = {lol};
    Game g = new Game(1,lolol);
    final BooleanPointer bp=new BooleanPointer(false);
    JFrame f= new JFrame();
    JButton start = new JButton("Start");
    JButton load = new JButton("Load");
    JPanel panel = new JPanel(new GridLayout(1,3));
    panel.setPreferredSize(new Dimension(150, 50));
    JPanel[] butp = new JPanel[2];
    GridBagConstraints[] c=new GridBagConstraints[2];
    for(int i=0; i<2; i++){
      butp[i]=new JPanel((new GridBagLayout()));
      c[i]=new GridBagConstraints();
      c[i].anchor = GridBagConstraints.CENTER;
    }
    final JTextField loadAddr = new JTextField("Insert the filename to load from");
    // jos startOrLoad==false kutsu start(), muuten kutsu load(loadAddr.getText())
    boolean startOrLoad=false;
    start.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          bp.set(false);
          synchronized (waiter) {
            waiter.notify();
          }}});
    load.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          try{
            bp.set(true);
            synchronized (waiter) {
              waiter.notify();
            }
          }
          catch(Exception ee){}}});
    butp[0].add(start,c[0]);
    butp[1].add(load,c[1]);
    for(int j=0; j<2; j++){
      panel.add(butp[j]);
    }
    panel.add(loadAddr);
    f.getContentPane().add(panel);
    f.pack();
    f.setVisible(true);
    synchronized(waiter) {
        try {
          waiter.wait();
        } catch (InterruptedException ex) {} 
      }
    f.setVisible(false);
    startOrLoad=bp.get();
    if(startOrLoad){
      String SloadAddr = loadAddr.getText();
      g=Game.load(SloadAddr);
    } else {
      g=Game.start(3);
    }
    g.play();
    System.out.println(g.getVictories());
  }
  public static Game start(int diff){
    Pokemon pp= new Pokemon(Species.PIKACHU);
    Pokemon[] pp2 = {pp};
    Game lol = new Game(1,pp2);
    Pokemon[] monit = lol.valitsePokemonit(6);
    Game g = new Game(diff,monit);
    return g;
  }
  public static Game load(String s){
    Pokemon pp= new Pokemon(Species.PIKACHU);
    Pokemon[] pp2 = {pp};
    Game g = new Game(1,pp2);
    File f=new File(s);
    try{
    Scanner sc = new Scanner(f);
    int ivic = Integer.parseInt(sc.next());
    int idiff = Integer.parseInt(sc.next());
    ArrayList<Pokemon> pokList = new ArrayList<Pokemon>();
    while(sc.hasNext()){
      String speciesName = sc.next();
      Species species = Species.getSpecies(speciesName);
      Pokemon p = new Pokemon(species);
      pokList.add(p);
    }
    Pokemon[] pokArr = new Pokemon[pokList.size()];
    for (int i=0; i<pokArr.length; i++){
      pokArr[i]=pokList.get(i);
    }
    g = new Game(idiff, pokArr);
    g.setVictories(ivic);
    sc.close();
    } catch(Exception e){}
    return g;
  }
  public void play(){
    final Game g=this;
    while(this.vic){
      JFrame f = new JFrame();
      JButton save = new JButton("Save");
      JPanel panel = new JPanel(new GridLayout(1,2));
      final JTextField saveAddr = new JTextField("Insert the filename to save into");
      JButton nextBattle = new JButton("Next battle");
      final BooleanPointer bop = new BooleanPointer(false);
      save.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          bop.set(true);
          synchronized (waiter) {
            waiter.notify();
          }}});
      nextBattle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          bop.set(false);
          synchronized (waiter) {
            waiter.notify();
          }}});
      panel.add(save);
      panel.add(nextBattle);
      panel.add(saveAddr);
      f.getContentPane().add(panel);
      f.pack();
      f.setVisible(true);
      synchronized(waiter) {
        try {
          waiter.wait();
        } catch (InterruptedException ex) {} 
      }
      f.setVisible(false);
      boolean saveOrNext=bop.get();
      if(saveOrNext){
        String SSaveAddr=saveAddr.getText();
        this.save(SSaveAddr);
      } else {
        this.nextBattle();
      }
    }
  }
  public void nextBattle(){
    Pelaaja player2 = Battle.generateAI(this.diff,6);
    this.vic=Battle.matsi(this.player,player2);
    if(this.vic){
      this.addVictory();
    }
    this.raiseDiff();
    for (Pokemon pok : this.player.getTiimi()){
      pok.heal(pok.getMaxHealth());
    }
  }
  public void save(String s){
    File f=new File(s);
    try{
    if (!f.exists()){
      f.createNewFile();
    }
    FileWriter fw = new FileWriter(f);
    String sv=this.toString();
    fw.write(sv);
    fw.close();
    } catch (Exception e){
    }
  }
  public String toString(){
    String ret="";
    Integer vict = (Integer) this.victories;
    Integer Idiff = (Integer) this.diff;
    ret += vict.toString()+" ";
    ret += Idiff.toString()+" ";
    Pokemon[] pok = this.player.getTiimi();
    for (Pokemon p : pok){
      ret += p.getSpecies().getName()+" ";
    }
    return ret;
  }
  public void raiseDiff(){
    if(this.diff<7){
      this.diff++;
    }
  }
  public void addVictory(){
    this.victories++;
  }
  public void setVic(boolean bool){
    this.vic=bool;
  }
  public int getVictories(){
    return this.victories;
  }
  public void setVictories(int v){
    this.victories=v;
  }
  public boolean playBattle(Pelaaja p2){
    return Battle.matsi(player,p2);
  }
  public Pokemon[] valitsePokemonit(int count){
    int[] diffCycle={3,4,2,1,5};
    Pokemon[] ret = new Pokemon[count];
    for (int i=0; i<count; i++){
      int j=i%5;
      int dif=diffCycle[j];
      ret[i]=valitse(dif);
    }
    return ret;
  }
  /**
   * Metodi pyytää käyttäjää valitsemaan Pokemonin nappia
   * painamalla niiden Pokemonien joukosta, joiden vaikeusluokka on
   * dif tai pienempi.
   * AE: dif<=5
   * @param dif Valittavan Pokemonin maksimaalinen vaikeusluokka
   * @param JFrame Frame, johon napit liitetään.
   */
  public Pokemon valitse(int dif){
    Pokemon ret=null;
    JFrame f = new JFrame();
    int count=0;
    Species[] pokemonList = new Species[Species.getValueAmount()];
    for (Species s : Species.values()){
      if(s.getDiffClass()<=dif){
        pokemonList[count]=s;
        count++;
      }
    }
    JLabel label = new JLabel("Valitse Pokemon:");
    label.setPreferredSize(new Dimension(700, 520));
    f.getContentPane().add(label,BorderLayout.PAGE_START);
    JPanel panel=new JPanel(new GridLayout(1,3));
    panel.setPreferredSize(new Dimension(200, 100));
    
    JPanel[] buttonPanels = new JPanel[count];
    GridBagConstraints[] c=new GridBagConstraints[count];
    
    JButton[] pokeButs = new JButton[count];
    for (int i=0; i<count; i++){
      Species sp = pokemonList[i];
      JButton but = new JButton(sp.getName());
      pokeButs[i]=but;
    }
    final PokemonPointer pt= new PokemonPointer();
    for (int i=0; i<count; i++){
      final Species[] pokemonList2=pokemonList;
      final int i2=i;
      c[i]=new GridBagConstraints();
      c[i].anchor = GridBagConstraints.CENTER;
      buttonPanels[i]=new JPanel((new GridBagLayout()));
      pokeButs[i].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          Pokemon pok= new Pokemon(pokemonList2[i2]);
          pt.set(pok);
          synchronized (waiter) {
            waiter.notify();
        }}});
      buttonPanels[i].add(pokeButs[i],c[i]);
    }
    for(int j=0; j<count; j++){
      panel.add(buttonPanels[j]);
    }
    f.getContentPane().add(panel);
    f.pack();
    f.setVisible(true);
    synchronized(waiter) {
     try {
       waiter.wait();
     } catch (InterruptedException ex) {}
    }
    f.setVisible(false);
    ret=pt.get();
    return ret;
  }
}