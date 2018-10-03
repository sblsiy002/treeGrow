package treeGrow;
import java.util.concurrent.atomic.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TreeGrow {
   static long startTime = 0;
   static int frameX;
   static int frameY;
   static String d="";
   static ForestPanel fp;
   static Tree tree[];
   static AtomicInteger ai=new AtomicInteger(0);
   static SunData sundata = new SunData();
   static SimulationStart sim; 

   static volatile boolean flag=true;

	// start timer
   private static void tick(){
      startTime = System.currentTimeMillis();
   }
	
	// stop timer, return time elapsed in seconds
   private static float tock(){
      return (System.currentTimeMillis() - startTime) / 1000.0f; 
   }
	
   public static void setupGUI(int frameX,int frameY,Tree [] trees) {
      Dimension fsize = new Dimension(800, 800);
      tree=trees;
   	// Frame init and dimensions
      
      JFrame frame = new JFrame("Photosynthesis"); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(fsize);
      frame.setSize(800, 800);
      JButton btnPlay=new JButton("Play");
      JButton btnReset=new JButton("Reset");
      JLabel lb=new JLabel("NUmber of years:  ");
      JButton btnEnd=new JButton("End");
      JButton btnPause=new JButton("Pause");
      JTextField txtCount = new JTextField("",5);
      btnPlay.setSize(100, 30);
      btnReset.setSize(100, 30);
      btnEnd.setSize(100, 30);
      btnPause.setSize(100, 30);
      JPanel p1 = new JPanel();
      p1.add(btnReset);
      p1.add(Box.createRigidArea(new Dimension(15,15)));
      p1.add(btnPause);
      p1.add(Box.createRigidArea(new Dimension(15,0)));
      p1.add(btnPlay);
      p1.add(Box.createRigidArea(new Dimension(15,0)));
      p1.add(btnEnd);
      p1.add(Box.createRigidArea(new Dimension(15,0)));
      p1.add(lb);
      p1.add(Box.createRigidArea(new Dimension(15,0)));
      p1.add(txtCount);
      p1.add(Box.createRigidArea(new Dimension(150,0)));

      float count=1;
      btnEnd.addActionListener(
            new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
               int d=JOptionPane.YES_NO_OPTION;
               JOptionPane.showConfirmDialog(null,"Do you want to exit?","",JOptionPane.YES_NO_OPTION);
               if(d==JOptionPane.YES_OPTION){
                  System.exit(0);
                  }else{}         
               }
            });
      
      btnReset.addActionListener(
            new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                  
                  for(int t = 0; t < trees.length; t++){  
                     trees[t].setExt(0.4f);               
                  }                      
               }
            });
            
      btnPause.addActionListener(
            new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                  flag=false;               
               }
            });
   
       btnPlay.addActionListener(
            new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                  sim = new SimulationStart(sundata);
                  sim.start();             
               }
              
            });
   
   
      p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS)); 
      	
   
      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      g.setPreferredSize(fsize);
   
      fp = new ForestPanel(trees);
      fp.setPreferredSize(new Dimension(frameX,frameY));
      JScrollPane scrollFrame = new JScrollPane(fp);
      fp.setAutoscrolls(true);
      scrollFrame.setPreferredSize(fsize);
      g.add(scrollFrame);
      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      frame.setContentPane(g);
         
        
         
      frame.add(p1);
   
   
      frame.setVisible(true);
      Thread fpt = new Thread(fp);
      fpt.start();
      
        
   }
	
 
   public static void main(String[] args) {
         	
   	// check that number of command line arguments is correct
      if(args.length != 1)
      {
         System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
         System.exit(0);
      }
   			
   	// read in forest and landscape information from file supplied as argument
      sundata.readData(args[0]);
      System.out.println("Data loaded");
      d=args[0];
      frameX = sundata.sunmap.getDimX();
      frameY = sundata.sunmap.getDimY();
      setupGUI(frameX, frameY, sundata.trees);
   	
   	// create and start simulation loop here as separate thread
   }
}