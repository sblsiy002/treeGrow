
package treeGrow;
import java.util.*;
import java.text.DecimalFormat;
public class Land{
	
	// to do
   int x=0;
   int y=0;
   float v=0.0f;
   float DataF[][];
   float DataS[][];

   SunData sunData;
   List<Integer> Arrx=new ArrayList<Integer>();
   List<Integer> Arry=new ArrayList<Integer>();
   List<Float> ArrV=new ArrayList<Float>();

	// sun exposure data here

   static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

   Land(int dx, int dy) {
      this.x=dx;
      this.y=dy;
      DataF=new float[x][y];
      DataS=new float[x][y];
   
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
      for(int u=0;u<x;u++){
         System.arraycopy(DataF[u],0,DataS[u],0,x);
      
      }}
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
   float getFull(int x, int y) {
      float vv=0.0f;
      vv=DataF[x][y];
      return vv;// incorrect value
   
   }
         
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
   void setFull(int x, int y, float val) {
      DataF[x][y]=val;
   }
	
	// return the current sun exposure of the shaded landscape at position <x,y>
   float getShade(int x, int y) {
      return DataS[x][y]; // incorrect value
   }
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
   void setShade(int x, int y, float val){
      DataS[x][y]=val;   
   }
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
   void shadow(Tree tree){
   	// to do
      DecimalFormat df=new DecimalFormat("0");
      float ex=tree.getExt();
      int b=tree.getY();
      int a=tree.getX();
      int h=Integer.parseInt(df.format(ex));
      for(int i=(a-h);i<(a+h);i++){
         for(int j=(b-h);j<(b+h);j++){
            DataF[i][j]=DataF[i][j]*0.1f;
         
         }}
   }
}