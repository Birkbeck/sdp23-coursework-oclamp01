package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.IOException;

class MachineLabelTest {
    private Machine machine1;
    private Machine machine2;

    @BeforeEach
    void setUp() {
        machine1 = new Machine(new Registers());
        machine2 = new Machine(new Registers());

    }

    @AfterEach
    void tearDown() {
        machine1 = null;
        machine2 = null;
    }

    // Testing string output of machine with a labelled instruction
    @Test
    void testToString1() {
        Translator t = new Translator("test/testFiles/test1.txt");
        try {
            t.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(
                "mov EAX 6\n" +
                        "mov EBX 1\n" +
                        "mov ECX 1\n" +
                        "f3: mul EBX EAX\n" +
                        "sub EAX ECX\n" +
                        "jnz EAX f3\n" +
                        "out EBX",
                machine1.toString());
    }

    // Similar test of string output of machine with no labelled instructions
    @Test
    void testToString2() {
        Translator t = new Translator("test/testFiles/test6.txt");
        try {
            t.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(
                "mov EAX 3\n" +
                        "out EAX",
                machine2.toString());
    }

    // Testing equality of two machines running two identical programs
    @Test
    void testEquals1() {
        Translator t1 = new Translator("test/testFiles/test6.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test11.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(true, machine1.equals(machine2));
    }

    // Testing equality of two machines running two similar programs but where one has a labelled instruction
    @Test
    void testEquals2() {
        Translator t1 = new Translator("test/testFiles/test11.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test13.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(false, machine1.equals(machine2));
    }

    // Testing equality of hashCodes of machines in testEquals1(), where programs have no labelled instructions
    @Test
    void testHashCode1() {
        Translator t1 = new Translator("test/testFiles/test6.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test11.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(machine1.hashCode(), machine2.hashCode());
    }

    // Testing equality of hashCodes of machines with labelled instructions
    @Test
    void testHashCode2() {
        Translator t1 = new Translator("test/testFiles/test13.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test14.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(true, machine1.equals(machine2));
    }

    // Testing string output of machine labels
    @Test
    void testLabelsToString() {
        Translator t1 = new Translator("test/testFiles/test13.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("[f3 = 0]", machine1.getLabels().toString());
    }

    // Testing equality of labels of two machines running identical programs with a labelled instruction
    @Test
    void testLabelsEquals1() {
        Translator t1 = new Translator("test/testFiles/test13.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test14.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(true, machine1.getLabels().equals(machine2.getLabels()));
    }

    // Testing equality of labels of two machines running non-identical programs with no labelled instructions
    @Test
    void testLabelsEquals2() {
        Translator t1 = new Translator("test/testFiles/test6.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test8.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(true, machine1.getLabels().equals(machine2.getLabels()));
    }

    // Testing equality of labels hashCodes of two machines running identical programs with a labelled instruction
    @Test
    void testLabelsHashCode1() {
        Translator t1 = new Translator("test/testFiles/test13.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test14.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(machine1.getLabels().hashCode(), machine2.getLabels().hashCode());
    }

    // Testing equality of labels hashCodes of two machines running similar programs with a labelled instruction,
    // but the same label refers to a different address
    @Test
    void testLabelsHashCode2() {
        Translator t1 = new Translator("test/testFiles/test14.txt");
        try {
            t1.readAndTranslate(machine1.getLabels(), machine1.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Translator t2 = new Translator("test/testFiles/test15.txt");
        try {
            t2.readAndTranslate(machine2.getLabels(), machine2.getProgram());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotEquals(machine1.getLabels().hashCode(), machine2.getLabels().hashCode());
    }
}