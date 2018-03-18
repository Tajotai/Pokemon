package Pokemon;
import Pokemon.AttackInstance;
import Pokemon.Type;
import Pokemon.Attack;
import Pokemon.TypeChart;
import Pokemon.Species;
public class Pokemon{
  // Attribuutit: //
  private Species species;
  private Type type;
  private int maxHealth;
  private int health;
  private int att;
  private int def;
  private int speed;
  private Attack[] attacks;
  // Konstruktori: //
  public Pokemon(Species s){
    this.species=s;
    this.type=species.getType();
    this.maxHealth=species.getMaxHealth();
    this.att=species.getAtt();
    this.def=species.getDef();
    this.health=this.maxHealth;
    this.attacks=species.getAttacks();
    this.speed=species.getSpeed();
  }
  // Getterit ja setterit: //
  public String getNimi(){
   return this.species.getName();
  }
  public Species getSpecies(){
    return this.species;
  }
  public Type getType(){
    return this.type;
  }
  public int getMaxHealth(){
    return this.maxHealth;
  }
  public int getHealth(){
    return this.health;
  }
  public int getAtt(){
    return this.att;
  }
  public int getDef(){
    return this.def;
  }
  public int getSpeed(){
    return this.speed;
  }
  public Attack[] getAttacks(){
    return this.attacks;
  }
  public void setMaxHealth(int mH){
    maxHealth=mH;
  }
  public void setAtt(int a){
    att=a;
  }
  public void setDef(int d){
    def=d;
  }
  public void setSpeed(int sp){
    speed=sp;
  }
  /**
   * Metodi käskee pokemonia hyökkäämään.
   * @param a Käytettävä hyökkäys
   * @param target Hyökkäyksen kohde
   * @throws AttackNotFoundException Jos Attack a ei löydy pokemonin osaamien hyökkäysten listasta attacks
   */
  public void attack(Attack a, Pokemon target){
      AttackInstance ai = new AttackInstance(a,this.att);
      ai.sendAttack(target);
  }
  /**
   * Tällä metodilla pokemon ottaa vastaan hyökkäyksen ja kärsii vahinkoa.
   * @param baseDamage pokemoniin kohdistetun hyökkäyksen perusvahinko
   * @param attackType pokemoniin kohdistetun hyökkäyksen tyyppi
   */
  public void takeDamage(int baseDamage,Type attackType){
    Double tm= TypeChart.typeMultiplier(attackType, this.type);
    double d= 0.3*baseDamage*tm/this.def;
    int damage = (int) d;
    this.health -= damage;
    if(this.health<0){
      this.health=0;
    }
  }
  /**
   * Tällä metodilla pokemonille annetaan lisää healthia
   * @param health Annettavan healthin määrä
   */
  public void heal(int health){
    this.health += health;
    if (this.health>this.maxHealth){
      this.health=this.maxHealth;
    }
  }
  /**
   * Metodi tarkistaa, sisältääkö hyökkäystaulukko hyökkäyksen.
   * @param attacks tarkistettava hyökkäystaulukko
   * @param a tarkistettava hyökkäys
   * @return true, jos Attack a on taulukossa attacks, muuten false
   */
  private static boolean containsAttack(Attack[] attacks, Attack a){
    for (Attack att : attacks){
      if (att.eq(a)){
        return true;
      }
    }
    return false;
  }
  public String getString(){
    String ret="";
    ret += "Species: "+species.getName()+"\n";
    ret += "Type: "+type.getName()+"\n";
    ret += "Max health: "+maxHealth+"\n";
    ret += "Health: "+health+"\n";
    ret += "Attack: "+att+"\n";
    ret += "Defense: "+def+"\n";
    ret += this.attackSetString()+"\n";
    return ret;
  }
  public String attackSetString(){
    String ret="Attacks: \n";
    for (int i=0; i<attacks.length; i++){
      String attStr = attacks[i].getString();
      ret += attStr +"\n";
    }
    return ret;
  }
  public String toString(){
    return getNimi();
  }
}