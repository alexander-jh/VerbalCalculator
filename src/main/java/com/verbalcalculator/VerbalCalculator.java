package com.verbalcalculator;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.FileNotFoundException;

@Command(name = "VerbalCalculator",
        version = "VerbalCalculator 1.0",
        mixinStandardHelpOptions = true)
public class VerbalCalculator implements Runnable {

    @Option(names = {"-f", "--file"})
    private String file;

    @Override
    public void run() {
        Agent agent = new Agent();
        try {
            String[] res = agent.parseAudio(file);
            Parser parser = new Parser(res);
            int out = parser.parse();
            parser.reportInput();
            reportResult(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reportResult(int n) {
        System.out.println(
                (new StringBuilder())
                        .append("Output from expression: ")
                        .append(n)
                        .append("\n")
        );
    }

    public static void main(String[] args) throws Exception {
        int exit = new CommandLine((new VerbalCalculator())).execute(args);
        System.exit(exit);
    }
}
