public class Wormhole {

   private double[] dest = new double[3];
   private String destId;
   private boolean explored;
   private boolean polarity;

   public Wormhole(double[] destIn, String destIdIn, boolean exp, boolean pol) {
      dest = destIn;
      destId = destIdIn;
      explored = exp;
      polarity = pol;
   }
   public Wormhole(double x, double y, double z, String destIdIn, boolean exp, boolean pol) {
      dest[0] = x;
      dest[1] = y;
      dest[2] = z;
      destId = destIdIn;
      explored = exp;
      polarity = pol;
   }

   public String toString() {
      if(polarity==true)
         return "Destination: "+dest[0]+","+dest[1]+""+dest[2]+
                "Destination ID: "+destId+", Explored: "+explored+", Polarity: (+)";
      else
         return "Destination: "+dest[0]+","+dest[1]+""+dest[2]+
                "Destination ID: "+destId+", Explored: "+explored+", Polarity: (-)";

   }
}
