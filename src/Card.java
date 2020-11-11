public class Card {

	private int value;

	Card(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * This is a method that can compare two Card together to see if their values are equal.
	 *
	 * @param obj  The object that you are comparing with
	 * @return if the values are equal, true. False if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Card c = (Card) obj;
		return (value == c.value);
	}

	/**
	 * This returns the contents of the value attribute in string form.
	 *
	 * @return  The string form of the Card's value attribute
	 */
	@Override
	public String toString() {
		return String.format("%d", value);
	}
}
