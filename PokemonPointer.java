package Pokemon;
import Pokemon.Pokemon;
public class PokemonPointer{
  private Pokemon mon;
  public PokemonPointer(){
  }
  public PokemonPointer(Pokemon p){
    this.mon=p;
  }
  public Pokemon get(){
    return this.mon;
  }
  public void set(Pokemon p){
    this.mon=p;
  }
}