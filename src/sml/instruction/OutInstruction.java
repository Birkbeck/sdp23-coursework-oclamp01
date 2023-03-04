package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class - COMPLETE

/**
 * Subclass of Instruction abstract class used for performing print operations to the console.
 * Contains single result field for register operand containing value to be printed, and static OP_CODE field to
 * enable execution in switch statement in Machine class.
 */
public class OutInstruction extends Instruction {
    protected final RegisterName result;

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
     * "Value of register" + name of result register + ":" + content of result register printed to console
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

    /**
     * @param o the object to be compared for equality
     *          Pattern matching is used to remove intermediate casting step
     * @return true if o is instanceof OutInstruction, and label, result and OP_CODE fields are equal,
     *          and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof OutInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.OP_CODE, other.OP_CODE);
        }
        return false;
    }

    /**
     * @return int hash of array of fields of OutInstruction (label, OP_CODE and result)
     */
    @Override
    public int hashCode() {
        return Objects.hash(label, OP_CODE, result);
    }
}