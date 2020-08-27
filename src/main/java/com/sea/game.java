import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

abstract class SuperSprite
{
	int X,Y,width,height;
	boolean visible,active;

	abstract public void paintSprite(Graphics g);
	abstract public void updateState();

	public int getX()
	{
		return X;
	}

    public int getY()
	{
		return Y;
	}

    public void setLocation(int X,int Y)
   {
   	   this.X=X;
   	   this.Y=Y;

    }

    public int getWidth()
    {
    	 return width;
    }

    public int getHeight()
    {
    	return height;
    }

    public void setSize(int width,int height)
    {
    	this.width=width;
    	this.height=height;
    }

    public boolean canVisible()
    {
    	return visible;
    }

    public void setVisible(boolean v)
    {
    	visible=v;
    }

    public boolean canMove()
    {
    	return active;
    }

    public void setMove(boolean m)
    {
    	active=m;
    }
}

class PigSprite extends SuperSprite    //猪头Sprite
{
	int seed;             
	Image SpriteImage,Frame;     
	Applet Game;          
	Random R;
	boolean showPig;      


public PigSprite(Image SpriteImage,Image Frame,Applet Game)
{
	R=new Random();

	this.SpriteImage=SpriteImage;
	this.Frame =Frame;
	this.Game=Game;
	showPig=false;

	setVisible(true);    
	setMove(true);       

}

public void updateState()
{
	if(active==true)
	{
		
		if(R.nextInt(seed)%100<1)
		{
			if(showPig==false)
				showPig=true;
		}
		else if(R.nextInt(seed)%100 > 95)
		{
			if(showPig==true)
				showPig=false;
		}
	}

}


public void paintSprite(Graphics g)    
{
	if(visible == true)
	{

		g.drawImage(Frame,X,Y,Game);    

		 if(showPig == true)
		 	g.drawImage(SpriteImage,X+12,Y+18,Game);
      }
}


public void setSeed(int seed)
{
	this.seed=seed;
}


public boolean hit(int X,int Y,int P_Width,int P_Height,int H_Width,int H_Height)
{
	if((this.X+P_Width>=X) && (this.Y+(P_Height/2)>=Y)
		&& (X+(H_Width/2)>=this.X) && (Y+(H_Height/2)>=this.Y) && showPig)
		{
			showPig=false;
			return true;
		}
		else
			return false;
}
}

class HammerSprite extends SuperSprite  
{

	Image hammer1,hammer2,currentImage;      
	Applet Game;          


public HammerSprite(Image hammer1,Image hammer2,Applet Game)
{


	this.hammer1=hammer1;
	this.hammer2=hammer2;
	this.Game=Game;

	currentImage=hammer1;

	setLocation(0,0);
	setVisible(false);   
	setMove(false);      

}
