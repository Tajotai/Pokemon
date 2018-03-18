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
	 * Hyvinkin yksinkertainen valitseAction()-metodi, koska AI-pelaajan ei kuulukaan tehdä muuta kuin hyökätä omalla vuorollaan
	 * Palauttaa yhden neljästä tällä hetkellä ottelussa olevan pokemonin hyökkäyksestä
	 */
	public Object valitseAction(){
		
		Random re=new Random();
		return super.current.getAttacks()[re.nextInt(4)];
	}
	/**
	 * Kutsutaan vain, kun AI-pelaajan nykyinen pokemon kuolee. 
	 * Palauttaa joko joko seuraavan tiimissä elossa olevan pokemonin tai nullin.
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