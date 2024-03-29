package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	int width;
	int height;
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;//50
		width = w/cpr;
		height = h/cpr;
		//2. Calculate the cell size. >>>10<<<
		cellSize = (w <= h) ? w/cpr : h/cpr;
		//3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];//50 by 50
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for (int x = 0; x < cpr; x++)
		{
			for (int y = 0; y < cpr; y++)
			{
				cells[x][y] = new Cell(x, y, cellSize);
			}
		}
		System.out.println("Done");
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive member to true of false
		for (int x = 0; x < cellsPerRow; x++)
		{
			for (int y = 0; y < cellsPerRow; y++)
			{
				cells[x][y].isAlive = (new Random().nextInt(2) == 0) ? false : true;
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for (int x = 0; x < cellsPerRow; x++)
		{
			for (int y = 0; y < cellsPerRow; y++)
			{
				cells[x][y].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for (int x = 0; x < cellsPerRow; x++)
		{
			for (int y = 0; y < cellsPerRow; y++)
			{
				cells[x][y].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int x = 0; x < cellsPerRow; x++)
		{
			for (int y = 0; y < cellsPerRow; y++)
			{
				livingNeighbors[x][y] = getLivingNeighbors(x,y);
			}
		}
		//8. check if each cell should live or die
		for (int x = 0; x < cellsPerRow; x++)
		{
			for (int y = 0; y < cellsPerRow; y++)
			{
				cells[x][y].liveOrDie(livingNeighbors[x][y]);
			}
		}
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int n = 0;
		boolean pxGood = (x+1 < width);
		boolean nxGood = (x-1 >= 0);
		boolean pyGood = (y+1 < width);
		boolean nyGood = (y-1 >= 0);
		if (pxGood && pyGood && cells[x+1][y+1].isAlive)n++;
		if (pxGood && cells[x+1][y].isAlive)n++;
		if (pxGood && nyGood && cells[x+1][y-1].isAlive)n++;
		if (pyGood && cells[x][y+1].isAlive)n++;
		if (nyGood && cells[x][y-1].isAlive)n++;
		if (nxGood && pyGood && cells[x-1][y+1].isAlive)n++;
		if (nxGood && cells[x-1][y].isAlive)n++;
		if (nxGood && nyGood && cells[x-1][y-1].isAlive)n++;
		return n;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		int x = e.getX()/cellSize;
		int y = e.getY()/cellSize;
		cells[x][y].isAlive=!cells[x][y].isAlive;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
