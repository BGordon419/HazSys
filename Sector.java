public class Sector {

   private String sectorId;
   private String name;
   private int[] coords = new int[3];
   private StarSystem[] systems = new StarSystem[0];

// Constructors
   public Sector(String id, String nameIn, int[] coordsIn) {
      sectorId = id;
      name = nameIn;
      coords = coordsIn;
   }
   public Sector(String id, String nameIn, int[] coordsIn, StarSystem[] systemIn) {
      sectorId = id;
      name = nameIn;
      coords = coordsIn;
      systems = systemIn;
   }
   public Sector(String id, String nameIn, int x, int y, int z, StarSystem[] systemIn) {
      sectorId = id;
      name = nameIn;
      coords[0] = x;
      coords[1] = y;
      coords[2] = z;
      systems = systemIn;
   }
   /**
   public Sector(Sector in) {
   sectorId = in.sectorId;
   name = in.name;
   coords = in.coords;
   systems = in.systems;
   }
   **/

// Methods
   public String getName() {
      return name;
   }
   public String getID() {
      return sectorId;
   }
   public int[] getCoords() {
      return coords;
   }
   public int getCoord(int dim) {
      if(dim==0)
         return coords[0];
      else if (dim==1)
         return coords[1];
      else if (dim==2)
         return coords[2];
      else
         return 0;
   }
   public StarSystem[] getSystems() {
      return systems;
   }
   public StarSystem getSystem(int sys) {
      return systems[sys];
   }
   public int numberOfSystems() {
      return systems.length;
   }

// Utils
   public Sector copy(Sector newSector) {

      return new Sector(sectorId, name, coords, systems);
   }
   public String toString() {
      if(systems==null)
         return "Error: Sector Empty.\nID: "+sectorId+", Name: "+name+
                  ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2];
      else
         return "ID: "+sectorId+", Name: "+name+
               ", Coordinates: "+coords[0]+","+coords[1]+","+coords[2]+
               ", Number of Systems: "+systems.length;
   }
}
