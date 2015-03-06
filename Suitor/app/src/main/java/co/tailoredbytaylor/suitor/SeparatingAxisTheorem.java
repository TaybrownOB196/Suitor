package co.tailoredbytaylor.suitor;

public final class SeparatingAxisTheorem
{
	private SeparatingAxisTheorem(){};
	
	public static Vector2D[] getSepAxes(Vector2D[] vecList)
	{
		Vector2D[] axes = new Vector2D[vecList.length];
		
		Vector2D edge = null;
		Vector2D normal = null;
		
		for(int a=0; a<axes.length; a++)
		{
			if(a == axes.length-1)
				edge = vecList[a].subtractVectors(vecList[0]);
			else
				edge = vecList[a].subtractVectors(vecList[a+1]);
		
			normal = edge.perpLVector();	//Use this function if the vertices are listed in CW order
			//normal = edge.perpRVector();	//Use this function if the vertices are listed in CCW order
			axes[a] = normal.normalizeVector();
		}
		
		return axes;
	}
	
	public static Vector2D getShapeProjection(Vector2D axis, Vector2D[] vertices)
	{
		float min = axis.dotProduct(vertices[0]);
		float max = min;
		
		for(int a=0; a<vertices.length; a++)
		{
			float dotP = axis.dotProduct(vertices[a]);
			if(dotP < min)
				min = dotP;
			else if(dotP > max)
				max = dotP;
		}
		
		return new Vector2D(min, max);
	}
	
	public static boolean projOverlap(Vector2D proj1, Vector2D proj2)
	{
		//Check to see if the maximum value for the second projection is before the start of the first projection
		//or
		//Check to see if the minimum value for the second projection is after the start of the first projection
		//If both return false then the second projection is outside of the first projection and therefore there is no collision
		return //X is min and Y is max
			proj1.getX() > proj2.getY() || proj1.getY() < proj2.getX();
			//If true, then there is no collision
			//This means that there is indeed a separating axis in between the two polygons
	}
	
	public static boolean getContainment(Vector2D proj1, Vector2D proj2)
	{
		return 
				proj2.getX() > proj1.getX() && proj2.getY() < proj1.getY();
	}
	
	public static float getOverlap(Vector2D projOne, Vector2D projTwo)
	{
		return
				Math.min(projOne.getY(), projTwo.getY())
				-
				Math.max(projOne.getX(), projTwo.getX());
	}
}
