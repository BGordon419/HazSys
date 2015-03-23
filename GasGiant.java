public class GasGiant extends Body {

   private String bodyType;
   private String zone;

   private Geosphere geo;
   private Atmosphere atmo;

   public GasGiant(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(Geosphere g, Atmosphere a, Hydrosphere h, Biosphere b) {
      geo = g;
      atmo = a;
   }

   public GasGiant copy() {
      return new GasGiant(super.getId(), super.getName(), bodyType, super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: "+bodyType+", Zone: "+zone;
   }
}
