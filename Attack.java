package Pokemon;
public enum Attack{
  //Luetellaan mahdolliset hyökkäykset. //
  TAILWHIP("Tail Whip",Type.NORMAL,30,100),
  TACKLE("Tackle",Type.NORMAL,50,90),
  SCRATCH("Scratch",Type.NORMAL,40,85),
  QUICKATTACK("Quick Attack",Type.NORMAL,40,85),
  HYPERFANG("Hyper Fang",Type.NORMAL,80,70),
  DOUBLEEDGE("Double Edge",Type.NORMAL,120,70),
  THUNDERSHOCK("Thunder Shock",Type.ELECTRIC,40,90),
  THUNDER("Thunder",Type.ELECTRIC,110,70),
  VINEWHIP("Vine Whip",Type.GRASS,45,100),
  RAZORLEAF("Razor Leaf",Type.GRASS,55,85),
  SEEDBOMB("Seed Bomb",Type.GRASS,80,80),  
  SOLARBEAM("Solar Beam",Type.GRASS,120,60),
  WATERGUN("Water Gun",Type.WATER,40,90),
  WATERPULSE("Water Pulse",Type.WATER,60,85),
  HYDROPUMP("Hydro Pump",Type.WATER,110,60),
  AQUATAIL("Aqua Tail",Type.WATER,90,80),
  EMBER("Ember",Type.FIRE,40,90),
  FIREFANG("Fire Fang",Type.FIRE,65,80),
  FLAMETHROWER("Flamethrower",Type.FIRE,95,80),
  INFERNO("Inferno",Type.FIRE,100,50),
  WINGATTACK("Wing Attack",Type.FLYING,50,90),
  HURRICANE("Hurricane",Type.FLYING,110,75),
  BITE("Bite",Type.NORMAL,60,90),
  GUST("Gust",Type.FLYING,40,95),
  AIRSLASH("Air Slash",Type.FLYING,75,75);
  //Attribuutit: //
  private String name;
  private Type type;
  private int power;
  private int accuracy;
  //Konstruktori://
  Attack(String name, Type type, int power, int acc){
    this.name=name;
    this.type=type;
    this.power=power;
    this.accuracy=acc;
  }
  //Getterit://
  public String getName(){
    return this.name;
  }
  public Type getType(){
    return this.type;
  }
  public int getPower(){
    return this.power;
  }
  public int getAccuracy(){
    return this.accuracy;
  }
  /**
   * Metodi tarkistaa, onko parametrina annettu Attack a
   * sama, kuin tämä Attack.
   * @param a Tähän Attackiin verrattava Attack.
   */
  public boolean eq(Attack a){
    if (this.name != a.name){
      return false;
    }
    return true;
  }
  /**
   * Tulostaa hyökkäyksen.
   */
  public String getString(){
    return ("Name: "+name+" Type: "+type.getName()+" Power: "+power+" Acc: "+accuracy);
  }
   public String toString(){
    return getName();
  }
}