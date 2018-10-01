
package treeGrow;
import java.util.*;
public class Land{
	
	// to do
   int x=0;
   int y=0;
   float v=0.0f;
   SunData sunData;
   List<Integer> Arrx=new ArrayList<Integer>();
   List<Integer> Arry=new ArrayList<Integer>();
   List<Float> ArrV=new ArrayList<Float>();

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
   	
      synchronized(Arrx){
         Iterator<Integer> itr=Arrx.iterator();
         Iterator<Integer> itr1=Arry.iterator();
         Iterator<Float> itr2=ArrV.iterator();
         while(itr.hasNext()&&itr1.hasNext()&&itr2.hasNext()){
            if(itr.next()==x && itr1.next()==y){
               v=itr2.next(); 
            }
         
         }
      }
   
      return v;// incorrect value
   
   }
         
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
   void setFull(int x, int y, float val) {
      
      Arrx.add(x);
      Arry.add(y);
      ArrV.add(val);
     // Arrx=Collections.synchronizedList(Arrx);
     // Arry=Collections.synchronizedList(Arry);
     // ArrV=Collections.synchronizedList(ArrV);
   
   }
	
	// return the current sun exposure of the shaded landscape at position <x,y>
   float getShade(int x, int y) {
      return 0.1f*getFull(x,y); // incorrect value
   }
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
   void setShade(int x, int y, float val){
   	
      synchronized(Arrx){
         Iterator<Integer> itr=Arrx.iterator();
         Iterator<Integer> itr1=Arry.iterator();
         Iterator<Float> itr2=ArrV.iterator();
         while(itr.hasNext()&&itr1.hasNext()&&itr2.hasNext()){
            if(itr.next()==x && itr1.next()==y){
               itr.remove();
               itr1.remove();
               itr2.remove();
                Arrx.add(x);
                Arry.add(y);
                ArrV.add(val);
            }
         
         }
      }
   }
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
   void shadow(Tree tree){
   	// to do
   }
}