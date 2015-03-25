import java.util.ArrayList;

public class Arc extends Body {

   private String zone;

   private ArrayList<Resource> geo;
   private ArrayList<Resource> atmo;
   private ArrayList<Resource> hydro;
   private ArrayList<Resource> bio;

   public Arc(String idIn, String nameIn, String orbitIn, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      zone = zoneIn;
   }
   public void fill(ArrayList<Resource> g, ArrayList<Resource> a,
                    ArrayList<Resource> h, ArrayList<Resource> b) {
      geo = g;
      atmo = a;
      hydro = h;
      bio = b;
   }

   public Arc copy() {
      return new Arc(super.getId(), super.getName(), super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: Ringworld Arc, Zone: "+zone;
   }
}
