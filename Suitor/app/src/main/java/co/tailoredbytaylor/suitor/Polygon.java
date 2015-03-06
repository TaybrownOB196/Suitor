package co.tailoredbytaylor.suitor;

public class Polygon
{	
	protected Vector2D[] mPolyVecs;
	protected Vector2D mCentroid;
	protected float mArea;
	
	public float getArea()
	{
		for(int a=0; a<this.mPolyVecs.length; a++)
		{
			if(a == this.mPolyVecs.length-1)
			{
				this.mArea += this.mPolyVecs[a].getX()*this.mPolyVecs[0].getY() - this.mPolyVecs[0].getX()*this.mPolyVecs[a].getY();
				break;
			}
			
			this.mArea += this.mPolyVecs[a].getX()*this.mPolyVecs[a+1].getY() - this.mPolyVecs[a+1].getX()*this.mPolyVecs[a].getY();
		}
		
		return this.mArea /= 2;
	}
	
	public Vector2D getCentroid()
	{
		float num = (1/(6*this.getArea()));
		float Cx = 0, Cy = 0;
		
		for(int a=0; a<this.mPolyVecs.length; a++)
		{
			if(a == this.mPolyVecs.length-1)
			{
				Cx += (this.mPolyVecs[a].getX()+mPolyVecs[0].getX())
					*(mPolyVecs[a].getX()*mPolyVecs[0].getY()-mPolyVecs[0].getX()*mPolyVecs[a].getY());
				Cy += (mPolyVecs[a].getY()+mPolyVecs[0].getY())
					*(mPolyVecs[a].getX()*mPolyVecs[0].getY()-mPolyVecs[0].getX()*mPolyVecs[a].getY());
				break;
			}
				
			Cx += (mPolyVecs[a].getX()+mPolyVecs[a+1].getX())
				*(mPolyVecs[a].getX()*mPolyVecs[a+1].getY()-mPolyVecs[a+1].getX()*mPolyVecs[a].getY());
			Cy += (mPolyVecs[a].getY()+mPolyVecs[a+1].getY())
				*(mPolyVecs[a].getX()*mPolyVecs[a+1].getY()-mPolyVecs[a+1].getX()*mPolyVecs[a].getY());
		}
		
		this.mCentroid.setX(Cx*=num);
		this.mCentroid.setX(Cy*=num);
		
		return this.mCentroid;
	}
	
	public void updateCentroid()
	{
		float num = (1/(6*this.getArea()));
		float Cx = 0, Cy = 0;
		
		for(int a=0; a<this.mPolyVecs.length; a++)
		{
			if(a == this.mPolyVecs.length-1)
			{
				Cx += (this.mPolyVecs[a].getX()+mPolyVecs[0].getX())
					*(mPolyVecs[a].getX()*mPolyVecs[0].getY()-mPolyVecs[0].getX()*mPolyVecs[a].getY());
				Cy += (mPolyVecs[a].getY()+mPolyVecs[0].getY())
					*(mPolyVecs[a].getX()*mPolyVecs[0].getY()-mPolyVecs[0].getX()*mPolyVecs[a].getY());
				break;
			}
				
			Cx += (mPolyVecs[a].getX()+mPolyVecs[a+1].getX())
				*(mPolyVecs[a].getX()*mPolyVecs[a+1].getY()-mPolyVecs[a+1].getX()*mPolyVecs[a].getY());
			Cy += (mPolyVecs[a].getY()+mPolyVecs[a+1].getY())
				*(mPolyVecs[a].getX()*mPolyVecs[a+1].getY()-mPolyVecs[a+1].getX()*mPolyVecs[a].getY());
		}
		
		this.mCentroid.setX(Cx*=num);
		this.mCentroid.setX(Cy*=num);
	}
	
	public void TranslatePolygon2D(float X, float Y)
	{
		for(int a=0; a<this.mPolyVecs.length; a++)
		{
			this.mPolyVecs[a].setX(this.mPolyVecs[a].getX()+X);
			this.mPolyVecs[a].setY(this.mPolyVecs[a].getY()+Y);
		}
		updateCentroid();
	}
	
	public void ScalePolygon2D(float X, float Y)
	{
		Matrix scale = new Matrix(2,2);
		scale.mMatrix[0][0] = X;
		scale.mMatrix[1][1] = Y;
		
		updateCentroid();
	}
	
	public void RotatePolygon2D(float degrees)
	{
		Matrix rotate = new Matrix(2,2);
		
		float radians = (float) Math.toRadians(degrees);
		
		rotate.mMatrix[0][0] = (float) Math.cos(radians);
		rotate.mMatrix[0][1] = (float) -Math.sin(radians);
		rotate.mMatrix[1][0] = (float) Math.sin(radians);
		rotate.mMatrix[1][1] = (float) Math.cos(radians);
		
		updateCentroid();
	}
	
	public void RotatePolygonAboutPoint2D(float degrees, float S, float T)
	{
		
		
		updateCentroid();
	}
	
	public void ReflectPolygon2D()
	{
		Matrix reflect = new Matrix(2,2);
		reflect.mMatrix[0][1] = 1;
		reflect.mMatrix[1][0] = 1;
		
		updateCentroid();
	}
	
	public void ShearPolygonXAxis2D(float X)
	{
		Matrix shearX = new Matrix(2,2);
		shearX.mMatrix[0][0] = 1;
		shearX.mMatrix[0][1] = X;
		shearX.mMatrix[1][1] = 1;
	
		updateCentroid();
	}
	
	public void ShearPolygonYAxis2D(float Y)
	{
		Matrix shearY = new Matrix(2,2);
		shearY.mMatrix[0][0] = 1;
		shearY.mMatrix[1][0] = Y;
		shearY.mMatrix[1][1] = 1;
	
		updateCentroid();
	}
	
	public boolean collidesWith(Polygon poly2)
	{
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(this.mPolyVecs);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(poly2.mPolyVecs);;
		
		for(int a=0; a<poly1Axes.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], this.mPolyVecs);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], poly2.mPolyVecs);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				return false;
			}
		}
		
		for(int b=0; b<poly2Axes.length; b++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], this.mPolyVecs);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], poly2.mPolyVecs);
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{
				return false;
			}
		}

		return true;
	}
	
	public MTV getMTV(Polygon poly2)
	{
		float mainOverlap = Float.MAX_VALUE;
		Vector2D smallest = null;
		
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(this.mPolyVecs);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(poly2.mPolyVecs);
		
		for(int a=0; a<poly1Axes.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], this.mPolyVecs);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], poly2.mPolyVecs);
			
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
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], this.mPolyVecs);
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], poly2.mPolyVecs);
			
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
		
		return new MTV(smallest, mainOverlap);
	}
	
}
