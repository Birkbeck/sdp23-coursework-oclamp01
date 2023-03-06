package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class translates the machine's program into its internal form. It reads the input file name
 * and scans each line of the text file, adding label (if it exists) to the HashMap labels, and each instruction
 * to the ArrayList instructions. The getInstruction() method also determines which instruction subclass to execute.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        // TODO: add code for all other types of instructions - COMPLETE
//        if (line.isEmpty())
//            return null;
//
//        String opcode = scan();
//        switch (opcode) {
//            case AddInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case SubInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case MulInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case DivInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case OutInstruction.OP_CODE -> {
//                String r = scan();
//                return new OutInstruction(label, Register.valueOf(r));
//            }
//
//            case MovInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new MovInstruction(label, Register.valueOf(r), Integer.valueOf(s));
//            }
//
//            case JnzInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new JnzInstruction(label, Register.valueOf(r), String.valueOf(s));
//            }
//
//            default -> {
//                System.out.println("Unknown instruction: " + opcode);
//            }
//        }
//        return null;

//        // TODO: Then, replace the switch by using the Reflection API - COMPLETE
        // First, the scan() method is used to scan the opcode of
        // the input line with label removed (see getLabel())
        // As opcodes are lowercase but instruction subclasses are upper camel case, the opcode string needs
        // to be properly formatted
        if (line.isEmpty()) {
            return null;
        }

        // Scan the line for first word (opcode)
        String opcode = scan();

        // Shift first character of opcode to uppercase
        char insFirstChar = opcode.charAt(0);
        insFirstChar = Character.toUpperCase(insFirstChar);

        // Pass in three-letter opcode to StringBuffer and set first character to uppercase
        StringBuffer buffer = new StringBuffer(opcode);
        buffer.setCharAt(0, insFirstChar);

        // Instruction is then formatted to be used in reflective constructor selection (e.g. "AddInstruction")
        String instruction = buffer.toString();
        instruction = "sml.instruction." + instruction + "Instruction";

        Object[] paramList;

        // This code produced with help from Jenkov at https://jenkov.com/tutorials/java-reflection/constructors.html
        try {
            Class thisInstruction = Class.forName(instruction);
            Constructor[] constructors = thisInstruction.getConstructors();
     //    thisInstruction.getConstructors() is of length 0, so we must choose constructors[0]
            Constructor insConstructor = constructors[0];
         // Reading the parameters taken by a given constructor
            Class[] paramsTypes = insConstructor.getParameterTypes();
//         As different instructions have different numbers of parameters, the parameter list must be large enough
//         for a given instruction
            paramList = new Object[paramsTypes.length];
//         For loop to iterate through parameter list to populate constructor with appropriate argument types
            for (int i = 0; i < paramList.length; i++) {
                if (i == 0) {
                    paramList[i] = label;
                }
                else {
                    Class newClass = paramsTypes[i];
                    if (newClass.getName().equals("java.lang.String")) {
                        String param = scan();
                        paramList[i] = param;
                    }
                    else if (newClass.getName().equals("sml.RegisterName")) {
                        String param = scan();
                        paramList[i] = Register.valueOf(param);
                    }
                    else if (newClass.getName().equals("java.lang.Integer")) {
                        Integer param = Integer.parseInt(scan());
                        paramList[i] = param;
                    }
                    }
                }
            try {
                return (Instruction) insConstructor.newInstance(paramList);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
        }


        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}