package co.tailoredbytaylor.suitor;

import java.util.Scanner;

public class Vector2D
{
	private float mVector2D[];
	
	public Vector2D()
	{
		this.mVector2D = new float[2];
		
		this.mVector2D[0] = 0;
		this.mVector2D[1] = 0;
	}
	
	public Vector2D(float x, float y)
	{
		this.mVector2D = new float[2];
		
		this.mVector2D[0] = x;
		this.mVector2D[1] = y;
	}
	
	public void fillVector()
	{
		Scanner sc = new Scanner(System.in);
		this.mVector2D[0] = sc.nextFloat();
		this.mVector2D[1] = sc.nextFloat();
	}
	
	public float getX()
	{
		return this.mVector2D[0];
	}
	
	public float getLargestXVal(Vector2D[] vecList)
	{
		float XVal = vecList[0].mVector2D[0];
		
		for(int a=0; a<vecList.length; a++)
		{
			if(XVal >= vecList[a].mVector2D[0])
				XVal = vecList[a].mVector2D[0];
			
		}
		
		return 0;
	}
	
	public float getSmallestXVal(Vector2D[] vecList)
	{
		float XVal = vecList[0].mVector2D[0];
		
		for(int a=0; a<vecList.length; a++)
		{
			if(XVal <= vecList[a].mVector2D[0])
				XVal = vecList[a].mVector2D[0];
			
		}
		
		return 0;
	}
	
	public float getY()
	{
		return this.mVector2D[1];
	}
	
	public float getLargestYVal(Vector2D[] vecList)
	{
		float YVal = vecList[0].mVector2D[1];
		
		for(int a=0; a<vecList.length; a++)
		{
			if(YVal >= vecList[a].mVector2D[1])
				YVal = vecList[a].mVector2D[1];
		}
		
		return 0;
	}
	
	public float getSmallestYVal(Vector2D[] vecList)
	{
		float YVal = vecList[0].mVector2D[1];
		
		for(int a=0; a<vecList.length; a++)
		{
			if(YVal <= vecList[a].mVector2D[1])
				YVal = vecList[a].mVector2D[1];
		}
		
		return 0;
	}
	
	public void setX(float X)
	{
		this.mVector2D[0] = X;
	}
	
	public void setY(float Y)
	{
		this.mVector2D[1] = Y;
	}
	
	public void printVector()
	{
		System.out.println("<"+this.mVector2D[0]+", "+this.mVector2D[1]+">");
	}
	
	public Vector2D copyVector()
	{
		return new Vector2D(this.mVector2D[0], this.mVector2D[1]);
	}
	
	public Matrix augmentVectorMatrix(Vector2D[] vList)
	{
		Matrix A = new Matrix(2, vList.length);
		int num = 0;
		
		for(int a=0; a<A.getHeight(); a++)
		{
			for(int b=0; b<vList.length; b++)
			{
				A.mMatrix[a][b] = vList[b].mVector2D[num];
			}
			num++;
		}
		
		return A;
	}
	
	public Vector2D[] VecMatrixToVeclist(Matrix vecMatrix)
	{
		Vector2D[] vecList = new Vector2D[vecMatrix.getLength()];
		
		for(int a=0; a<vecMatrix.getLength(); a++)
		{
			vecList[a] = new Vector2D(vecMatrix.mMatrix[0][a], vecMatrix.mMatrix[1][a]);
		}

		return vecList;
	}
	
	public Vector2D addVectors(Vector2D rhs)
	{
		return new Vector2D(this.mVector2D[0]+rhs.mVector2D[0], this.mVector2D[1]+rhs.mVector2D[1]);
	}
	
	public Vector2D subtractVectors(Vector2D rhs)
	{
		return new Vector2D(this.mVector2D[0]-rhs.mVector2D[0], this.mVector2D[1]-rhs.mVector2D[1]);
	}
	
	public Vector2D multiplyVectors(Vector2D rhs)
	{
		return new Vector2D(this.mVector2D[0]*rhs.mVector2D[0], this.mVector2D[1]*rhs.mVector2D[1]);
	}
	
