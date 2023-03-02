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

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 88);
        registers.set(EBX, 8);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(11, machine.getRegisters().get(EAX));
    }

    @Test
    void testToStringNoLabel() {
        registers.set(ESI, 8);
        registers.set(ESP, 2);
        Instruction instruction = new DivInstruction(null, ESI, ESP);
        Assertions.assertEquals("div ESI ESP", instruction.toString());
    }

    @Test
    void testToStringWithLabel() {
        registers.set(ESI, 3);
        registers.set(ESP, 4);
        Instruction instruction = new DivInstruction("c2", ESI, ESP);
        Assertions.assertEquals("c2: div ESI ESP", instruction.toString());
    }
}