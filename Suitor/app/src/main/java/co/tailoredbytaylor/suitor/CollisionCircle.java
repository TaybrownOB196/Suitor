package co.tailoredbytaylor.suitor;

public class CollisionCircle implements CollisionObjectInterface2D
{
	public Vector2D mVecCntr;
	
	private float mRadius;
	private float mArea;
	
	public CollisionCircle(float r)
	{
		this.mRadius = r;
		
		this.mArea = (float)(Math.PI * Math.pow(r, 2));
	}
	
	@Override
	public float getArea()
	{
		return mArea;
	}
	
	@Override
	public boolean touchBounds(Vector2D pnt)
	{
		if(((Math.pow((pnt.getX() - this.mVecCntr.getX()), 2))+
			(Math.pow((pnt.getY() - this.mVecCntr.getY()), 2))) == Math.pow(this.mRadius, 2))
				return true;
			else
				return false;
	}

	@Override
	public boolean inBounds(Vector2D pnt)
	{
		if(((Math.pow((pnt.getX() - this.mVecCntr.getX()), 2))+
			(Math.pow((pnt.getY() - this.mVecCntr.getY()), 2))) < Math.pow(this.mRadius, 2))
				return true;
			else
				return false;
	}

	@Override
	public boolean collidesWith(CollisionCircle circ)
	{
		//If the distance between the centers of each cirle is less than or equal to the sum
		//of the radii of the two circles, then the circles are colliding 
		if((this.mVecCntr.subtractVectors(circ.mVecCntr).magnitudeVector()) <= (this.mRadius + circ.mRadius))
			return true;
		else
			return false;
	}

	@Override
	public boolean collidesWith(CollisionPolygon poly)//Implement SAT
	{
		//Step 1: Obtaining the Seperating Axis
		
		//Step 2: Projecting the 2D shape onto an axis
		//Step 3: Finding the Minimum Translation Vector

		return false;
	}

	@Override
	public boolean containsCirc(CollisionCircle circ)
	{
		
		return false;
	}

	@Override
	public boolean containsPoly(CollisionPolygon circ)
	{
		
		return false;
	}
}