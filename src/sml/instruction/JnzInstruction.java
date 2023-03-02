package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Subclass of Instruction abstract class used for branching to another instruction
 * if value in result register is not zero.
 * Contains result field for register where value is to be stored, the String label
 * indicating which instruction to branch to, and static OP_CODE field to enable execution
 * in switch statement in Machine class.
 */
public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String operandLabel;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubInstruction that)) return false;
        return false;
        // TODO
    }

    @Override
    public int hashCode() {
        return Objects.hash(result); // TODO
    }
}