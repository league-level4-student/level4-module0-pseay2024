package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Cell c = maze.cells[new Random().nextInt(width)][new Random().nextInt(height)];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(c);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
	    ArrayList<Cell> neighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if (neighbors.size() > 0)
		{
			//C1. select one at random.
			Cell c = neighbors.get(new Random().nextInt(neighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(c);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, c);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = c;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(c);
		}	
		//D. if all neighbors are visited
		if (getUnvisitedNeighbors(currentCell).size() == 0)
		{
			//D1. if the stack is not empty
			if (uncheckedCells.size() != 0)
			{
				// D1a. pop a cell from the stack
				Cell c = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = c;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}	
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2)
	{
		if (getUnvisitedNeighbors(c1).contains(c2))
		{
			int x1 = c1.getX();
			int y1 = c1.getY();
			int x2 = c2.getX();
			int y2 = c2.getY();
		    if (x1 - x2 == -1)
		    {
		    	//right wall
		    	c1.setEastWall(false);
		    	c2.setWestWall(false);
		    }
		    else if (x1 - x2 == 0)
		    {
		    	//upper or lower wall
		    	if (y1 - y2 == 1)
		    	{
		    		// upper wall
		    		c1.setNorthWall(false);
		    		c2.setSouthWall(false);
		    		
		    	}
		    	else
		    	{
		    		// lower wall
		    		c1.setSouthWall(false);
		    		c2.setNorthWall(false);
		    	}
		    }
		    else
		    {
		    	//left wall
		    	c1.setWestWall(false);
		    	c2.setEastWall(false);
		    }
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> maybeCells = new ArrayList<Cell>();
	    if (y-1 >= 0) maybeCells.add(maze.getCell(x, y-1));
		if (y+1 < maze.getHeight()) maybeCells.add(maze.getCell(x, y+1));
		if (x-1 >= 0) maybeCells.add(maze.getCell(x-1, y));
		if (x+1 < maze.getWidth()) maybeCells.add(maze.getCell(x+1, y));
		for (Cell ce : maybeCells)
		{
			if (ce.hasBeenVisited() == false)
			{
				cells.add(ce);
			}
		}
		return cells;
	}
}
