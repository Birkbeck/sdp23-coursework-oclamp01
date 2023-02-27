package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private String targetLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String targetLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.targetLabel = targetLabel;
    }

    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(result) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        else {
            // TO-DO: Parse label, return number (new program counter)
            return 0;
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + targetLabel;
    }
}