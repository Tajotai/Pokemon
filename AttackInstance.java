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
   * Metodi kertoo, osuuko hy�kk�ys.
   */
  private boolean hit(){
    if(hitrand.nextInt(100)<this.attack.getAccuracy()){
      return true;
    }
    return false;
  }
  /**
   * public void sendAttack(Pokemon target) l�hett�� hy�kk�yksen
   * vastaanotettavaksi Pokemonille target, jos hy�kk�ys osuu.
   * Perusvahinko baseDamage on pokemonin hy�kk�ysluku att kertaa
   * hy�kk�yksen voima power.
   * @param target Hy�kk�yksen kohde
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