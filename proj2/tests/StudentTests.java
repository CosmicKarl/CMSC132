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

  // Tests that the copy constructor works.
  @Test public void copyConstcret1() throws Exception {
    Reversi otherGame= new Reversi(), otherGame2= new Reversi(),
            otherGame3= new Reversi(), reversi, reversi2, reversi3;
    int i, j;

    // empty board scenario
    reversi= new Reversi(otherGame);
    assertNotNull(reversi);
    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" + 
                 "4 - - - - - - - -\n" +
                 "3 - - - - - - - -\n" +
                 "2 - - - - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());

    // full black board scenario
    otherGame2= new Reversi();
    for (i= 0; i < 8; i++)
      for (j= 0; j < 8; j++)
        otherGame2.setSquare(i, j, Piece.BLACK);
    reversi2= new Reversi(otherGame2);
    assertEquals("7 b b b b b b b b\n" +
                 "6 b b b b b b b b\n" +
                 "5 b b b b b b b b\n" +
                 "4 b b b b b b b b\n" +
                 "3 b b b b b b b b\n" +
                 "2 b b b b b b b b\n" +
                 "1 b b b b b b b b\n" +
                 "0 b b b b b b b b\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi2.toString());

    // board with some black scenario
    otherGame3.setSquare(5, 2, Piece.BLACK);
    otherGame3.setSquare(4, 3, Piece.WHITE);
    otherGame3.setSquare(2, 5, Piece.WHITE);
    otherGame3.setSquare(1, 6, Piece.BLACK);
    otherGame3.setSquare(2, 6, Piece.BLACK);
    otherGame3.setSquare(3, 7, Piece.BLACK);
    reversi3= new Reversi(otherGame3);
    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - b - - - - -\n" +
                 "4 - - - w - - - -\n" +
                 "3 - - - - - - - b\n" +
                 "2 - - - - - w b -\n" +
                 "1 - - - - - - b -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi3.toString());
  }

  
  // Tests calling setTurn() with Piece.NONE, which should result in a
  // IllegalArgumentException.
  @Test(expected= IllegalArgumentException.class) public void testSecret4() {
    Reversi reversi= new Reversi();

    reversi.setTurn(Piece.NONE);
  }

  
 

  // Tests that the count() method counts all of the squares on the board.
  @Test public void countTest() {
    Reversi reversi= new Reversi();
    int i, j;

    // half black, half white board
    for (i= 0; i < 8; i++)
      for (j= 0; j < 8; j++)
        reversi.setSquare(i, j, i > 3 ? Piece.BLACK : Piece.WHITE);

    assertEquals(32, reversi.count(Piece.BLACK));
    assertEquals(32, reversi.count(Piece.WHITE));
  }

  

  // Tests that the validMove() method returns false for moves that don't
  // trap any opposing pieces.
  @Test public void testSecret9() {
    Reversi reversi= new Reversi();
    int i, j;

    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          reversi.setSquare(i, j, Piece.BLACK);

    assertFalse(reversi.validMove(3, 3, Piece.WHITE));
  }

 

  // Tests that the validMove() method recognizes moves in all directions.
  @Test public void testSecret11() {
    Reversi reversi= new Reversi();
    int i, j;

    reversi.setSquare(3, 3, Piece.WHITE);
    // a black ring surrounding the white piece at (3, 3)
    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          reversi.setSquare(i, j, Piece.BLACK);

    // checking validMove() for white pieces in the eight squares that
    // should cause a flip
    for (i= 1; i <= 5; i += 2)
      for (j= 1; j <= 5; j += 2)
        if (i != 3 || j != 3)
          assertTrue(reversi.validMove(i, j, Piece.WHITE));
  }

  // Tests that the move() method flips pieces in all directions.
  @Test public void flipAllDir() {
    Reversi reversi= new Reversi(), reversi2= new Reversi();
    int i, j;

    reversi.setSquare(3, 3, Piece.WHITE);
    // a black ring surrounding the white piece at (3, 3)
    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          reversi.setSquare(i, j, Piece.BLACK);

    // putting white pieces in the eight squares that should cause a flip
    for (i= 1; i <= 5; i += 2)
      for (j= 1; j <= 5; j += 2)
        if (i != 3 || j != 3) {
          reversi.setTurn(Piece.WHITE);
          reversi.move(i, j, Piece.WHITE);
        }

    // checking the squares that should have been flipped
    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          assertTrue(reversi.getSquare(i, j).isWhite());

    // the same operation again, just switching the colors

    reversi2.setSquare(3, 3, Piece.BLACK);
    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          reversi2.setSquare(i, j, Piece.WHITE);

    for (i= 1; i <= 5; i += 2)
      for (j= 1; j <= 5; j += 2)
        if (i != 3 || j != 3) {
          reversi2.setTurn(Piece.BLACK);
          reversi2.move(i, j, Piece.BLACK);
        }

    for (i= 2; i < 5; i++)
      for (j= 2; j < 5; j++)
        if (i == 2 || i == 4 || j == 2 || j == 4)
          assertTrue(reversi2.getSquare(i, j).isBlack());
  }
}