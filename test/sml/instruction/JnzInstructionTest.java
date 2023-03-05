package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.IOException;
import java.lang.reflect.Field;

import static sml.Registers.Register.*;

class JnzInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  // Testing successful execution of jnzInstruction in program which decrements value of EAX (initially 4)
  // until it reaches 0. Expected final value of EAX is 0 following program execution
  // Branching is expected and final value of register demonstrates successful branching
  @Test
  void executeValid1() {
    Translator t = new Translator("test/testFiles/test2a.txt");
    try {
      t.readAndTranslate(machine.getLabels(), machine.getProgram());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    machine.execute();
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

  // Testing successful execution of jnzInstruction in program which decrements value of EAX (initially 4)
  // until it reaches 0. Expected final value of EAX is 3 following program execution (where only the first
  // subtraction operation from EAX occurs. In test2b.txt ECX is set to 0 and replaces EAX in the branching instruction
  // in test2a.txt.
  // No branching is expected and final value of register demonstrates successful non-branching
  @Test
  void executeValid2() {
    Translator t = new Translator("test/testFiles/test2b.txt");
    try {
      t.readAndTranslate(machine.getLabels(), machine.getProgram());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    machine.execute();
    Assertions.assertEquals(3, machine.getRegisters().get(EAX));
  }

  // Testing toString() result of instruction without label
  @Test
  void testToStringNoLabel() {
    registers.set(EAX, 3);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    Assertions.assertEquals("jnz EAX f3", instruction.toString());
  }

  // Testing toString() result of instruction with label
  @Test
  void testToStringWithLabel() {
    registers.set(ESI, 4);
    Instruction instruction = new JnzInstruction("f2", ESI, "a2");
    Assertions.assertEquals("f2: jnz ESI a2", instruction.toString());
  }

  // Testing equality of two instructions of same type and same label, result register and operandLabel
  @Test
  void testEqualsandHashCode1() {
    Instruction instructionOne = new JnzInstruction("f2", EAX, "f3");
    Instruction instructionTwo = new JnzInstruction("f2", EAX, "f3");
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Second (trivial) test of equality of two instructions of same type and same label, result register and operandLabel
  @Test
  void testEqualsandHashCode2() {
    Instruction instructionOne = new JnzInstruction("d4", ESI, "b4");
    Instruction instructionTwo = new JnzInstruction("d4", ESI, "b4");
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two jnz instructions with different labels (one null)
  @Test
  void testEqualsandHashCode3() {
    Instruction instructionOne = new JnzInstruction(null , ESI, "d2");
    Instruction instructionTwo = new JnzInstruction("f3", ESI, "d2");
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two jnz instructions with same labels (both null)
  @Test
  void testEqualsandHashCode4() {
    Instruction instructionOne = new JnzInstruction(null , ESI, "d2");
    Instruction instructionTwo = new JnzInstruction(null, ESI, "d2");
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two jnz instructions with different labels (both non-null)
  @Test
  void testEqualsandHashCode5() {
    Instruction instructionOne = new JnzInstruction("f2" , ESI, "a1");
    Instruction instructionTwo = new JnzInstruction("f3", ESI, "a1");
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two jnz instructions with different result registers
  @Test
  void testEqualsandHashCode6() {
    Instruction instructionOne = new JnzInstruction("h4" , ESI, "a2");
    Instruction instructionTwo = new JnzInstruction("h4", ECX, "a2");
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two jnz instructions with different operandLabels
  @Test
  void testEqualsandHashCode7() {
    Instruction instructionOne = new JnzInstruction("h4" , ESI, "a4");
    Instruction instructionTwo = new JnzInstruction("h4", ESI, "a6");
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }
}