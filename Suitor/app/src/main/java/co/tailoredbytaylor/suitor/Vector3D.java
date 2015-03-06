package co.tailoredbytaylor.suitor;

import java.util.Scanner;

public class Vector3D 
{	
	private float[] mVector3D;
	
	public Vector3D()
	{
		this.mVector3D = new float[3];
		
		this.mVector3D[0] = 0;
		this.mVector3D[1] = 0;
		this.mVector3D[2] = 0;
	}
	
	public Vector3D(float x, float y, float z)
	{
		this.mVector3D = new float[3];
		
		this.mVector3D[0] = x;
		this.mVector3D[1] = y;
		this.mVector3D[2] = z;
	}
	
	public void fillVector()
	{
		Scanner sc = new Scanner(System.in);
		
		this.mVector3D[0] = sc.nextFloat();
		this.mVector3D[1] = sc.nextFloat();
		this.mVector3D[2] = sc.nextFloat();
	}
	
	public float getX()
	{
		return this.mVector3D[0];
	}
	
	public float getY()
	{
		return this.mVector3D[1];
	}
	
	public float getZ()
	{
		return this.mVector3D[2];
	}
	
	
	public void setX(float X)
	{
		this.mVector3D[0] = X;
	}
	
	public void setY(float Y)
	{
		this.mVector3D[1] = Y;
	}
	
	public void setZ(float Z)
	{
		this.mVector3D[2] = Z;
	}
	
	public void printVector()
	{
		System.out.println("<"+this.mVector3D[0]+", "+this.mVector3D[1]+", "+this.mVector3D[2]+">");
	}
	
	public Vector3D copyVector()
	{
		return new Vector3D(this.mVector3D[0], this.mVector3D[1], this.mVector3D[2]);
	}
	
	public Matrix augmentVectorMatrix(Vector3D[] vList)
	{
		Matrix A = new Matrix(3, vList.length);
		int num = 0;
		
		for(int a=0; a<A.getHeight(); a++)
		{
			for(int b=0; b<vList.length; b++)
			{
				A.mMatrix[a][b] = vList[b].mVector3D[num];
			}
			num++;
		}
		
		return A;
	}
	
	public Vector3D addVectors(Vector3D rhs)
	{
		return new Vector3D(this.mVector3D[0]+rhs.mVector3D[0], this.mVector3D[1]+rhs.mVector3D[1], this.mVector3D[2]+rhs.mVector3D[2]);
	}
	
	public Vector3D subtractVectors(Vector3D rhs)
	{
		return new Vector3D(this.mVector3D[0]-rhs.mVector3D[0], this.mVector3D[1]-rhs.mVector3D[1], this.mVector3D[2]-rhs.mVector3D[2]);
	}
	
	public Vector3D multiplyVectors(Vector3D rhs)
	{
		return new Vector3D(this.mVector3D[0]*rhs.mVector3D[0], this.mVector3D[1]*rhs.mVector3D[1], this.mVector3D[2]*rhs.mVector3D[2]);
	}
	
	public Vector3D scalarVector(float scalar)
	{
		return new Vector3D(this.mVector3D[0]*scalar, this.mVector3D[1] * scalar, this.mVector3D[2] * scalar);
	}
	
	public float dotProduct(Vector3D rhs)
	{
		return ((this.mVector3D[0]*rhs.mVector3D[0])+(this.mVector3D[1]*rhs.mVector3D[1])+(this.mVector3D[2]*rhs.mVector3D[2]));
		
	}
	
	public Vector3D crossProduct(Vector3D rhs)
	{
		return new Vector3D(
				this.getY()*rhs.getZ() - this.getZ()*rhs.getY(),
				-this.getX()*rhs.getZ() - this.getZ()*rhs.getX(),
				this.getX()*rhs.getY() - this.getY()*rhs.getX());
	}
	
	public float magnitudeVector()
	{
		return (float)Math.sqrt(Math.pow(this.mVector3D[0],2) + Math.pow(this.mVector3D[1],2) + Math.pow(this.mVector3D[2],2));
	}
	
	public Vector3D normalizeVector()
	{
		return new Vector3D(this.mVector3D[0]/(this.magnitudeVector()), 
							this.mVector3D[1]/(this.magnitudeVector()), 
							this.mVector3D[2]/(this.magnitudeVector()));
	}
	
	public Vector3D scaleVector(float scalar)
	{
		Matrix scale = new Matrix(3, 3);
		
		scale.mMatrix[0][0] = scalar;
		scale.mMatrix[1][1] = scalar;
		scale.mMatrix[2][2] = 1;
		
		return this.matrixTimesVector(scale);
	}
	
	public Vector3D reflectVector()
	{
		Matrix reflect = new Matrix(3, 3);
		
		reflect.mMatrix[0][1] = 1;
		reflect.mMatrix[1][0] = 1;
		reflect.mMatrix[2][2] = 1;
		
		return this.matrixTimesVector(reflect);
	}
	
	public Vector3D translateVector(float x, float y)
	{
		Matrix translate = new Matrix(3, 3);
		
		translate.mMatrix[0][0] = 1;
		translate.mMatrix[0][2] = x;
		translate.mMatrix[1][1] = 1;
		translate.mMatrix[1][2] = y;
		translate.mMatrix[2][2] = 1;
		
		return this.matrixTimesVector(translate);
	}
	
	public Vector3D rotateVector(float degrees)
	{				
		Matrix rotate = new Matrix(3, 3);
		
		float radians = (float)(degrees*(Math.PI/180));
		
		rotate.mMatrix[0][0] = (float) Math.cos(radians);
		rotate.mMatrix[0][1] = (float) -Math.sin(radians);
		rotate.mMatrix[1][0] = (float) Math.sin(radians);
		rotate.mMatrix[1][1] = (float) Math.cos(radians);
		rotate.mMatrix[2][2] = 1;
		
		return this.matrixTimesVector(rotate);
	}
	
	public Vector3D matrixTimesVector(Matrix A)
	{
		Vector3D v = new Vector3D();
		
		for(int a=0; a<3; a++)
		{
			v.mVector3D[a] = returnMultiple(A, this, a);
		}
		
		return v;
	}
	
	private float returnMultiple(Matrix A, Vector3D v, int row)
	{
		float multiple = 0;
		
			for(int b=0; b<3; b++)
			{
				multiple += A.mMatrix[row][b] * v.mVector3D[b];
			}
		
		return multiple;
	}
	
	public Vector2D R3toR2Vector()
	{
		return new Vector2D(this.mVector3D[0], this.mVector3D[1]);
	}
	
	public Matrix vectorMatrixR3toR2(Vector3D[] vecList)
	{
		Matrix A = this.augmentVectorMatrix(vecList);
		Matrix B = new Matrix(2, A.getLength());
						
		for(int a=0; a<B.getHeight(); a++)
		{
			for(int b=0; b<B.getLength(); b++)
			{
				B.mMatrix[a][b] = A.mMatrix[a][b];
			}
		}
		return B;
	}
	
	public Matrix vectorMatrixR3toR2(Matrix vecMatrix)
	{
		Matrix B = new Matrix(2, vecMatrix.getLength());
						
		for(int a=0; a<B.getHeight(); a++)
		{
			for(int b=0; b<B.getLength(); b++)
			{
				B.mMatrix[a][b] = vecMatrix.mMatrix[a][b];
			}
		}
		return B;
	}
}
