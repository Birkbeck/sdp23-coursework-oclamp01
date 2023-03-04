package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class OutInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
    }

    @Test
    void executeValidTwo() {
    }

    // Testing toString() result of instruction without label
    @Test
    void testToStringNoLabel() {
        registers.set(ESI, 8);
        Instruction instruction = new OutInstruction(null, ESI);
        Assertions.assertEquals("out ESI", instruction.toString());
    }

    // Testing toString() result of instruction with label
    @Test
    void testToStringWithLabel() {
        registers.set(ESI, 8);
        Instruction instruction = new OutInstruction("f2", EBX);
        Assertions.assertEquals("f2: out EBX", instruction.toString());
    }

    // Testing equality of two instructions of same type, same label and result register
    @Test
    void testEqualsandHashCode1() {
        Instruction instructionOne = new OutInstruction("f2", EAX);
        Instruction instructionTwo = new OutInstruction("f2", EAX);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Second (trivial) test of equality of two instructions of same type, same label and result register
    @Test
    void testEqualsandHashCode2() {
        Instruction instructionOne = new OutInstruction("d4", ESI);
        Instruction instructionTwo = new OutInstruction("d4", ESI);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two out instructions with different labels (one null)
    @Test
    void testEqualsandHashCode3() {
        Instruction instructionOne = new OutInstruction(null , ESI);
        Instruction instructionTwo = new OutInstruction("f3", ESI);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two out instructions with same labels (both null)
    @Test
    void testEqualsandHashCode4() {
        Instruction instructionOne = new OutInstruction(null , ESI);
        Instruction instructionTwo = new OutInstruction(null, ESI);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two out instructions with different labels (both non-null)
    @Test
    void testEqualsandHashCode5() {
        Instruction instructionOne = new OutInstruction("f2" , ESI);
        Instruction instructionTwo = new OutInstruction("f3", ESI);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two out instructions with different result registers
    @Test
    void testEqualsandHashCode6() {
        Instruction instructionOne = new OutInstruction("h4" , ESI);
        Instruction instructionTwo = new OutInstruction("h4", ECX);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }
}