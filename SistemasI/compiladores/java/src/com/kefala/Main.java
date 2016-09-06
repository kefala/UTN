package com.kefala;

import java.io.*;
import java.util.Scanner;

/**
 * Created by kefala on 05/09/16.
 */
public class Main {
    public static void main(String[] args) {
        String fileName;
        if (args.length == 0) {
            System.out.print("Ingrese el nombre del archivo fuente en PL/0: ");
            fileName = new Scanner(System.in).nextLine();
        } else {
            fileName = args[0];
        }

        if (fileName.isEmpty()) {
            System.out.println("Error!\n");
            System.out.println("Uso: java -jar \"Compilar.jar\" <archivo>\n");
        } else {
            Reader sourceFile = null;
            try {
                sourceFile = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            LexicalAnalizer aLex = new LexicalAnalizer(sourceFile);
            ErrorIndicator errIndicator = new ErrorIndicator(aLex);
            SemanticAnalizer aSem = new SemanticAnalizer(errIndicator);
            CodeGenerator codeGen = new CodeGenerator(fileName, errIndicator);
            SyntacticAnalizer aSyn = new SyntacticAnalizer(aLex, aSem, codeGen, errIndicator);
            try {
                aSyn.analizar();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            try {
                sourceFile.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }
}
