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

  @Test
  void executeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void testToStringNoLabel() {
    registers.set(EAX, 3);
    registers.set(EDX, 4);
    Instruction instruction = new AddInstruction(null, EAX, EDX);
    Assertions.assertEquals("add EAX EDX", instruction.toString());
  }

  @Test
  void testToStringWithLabel() {
    registers.set(EAX, 3);
    registers.set(EDX, 4);
    Instruction instruction = new AddInstruction("f3", EAX, EDX);
    Assertions.assertEquals("f3: add EAX EDX", instruction.toString());
  }

  @Test
  void testEqualsOne() {
    registers.set(EAX, 3);
    registers.set(EDX, 4);
    Instruction instructionOne = new AddInstruction("f2", EAX, EBX);
    Instruction instructionTwo = new AddInstruction("f2", EAX, EBX);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
  }

  @Test
  void testEqualsTwo() {
    registers.set(ESI, 5);
    registers.set(ESP, 6);
    Instruction instructionOne = new AddInstruction("f2", ESI, ESP);
    Instruction instructionTwo = new SubInstruction("f2", ESI, ESP);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
  }
}