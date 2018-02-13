package hw5;

public class HashKey {
	
	private Integer number;
	private String text;

	public HashKey(String text, int number) {
		
		this.text = text;
		this.number = number;
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return String.format("Text: %s , Number: %d", text,number);
	}
	
	public int hashCode(String text, int number) {
		int h = 17;
		for (int i = 0; i < text.length(); i++) {
			h = text.charAt(i) + 31 * h;
		}
		h = number + 31 * h;
		return h;
	}
	
	public boolean equals(Object x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		HashKey that = (HashKey) x;
		if (!((this.number == that.number) && (this.text == that.text))) return false;
		return true;
	}
}
