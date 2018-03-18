package Pokemon;
import java.util.Random;
public class AI extends Pelaaja{
	
	/** 
	* AI-pelaajan luokka
	* Konstruktoria kutsutaan pokemon-arraylla, jolloin superia kutsutaan samalla arraylla
	*/
	public AI(Pokemon[] p){
		super(p);
	}

	/**
	 * Hyvinkin yksinkertainen valitseAction()-metodi, koska AI-pelaajan ei kuulukaan tehd� muuta kuin hy�k�t� omalla vuorollaan
	 * Palauttaa yhden nelj�st� t�ll� hetkell� ottelussa olevan pokemonin hy�kk�yksest�
	 */
	public Object valitseAction(){
		
		Random re=new Random();
		return super.current.getAttacks()[re.nextInt(4)];
	}
	/**
	 * Kutsutaan vain, kun AI-pelaajan nykyinen pokemon kuolee. 
	 * Palauttaa joko joko seuraavan tiimiss� elossa olevan pokemonin tai nullin.
	 */
	public Object valitseSwap(){
		for(Object a : super.tiimi){
			Pokemon b=(Pokemon) a;
			if(b.getHealth()>0){
				return a;
			}
		}
	return null;}
}