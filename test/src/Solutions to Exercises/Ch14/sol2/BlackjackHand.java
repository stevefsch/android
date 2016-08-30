import java.util.Vector;
public class BlackjackHand
{
	// Add a card to the hand
	public BlackjackHand add(Card card) {
		hand.add(card);
		return this;
	}

  // Calculates the value of the hand. 
  // Ace is counted as 10 unless this will cause the value to exceed 21
	public int value() {
    int handValue = 0;
    int cardValue = 0;
    for(int i = 0 ; i< hand.size() ; i++) {
      cardValue = ((Card)(hand.elementAt(i))).getValue();
      handValue += cardValue >= 10 ? 10 : cardValue;
    }
    if(hasAce() && handValue < 12)
      handValue += 10;
      
    return handValue;

	}

  // Tests for blackjack
  public boolean isBlackjack() {
    return (hand.size() == 2 && hasAce() && hasTen());
  }

  // Calculates whether the value of a hand exceeds 21
  public boolean isBust() {
    return value() > 21;
  }

  // Tests for an Ace in the hand
  public boolean hasAce() {
    for(int i = 0 ; i< hand.size() ; i++)
      if(((Card)(hand.elementAt(i))).getValue()==Card.ACE)
        return true;

    return false;
  }

  // Tests for a 10, J, Q, or K in the hand
  public boolean hasTen() {
    for(int i = 0 ; i< hand.size() ; i++)
      if(((Card)(hand.elementAt(i))).getValue() >= 10)
        return true;

    return false;
  }
  

  public String toString() {
    StringBuffer str = new StringBuffer();
    for(int i = 0 ; i< hand.size() ; i++)
      str.append(((Card)(hand.elementAt(i))).toString()).append(' ');
    return str.toString();

  }

	private Vector hand = new Vector();			// Stores the cards in the hand
}