	public Vector2D scalarVector(float scalar)
	{
		return new Vector2D(this.mVector2D[0]*scalar, this.mVector2D[1] * scalar);
	}
	
	public Vector2D negateVector()
	{
		return new Vector2D(-this.mVector2D[0], -this.mVector2D[1]);
	}
	
	public float dotProduct(Vector2D rhs)
	{
		return ((this.mVector2D[0]*rhs.mVector2D[0])+(this.mVector2D[1]*rhs.mVector2D[1]));
	}
	
	public float magnitudeVector()
	{
		return (float)Math.sqrt(Math.pow(this.mVector2D[0],2) + Math.pow(this.mVector2D[1],2));
	}
	
	public float angleBetweenVectorsD(Vector2D rhs)
	{
		Vector2D temp = rhs.subtractVectors(this);
		if(temp.magnitudeVector() == 0)
			return 0;
		else
		{
			return (float) Math.toDegrees( 
				Math.acos(((Math.pow(rhs.magnitudeVector(), 2) + 
				Math.pow(this.magnitudeVector(), 2) - 
				Math.pow(temp.magnitudeVector(), 2))/
				(2*this.magnitudeVector()*rhs.magnitudeVector()))));
		}
	}
	
	public float angleBetweenVectorsR(Vector2D rhs)
	{
		Vector2D temp = rhs.subtractVectors(this);
		if(temp.magnitudeVector() == 0)
			return 0;
		else
		{
			return (float)
				Math.acos(((Math.pow(rhs.magnitudeVector(), 2) + 
				Math.pow(this.magnitudeVector(), 2) - 
				Math.pow(temp.magnitudeVector(), 2))/
				(2*this.magnitudeVector()*rhs.magnitudeVector())));
		}
	}
	
	public Vector2D normalizeVector()//Turns the vector into a unit vector
	{
		return new Vector2D(this.mVector2D[0]/(this.magnitudeVector()), this.mVector2D[1]/(this.magnitudeVector()));
	}
	
	public Vector2D scaleVector(float scalarX, float scalarY)
	{
		Matrix scale = new Matrix(2, 2);
		
		scale.mMatrix[0][0] = scalarX;
		scale.mMatrix[1][1] = scalarY;
		
		return this.matrixTimesVector(scale);
	}
	
	public Vector2D perpRVector()
	{
		return new Vector2D(this.mVector2D[1], -this.mVector2D[0]);
	}
	
	public Vector2D perpLVector()
	{
		return new Vector2D(-this.mVector2D[1], this.mVector2D[0]);
	}
	
	public Vector2D reflectVector()
	{
		Matrix reflect = new Matrix(2, 2);
		
		reflect.mMatrix[0][1] = 1;
		reflect.mMatrix[1][0] = 1;
		
		return this.matrixTimesVector(reflect);
	}
	
	public Vector2D translateVector(float x, float y)
	{
		Matrix translate = new Matrix(3, 3);
		
		translate.mMatrix[0][0] = 1;
		translate.mMatrix[0][2] = x;
		translate.mMatrix[1][1] = 1;
		translate.mMatrix[1][2] = y;
		translate.mMatrix[2][2] = 1;
		
		Vector2D v2 = this.copyVector();
		Vector3D v3 = v2.R2toR3Vector();
		v3 = v3.matrixTimesVector(translate);
		
		return v3.R3toR2Vector();
	}
	
	public Vector2D translateVector(Vector2D point)
	{
		Matrix translate = new Matrix(3, 3);
		
		translate.mMatrix[0][0] = 1;
		translate.mMatrix[0][2] = point.getX();
		translate.mMatrix[1][1] = 1;
		translate.mMatrix[1][2] = point.getY();
		translate.mMatrix[2][2] = 1;
		
		Vector2D v2 = this.copyVector();
		Vector3D v3 = v2.R2toR3Vector();
		v3 = v3.matrixTimesVector(translate);
		
		return v3.R3toR2Vector();
	}
	
