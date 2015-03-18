package reversi;

public enum Piece {

  BLACK, WHITE, NONE;

  public boolean isBlack() {
    return this == BLACK;
  }

  public boolean isWhite() {
    return this == WHITE;
  }

  public boolean isNone() {
    return this == NONE;
  }

  public boolean isSame(Piece piece) {
    return this == piece;
  }

  public String toString() {
    switch (this) {
      case BLACK:
        return "b";
      case WHITE:
        return "w";
      case NONE:
        return "-";
    }

    return "-";  // make compiler happy even though we should never reach here
  }

}
