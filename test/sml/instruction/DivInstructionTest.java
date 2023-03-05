package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class DivInstructionTest {
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

    // Testing result of division of two positive integers, expecting 0
    // (noting that integer (not floor) division is specified)
    @Test
    void executeValid1() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    // Testing result of division of two negative integers
    @Test
    void executeValid2() {
        registers.set(ESP, -8);
        registers.set(ESI, -2);
        Instruction instruction = new DivInstruction(null, ESP, ESI);
        instruction.execute(machine);
        Assertions.assertEquals(4, machine.getRegisters().get(ESP));
    }

    // Testing toString() result of instruction without label
    @Test
    void testToStringNoLabel() {
        registers.set(ESI, 8);
        registers.set(ESP, 2);
        Instruction instruction = new DivInstruction(null, ESI, ESP);
        Assertions.assertEquals("div ESI ESP", instruction.toString());
    }

    // Testing toString() result of instruction with label
    @Test
    void testToStringWithLabel() {
        registers.set(ESI, 3);
        registers.set(ESP, 4);
        Instruction instruction = new DivInstruction("c2", ESI, ESP);
        Assertions.assertEquals("c2: div ESI ESP", instruction.toString());
    }

    // Testing equality of two instructions of different types but same label (not null), result and source registers
    @Test
    void testEqualsandHashCode1() {
        Instruction instructionOne = new DivInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new AddInstruction("f2", EAX, EBX);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two instructions of same type and same label, result and source registers
    @Test
    void testEqualsandHashCode2() {
        Instruction instructionOne = new DivInstruction("f2", EAX, EBX);
        Instruction instructionTwo = new DivInstruction("f2", EAX, EBX);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two div instructions with different labels (one null)
    @Test
    void testEqualsandHashCode3() {
        Instruction instructionOne = new DivInstruction(null , ESI, ESP);
        Instruction instructionTwo = new DivInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two div instructions with same labels (both null)
    @Test
    void testEqualsandHashCode4() {
        Instruction instructionOne = new DivInstruction(null , ESI, ESP);
        Instruction instructionTwo = new DivInstruction(null, ESI, ESP);
        Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
        Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two div instructions with different labels (both non-null)
    @Test
    void testEqualsandHashCode5() {
        Instruction instructionOne = new DivInstruction("f2" , ESI, ESP);
        Instruction instructionTwo = new DivInstruction("f3", ESI, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two div instructions with different result registers
    @Test
    void testEqualsandHashCode6() {
        Instruction instructionOne = new DivInstruction("h4" , ESI, ESP);
        Instruction instructionTwo = new DivInstruction("h4", ECX, ESP);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    // Testing equality of two div instructions with different source registers
    @Test
    void testEqualsandHashCode7() {
        Instruction instructionOne = new DivInstruction("h4" , ESI, EDX);
        Instruction instructionTwo = new DivInstruction("h4", ESI, EDI);
        Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
        Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }
}