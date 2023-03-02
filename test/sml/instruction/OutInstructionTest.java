package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

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

    @Test
    void executeValid() {
    }

    @Test
    void executeValidTwo() {
    }

    @Test
    void testToStringNoLabel() {
        registers.set(ESI, 8);
        Instruction instruction = new OutInstruction(null, ESI);
        Assertions.assertEquals("out ESI", instruction.toString());
    }

    @Test
    void testToStringWithLabel() {
        registers.set(ESI, 8);
        Instruction instruction = new OutInstruction("f2", ESI);
        Assertions.assertEquals("f2: out ESI", instruction.toString());
    }
}