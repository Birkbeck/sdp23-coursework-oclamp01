package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    // This test was produced with help from Baeldung at https://www.baeldung.com/java-testing-system-out-println
    // Testing comparison of outInstruction output to console with expected string
    // This test reassigns the standard output stream to a PrintStream with a ByteArrayOutputStream
    // The trimmed output (avoiding line separators) is then compared with the expected value
    // The standard output stream is then restored to System.out at the end of the test
    @Test
    void executeValid1() {
        registers.set(EAX, 4);
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);

        String actualResult = outputStreamCaptor.toString().trim();
        Assertions.assertEquals("Value of register EAX: 4", actualResult);
        System.setOut(System.out);
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

    // Second (trivial) test of equality of two instructions of same type, same label (not null) and result register
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