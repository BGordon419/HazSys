import java.util.ArrayList;

public class GasGiant extends Body {

   private String zone;

   private ArrayList<Resource> atmo;

   public GasGiant(String idIn, String nameIn, String orbitIn, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> a) {
      atmo = a;
   }

   public ArrayList<Resource> getAtmosphere() {
      return atmo;
   }

   public GasGiant copy() {
      return new GasGiant(super.getId(), super.getName(), super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: Gas Giant, Zone: "+zone;
   }
}
