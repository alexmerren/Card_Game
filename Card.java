public class Card {

	// Attributes
	private int value;

	// Constructors
	Card(int value) {
		this.value = value;
	}

	// Getters
	public int getValue() {
		return value;
	}

	// Setters
	public void setValue(int newValue) {
		this.value = newValue;
	}

	// Auxiliary Methods
	public boolean isPreferredCard(Player player) {
		return (value == player.getPreferredNumber());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card c = (Card) o;
		return (value == c.value);
	}

	@Override
	public String toString() {
		String str = String.format("%d", value);
		return str;
	}

}
