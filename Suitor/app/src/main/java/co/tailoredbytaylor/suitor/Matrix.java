package co.tailoredbytaylor.suitor;

import java.util.Scanner;

public class Matrix 
{
	private int mCols, mRows;
	public float mMatrix[][];
	
	public Matrix(int rows, int cols)
	{
		this.mCols = cols;
		this.mRows = rows;
		
		this.mMatrix = new float[mRows][mCols];
	}
	
	private class Coordinate//(y,x)
	//Inner Class Coordinate to access positions in the matrices
	{
		private int y, x;
		
		public Coordinate(int Y, int X)//(y,x)
		{
			this.y = Y;
			this.x = X;
		}
		/*
		public void setCoords(int Y, int X)//(y,x)
		{
			this.y = Y;
			this.x = X;
		}
		
		public void printCoords()//(y,x)
		{
			System.out.println("("+this.y+", "+this.x+")");
		}
		*/
	}
	
	public int getHeight()
	{
		return this.mRows;
	}
	
	public int getLength()
	{
		return this.mCols;
	}
	
	public void fillMatrix()
	{
		for(int a=0; a<this.getHeight(); a++)
		{
			for(int b=0; b<this.getLength(); b++)
			{
				Scanner sc = new Scanner(System.in);
				this.mMatrix[a][b] = sc.nextFloat();
			}
		}
	}

	public void fillMatrixRand(int high, int low)
	{
		for(int a=0; a<this.getHeight(); a++)
		{
			for(int b=0; b<this.getLength(); b++)
			{
				this.mMatrix[a][b] = (int)((Math.random()*((high-low)+1)));
			}
		}
	}
	
	public Matrix copyMatrix(Matrix rhs)
	{
		Matrix A = new Matrix(rhs.getHeight(), rhs.getLength());
		
		for(int a=0; a<rhs.getHeight(); a++)
		{
			for(int b=0; b<rhs.getLength(); b++)
			{
				A.mMatrix[a][b] = rhs.mMatrix[a][b];
			}
		}
		
		return A;
	}
	
	public Matrix augmentMatrix(Matrix rhs)
	{
		if(this.getHeight() != rhs.getHeight())
		{
			System.out.println("Invalid Dimensions, Cannot augment.");
			return this;
		}
		
		else
		{
			Matrix A = new Matrix(this.getHeight(), (this.getLength()+rhs.getLength()));
			int num = 0;
			
			for(int a=0; a<this.getHeight(); a++)
			{
				for(int b=0; b<this.getLength(); b++)
				{
					A.mMatrix[a][b] = this.mMatrix[a][b];
					num = b;
				}
				
				for(int c=0; c<rhs.getLength(); c++)
				{
					A.mMatrix[a][num+c] = rhs.mMatrix[a][c];
				}
				num = 0;
			}
			
			return A;
		}
	}
	
