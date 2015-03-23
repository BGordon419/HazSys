public class Planet extends Body {

   private String bodyType;
   private String zone;

   private Geosphere geo;
   private Atmosphere atmo;
   private Biosphere bio;

   public Planet(String nameIn, String idIn, String orbitIn, String type, String zoneIn) {
      super(idIn, nameIn, orbitIn);
      bodyType = type;
      zone = zoneIn;
   }
   public void fill(Geosphere g, Atmosphere a, Biosphere b) {
      geo = g;
      atmo = a;
      bio = b;
   }

   public Planet copy() {
      return new Planet(super.getId(), super.getName(), bodyType, super.getOrbit(), zone);
   }
   public String toString() {
      return super.toString()+", Type: "+bodyType+", Zone: "+zone;
   }
}
