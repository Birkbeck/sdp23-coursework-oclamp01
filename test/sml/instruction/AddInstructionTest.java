package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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

  // Testing addition of two positive integers
  @Test
  void executeValid1() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  // Testing addition of two integers where one is negative
  @Test
  void executeValid2() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  // Testing addition of two integers where one is negative and one is zero
  @Test
  void executeValid3() {
    registers.set(EAX, -5);
    registers.set(EBX, 0);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }

  // Testing toString() result of instruction without label
  @Test
  void testToStringNoLabel() {
    Instruction instruction = new AddInstruction(null, EAX, EDX);
    Assertions.assertEquals("add EAX EDX", instruction.toString());
  }

  // Testing toString() result of instruction with label
  @Test
  void testToStringWithLabel() {
    Instruction instruction = new AddInstruction("f3", EAX, EDX);
    Assertions.assertEquals("f3: add EAX EDX", instruction.toString());
  }

  // Testing equality of two instructions of different types but same label, result and source registers
  @Test
  void testEqualsandHashCode1() {
    Instruction instructionOne = new AddInstruction("f2", EAX, EBX);
    Instruction instructionTwo = new SubInstruction("f2", EAX, EBX);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two instructions of same type and same label, result and source registers
  @Test
  void testEqualsandHashCode2() {
    Instruction instructionOne = new AddInstruction("f2", EAX, EBX);
    Instruction instructionTwo = new AddInstruction("f2", EAX, EBX);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two add instructions with different labels (one null)
  @Test
  void testEqualsandHashCode3() {
    Instruction instructionOne = new AddInstruction(null , ESI, ESP);
    Instruction instructionTwo = new AddInstruction("f3", ESI, ESP);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two add instructions with same labels (both null)
  @Test
  void testEqualsandHashCode4() {
    Instruction instructionOne = new AddInstruction(null , ESI, ESP);
    Instruction instructionTwo = new AddInstruction(null, ESI, ESP);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two add instructions with different labels (both non-null)
  @Test
  void testEqualsandHashCode5() {
    Instruction instructionOne = new AddInstruction("f2" , ESI, ESP);
    Instruction instructionTwo = new AddInstruction("f3", ESI, ESP);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two add instructions with different result registers
  @Test
  void testEqualsandHashCode6() {
    Instruction instructionOne = new AddInstruction("h4" , ESI, ESP);
    Instruction instructionTwo = new AddInstruction("h4", ECX, ESP);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two add instructions with different source registers
  @Test
  void testEqualsandHashCode7() {
    Instruction instructionOne = new AddInstruction("h4" , ESI, EDX);
    Instruction instructionTwo = new AddInstruction("h4", ESI, EDI);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }
}