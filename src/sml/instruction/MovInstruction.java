package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private Integer operandInt;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, Integer operandInt) {
        super(label, OP_CODE);
        this.result = result;
        this.operandInt = operandInt;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, operandInt);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

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