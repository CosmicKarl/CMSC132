package tests;

import reversi.Piece;
import reversi.Reversi;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests that the get/set methods are working, since other methods may
  // rely upon them.
  @Test public void testPublic1() {
    Reversi reversi= new Reversi();

    reversi.setSquare(3, 3, Piece.WHITE);
    reversi.setSquare(3, 4, Piece.BLACK);

    assertEquals(Piece.WHITE, reversi.getSquare(3, 3));
    assertEquals(Piece.BLACK, reversi.getSquare(3, 4));
  }

  // Tests calling setSquare() on a square that is off the board (too far to
  // the right), which should result in a a NoSuchElementException.
  @Test(expected=NoSuchElementException.class) public void testPublic2() {
    Reversi reversi= new Reversi();

    reversi.setSquare(10, 4, Piece.WHITE);
  }

  // Tests the result of the count() method.
  @Test public void testPublic3() {
    Reversi reversi= new Reversi();

    reversi.setSquare(5, 2, Piece.BLACK);
    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(2, 5, Piece.WHITE);
    reversi.setSquare(1, 6, Piece.BLACK);
    reversi.setSquare(2, 6, Piece.BLACK);
    reversi.setSquare(3, 7, Piece.BLACK);

    assertEquals(4, reversi.count(Piece.BLACK));
    assertEquals(2, reversi.count(Piece.WHITE));
  }

  // Tests that after reset() is called the starting turn is black's.
  @Test public void testPublic4() {
    Reversi reversi= new Reversi();

    reversi.reset(Piece.BLACK);

    assertEquals(Piece.BLACK, reversi.getTurn());
  }

  // Tests the basic operation of toString().
  @Test public void testPublic5() {
    Reversi reversi= new Reversi();

    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(4, 4, Piece.BLACK);
  
    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" +
                 "4 - - - w b - - -\n" +
                 "3 - - - - - - - -\n" +
                 "2 - - - - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
  }


  // Tests the validity of a move that should trap one opposing piece, in a
  // horizontal direction.
  @Test public void testPublic6() {
    Reversi reversi= new Reversi();

    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(4, 4, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    assertTrue(reversi.validMove(4, 2, Piece.BLACK));
  }

  // Tests the validity of a move that should trap several opposing pieces,
  // in a vertical direction.
  @Test public void testPublic7() {
    Reversi reversi= new Reversi();

    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(3, 3, Piece.BLACK);
    reversi.setSquare(2, 3, Piece.BLACK);

    reversi.setTurn(Piece.WHITE);

    assertTrue(reversi.validMove(1, 3, Piece.WHITE));
  }

  // Tests the validity of a move that should trap several opposing pieces,
  // in more than one diagonal direction (note that this board
  // configuration, and some other ones in later tests, cannot come up
  // during actual play of the game).
  @Test public void testPublic8() {
    Reversi reversi= new Reversi();

    reversi.setSquare(2, 2, Piece.BLACK);
    reversi.setSquare(3, 3, Piece.WHITE);
    reversi.setSquare(6, 5, Piece.WHITE);
    reversi.setSquare(7, 6, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    assertTrue(reversi.validMove(4, 4, Piece.BLACK));
  }

  // Tests an invalid move to a square that is already occupied.
  @Test public void testPublic9() {
    Reversi reversi= new Reversi();

    reversi.setSquare(3, 3, Piece.WHITE);
    reversi.setSquare(3, 4, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    assertFalse(reversi.validMove(3, 4, Piece.BLACK));
  }

  // Tests an invalid move to a square that is off the board.
  @Test public void testPublic10() {
    Reversi reversi= new Reversi();

    reversi.setSquare(3, 3, Piece.WHITE);
    reversi.setSquare(3, 4, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    assertFalse(reversi.validMove(10, 20, Piece.BLACK));
  }

  // Tests whether the reset() method properly resets the game board.
  @Test public void testPublic11() {
    Reversi reversi= new Reversi();

    reversi.setSquare(5, 2, Piece.BLACK);
    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(2, 5, Piece.WHITE);
    reversi.setSquare(1, 6, Piece.BLACK);
    reversi.setSquare(2, 6, Piece.BLACK);
    reversi.setSquare(3, 7, Piece.BLACK);

    reversi.reset(Piece.WHITE);

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" +
                 "4 - - - w b - - -\n" +
                 "3 - - - b w - - -\n" +
                 "2 - - - - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
    assertEquals(Piece.WHITE, reversi.getTurn());
  }

  // Tests whether a basic move, that just traps just one opposing piece, in
  // the horizontal right direction, is performed correctly.
  @Test public void testPublic12() {
    Reversi reversi= new Reversi();

    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(4, 4, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    reversi.move(4, 2, Piece.BLACK);

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" +
                 "4 - - b b b - - -\n" +
                 "3 - - - - - - - -\n" +
                 "2 - - - - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
    assertEquals(Piece.WHITE, reversi.getTurn());
  }

  // Tests whether a move that should trap several opposing pieces, in the
  // vertical up direction, is performed correctly.
  @Test public void testPublic13() {
    Reversi reversi= new Reversi();

    reversi.setSquare(4, 3, Piece.WHITE);
    reversi.setSquare(3, 3, Piece.BLACK);
    reversi.setSquare(2, 3, Piece.BLACK);

    reversi.setTurn(Piece.WHITE);

    reversi.move(1, 3, Piece.WHITE);

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" +
                 "4 - - - w - - - -\n" +
                 "3 - - - w - - - -\n" +
                 "2 - - - w - - - -\n" +
                 "1 - - - w - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
    assertEquals(Piece.BLACK, reversi.getTurn());
  }

  // Tests whether a move that should trap several opposing pieces, in more
  // than one diagonal direction (up and right, and down and left), is
  // performed correctly.
  @Test public void testPublic14() {
    Reversi reversi= new Reversi();

    reversi.setSquare(2, 2, Piece.BLACK);
    reversi.setSquare(3, 3, Piece.WHITE);
    reversi.setSquare(5, 5, Piece.WHITE);
    reversi.setSquare(6, 6, Piece.BLACK);

    reversi.setTurn(Piece.BLACK);

    reversi.move(4, 4, Piece.BLACK);

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - b -\n" +
                 "5 - - - - - b - -\n" +
                 "4 - - - - b - - -\n" +
                 "3 - - - b - - - -\n" +
                 "2 - - b - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
    assertEquals(Piece.WHITE, reversi.getTurn());
  }

  // Tests that an invalid move does not alter the board.
  @Test public void testPublic15() {
    Reversi reversi= new Reversi();

    reversi.reset(Piece.BLACK);

    reversi.move(3, 4, Piece.BLACK);

    assertFalse(reversi.validMove(3, 4, Piece.BLACK));

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - - - - -\n" +
                 "5 - - - - - - - -\n" +
                 "4 - - - w b - - -\n" +
                 "3 - - - b w - - -\n" +
                 "2 - - - - - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
  }

  // Tests that an invalid move does not change the turn.
  @Test public void testPublic16() {
    Reversi reversi= new Reversi();

    reversi.reset(Piece.BLACK);

    reversi.move(3, 4, Piece.BLACK);

    assertFalse(reversi.validMove(3, 4, Piece.BLACK));
    assertEquals(Piece.BLACK, reversi.getTurn());
  }

  // Tests whether several valid moves in sequence, in different directions,
  // are all performed correctly.
  @Test public void testPublic17() {
    Reversi reversi= new Reversi();

    reversi.reset(Piece.BLACK);

    reversi.move(4, 2, Piece.BLACK);
    reversi.move(5, 4, Piece.WHITE);
    reversi.move(3, 5, Piece.BLACK);
    reversi.move(4, 1, Piece.WHITE);
    reversi.move(6, 4, Piece.BLACK);
    reversi.move(2, 3, Piece.WHITE);
    reversi.move(4, 0, Piece.BLACK);
    reversi.move(5, 3, Piece.WHITE);

    assertEquals("7 - - - - - - - -\n" +
                 "6 - - - - b - - -\n" +
                 "5 - - - w b - - -\n" +
                 "4 b b b w b - - -\n" +
                 "3 - - - w b b - -\n" +
                 "2 - - - w - - - -\n" +
                 "1 - - - - - - - -\n" +
                 "0 - - - - - - - -\n" +
                 "  0 1 2 3 4 5 6 7\n", reversi.toString());
    assertEquals(Piece.BLACK, reversi.getTurn());
  }

}
