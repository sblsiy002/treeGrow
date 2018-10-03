package treeGrow;

// Trees define a canopy which covers a square area of the landscape
public class Tree{
	
private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;	// extent of canopy out in vertical and horizontal from center
	
	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent
	
public	
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}
	
	// return the x-position of the tree center
	synchronized int getX() {
		return xpos;
	}
	
	// return the y-position of the tree center
	synchronized int getY() {
		return ypos;
	}
	
	// return the extent of the tree
	synchronized float getExt() {
		return ext;
	}
	
	// set the extent of the tree to <e>
	synchronized void setExt(float e) {
		ext = e;
	}

	// return the average sunlight for the cells covered by the tree
   synchronized float sunexposure(Land land){
	
    float s = 0.0f;
    int counter = 0;
    int ypos = this.ypos;
    float ext = this.ext;
    int xpos = this.xpos;
    float[][] tD = land.matrix ;  
    int x = Math.round(xpos-ext);
    int xx = Math.round(xpos+ext);
    int y = Math.round(ypos-ext);
    int yy = Math.round(ypos+ext);
    for(int i = x;i <= xx;i++){
      for(int j=y;j<=yy;j++){
         if(j>=0&&j<3000&&i>=0&&i<3000){
            counter +=1;
            s = tD[j][i] + s;
         }
      }
    }
      
      
		return s/counter ; // not correct
	}
	
	// is the tree extent within the provided range [minr, maxr)
	synchronized boolean inrange(float minr, float maxr) {
		return (ext >= minr && ext < maxr);
	}
	
	// grow a tree according to its sun exposure
	synchronized void sungrow(Land land) {
		float grow = sunexposure(land);
      grow = grow/1000;
      int xpo = xpos;
      int ypo = ypos;
      float exte = ext + grow;
      setExt(exte);
	}
}