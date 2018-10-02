package treeGrow;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author SBTELV002
 */
public class ParallelThreads extends Thread{
    
    /**
     *
     */
    public SunData data;
    boolean loop;
    volatile boolean running = true;
    volatile boolean paused = false;
    final Object pauseLock = new Object();
    
    public ParallelThreads(SunData sundata){
    
        data = sundata;
        loop = true;
    }

    public boolean loop(boolean b){
    loop = b;
    return loop;
    }
    
    @Override
    public void run(){
        
        while(running){
            synchronized(pauseLock){
                    if (!running) { // may have changed while waiting to
                                // synchronize on pauseLock
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait(); // will cause this Thread to block until 
                                          // another thread calls pauseLock.notifyAll()
                                          // Note that calling wait() will 
                                          // relinquish the synchronized lock that this 
                                          // thread holds on pauseLock so another thread
                                          // can acquire the lock to call notifyAll()
                                          // (link with explanation below this code)
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) { // running might have changed since we paused
                        break;
                    }
                }}
            
                float minh = 18.0f;
                    float maxh = 20.0f;
                    for(int layer = 0; layer <= 10; layer++) {

                           for(int t = 0; t < data.trees.length; t++){
                               if(data.trees[t].inrange(minh, maxh)){

                                   data.trees[t].sungrow(data.sunmap);

                               }


                            }
                            maxh = minh;  // next band of trees
                            minh -= 2.0f;
                    }	
                }
        
    }
    
        public void stops() {
        running = false;
        // you might also want to interrupt() the Thread that is 
        // running this Runnable, too, or perhaps call:
        resume();
        // to unblock
    }

    public void pause() {
        
        paused = true;
    }

    public void resumes() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }
    
}
