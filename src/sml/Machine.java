package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.lang.reflect.*;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 8 registers and methods to access and change them.
 *
 */
public final class
Machine {

	private final Labels labels = new Labels();

	private final List<Instruction> program = new ArrayList<>();

	private final Registers registers;

	// The program counter; it contains the index (in program)
	// of the next instruction to be executed.
	private int programCounter = 0;

	/**
	 * @param registers
	 */
	public Machine(Registers registers) {
		this.registers = registers;
	}

	/**
	 * Execute the program in program, beginning at instruction 0.
	 * Precondition: the program and its labels have been stored properly.
	 */
	public void execute() {
		programCounter = 0;
		registers.clear();
		while (programCounter < program.size()) {
			Instruction ins = program.get(programCounter);
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}

	/**
	 * @return this machine's labels (HashMap<String>, <Integer>)
	 */
	public Labels getLabels() {
		return this.labels;
	}

	/**
	 * @return this machine's program (ArrayList<Instruction>)
	 */
	public List<Instruction> getProgram() {
		return this.program;
	}

	/**
	 * @return this machine's registers (HashMap<String>, <Integer>)
	 */
	public Registers getRegisters() {
		return this.registers;
	}


	/**
	 * String representation of the program under execution.
	 *
	 * @return pretty formatted version of the code.
	 */
	@Override
	public String toString() {
		return program.stream()
				.map(Instruction::toString)
				.collect(Collectors.joining("\n"));
	}

	// TODO: use pattern matching for instanceof - COMPLETE
	// https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
	/**
	 * @param o the object being compared for equality
	 * @return true if o is an object of type Machine and labels, programs, registers and programCounter are all
	 * equal, and false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine other) {
			// Machine other = (Machine) o; // Casting of o to Machine unnecessary if Object o is of Machine type,
											// else return false
			return Objects.equals(this.labels, other.labels)
					&& Objects.equals(this.program, other.program)
					&& Objects.equals(this.registers, other.registers)
					&& this.programCounter == other.programCounter;
		}
		return false;
	}

	/**
	 * @return int hash of array of fields of machine
	 */
	@Override
	public int hashCode() {
		return Objects.hash(labels, program, registers, programCounter);
	}
}
