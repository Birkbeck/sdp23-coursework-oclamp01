package sml;

// TODO: write a JavaDoc for the class - COMPLETE

/**
 * Represents an abstract instruction. Contains fields for String label, String opcode and a static constant
 * NORMAL_PROGRAM_COUNTER_UPDATE which is returned in instruction subclasses to indicate non-branching
 * instructions. This class is extended in seven instructions in folder "instructions", which
 * implement methods for executing the instruction, toString(), equals() and hashCode(). A constructor with label and
 * opcode parameters is provided and extended (using super keyword) in the instruction subclasses. Additionally,
 * methods for getting labels and opcodes in a given instruction are provided, as well as for getting the String
 * representation of the label.
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	/**
	 * @return instruction's label (either null or non-whitespace sequence of characters)
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return instruction's three-letter opcode
	 */
	public String getOpcode() {
		return opcode;
	}

	// Static constant to indicate non-branching instruction
	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);

	/**
	 * @return empty string if getLabel() returns null, otherwise label followed by ": "
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean? - COMPLETE
	// An abstract method declaration means that a given method is not implemented; because
	// the Instruction class contains abstract methods, it is itself an abstract class.
	// For a subclass to be non-abstract, all abstract methods must be implemented. In the case of toString,
	// all Instruction subclasses will override this method in different ways, with only the return type (String)
	// and parameter list common to all subclasses.
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine) - COMPLETE
	// TODO: Fully implement and test all equals and hashCode implementations - COMPLETE
	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
}
