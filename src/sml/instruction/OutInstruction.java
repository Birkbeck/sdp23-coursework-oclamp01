package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Subclass of Instruction abstract class used for performing print operations to the console.
 * Contains single result field for register operand containing value to be printed, and static OP_CODE field to
 * enable execution in switch statement in Machine class.
 */
public class OutInstruction extends Instruction {
    private final RegisterName result;

    public static final String OP_CODE = "out";

    /**
     * @param label (optional) label of instruction
     * @param result register containing value to be printed to the console
     */
    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    /**
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that this is not a branch instruction
     * Name of register and value printed to console
     */
    @Override
    public int execute(Machine m) {
        int output = m.getRegisters().get(result);
        System.out.println("Value of register " + result + ": " + output);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * @return label (if applicable) + OP_CODE ("out") + name of result register (e.g. "out ESP")
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutInstruction that)) return false;
        return result.equals(that.result); // TODO
    }

    @Override
    public int hashCode() {
        return Objects.hash(result); // TODO
    }
}