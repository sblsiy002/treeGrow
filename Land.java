package treeGrow;

public class Land{
	
	// to do
   int x=0;
   int y=0;
   SunData sunData;
	// sun exposure data here

   static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

   Land(int dx, int dy) {
      this.x=dx;
      this.y=dy;
   }

	// return the number of landscape cells in the x dimension
   int getDimX() {
      return this.x;	}
	
	// return the number of landscape cells in the y dimension
   int getDimY() {
   	  return this.y;   }
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
   void resetShade() {
   	// to do
        }
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
   float getFull(int x, int y) {
   	// to do
      return 3.0f; // incorrect value
   }
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
   void setFull(int x, int y, float val) {
   	   this.x=x;
         this.y=y;
         
         }
	
	// return the current sun exposure of the shaded landscape at position <x,y>
   float getShade(int x, int y) {
   	// to do 
      return 3.0f; // incorrect value
   }
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
   void setShade(int x, int y, float val){
   	// to do
      
   }
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
   void shadow(Tree tree){
   	// to do
   }
}