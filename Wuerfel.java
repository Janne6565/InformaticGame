import java.lang.Math; 
public class Wuerfel {
  
  
      private int anzahlSeiten;
      private int min = 0;
      private int max = 6;
      
      public Wuerfel (int pMin, int pMax) {
          min = pMin;
          max = pMax;
        }
      
      public int wuerfeln(){
          return (min + (int) (Math.random() * ((max - min) + 1)));
        }
      
      public void setMin(int pMin){
          min = pMin;
        }
      
      public void setMax(int pMax){
          max = pMax;
        }
        
      public int getMin(){ //Mir fällt kein wirklicher Zweck für diese Funktionen ein, aber wo ein setter, da ein getter!
          return min;
        }
        
      public int getMax(){
          return max;
        }
    
}