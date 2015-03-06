package co.tailoredbytaylor.suitor;

public class MTV
{
	private Vector2D mAxis;
	private float mDepth;
	
	public MTV()
	{
		this.mAxis = null;
		this.mDepth = 0;
	}
	
	public MTV(Vector2D axis, float depth)
	{
		this.mAxis = axis;
		this.mDepth = depth;
	}
	
	public void printMTV()
	{
		System.out.print("Axis: ");
		getAxis().printVector();
		System.out.println("Depth:"+getDepth());
	}
	
	public Vector2D getAxis()
	{
		return this.mAxis;
	}
	
	public float getDepth()
	{
		return this.mDepth;
	}
}
