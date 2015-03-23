public class Arc extends Body {

   private String bodyType;
   private String zone;

   private Geosphere geo;
   private Atmosphere atmo;
   private Hydrosphere hydro;
   private Biosphere bio;

   public Arc(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(Geosphere g, Atmosphere a, Hydrosphere h, Biosphere b) {
      geo = g;
      atmo = a;
      hydro = h;
      bio = b;
   }

   public Arc copy() {
      return new Arc(super.getId(), super.getName(), bodyType, super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: "+bodyType+", Zone: "+zone;
   }
}
