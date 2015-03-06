package co.tailoredbytaylor.suitor;

public class Vector2DPhysics
{
	static Vector2D 
	mVelocity, 		//Rate of change os displacement. The vector measuring displacement traveled in a certain time
	mAcceleration, 	//Rate of change of velocity
	mWeight,		//The vector measuring the force required in a particular direction to keep something in the same place against the pull of gravity
	mDisplacement;	//Change of position. The vector between two points with a distance
	
	final float mGravity = 9.8f;
	
	float mDistance,//A scalar quantity measuring the shortest distance between two points
	mMass,			//A scalar quantity measuring the how much forse is required to move and object in whatever direction
	mSpeed;			//A scalar quantity measuring the distance oething travels in a certain time
	
	int mTime;
	
	private Vector2DPhysics(){};
	
	//Function to calculate the angle of a cannonball over time
	////Use the x value of the movement vector as the adjacent
	////Use the y value of the movement vector as the opposite
	////Calculate the angle using tan(y/x)
	
	//Function to calculate the position 
	////
	
	public static void main(String[] args)
	{
		
	}
}
