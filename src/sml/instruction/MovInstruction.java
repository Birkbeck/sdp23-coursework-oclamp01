package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class - COMPLETE

/**
 * Subclass of Instruction abstract class used for moving integer values to the specified register.
 * Contains result field for register where value is to be stored, the Integer value to be stored,
 * and static OP_CODE field to enable execution in switch statement in Machine class.
 */
public class MovInstruction extends Instruction {
    protected final RegisterName result;
    protected final Integer operandInt;

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

    /**
     * @param o the object to be compared for equality
     *          Pattern matching is used to remove intermediate casting step
     * @return true if o is instanceof MovInstruction, and label, result, operandInt and OP_CODE fields are equal,
     *          and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.operandInt, other.operandInt)
                    && Objects.equals(this.OP_CODE, other.OP_CODE);
        }
        return false;
    }

    /**
     * @return int hash of array of fields of MovInstruction (label, OP_CODE, result and operandInt)
     */
    @Override
    public int hashCode() {
        return Objects.hash(label, OP_CODE, result, operandInt);
    }
}