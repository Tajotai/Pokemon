package Pokemon;
import Pokemon.Type;
public class TypeChart{
   /**
    * Taulukko tyyppikertoimista, jotka vaikuttavat hyökkäyksen aiheuttamaan
    * vahingon hyökkäyksen tyypin ja vastaanottavan pokemonin tyypin perusteella.
    */
   private static double[] typeMultipliers;
   // Konstruktori: //
   public TypeChart(){
     int cc=Type.values().length;
     cc=cc*cc;
     typeMultipliers=new double[cc];
     for (int i=0;i<cc;i++){
       typeMultipliers[i]=1.0;
     }
     int[][] doubled ={{0,1},{1,4},{2,1},{2,5},{4,0},{4,6},{5,0}};
     int[][] halved ={{0,0},{0,4},{0,5},{0,6},{1,0},{1,1},{2,0},{2,2},
                      {3,6},{4,1},{4,4},{5,2},{5,6},{6,1},{6,2},{6,4},{6,6}};
     for (int[] ii : doubled){
       int a=ii[0];
       int b=ii[1];
       int c=Type.values().length;
       typeMultipliers[a*c+b]=2.0;
     }
     for (int[] ii : halved){
       int a=ii[0];
       int b=ii[1];
       int c=Type.values().length;
       typeMultipliers[a*c+b]=0.5;
     }
   }
   /**
    * Luokkametodi palauttaa tyyppiparin tyyppikertoimen.
    * @param attackType hyökkäyksen tyyppi
    * @param targetType hyökkäyksen kohteena olevan pokemonin tyyppi
    */
   public static double typeMultiplier(Type attackType, Type targetType){
     int a=attackType.getNumber();
     int b=targetType.getNumber();
     int c=Type.values().length;
     int i=a*c+b;
     return typeMultipliers[i];
   }
 }
