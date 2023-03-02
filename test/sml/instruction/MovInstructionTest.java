package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
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
    Instruction instruction = new MovInstruction(null, EAX, 7);
    instruction.execute(machine);
    Assertions.assertEquals(7, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EBX, 6);
    Instruction instruction = new MovInstruction(null, EBX, 21);
    instruction.execute(machine);
    Assertions.assertEquals(21, machine.getRegisters().get(EBX));
  }

  @Test
  void testToStringNoLabel() {
    registers.set(EAX, 3);
    Instruction instruction = new MovInstruction(null, EAX, 8);
    Assertions.assertEquals("mov EAX 8", instruction.toString());
  }

  @Test
  void testToStringWithLabel() {
    registers.set(EDX, 4);
    Instruction instruction = new MovInstruction("f2", EDX, 46);
    Assertions.assertEquals("f2: mov EDX 46", instruction.toString());
  }
}