	public Vector2D rotateVector(float degrees)
	{				
		Matrix rotate = new Matrix(2, 2);
		
		float radians = (float) Math.toRadians(degrees);
		
		rotate.mMatrix[0][0] = (float) Math.cos(radians);
		rotate.mMatrix[0][1] = (float) -Math.sin(radians);
		rotate.mMatrix[1][0] = (float) Math.sin(radians);
		rotate.mMatrix[1][1] = (float) Math.cos(radians);
		
		return this.matrixTimesVector(rotate);
	}
	
	public Vector2D rotateVectorAboutPoint(float degrees, float s, float t)
	{
		float xTrans = this.getX()-s, yTrans = this.getY()-t;
		//float newX = (float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.cos(radians-Math.atan2(yTrans, xTrans))+s);
		//float newY = (float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.sin(radians-Math.atan2(yTrans, xTrans))+t);
		
		return new Vector2D
				(
				(float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.cos(degrees-Math.atan2(yTrans, xTrans))+s),
				(float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.sin(degrees-Math.atan2(yTrans, xTrans))+t)
				);
				
	}
	
	public Vector2D rotateVectorAboutPoint(float degrees, Vector2D point)
	{
		float xTrans = this.getX()-point.getX(), yTrans = this.getY()-point.getY();
		//float newX = (float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.cos(radians-Math.atan2(yTrans, xTrans))+s);
		//float newY = (float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.sin(radians-Math.atan2(yTrans, xTrans))+t);
		
		return new Vector2D
				(
				(float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.cos(degrees-Math.atan2(yTrans, xTrans))+point.getX()),
				(float)(Math.sqrt(Math.pow(xTrans, 2)+Math.pow(yTrans,2))*Math.sin(degrees-Math.atan2(yTrans, xTrans))+point.getY())
				);
	}
	
	public Vector2D matrixTimesVector(Matrix A)
	{
		Vector2D v = new Vector2D();
		
		for(int a=0; a<2; a++)
		{
			v.mVector2D[a] = returnMultiple(A, this, a);
		}
		
		return v;
	}
	
	private float returnMultiple(Matrix A, Vector2D v, int row)
	{
		float multiple = 0;
		
			for(int b=0; b<2; b++)
			{
				multiple += A.mMatrix[row][b] * v.mVector2D[b];
			}
		
		return multiple;
	}
	
	public Vector3D R2toR3Vector()
	{
		return new Vector3D(this.mVector2D[0], this.mVector2D[1], 1);
	}
	
	public Matrix vectorMatrixR2toR3(Vector2D[] vecList)
	{
		Matrix A = this.augmentVectorMatrix(vecList);
		Matrix B = new Matrix(3, A.getLength());
						
		for(int a=0; a<A.getHeight(); a++)
		{
			for(int b=0; b<A.getLength(); b++)
			{
				B.mMatrix[a][b] = A.mMatrix[a][b];
			}
		}
		
		for(int c=0; c<A.getLength(); c++)
			B.mMatrix[2][c] = 1;
		
		return B;
	}
	
	public Matrix vectorMatrixR2toR3(Matrix vecMatrix)
	{
		Matrix B = new Matrix(3, vecMatrix.getLength());
						
		for(int a=0; a<vecMatrix.getHeight(); a++)
		{
			for(int b=0; b<vecMatrix.getLength(); b++)
			{
				B.mMatrix[a][b] = vecMatrix.mMatrix[a][b];
			}
		}
		
		for(int c=0; c<vecMatrix.getLength(); c++)
			B.mMatrix[2][c] = 1;
		
		return B;
	}
	
	public Vector2D intersectionPoint(Vector2D A, Vector2D B,Vector2D C, Vector2D D)
	{
		float tX, tY, sX, sY, val1, val2, val3, scalar;
		
		tX = B.getX()-A.getX();
		tY = B.getY()-A.getY();
		sX = C.getX()-D.getX();
		sY = C.getY()-D.getY();
		
		val1 = C.getX()-A.getX();
		val2 = C.getY()-A.getY();
		
		if(tY*sX - tX*sY == 0)
		{
			System.out.println("Lines AB and CD do not intersect");
			return null;
		}
		
		val3 = (tY*val1 - tX*val2);
		scalar = val3/(tY*sX - tX*sY);
		
		D = D.subtractVectors(C);
		return C.addVectors(D.scalarVector(scalar));
	}
}
