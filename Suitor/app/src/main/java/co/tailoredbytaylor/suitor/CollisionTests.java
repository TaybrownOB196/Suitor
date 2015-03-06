package co.tailoredbytaylor.suitor;

public class CollisionTests
{
	public static void main(String[] args)
	{
		//Moving Object in ObjectMovementTests
		Vector2D V01 = new Vector2D(2,4);
		Vector2D V02 = new Vector2D(4,2);
		Vector2D V03 = new Vector2D(2,0);
		Vector2D V04 = new Vector2D(1,1);
		Vector2D[] poly1 = {V01, V02, V03, V04};
		
		//Static Object in ObjectMovementTests
		Vector2D V09 = new Vector2D(2,7);
		Vector2D V10 = new Vector2D(2,5);
		Vector2D V11 = new Vector2D(0,5);
		Vector2D V12 = new Vector2D(0,7);
		Vector2D[] poly2 = {V09, V10, V11, V12};
		
		boolean collides = false;
		int count = 0;
		
		Vector2D[] poly1Axes = SeparatingAxisTheorem.getSepAxes(poly1);
		Vector2D[] poly2Axes = SeparatingAxisTheorem.getSepAxes(poly2);
		
		for(int a=0; a<poly1Axes.length; a++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], poly1);
			proj1.printVector();
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly1Axes[a], poly2);
			proj2.printVector();
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{//There is a separating axis in between the two polygons
				collides = false;
				break;
			}
			else
			{
				collides = true;
				count++;
			}	 
		}
		
		for(int b=0; b<poly2Axes.length; b++)
		{
			Vector2D proj1 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], poly1);
			proj1.printVector();
			Vector2D proj2 = SeparatingAxisTheorem.getShapeProjection(poly2Axes[b], poly2);
			proj2.printVector();
			
			if(SeparatingAxisTheorem.projOverlap(proj1, proj2))
			{//There is a separating axis in between the two polygons
				collides = false;
				break;
			}
			else
			{
				collides = true;
				count++;
			}
			
			if(count == 8)
			{
				System.out.println(SeparatingAxisTheorem.getOverlap(proj1, proj2));
			}
		}
		
		if(collides)
		{
			System.out.println(count);
			System.out.println("Collision!");
		}
		else
		{
			System.out.println(count);
			System.out.println("No Collision!");
		}
	}
}
