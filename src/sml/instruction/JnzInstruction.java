package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String operandLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String operandLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.operandLabel = operandLabel;
    }

    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(result) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        else {
            return m.getLabels().getAddress(operandLabel);
        }
    }

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