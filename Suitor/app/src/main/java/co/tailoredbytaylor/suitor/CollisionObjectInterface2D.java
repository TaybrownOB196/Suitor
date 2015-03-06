package co.tailoredbytaylor.suitor;

public interface CollisionObjectInterface2D
{	
	public float getArea();
	
	public boolean touchBounds(Vector2D pnt);
	public boolean inBounds(Vector2D pnt);
	
	public boolean collidesWith(CollisionCircle circ);
	public boolean collidesWith(CollisionPolygon poly);
	public boolean containsCirc(CollisionCircle circ);
	public boolean containsPoly(CollisionPolygon circ);
}