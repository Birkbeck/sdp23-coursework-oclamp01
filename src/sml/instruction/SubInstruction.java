package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Subclass of Instruction abstract class used for performing subtraction operations. Contains result and source fields
 * for register operands, and static OP_CODE field to enable execution in switch statement in Machine class.
 */
public class SubInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";

    /**
     * @param label (optional) label of instruction
     * @param result register where result of subtraction will be stored, and which contains first subtraction operand
     * @param source register containing second subtraction operand
     */
    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that this is not a branch instruction
     * result register is set to the int value produced by subtraction of values in result and source registers
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 - value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * @return label (if applicable) + OP_CODE ("sub") + name of result and source registers (e.g. "f2: sub EAX ECX")
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SubInstruction) {
            // TODO: pattern-matching for instanceof
            SubInstruction other = (SubInstruction) o;
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source)
                    && Objects.equals(this.OP_CODE, other.OP_CODE);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source); // TODO
    }
}
