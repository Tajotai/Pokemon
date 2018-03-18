package Pokemon;
import Pokemon.Game;
public class GamePointer{
  private Game g;
  public GamePointer(Game gg){
    this.g=gg;
  }
  public Game get(){
    return this.g;
  }
  public void set(Game gg){
    this.g=gg;
  }
}