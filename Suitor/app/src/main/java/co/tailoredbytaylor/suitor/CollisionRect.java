package co.tailoredbytaylor.suitor;

public class CollisionRect implements CollisionObjectInterface2D
{
	private Vector2D mVec;
	private Vector2D[] mVecList;
	private int mWidth, mHeight;
	private float mArea;
	
	public CollisionRect(int x, int y, int width, int height)
	{
		this.mVecList = new Vector2D[4];
		if(width > 0 && height > 0)
		{
			mVecList[0] = new Vector2D(x+width, y+height);
			mVecList[1] = new Vector2D(x+width, y);
			mVecList[2] = new Vector2D(x, y);
			mVecList[3] = new Vector2D(x, y+height);
		}
		else if(width < 0 && height < 0)
		{
			mVecList[2] = new Vector2D(x, y);
			mVecList[1] = new Vector2D(x, y+height);
			mVecList[0] = new Vector2D(x+width, y+height);
			mVecList[3] = new Vector2D(x+width, y);
		}
		else if(width < 0 && height > 0)
		{
			mVecList[0] = new Vector2D(x, y+height);
			mVecList[2] = new Vector2D(x, y);
			mVecList[2] = new Vector2D(x+width, y);
			mVecList[0] = new Vector2D(x+width, y+height);
		}
		else if(width > 0 && height < 0)
		{
			mVecList[0] = new Vector2D(x+width, y);
			mVecList[0] = new Vector2D(x+width, y+height);
			mVecList[2] = new Vector2D(x, y+height);
			mVecList[2] = new Vector2D(x, y);
		}
	
		this.mVec = this.mVecList[0];
		this.mWidth = width;
		this.mHeight = height;
		
		this.mArea = this.mWidth*this.mHeight;
	}
	
	@Override
	public float getArea() 
	{
		return mArea;
	}
	
	@Override
	public boolean inBounds(Vector2D pnt)
	{
		if(pnt.getX() < this.mVec.getX())
			return false;
		if(pnt.getX() > this.mVec.getX() + this.mWidth)
			return false;
		if(pnt.getY() < this.mVec.getY())
			return false;
		if(pnt.getY() > this.mVec.getY() + this.mHeight)
			return false;

		return true;
	}
	
	@Override
	public boolean touchBounds(Vector2D pnt)
	{
		if(pnt.getX() == this.mVec.getX())
			if(pnt.getY() < this.mVec.getY() && pnt.getY() > this.mVec.getY() + this.mHeight)
				return true;
		if(pnt.getX() == this.mVec.getX() + this.mWidth)
			if(pnt.getY() < this.mVec.getY() && pnt.getY() > this.mVec.getY() + this.mHeight)
				return true;
		
		if(pnt.getY() == this.mVec.getY())
			if(pnt.getX() < this.mVec.getX() && pnt.getX() > this.mVec.getX() + this.mWidth)
				return true;
		if(pnt.getY() == this.mVec.getY() + this.mHeight)
			if(pnt.getX() < this.mVec.getX() && pnt.getX() > this.mVec.getX() + this.mWidth)
				return true;

		return false;
	}

	@Override
	public boolean collidesWith(CollisionCircle circ)
	{
		
		return false;
	}



	@Override
	public boolean collidesWith(CollisionPolygon poly)//Implement SAT
	{
		
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