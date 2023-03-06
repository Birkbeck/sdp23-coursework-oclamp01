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

  // Testing that register successfully updates following mov operation
  @Test
  void executeValid1() {
    registers.set(EAX, 5);
    Instruction instruction = new MovInstruction(null, EAX, 7);
    instruction.execute(machine);
    Assertions.assertEquals(7, machine.getRegisters().get(EAX));
  }

  // Testing that different register successfully updates following mov operation
  @Test
  void executeValid2() {
    registers.set(EBX, 6);
    Instruction instruction = new MovInstruction(null, EBX, 21);
    instruction.execute(machine);
    Assertions.assertEquals(21, machine.getRegisters().get(EBX));
  }

  // Testing MovInstruction in program file using reflective Translator.getInstruction()
  @Test
  void executeValid3() {
    Translator t = new Translator("test/testFiles/test17.txt");
    try {
      t.readAndTranslate(machine.getLabels(), machine.getProgram());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    machine.execute();
    Assertions.assertEquals(8, machine.getRegisters().get(EAX));
  }

  // Testing toString() result of instruction without label
  @Test
  void testToStringNoLabel() {
    registers.set(EAX, 3);
    Instruction instruction = new MovInstruction(null, EAX, 8);
    Assertions.assertEquals("mov EAX 8", instruction.toString());
  }

  // Testing toString() result of instruction with label
  @Test
  void testToStringWithLabel() {
    registers.set(EDX, 4);
    Instruction instruction = new MovInstruction("f2", EDX, 46);
    Assertions.assertEquals("f2: mov EDX 46", instruction.toString());
  }

  // Testing equality of two instructions of same type and same label (not null), result register and operandInt
  @Test
  void testEqualsandHashCode1() {
    Instruction instructionOne = new MovInstruction("f2", EAX, 3);
    Instruction instructionTwo = new MovInstruction("f2", EAX, 3);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Second (trivial) test of equality of two instructions of same type and same label, result register and operandInt
  @Test
  void testEqualsandHashCode2() {
    Instruction instructionOne = new MovInstruction("d4", ESI, 10);
    Instruction instructionTwo = new MovInstruction("d4", ESI, 10);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two mov instructions with different labels (one null)
  @Test
  void testEqualsandHashCode3() {
    Instruction instructionOne = new MovInstruction(null , ESI, 18);
    Instruction instructionTwo = new MovInstruction("f3", ESI, 18);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two mov instructions with same labels (both null)
  @Test
  void testEqualsandHashCode4() {
    Instruction instructionOne = new MovInstruction(null , ESI, 2);
    Instruction instructionTwo = new MovInstruction(null, ESI, 2);
    Assertions.assertEquals(true, instructionOne.equals(instructionTwo));
    Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two mov instructions with different labels (both non-null)
  @Test
  void testEqualsandHashCode5() {
    Instruction instructionOne = new MovInstruction("f2" , ESI, 1);
    Instruction instructionTwo = new MovInstruction("f3", ESI, 1);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two mov instructions with different result registers
  @Test
  void testEqualsandHashCode6() {
    Instruction instructionOne = new MovInstruction("h4" , ESI, 3);
    Instruction instructionTwo = new MovInstruction("h4", ECX, 3);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }

  // Testing equality of two mov instructions with different operandInts
  @Test
  void testEqualsandHashCode7() {
    Instruction instructionOne = new MovInstruction("h4" , ESI, 4);
    Instruction instructionTwo = new MovInstruction("h4", ESI, 8);
    Assertions.assertEquals(false, instructionOne.equals(instructionTwo));
    Assertions.assertNotEquals(instructionOne.hashCode(), instructionTwo.hashCode());
  }
}