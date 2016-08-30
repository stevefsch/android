// Class defining a card. This is a little different to the version  the chapter 13.  

class Card {
	// Constructor:
	public Card(int value, int suit) {
		assert (value>=ACE && value<=KING) && (suit>=HEARTS && suit<=SPADES);
		this.value=value;
		this.suit=suit;
	}

  // Constructor for a MARKER card
  // This is not really a default constructor so we have a parameter that is not used
  // so as to differentiate it from the default constructor that has no arguments. 
  // The parameter must be specified when this constructor is called.
  // The constructor is private because it is only used inside the class.
  private Card(int markerValue) {
    value = MARKER;
  }

  public boolean isMarker() {
    return value == -1;
  }

	// Return the value of a card
	public int getValue() {
    return value;  
  }

	// Method to display a card:
	public String toString() {
	 String valueStr = null;
    switch(value) {
      case ACE:
        valueStr = "A";
        break;
      case JACK:
        valueStr = "J";
        break;
      case QUEEN:
        valueStr = "Q";
        break;
      case KING:
        valueStr = "K";
        break;
      case MARKER:
        valueStr = " MARKER";
        break;
      default:
        valueStr = String.valueOf(value);
        break;
    }

    char suitChar = ' ';  
		switch(suit) {
			case CLUBS:
        suitChar = 'C';
				break;
			case DIAMONDS:
        suitChar = 'D';
        break;
      case HEARTS:
        suitChar = 'H';
        break;
      case SPADES:
        suitChar = 'S';
            break;
		}
    StringBuffer cardStr = new StringBuffer();
    return cardStr.append(valueStr).append(suitChar).toString();
	}

  // Suit values
  public static final int HEARTS=0;
  public static final int CLUBS=1;
  public static final int DIAMONDS=2;
  public static final int SPADES=3;

  // Card face values   
  public static final int ACE=1;    
  public static final int JACK=11;
  public static final int QUEEN=12;
  public static final int KING=13;
  public static final int MARKER = -1;  // Marker card value

  // Definition of a marker card
  public static final Card MARKER_CARD = new Card(MARKER);

  // Members defining the suit and value of a Card instance
  private int suit = 0;
  private int value = 0;

}
