@SuppressWarnings("hiding")
public class Pair<String, Double>{
   private String first;
   private double second;
   
   public Pair(String first, double second){
     this.first = first;
     this.second = second;
   }

   public void setFirst(String first){
    this.first = first;
   }

   public void setSecond(double second) {
     this.second = second;
   }

   public String getFirst() {
     return this.first;
   }

   public double getSecond() {
     return this.second;
   }
   
   public int compareTo(Object d){
	   double param = (double) d;
	   if ( this.second > param){
		   return 1;
	   }
	   else
		   if (this.second < param){
			   return -1;
		   }
		   else
			   return 0;
   }
}