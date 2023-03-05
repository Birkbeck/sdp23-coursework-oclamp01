package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class RegistersTest {
    private Machine machine1;
    private Registers registers1;
    private Machine machine2;
    private Registers registers2;

    @BeforeEach
    void setUp() {
        machine1 = new Machine(new Registers());
        registers1 = machine1.getRegisters();
        machine2 = new Machine(new Registers());
        registers2 = machine2.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine1 = null;
        registers1 = null;
        machine2 = null;
        registers2 = null;
    }

    // Testing string output of registers after setting four registers
    @Test
    void testToString1() {
        registers1.set(EAX, 3);
        registers1.set(EBX, 4);
        registers1.set(ECX, 5);
        registers1.set(ESI, 6);
        Assertions.assertEquals("[EAX = 3, EBX = 4, ECX = 5, EDX = 0, ESP = 0, EBP = 0, ESI = 6, EDI = 0]",
                registers1.toString());
    }

    // Similar test of string output with other four registers set to new values
    @Test
    void testToString2() {
        registers2.set(EDX, 1);
        registers2.set(ESP, 2);
        registers2.set(EBP, 3);
        registers2.set(EDI, 4);
        Assertions.assertEquals("[EAX = 0, EBX = 0, ECX = 0, EDX = 1, ESP = 2, EBP = 3, ESI = 0, EDI = 4]",
                registers2.toString());
    }

    // Testing equality of two sets of registers with registers set to same values
    @Test
    void testEquals1() {
        registers1.set(EAX, 3);
        registers1.set(EBX, 4);
        registers1.set(ECX, 5);
        registers1.set(ESI, 6);
        registers2.set(EAX, 3);
        registers2.set(EBX, 4);
        registers2.set(ECX, 5);
        registers2.set(ESI, 6);
        Assertions.assertEquals(true, registers1.equals(registers2));
    }

    // Testing equality of two sets of registers with one register set to different values
    @Test
    void testEquals2() {
        registers1.set(EAX, 3);
        registers1.set(EBX, 4);
        registers1.set(ECX, 5);
        registers1.set(ESI, 6);
        registers1.set(EBP, 8);
        registers2.set(EAX, 3);
        registers2.set(EBX, 4);
        registers2.set(ECX, 4);
        registers2.set(ESI, 6);
        registers2.set(EBP, 8);
        Assertions.assertEquals(false, registers1.equals(registers2));
    }

    // Testing equality of two hashCodes when registers are set to same values
    @Test
    void testHashCode1() {
        registers1.set(EAX, 3);
        registers1.set(EBX, 4);
        registers1.set(ECX, 5);
        registers1.set(ESI, 6);
        registers2.set(EAX, 3);
        registers2.set(EBX, 4);
        registers2.set(ECX, 5);
        registers2.set(ESI, 6);
        Assertions.assertEquals(registers1.hashCode(), registers2.hashCode());
    }

    // Testing equality of two sets of registers with additional register set to same values
    @Test
    void testHashCode2() {
        registers1.set(EAX, 3);
        registers1.set(EBX, 4);
        registers1.set(ECX, 5);
        registers1.set(ESI, 6);
        registers1.set(EBP, 9);
        registers2.set(EAX, 3);
        registers2.set(EBX, 4);
        registers2.set(ECX, 5);
        registers2.set(ESI, 6);
        registers2.set(EBP, 9);
        Assertions.assertEquals(registers1.hashCode(), registers2.hashCode());
    }
}