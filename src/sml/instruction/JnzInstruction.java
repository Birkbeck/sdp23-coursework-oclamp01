package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class - COMPLETE

/**
 * Subclass of Instruction abstract class used for branching to another instruction
 * if value in result register is not zero.
 * Contains result field for register where value is to be stored, the String label
 * indicating which instruction to branch to, and static OP_CODE field to enable execution
 * in switch statement in Machine class.
 * @author oclamp01
 */
public class JnzInstruction extends Instruction {
    protected final RegisterName result;
    protected final String operandLabel;

    public static final String OP_CODE = "jnz";

    /**
     * @param label (optional) label of instruction
     * @param result register which will be checked for value == 0
     * @param operandLabel label indicating which instruction (with same label) to branch to
     */
    public JnzInstruction(String label, RegisterName result, String operandLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.operandLabel = operandLabel;
    }

    /**
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE if result = 0, indicating that the branch condition is not satisfied, or
     * int value of address corresponding to String label in Labels HashMap
     */
    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(result) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        else {
            return m.getLabels().getAddress(operandLabel);
        }
    }

    /**
     * @return label (if applicable) + OP_CODE ("jnz") + name of result register + label (e.g. "jnz EBX f3")
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + operandLabel;
    }

    /**
     * @param o the object to be compared for equality
     *          Pattern matching is used to remove intermediate casting step
     * @return true if o is instanceof JnzInstruction, and label, result, operandLabel and OP_CODE fields are equal,
     *          and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof JnzInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.operandLabel, other.operandLabel)
                    && Objects.equals(this.OP_CODE, other.OP_CODE);
        }
        return false;
    }

    /**
     * @return int hash of array of fields of JnzInstruction (label, OP_CODE. result and operandLabel)
     */
    @Override
    public int hashCode() {
        return Objects.hash(label, OP_CODE, result, operandLabel);
    }
}