package tests;

import org.junit.*;

import reversi.Piece;
import reversi.Reversi;
import static org.junit.Assert.*;

public class StudentTests {
	
  //tests validMove() when no valid move is available
  @Test public void TestNotrap(){
	  Reversi rev = new Reversi();
	 
	  rev.setSquare(6, 2, Piece.WHITE);
	  rev.setSquare(7, 2, Piece.WHITE);		//Checks no trap when reaching border
	  rev.setSquare(7, 3, Piece.WHITE);		//Checks no trap when reaching border
	  rev.setSquare(7, 4, Piece.WHITE);		//Checks no trap when reaching border
	  rev.setSquare(5, 3, Piece.WHITE);		//Checks no trap when reaching border
	  rev.setSquare(6, 4, Piece.WHITE);		//checks no trap
	  assertFalse(rev.validMove(6, 3, Piece.BLACK));
	  
  }
  
  //Test what that starting on the border does not mess with validMove
  @Test public void onBorder(){
	  Reversi rev = new Reversi();
		 
	  
	  assertFalse(rev.validMove(7,7, Piece.BLACK));		//In top right corner
	  assertFalse(rev.validMove(7,0, Piece.BLACK));		//In top left corner
	  assertFalse(rev.validMove(0,0, Piece.BLACK));		//In bot left corner
	  assertFalse(rev.validMove(0,7, Piece.BLACK));		//In bot right corner
	  assertFalse(rev.validMove(5,7, Piece.BLACK));		//on right border
	  assertFalse(rev.validMove(7,2, Piece.BLACK));		//In left border
	  assertFalse(rev.validMove(5,7, Piece.BLACK));		//on right border
	  assertFalse(rev.validMove(2,7, Piece.BLACK));		//In left border
  }
  
  
  //Test wheather it will flip multiple pieces on one turn correctly in the corner
  @Test public void MultiFlip(){
	  Reversi reversi= new Reversi();

	    reversi.reset(Piece.BLACK);

	    reversi.setSquare(7, 5, Piece.BLACK);
	    reversi.setSquare(4, 7, Piece.BLACK);
	    reversi.setSquare(2, 0, Piece.BLACK);
	    reversi.setSquare(6,4,Piece.WHITE);
	    reversi.setSquare(5,3,Piece.WHITE);
	    reversi.setSquare(4,0,Piece.WHITE);
	    reversi.setSquare(4,1,Piece.WHITE);
	    reversi.setSquare(4,3,Piece.WHITE);
	    reversi.setSquare(4,4,Piece.WHITE);
	    reversi.setSquare(4,5,Piece.WHITE);
	    reversi.setSquare(4,6,Piece.WHITE);
	    reversi.setSquare(3,1,Piece.WHITE);
	    /* 
	     This is board before we call move at location p
	     "7 - - - - - b - -\n" +	     
         "6 - - - - w - - -\n" +
         "5 - - - w - - - -\n" +
         "4 w w p w w w w b\n" +
         "3 - w - b w - - -\n" +
         "2 b - - - - - - -\n" +
         "1 - - - - - - - -\n" +
         "0 - - - - - - - -\n" +
         "  0 1 2 3 4 5 6 7\n"
         */
	    reversi.move(4, 2, Piece.BLACK);
	    assertEquals("7 - - - - - b - -\n" +
                     "6 - - - - b - - -\n" +
                     "5 - - - b - - - -\n" +
                     "4 w w b b b b b b\n" +
                     "3 - b - b w - - -\n" +
                     "2 b - - - - - - -\n" +
                     "1 - - - - - - - -\n" +
                     "0 - - - - - - - -\n" +
                     "  0 1 2 3 4 5 6 7\n", reversi.toString());
	   
  }
  
//Test wheather it will flip multiple pieces on one turn correctly
  @Test public void MultiFlipCorner(){
	  Reversi reversi= new Reversi();

	    reversi.reset(Piece.BLACK);

	    reversi.setSquare(4, 0, Piece.BLACK);
	    reversi.setSquare(4, 4, Piece.BLACK);
	 
	    reversi.setSquare(3,0,Piece.WHITE);
	    reversi.setSquare(2,0,Piece.WHITE);
	    reversi.setSquare(1,0,Piece.WHITE);
	    reversi.setSquare(1,1,Piece.WHITE);
	    reversi.setSquare(2,2,Piece.WHITE);
	   
	    
	
	    /* 
	     This is board before we call move at location p
	     "7 - - - - - - - -\n" +
         "6 - - - - - - - -\n" +
         "5 - - - - - - - -\n" +
         "4 b - - w b - - -\n" +
         "3 w - - b w - - -\n" +
         "2 w - w - - - - -\n" +
         "1 w w - - - - - -\n" +
         "0 p - - - - - - -\n" +
         "  0 1 2 3 4 5 6 7\n",
         */
	    reversi.move(0, 0, Piece.BLACK);
	    
	    assertEquals("7 - - - - - - - -\n" +
                     "6 - - - - - - - -\n" +
                     "5 - - - - - - - -\n" +
                     "4 b - - w b - - -\n" +
                     "3 b - - b w - - -\n" +
                     "2 b - b - - - - -\n" +
                     "1 b b - - - - - -\n" +
                     "0 b - - - - - - -\n" +
                     "  0 1 2 3 4 5 6 7\n", reversi.toString());
	   
  }

}

 