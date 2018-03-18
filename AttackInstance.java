package Pokemon;
import java.util.Random;
public class AttackInstance{
  // Attribuutit: //
  private Attack attack;
  private Random hitrand=new Random();
  private int att;
  // Konstruktori: //
  public AttackInstance(Attack attack, int att){
    this.attack=attack;
    this.att=att;
  }
  /**
   * Metodi kertoo, osuuko hyökkäys.
   */
  private boolean hit(){
    if(hitrand.nextInt(100)<this.attack.getAccuracy()){
      return true;
    }
    return false;
  }
  /**
   * public void sendAttack(Pokemon target) lähettää hyökkäyksen
   * vastaanotettavaksi Pokemonille target, jos hyökkäys osuu.
   * Perusvahinko baseDamage on pokemonin hyökkäysluku att kertaa
   * hyökkäyksen voima power.
   * @param target Hyökkäyksen kohde
   */
  public void sendAttack(Pokemon target){
    if (!hit()){
      
    } else {
      int baseDamage=this.att*attack.getPower();
      Type t = attack.getType();
      target.takeDamage(baseDamage, t);
    }
  }
}