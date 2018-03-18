package Pokemon;
public enum Type{
  // luetellaan mahdolliset tyypit: //
  GRASS("Grass",0), WATER("Water",1), ELECTRIC("Electric",2), NORMAL ("Normal",3), FIRE ("Fire",4),
  FLYING("Flying",5), STEEL("Steel",6);
  // tyypin nimi tulostamista varten //
  private String name;
  private int number;
  // Konstruktori, joka konstruoi nimen ja numeron. //
  Type(String name, int number){
    this.name=name;
    this.number=number;
  }
  // Getterit:
  public String getName(){
    return this.name;
  }
  public int getNumber(){
    return this.number;
  }
}