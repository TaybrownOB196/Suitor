package co.tailoredbytaylor.suitor;

public interface CollisionObject2D
{	
	public float getArea();
	
	public boolean touchBounds(Vector2D pnt);
	public boolean inBounds(Vector2D pnt);
	public boolean collidesWith(CollisionCircle circ);
	public boolean collidesWith(CollisionRect rect);
	//public boolean collidesWith(Polygon poly);
}