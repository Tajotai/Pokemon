package Pokemon;
import java.util.HashMap;
public class AttackSets{
  // lista kaikkien pokemonien attackseteistä HashMappina. //
  private static HashMap<String, Attack[]> sets=new HashMap<String, Attack[]>();
  // Konstruktori: //
  public AttackSets(){
    Attack[] pikachuAttacks ={Attack.TAILWHIP,Attack.THUNDERSHOCK,Attack.QUICKATTACK,Attack.THUNDER};
    Attack[] squirtleAttacks ={Attack.TACKLE,Attack.WATERGUN,Attack.WATERPULSE,Attack.HYDROPUMP};
    Attack[] charmanderAttacks ={Attack.SCRATCH,Attack.EMBER,Attack.FIREFANG,Attack.INFERNO};
    Attack[] bulbasaurAttacks ={Attack.TACKLE,Attack.VINEWHIP, Attack.RAZORLEAF,Attack.SEEDBOMB};
    Attack[] pidgeyAttacks ={Attack.TACKLE,Attack.QUICKATTACK,Attack.WINGATTACK,Attack.HURRICANE};
    Attack[] rattataAttacks ={Attack.TAILWHIP,Attack.BITE,Attack.HYPERFANG,Attack.DOUBLEEDGE};
    Attack[] raichuAttacks ={Attack.TAILWHIP,Attack.THUNDERSHOCK,Attack.QUICKATTACK,Attack.THUNDER};
    Attack[] wartortleAttacks ={Attack.TACKLE,Attack.WATERGUN,Attack.WATERPULSE,Attack.HYDROPUMP};
    Attack[] charmeleonAttacks ={Attack.SCRATCH,Attack.EMBER,Attack.FIREFANG,Attack.INFERNO};
    Attack[] ivysaurAttacks ={Attack.TACKLE,Attack.VINEWHIP, Attack.RAZORLEAF,Attack.SEEDBOMB};
    Attack[] blastoiseAttacks ={Attack.TAILWHIP,Attack.WATERGUN,Attack.AQUATAIL,Attack.HYDROPUMP};
    Attack[] charizardAttacks ={Attack.WINGATTACK,Attack.EMBER,Attack.FLAMETHROWER,Attack.INFERNO};
    Attack[] venusaurAttacks ={Attack.TACKLE,Attack.VINEWHIP, Attack.SOLARBEAM,Attack.DOUBLEEDGE};
    Attack[] pidgeottoAttacks ={Attack.TACKLE,Attack.QUICKATTACK,Attack.WINGATTACK,Attack.HURRICANE};
    Attack[] raticateAttacks ={Attack.TACKLE,Attack.QUICKATTACK,Attack.HYPERFANG,Attack.DOUBLEEDGE};
    Attack[] pidgeotAttacks ={Attack.TACKLE,Attack.GUST,Attack.AIRSLASH,Attack.HURRICANE};
    sets.put("Pikachu",pikachuAttacks);
    sets.put("Squirtle",squirtleAttacks);
    sets.put("Charmander",charmanderAttacks);
    sets.put("Bulbasaur",bulbasaurAttacks);
    sets.put("Pidgey",pidgeyAttacks);
    sets.put("Rattata",rattataAttacks);
    sets.put("Raichu",raichuAttacks);
    sets.put("Wartortle",wartortleAttacks);
    sets.put("Charmeleon",charmeleonAttacks);
    sets.put("Ivysaur",ivysaurAttacks);
    sets.put("Blastoise",blastoiseAttacks);
    sets.put("Charizard",charizardAttacks);
    sets.put("Venusaur",venusaurAttacks);
    sets.put("Pidgeotto",pidgeottoAttacks);
    sets.put("Raticate",raticateAttacks);
    sets.put("Pidgeot",pidgeotAttacks);
  }
  /**
   * Palauttaa pokemonlajin kaikki mahdolliset
   * hyökkäykset Attack[]-taulukossa.
   * @param pokemon kyseltävän pokemonlajin nimi Stringinä.
   */
  public static Attack[] getAttackSet(String pokemon){
    return sets.get(pokemon);
  }
  /**
   * Palauttaa pokemonin kaikki mahdolliset
   * hyökkäykset Attack[]-taulukossa.
   * @param pokemon Pokemon, jonka attackSet palautetaan
   */
  public static Attack[] getAttackSet(Pokemon pokemon){
    String pok = pokemon.getNimi();
    return sets.get(pok);
  }
}