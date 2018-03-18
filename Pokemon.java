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
   * Metodi k�skee pokemonia hy�kk��m��n.
   * @param a K�ytett�v� hy�kk�ys
   * @param target Hy�kk�yksen kohde
   * @throws AttackNotFoundException Jos Attack a ei l�ydy pokemonin osaamien hy�kk�ysten listasta attacks
   */
  public void attack(Attack a, Pokemon target){
      AttackInstance ai = new AttackInstance(a,this.att);
      ai.sendAttack(target);
  }
  /**
   * T�ll� metodilla pokemon ottaa vastaan hy�kk�yksen ja k�rsii vahinkoa.
   * @param baseDamage pokemoniin kohdistetun hy�kk�yksen perusvahinko
   * @param attackType pokemoniin kohdistetun hy�kk�yksen tyyppi
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
   * T�ll� metodilla pokemonille annetaan lis�� healthia
   * @param health Annettavan healthin m��r�
   */
  public void heal(int health){
    this.health += health;
    if (this.health>this.maxHealth){
      this.health=this.maxHealth;
    }
  }
  /**
   * Metodi tarkistaa, sis�lt��k� hy�kk�ystaulukko hy�kk�yksen.
   * @param attacks tarkistettava hy�kk�ystaulukko
   * @param a tarkistettava hy�kk�ys
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