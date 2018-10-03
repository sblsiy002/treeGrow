package treeGrow;
import java.util.concurrent.*;
public class SimulationStart extends Thread{

   SunData sundata;
   TreeGrow tg = new TreeGrow();
   
   public SimulationStart(SunData sundata){
      this.sundata = sundata;
   }
   public void run(){
      while(true){
      
      
      float minh = 18.0f;
		float maxh = 20.0f;
		for(int layer = 0; layer < 10; layer++) {
			for(int t = 0; t < sundata.trees.length; t++){
            if(sundata.trees[t].inrange(minh,maxh)){
               sundata.trees[t].sungrow(sundata.sunmap);
            }
			}
         
			maxh = minh;  // next band of trees
			minh -= 2.0f;

}      
     
      
      }
   }

}