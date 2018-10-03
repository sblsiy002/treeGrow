package treeGrow;
public class Land{
	
	// to do
   int dimx;
   int dimy;
   float  initial_matrix[][];
   float matrix[][];
   volatile int yearcounter;
	// sun exposure data here

   static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

   Land(int dx, int dy) {
   	// to do
      this.dimx=dx;
      this.dimy=dy;
      initial_matrix =new float[dx][dy];
      matrix =new float[dx][dy];
      yearcounter=0;
   }

	// return the number of landscape cells in the x dimension
   int getDimX() {
   	// to do
      return this.dimx; // incorrect value
   }
	
	// return the number of landscape cells in the y dimension
   int getDimY() {
   	// to do
      return this.dimy; // incorrect value
   }
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
   synchronized void resetShade() {
   	// to do
      for (int i = 0; i < initial_matrix.length; i++) {
         System.arraycopy(initial_matrix[i], 0, matrix[i], 0, initial_matrix[0].length);
      }
   }
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
   float getFull(int x, int y) {
   	// to do
      float sunexpoture=initial_matrix[x][y];
      return sunexpoture ; // incorrect value
   }
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
   void setFull(int x, int y, float val) {
   	// to do 
      initial_matrix[y][x]=val;
   }
	
	// return the current sun exposure of the shaded landscape at position <x,y>
   float getShade(int x, int y) {
   	// to do 
      float sun_val=matrix[x][y];
      return  sun_val; // incorrect value
   }
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
   void setShade(int x, int y, float val){
   	// to do
      matrix[x][y]= val;
   }
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
   synchronized void shadow(Tree tree){
      int x_cod = tree.getX();
      int y_cod = tree.getY();
      float ext = tree.getExt();
      for(int i = Math.round(x_cod-ext); i <= Math.round(x_cod+ext);i++){
         for(int j = Math.round(y_cod-ext); j <= Math.round(x_cod+ext);j++){
            matrix[i][j] = matrix[i][j]*0.10f;
         }   
      }
      
   }
}