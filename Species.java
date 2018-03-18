package Pokemon;
import Pokemon.Attack;
import Pokemon.AttackSets;
import Pokemon.Type;
import java.util.Random;
public enum Species{
  //luetellaan mahdolliset pokemonlajit//
  PIKACHU("Pikachu",Type.ELECTRIC,40,55,35,90,2),
  SQUIRTLE("Squirtle",Type.WATER,44,38,65,43,1),
  CHARMANDER("Charmander",Type.FIRE,39,52,43,65,1),
  BULBASAUR("Bulbasaur",Type.GRASS,45,49,49,45,1),
  PIDGEY("Pidgey",Type.FLYING,40,45,40,55,1),
  RATTATA("Rattata",Type.NORMAL,30,56,35,72,1),
  RAICHU("Raichu",Type.ELECTRIC,60,90,55,100,4),
  WARTORTLE("Wartortle",Type.WATER,59,63,75,55,3),
  CHARMELEON("Charmeleon",Type.FIRE,55,64,58,80,3),
  IVYSAUR("Ivysaur",Type.GRASS,65,65,63,60,3),
  BLASTOISE("Blastoise",Type.WATER,79,83,100,78,5),
  CHARIZARD("Charizard",Type.FIRE,75,84,78,100,5),
  VENUSAUR("Venusaur",Type.GRASS,80,85,87,80,5),
  PIDGEOTTO("Pidgeotto",Type.FLYING,63,60,55,75,3),
  RATICATE("Raticate",Type.NORMAL,60,81,65,97,4),
  PIDGEOT("Pidgeot",Type.FLYING,83,80,75,91,5);
  //attribuutit://
  /*
   * valueAmount kertoo pokemonlajien m‰‰r‰n ja se tulisi
   * p‰ivitt‰‰ aina, kun pokemonlajeja lis‰t‰‰n Speciesiin
   * tai poistetaan siit‰.
   */
  private static int valueAmount=16;
  private String name;
  private Type type;
  private Attack[] attacks;
  private int maxHealth;
  private int att;
  private int def;
  private int speed;
  private int diffClass;
  //Konstruktori://
  Species(String name, Type type, int mH, int att, int def, int speed, int dc){
    this.name=name;
    this.type=type;
    this.attacks=AttackSets.getAttackSet(name);
    this.maxHealth=mH;
    this.att=att;
    this.def=def;
    this.speed=speed;
    this.diffClass=dc;
  }
  // Getterit: //
  public static int getValueAmount(){
    return Species.valueAmount;
  }
  public String getName(){
    return this.name;
  }
  public Type getType(){
    return this.type;
  }
  public int getMaxHealth(){
    return this.maxHealth;
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
  public int getDiffClass(){
    return this.diffClass;
  }
  public static Species getSpecies(String name){
    for (Species s : Species.values()){
      if(s.name.equals(name)){
        return s;
      }
    }
    return Species.PIKACHU;
  }
  /**
   * Palauttaa sattumanvaraisen Speciesin parametrina annetusta vaikeusluokasta.
   * @param dc Palautettavan pokemonlajin vaikeusluokka
   */
  public static Species getRand(int dc){
    int summa=0;
    Species last=Species.PIKACHU;
    Random rng = new Random();
    for (Species s : Species.values()){
      if(s.diffClass==dc){
        summa++;
      }
    }
    for (Species s : Species.values()){
      if(s.diffClass==dc){
        int n=rng.nextInt(summa);
        if (n==0){
          return s;
        }
        summa--;
        if(summa==0){
          last=s;
        }
      }
    }
    return last;
  }
}