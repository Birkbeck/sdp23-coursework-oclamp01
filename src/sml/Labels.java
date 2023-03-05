package sml;

import sml.instruction.AddInstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class - COMPLETE

/**
 * Represents labels (if any) in a given program. Internally represented as a HashMap of String label values
 * and Integer addresses in memory. Class contains methods for adding non-null labels, getting addresses
 * associated with a given label (and checks to avoid null pointers), clearing the labels, as well as
 * toString(), equals() and hashCode() methods.
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates. - COMPLETE
		// Simple if statement to check whether labels HashMap already contains key
		// referred to by String label
		if (!labels.containsKey(label)) {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) throws NullPointerException {
		// TODO: Where can NullPointerException be thrown here?
		//       Add code to deal with non-existent labels.
		//       A NullPointerException may be thrown if a label in a program's JnzInstruction does not match
		//		 an instruction in the program. E.g. if there is no instruction with label "f3" where there is
		//		 also an instruction "jnz EAX f3"
		try {
			return labels.get(label);
		}
		catch (NullPointerException e) {
			System.out.println
					("NullPointerException thrown because label does not match an instruction in the program");
			return -1;
		}
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers). - COMPLETE
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine). - COMPLETE
	/**
	 * @param o the object being tested for equality
	 * @return true if Object o of type Labels and mappings are equal between this and o, and false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		// Note use of pattern matching to avoid intermediate casting step
		if (o instanceof Labels obj) {
			return labels.equals(obj.labels);
		}
		return false;
	}

	/**
	 * @return hashCode of HashMap labels. Note that .hashCode() is used instead of Objects.hash() (as in instruction
	 * subclasses) due to single object reference
	 */
	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