	public void printMatrix()
	{
		for(int a=0; a<this.getHeight(); a++)
		{
			for(int b=0; b<this.getLength(); b++)
			{
				System.out.print(this.mMatrix[a][b]+" ");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	public Matrix identityMatrix(int dim)
	{
		Matrix A = new Matrix(dim, dim);
		for(int a=0; a<dim; a++)
		{
			A.mMatrix[a][a] = 1;
		}
		
		return A;
	}
	
	public Matrix transposeMatrix()
	{
		Matrix A = new Matrix(this.getLength(), this.getHeight());
		
		for(int a=0; a<A.getHeight(); a++)
		{
			for(int b=0; b<A.getLength(); b++)
			{
				A.mMatrix[a][b] = this.mMatrix[b][a];
			}
		}
		
		return A;
	}
	
    private void interchangeRow(int rowOne,int rowTwo)
    {
    	float[] temp = new float[this.getLength()];

        for (int a=0; a<this.getLength(); a++)
        {
            temp[a] = this.mMatrix[rowTwo][a];
            this.mMatrix[rowTwo][a] = this.mMatrix[rowOne][a];
            this.mMatrix[rowOne][a] = temp[a];
        }
    }

    private void replacementRow(int rowOne, float scalar, int rowTwo)
    {
    	float[] temp = new float [this.getLength()];
        
        for (int a=0; a<this.getLength(); a++)
        {
            temp[a] = this.mMatrix[rowOne][a]*(scalar);
            this.mMatrix[rowTwo][a] += (temp[a]);
        }   
    }

    private void scaleRow(int row, float scalar)
    {
        for(int a=0; a<this.getLength(); a++)
        	this.mMatrix[row][a] = this.mMatrix[row][a]*(scalar);
    }
	
    private boolean isColumnZero(int row, int col)//(y,x)
    {
    	for(int a=row; a<this.getHeight(); a++)
    	{
    		if(this.mMatrix[a][col] == 0)
    			continue;
    		
    		else
    			return false;
    	}
    	
		return true;
    }
    
    private boolean isRowZero(int row, int col)
    {
    	for(int a=col; a<this.getLength(); a++)
    	{
    		if(this.mMatrix[row][a] == 0)
    			continue;
    		
    		else
    			return false;
    	}
    	
		return true;
    }
           
    public Matrix addMatrix(Matrix rhs)
    {
    	if((this.getHeight() == rhs.getHeight()) && (this.getLength() == this.getLength()))
    	{
    		Matrix C = new Matrix(this.getHeight(), this.getLength());
    		
    		for(int a=0; a<this.getHeight(); a++)
    		{
    			for(int b=0; b<this.getLength(); b++)
    			{
    				C.mMatrix[a][b] = this.mMatrix[a][b] + rhs.mMatrix[a][b];
    			}
    		}
    		
    		return C;
    	}
    	
    	else
    	{
    		System.out.println("Invalid Dimensions, Cannot add matrices.");
    		return this;
    	}
    }
    
    public Matrix subtractMatrix(Matrix rhs)
    {
    	if((this.getHeight() == rhs.getHeight()) && (this.getLength() == this.getLength()))
    	{
    		Matrix C = new Matrix(this.getHeight(), this.getLength());
    		
    		for(int a=0; a<this.getHeight(); a++)
    		{
    			for(int b=0; b<this.getLength(); b++)
    			{
    				C.mMatrix[a][b] = this.mMatrix[a][b] - rhs.mMatrix[a][b];
    			}
    		}
    		
    		return C;
    	}
    	
    	else
    	{
    		System.out.println("Invalid Dimensions, Cannot subtract matrices.");
    		return this;
    	}
    }
    
    public Matrix scalarMatrix(float scalar)
    {
    	Matrix C = new Matrix(this.getHeight(), this.getLength());
		
		for(int a=0; a<this.getHeight(); a++)
		{
			for(int b=0; b<this.getLength(); b++)
			{
				C.mMatrix[a][b] = this.mMatrix[a][b] * scalar;
			}
		}
		
		return C;
    }
    
    public Matrix multiplyMatrix(Matrix rhs)
    {
    	if(this.getLength() != rhs.getHeight())
    	{
    		System.out.println("Invalid Dimension, Cannot multiply.");
    		return this;
    	}
    	
    	else
    	{
    		Matrix C = new Matrix(this.getHeight(), rhs.getLength());
    		
    		for(int a=0; a<C.getLength(); a++)
    		{
    			for(int b=0; b<C.getHeight(); b++)
    			{
    				C.mMatrix[b][a] = returnMultiple(this, rhs, b, a);
    			}
    		}
    		
    		return C;
    	}
    }
    
    private float returnMultiple(Matrix A, Matrix B, int col, int row)
    {
    	float multiple = 0;
    	
    	for(int a=0; a<A.getLength(); a++)
    	{
    		multiple += (A.mMatrix[col][a] * B.mMatrix[a][row]);
    	}
    	
    	return multiple;
    }

    public Coordinate findPivot(int row, int col)
    {
    	//Pivot Rules: 
    	//Pivot should be equal to 1 or the largest absolute value term in the column
    	//Pivots should never be above the top line
    	//Pivots should never be across the left most pivot column
    	//If current row is the last row in the matrix and the pivot column
    	
    	Coordinate pivot = new Coordinate(row, col);  	
    	
    	if(this.isColumnZero(pivot.y, pivot.x))
		{
			//Checks to see it the current column is filled with zeroes
			//If the column is, then the method increments the x coordinate
			//in pivot to check the next column
			pivot.x++;
			if(pivot.x >= this.getLength()-1)
			{
				pivot.x = this.getLength()-1;
				return pivot;
			}
		}
		
		if(this.isRowZero(pivot.y, pivot.x))
		{
			//Checks to see if the current row is filled with zeroes
			//If the row is, then it is switched with the bottom-most nonzero row
			for(int i=this.getHeight()-1; i>=0; i--)
			{
				if(this.isRowZero(i, pivot.x))
					continue;
				else
					this.interchangeRow(pivot.y, 1);
   			}
			if(pivot.x == this.getHeight()-1)
				return pivot;
		}
    	
		if(this.mMatrix[pivot.y][pivot.x] == 1.0f)
		{
			this.interchangeRow(pivot.y, row);
			pivot.y = row;
			return pivot;
		}
		
    	for(int a=pivot.y; a<this.getHeight(); a++)
    	{    		
    		if(this.mMatrix[pivot.y][pivot.x] <= this.mMatrix[a][pivot.x])
    		{
    			//Find the value in the column with the largest abs. value
    			//Move the row with aforementioned to the top of the submatrix
    			pivot.y = a;
    			this.interchangeRow(pivot.y, a);
    		}
    		
    		else if(this.mMatrix[a][pivot.x] == 1.0f)
    		{
    			this.interchangeRow(a, row);
    			pivot.y = row;
				
    			return pivot;
    		}
    			
    		this.interchangeRow(pivot.y, row);
        	pivot.y = row;
    	}
    	
    	return pivot;
    }

    private void regulateREF(Coordinate point)
    {
    	if(this.mMatrix[point.y][point.x] < 0.0f)
			this.scaleRow(point.y, -1);
		for(int i=0; i<this.getHeight(); i++)
			for(int j=0; j<this.getLength(); j++)
				if(this.mMatrix[i][j] == -0.0f)
					this.mMatrix[i][j] = 0.0f;
    }
    
    public void REF()
    {
    	int submatrix = 0;//A variable to represent the current column
    	Coordinate pivot = new Coordinate(0, 0);
    	
    	for(int a=0; a<this.getHeight(); a++)
    	{
    		//Step 1:
    		pivot = this.findPivot(a, submatrix);
    		
    		//Step 2:
    		if(this.mMatrix[pivot.y][pivot.x] != 1.0f)
    		{
    			float scalar = 1/(this.mMatrix[pivot.y][pivot.x]);
    			
    			if(this.mMatrix[pivot.y][pivot.x] < 0.0f)
    				this.scaleRow(pivot.y, -scalar);
    			else if(this.mMatrix[pivot.y][pivot.x] > 0.0f)
    				this.scaleRow(pivot.y, scalar);
    		}
    		
    		//Step 3:
    		for(int b=pivot.y+1; b<this.getHeight(); b++)
    		{
    			if(this.mMatrix[b][pivot.x] != 0.0f)
    			{
    				float scalar = (this.mMatrix[b][pivot.x]/this.mMatrix[pivot.y][pivot.x]);
    				
    				if(this.mMatrix[b][pivot.x] < 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, -scalar, b);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, scalar, b);
    				}

    				else if(this.mMatrix[b][pivot.x] > 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, -scalar, b);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, scalar, b);
    				}
    			}
    			else
    				continue;
    		}
    		
    		if(this.mMatrix[pivot.y][pivot.x] != 1.0f)
    		{
    			float scalar = 1/(this.mMatrix[pivot.y][pivot.x]);
    			
    			if(this.mMatrix[pivot.y][pivot.x] < 0.0f)
    				this.scaleRow(pivot.y, -scalar);
    			else if(this.mMatrix[pivot.y][pivot.x] > 0.0f)
    				this.scaleRow(pivot.y, scalar);
    		}
    		
    		this.regulateREF(pivot);
    		
    		//Step 4:
    		if(submatrix <= pivot.x)
        		submatrix = pivot.x;
        	submatrix++;
        	
    	}
    		
    }

    public void RREF()
    {
    	int submatrix = 0;//A variable to represent the current column
    	Coordinate pivot = new Coordinate(0, 0);
    	
    	for(int a=0; a<this.getHeight(); a++)
    	{
    		//Step 1:
    		pivot = this.findPivot(a, submatrix);
    		
    		//Step 2:
    		if(this.mMatrix[pivot.y][pivot.x] != 1.0f)
    		{
    			float scalar = 1/(this.mMatrix[pivot.y][pivot.x]);
    			
    			if(this.mMatrix[pivot.y][pivot.x] < 0.0f)
    				this.scaleRow(pivot.y, -scalar);
    			else if(this.mMatrix[pivot.y][pivot.x] > 0.0f)
    				this.scaleRow(pivot.y, scalar);
    		}
    		
    		//Step 3:
    		for(int b=pivot.y+1; b<this.getHeight(); b++)
    		{
    			if(this.mMatrix[b][pivot.x] != 0.0f)
    			{
    				float scalar = (this.mMatrix[b][pivot.x]/this.mMatrix[pivot.y][pivot.x]);
    				
    				if(this.mMatrix[b][pivot.x] < 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, -scalar, b);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, scalar, b);
    				}

    				else if(this.mMatrix[b][pivot.x] > 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, -scalar, b);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, scalar, b);
    				}
    			}
    			else
    				continue;
    		}
    		
    		if(this.mMatrix[pivot.y][pivot.x] != 1.0f)
    		{
    			float scalar = 1/(this.mMatrix[pivot.y][pivot.x]);
    			
    			if(this.mMatrix[pivot.y][pivot.x] < 0.0f)
    				this.scaleRow(pivot.y, -scalar);
    			else if(this.mMatrix[pivot.y][pivot.x] > 0.0f)
    				this.scaleRow(pivot.y, scalar);
    		}
    		
    		this.regulateREF(pivot);
    		
    		//Step 5:
    		for(int c=0; c<pivot.y; c++)
    		{
    			if(this.mMatrix[c][pivot.x] != 0.0f)
    			{
    				float scalar = (this.mMatrix[c][pivot.x]/this.mMatrix[pivot.y][pivot.x]);
    				
    				if(this.mMatrix[c][pivot.x] < 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, -scalar, c);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, scalar, c);
    				}

    				else if(this.mMatrix[c][pivot.x] > 0.0f)
    				{
    					if((scalar*this.mMatrix[pivot.y][pivot.x]) > 0.0f)
    						this.replacementRow(pivot.y, -scalar, c);
    					else if((scalar*this.mMatrix[pivot.y][pivot.x]) < 0.0f)
    						this.replacementRow(pivot.y, scalar, c);
    				}
    			}
    			else
    				continue;
    		}
    		
    		//Step 4:
    		if(submatrix < pivot.x)
        		submatrix = pivot.x;
        	submatrix++;
        	if(submatrix == this.getLength())
        		break;
    	}	
    }
}
