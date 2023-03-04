package sml;

import java.util.*;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class - COMPLETE
/**
 * Represents the Registers, which are used by the instructions to perform operations within the Machine.
 * There are 8 registers, stored as enum variables of type Registers.Register. There are several methods to
 * work with them, including to clear them (at the start of an operation in the constructor), getters and setters
 * using the HashMap set() and get() methods, as well as overridden equals(), hashCode() and toString() methods.
 */
public final class Registers {
    // Internal representation of registers using HashMap mapping String labels to Integer addresses in memory
    private final Map<Register, Integer> registers = new HashMap<>();

    /**
     * enum Register containing String name of eight registers belonging to a given machine. This enum implements
     * the RegisterName interface
     */
    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    /**
     * Constructor which sets the registers HashMap to empty (see clear())
     */
    public Registers() {
        clear(); // the class is final
    }

    /**
     * Sets the value of each register in the Register enum to 0
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    // TODO: use pattern matching for instanceof - COMPLETE
    // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
    /**
     * @param o the object being compared for equality
     * @return true if o is object of type Registers and mappings are equal between this and o, and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Registers obj) {
            // Registers obj = (Registers) o; // Casting of o to Registers unnecessary if Object o is of Machine type,
                                                // else return false
            return registers.equals(obj.registers);
        }
        return false;
    }

    /**
     * @return hashCode of HashMap registers
     */
    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    /**
     * @return a sorted mapping of each key and value over the entrySet of the HashMap registers,
     * with a comma delimiter between each key-value pair and square brackets
     * at the start and end of the returned string.
     * E.g. [f2 = 2, f3 = 3]
     */
    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
