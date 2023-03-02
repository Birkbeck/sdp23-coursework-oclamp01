package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Subclass of Instruction abstract class used for moving integer values to the specified register.
 * Contains result field for register where value is to be stored, the Integer value to be stored,
 * and static OP_CODE field to enable execution in switch statement in Machine class.
 */
public class MovInstruction extends Instruction {
    private final RegisterName result;
    private Integer operandInt;

    public static final String OP_CODE = "mov";

    /**
     * @param label (optional) label of instruction
     * @param result register where Integer value is to be stored
     * @param operandInt Integer value to be stored in result
     */
    public MovInstruction(String label, RegisterName result, Integer operandInt) {
        super(label, OP_CODE);
        this.result = result;
        this.operandInt = operandInt;
    }

    /**
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that this is not a branch instruction
     * result register updated with operandInt using HashMap set() method
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, operandInt);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * @return label (if applicable) + OP_CODE ("mov") + name of result register + operandInt (e.g. "mov ECX 5")
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + operandInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovInstruction that)) return false;
        return result.equals(that.result) && operandInt.equals(that.operandInt); // TODO
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, operandInt); // TODO
    }
}