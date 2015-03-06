package co.tailoredbytaylor.suitor;

public class CollisionPolygon implements CollisionObjectInterface2D
{
	private Vector2D[] mVecList;
	private float mArea;
	
	public CollisionPolygon(Vector2D[] vecList)
	{
		this.mVecList = vecList;
		mArea = this.getArea();
	}
	
	public Vector2D[] getVecList()
	{
		return this.mVecList;
	}
	
	public int getVecCount()//Returns number of Vector2D objects in the Matrix
	{
		return this.mVecList.length;
	}
	
	@Override
	public float getArea()
	{
		for(int a=0; a<this.mVecList.length; a++)
		{
			if(a == this.mVecList.length-1)
			{
				this.mArea += this.mVecList[a].getX()*this.mVecList[0].getY() - this.mVecList[0].getX()*this.mVecList[a].getY();
				break;
			}
			
			this.mArea += this.mVecList[a].getX()*this.mVecList[a+1].getY() - this.mVecList[a+1].getX()*this.mVecList[a].getY();
		}

		return this.mArea /= 2;
	}
	
	@Override
	public boolean touchBounds(Vector2D pnt)
	{
		
		return false;
	}

	@Override
	public boolean inBounds(Vector2D pnt)//Ray Casting Algorithm
	{
		
		return false;
	}

	@Override
	public boolean collidesWith(CollisionCircle circ)
	{
		
		return false;
	}
	
	public boolean collidesWith(Vector2D[] vecList)
	{
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(this.mVecList);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(vecList);
		

		
		for(int a=0; a<poly1Axes.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], this.mVecList);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], vecList);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				//overlap = false;
				return false;
				//break;
			}
		}
		
		for(int b=0; b<poly2Axes.length; b++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], this.mVecList);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], vecList);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				//overlap = false;
				return false;
				//break;
			}

		}
		
		/*
		if(!overlap)
		{
			return false;
			//System.out.println("No Collision!");
		}
		*/
		return true;
	}
	
	public MTV getMTV(Vector2D[] vecList)
	{
		float mainOverlap = Float.MAX_VALUE;
		Vector2D smallest = null;
		
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(this.mVecList);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(vecList);
		
		for(int a=0; a<poly1Axes.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], this.mVecList);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], vecList);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				return null;
			}
			else
			{
				float tempOverlap1 = SeparatingAxisTheorem.getOverlap(proj1, proj2);
				
				if(tempOverlap1 < mainOverlap)
				{
					mainOverlap = tempOverlap1;
					smallest = poly1Axes[a];
				}
			}	
		}
		
		for(int b=0; b<poly2Axes.length; b++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], this.mVecList);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], vecList);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				return null;
			}
			else
			{
				float tempOverlap2 = SeparatingAxisTheorem.getOverlap(proj1, proj2);
				
				if(tempOverlap2 < mainOverlap)
				{
					mainOverlap = tempOverlap2;
					smallest = poly2Axes[b];
				}
			}
		}
		
	new MTV(smallest, mainOverlap).printMTV();
	return new MTV(smallest, mainOverlap);
}

	
	
	@Override
	public boolean collidesWith(CollisionPolygon poly)//Implement SAT
	{
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(this.mVecList);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(poly.mVecList);
		
		boolean overlap = false;
		
		for(int a=0; a<this.mVecList.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], this.mVecList);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], poly.mVecList);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				overlap = false;
				break;
			}
			
			else
			{
				overlap = true;
			}		
		}
		
		for(int b=0; b<poly2Axes.length; b++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], this.mVecList);
			proj1.printVector();
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], poly.mVecList);
			proj2.printVector();
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				overlap = false;
				break;
			}
			else
			{
				overlap = true;
			}
		}
		
		if(overlap)
		{
			System.out.println("Collision!");
		}
		else
		{
			System.out.println("No Collision!");
			return false;
		}

		return true;
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
