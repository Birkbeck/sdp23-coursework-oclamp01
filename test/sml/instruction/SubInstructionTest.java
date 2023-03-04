package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class SubInstructionTest {
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

    // Testing subtraction of two positive integers
    @Test
    void executeValid1() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    // Testing subtraction of two integers, one negative
    @Test
    void executeValid2() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }

    // Testing subtraction of two integers, one negative and one zero
    @Test
    void executeValid3() {
        registers.set(EAX, -5);
        registers.set(EBX, 0);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
    }

    // Testing toString() result of instruction without label
    @Test
    void testToStringNoLabel() {
        Instruction instruction = new SubInstruction(null, EBX, ESI);
        Assertions.assertEquals("sub EBX ESI", instruction.toString());
    }

    // Testing toString() result of instruction with label
    @Test
    void testToStringWithLabel() {
        Instruction instruction = new SubInstruction("d8", EAX, EDX);
        Assertions.assertEquals("d8: sub EAX EDX", instruction.toString());
    }

    // Testing equality of two instructions of different types but same label, result and source registers
    @Test
    void testEqualsandHashCode1() {
        Instruction instructionOne = new SubInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new MulInstruction("f2", EAX, EBX);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two instructions of same type and same label, result and source registers
    @Test
    void testEqualsandHashCode2() {
        Instruction instructionOne = new SubInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new SubInstruction("f2", EAX, EBX);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two sub instructions with different labels (one null)
    @Test
    void testEqualsandHashCode3() {
        Instruction instructionOne = new SubInstruction(null , ESI, ESP);
        Instruction instructionTwo = new SubInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two sub instructions with same labels (both null)
    @Test
    void testEqualsandHashCode4() {
        Instruction instructionOne = new SubInstruction(null , ESI, ESP);
        Instruction instructionTwo = new SubInstruction(null, ESI, ESP);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two sub instructions with different labels (both non-null)
    @Test
    void testEqualsandHashCode5() {
        Instruction instructionOne = new SubInstruction("f2" , ESI, ESP);
        Instruction instructionTwo = new SubInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two sub instructions with different result registers
    @Test
    void testEqualsandHashCode6() {
        Instruction instructionOne = new SubInstruction("h4" , ESI, ESP);
        Instruction instructionTwo = new SubInstruction("h4", ECX, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two sub instructions with different source registers
    @Test
    void testEqualsandHashCode7() {
        Instruction instructionOne = new SubInstruction("h4" , ESI, EDX);
        Instruction instructionTwo = new SubInstruction("h4", ESI, EDI);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }
}