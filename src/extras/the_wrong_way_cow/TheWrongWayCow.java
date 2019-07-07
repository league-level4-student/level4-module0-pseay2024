//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.ArrayList;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
        ArrayList<int[]> h = getHorizontal(field);
        ArrayList<int[]> bh = getBackwardsHorizontal(field);
        ArrayList<int[]> v = getVerticle(field);
        ArrayList<int[]> bv = getBackwardsVerticle(field);
        int[] r = new int[2];
        if (h.size() == 1) r = h.get(0);
        if (bh.size() == 1) r = h.get(0);
        if (v.size() == 1) r = h.get(0);
        if (bv.size() == 1) r = h.get(0);
        return r;
    }
    private static ArrayList<int[]> getHorizontal(char[][] cs)
    {
    	ArrayList<int[]> cows = new ArrayList<int[]>();
    	for (int i = 0; i < cs.length - 2; i++)
    	{
    		for (int j = 0; j < cs.length - 2; j++)
    		{
    			char c = cs[i][j];
    			char o = cs[i+1][j+1];
    			char w = cs[i+2][j+2];
    			if (c == 'c' && o =='o' && w=='w')
    			{
    				int[] cow = {i,j};
    				cows.add(cow);
    			}
    		}
    	}
    	return cows;
    }
    private static ArrayList<int[]> getBackwardsHorizontal(char[][] cs)
    {
    	ArrayList<int[]> cows = new ArrayList<int[]>();
    	for (int i = cs.length - 2; i >= 0; i--)
    	{
    		for (int j = cs.length - 2; j >= 0; j--)
    		{
    			char c = cs[i][j];
    			char o = cs[i+1][j+1];
    			char w = cs[i+2][j+2];
    			if (c == 'c' && o =='o' && w=='w')
    			{
    				int[] cow = {i,j};
    				cows.add(cow);
    			}
    		}
    	}
    	return cows;
    }
    private static ArrayList<int[]> getVerticle(char[][] cs)
    {
    	ArrayList<int[]> cows = new ArrayList<int[]>();
    	for (int j = 0; j >= cs.length - 2; j--)
    	{
    		for (int i = 0; i >= cs.length-2; i--)
    		{
    			char c = cs[i][j];
    			char o = cs[i+1][j+1];
    			char w = cs[i+2][j+2];
    			if (c == 'c' && o =='o' && w=='w')
    			{
    				int[] cow = {i,j};
    				cows.add(cow);
    			}
    		}
    	}
    	return cows;
    }
    private static ArrayList<int[]> getBackwardsVerticle(char[][] cs)
    {
    	ArrayList<int[]> cows = new ArrayList<int[]>();
    	for (int j = cs.length - 2; j >= 0; j--)
    	{
    		for (int i = cs.length - 2; i >= 0; i--)
    		{
    			char c = cs[i][j];
    			char o = cs[i+1][j+1];
    			char w = cs[i+2][j+2];
    			if (c == 'c' && o =='o' && w=='w')
    			{
    				int[] cow = {i,j};
    				cows.add(cow);
    			}
    		}
    	}
    	return cows;
    }
}
