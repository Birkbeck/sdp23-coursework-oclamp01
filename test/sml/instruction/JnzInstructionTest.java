package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class JnzInstructionTest {
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

  @Test
  void testToStringNoLabel() {
    registers.set(EAX, 3);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    Assertions.assertEquals("jnz EAX f3", instruction.toString());
  }

  @Test
  void testToStringWithLabel() {
    registers.set(ESI, 4);
    Instruction instruction = new JnzInstruction("f2", ESI, "a2");
    Assertions.assertEquals("f2: jnz ESI a2", instruction.toString());
  }
}