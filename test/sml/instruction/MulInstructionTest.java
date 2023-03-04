package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MulInstructionTest {
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

    // Testing result of multiplication with two positive integers
    @Test
    void executeValid1() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    // Testing result of multiplication with two integers, one positive and one negative
    @Test
    void executeValid2() {
        registers.set(EAX, 7);
        registers.set(EBX, -2);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-14, machine.getRegisters().get(EAX));
    }

    // Testing result of multiplication with two integers, one negative and one zero
    @Test
    void executeValid3() {
        registers.set(EAX, -6);
        registers.set(EBX, 0);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    // Testing toString() result of instruction without label
    @Test
    void testToStringNoLabel() {
        Instruction instruction = new MulInstruction(null, ECX, EDX);
        Assertions.assertEquals("mul ECX EDX", instruction.toString());
    }

    // Testing toString() result of instruction with label
    @Test
    void testToStringWithLabel() {
        Instruction instruction = new MulInstruction("b2", EBX, ESP);
        Assertions.assertEquals("b2: mul EBX ESP", instruction.toString());
    }

    // Testing equality of two instructions of different types but same label, result and source registers
    @Test
    void testEqualsandHashCode1() {
        Instruction instructionOne = new MulInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new DivInstruction("f2", EAX, EBX);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two instructions of same type and same label, result and source registers
    @Test
    void testEqualsandHashCode2() {
        Instruction instructionOne = new MulInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new MulInstruction("f2", EAX, EBX);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two mul instructions with different labels (one null)
    @Test
    void testEqualsandHashCode3() {
        Instruction instructionOne = new MulInstruction(null , ESI, ESP);
        Instruction instructionTwo = new MulInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two mul instructions with same labels (both null)
    @Test
    void testEqualsandHashCode4() {
        Instruction instructionOne = new MulInstruction(null , ESI, ESP);
        Instruction instructionTwo = new MulInstruction(null, ESI, ESP);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two mul instructions with different labels (both non-null)
    @Test
    void testEqualsandHashCode5() {
        Instruction instructionOne = new MulInstruction("f2" , ESI, ESP);
        Instruction instructionTwo = new MulInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two mul instructions with different result registers
    @Test
    void testEqualsandHashCode6() {
        Instruction instructionOne = new MulInstruction("h4" , ESI, ESP);
        Instruction instructionTwo = new MulInstruction("h4", ECX, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two mul instructions with different source registers
    @Test
    void testEqualsandHashCode7() {
        Instruction instructionOne = new MulInstruction("h4" , ESI, EDX);
        Instruction instructionTwo = new MulInstruction("h4", ESI, EDI);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }
}