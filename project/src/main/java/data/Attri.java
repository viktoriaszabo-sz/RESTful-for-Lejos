package data;

//THIS IS FOR SHARING ATTRIBUTES FROM EV3 TO THE SERVICE

public class Attri 
{
    private int speed = 0;
    private int turnangle = 0;
    private int maxobs = 0;
	private int id;
     
    
    public  int getSpeed()
    {
    	return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public  int getTurnangle()
    {
    	return turnangle;
    }
    public void setTurnangle(int turnangle)
    {
    	this.turnangle = turnangle;
    }
    
    public  int getMaxobs()				
    {
    	return maxobs;
    }
    public void setMaxobs(int maxobs)
    {
    	this.maxobs = maxobs;
    }
    
    
    public Attri() {}
    public Attri(int speed, int turnangle, int maxobs)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;				//parameterized constructor for ev3_service
    	this.maxobs = maxobs;
    }
	public Attri(int id, int speed, int turnangle, int maxobs) {
		this.id = id;
		this.speed = speed; 
		this.turnangle = turnangle; 
		this.maxobs = maxobs;
	}
}