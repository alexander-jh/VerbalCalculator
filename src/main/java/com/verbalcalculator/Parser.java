package com.verbalcalculator;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private final int first;
    private final int second;
    private final char operator;
    private static final Map<String, Character> OPERATORS;
    private static final Map<String, Integer> NUMBERS;

    static {
        OPERATORS = new HashMap<String, Character>();
        OPERATORS.put("times", '*');
        OPERATORS.put("plus", '+');
        OPERATORS.put("minus", '-');
    }

    static {
        NUMBERS = new HashMap<String, Integer>();
        NUMBERS.put("zero", 0);
        NUMBERS.put("one", 1);
        NUMBERS.put("two", 2);
        NUMBERS.put("to", 2);
        NUMBERS.put("too", 2);
        NUMBERS.put("three", 3);
        NUMBERS.put("four", 4);
        NUMBERS.put("five", 5);
        NUMBERS.put("six", 6);
        NUMBERS.put("seven", 7);
        NUMBERS.put("eight", 8);
        NUMBERS.put("nine", 9);
        NUMBERS.put("ten", 10);
        NUMBERS.put("eleven", 11);
        NUMBERS.put("twelve", 12);
        NUMBERS.put("thirteen", 13);
        NUMBERS.put("fourteen", 14);
        NUMBERS.put("fifteen", 15);
        NUMBERS.put("sixteen", 16);
        NUMBERS.put("seventeen", 17);
        NUMBERS.put("eighteen", 18);
        NUMBERS.put("nineteen", 19);
        NUMBERS.put("twenty", 20);
        NUMBERS.put("thirty", 30);
        NUMBERS.put("fourty", 40);
        NUMBERS.put("fifty", 50);
        NUMBERS.put("sixty", 60);
        NUMBERS.put("seventy", 70);
        NUMBERS.put("eighty", 80);
        NUMBERS.put("ninety", 90);
    }

    public Parser(String[] input) throws Exception {
        if (input.length > 5) {
            throw new Exception("Unexpected array size\n");
        }
        int j = input.length;
        if (j == 3) {
            if (NUMBERS.containsKey(input[0]) && NUMBERS.containsKey(input[2])
                    && OPERATORS.containsKey(input[1])) {
                this.first = NUMBERS.get(input[0]);
                this.second = NUMBERS.get(input[2]);
                this.operator = OPERATORS.get(input[1]);
            } else {
                throw new Exception("Unexpected input\n");
            }
        } else if (j == 4) {
            if (OPERATORS.containsKey(input[1]) && NUMBERS.containsKey(input[0])
                    && NUMBERS.containsKey(input[2])
                    && NUMBERS.containsKey(input[3])) {
                this.first = NUMBERS.get(input[0]);
                this.operator = OPERATORS.get(input[1]);
                this.second = NUMBERS.get(input[2]) + NUMBERS.get(input[3]);
            } else if (OPERATORS.containsKey(input[2])
                    && NUMBERS.containsKey(input[1])
                    && NUMBERS.containsKey(input[0])
                    && NUMBERS.containsKey(input[3])) {
                this.first = NUMBERS.get(input[0]) + NUMBERS.get(input[1]);
                this.operator = OPERATORS.get(input[2]);
                this.second = NUMBERS.get(input[3]);
            } else {
                throw new Exception("Unexpected input\n");
            }
        } else {
            if (OPERATORS.containsKey(input[2]) && NUMBERS.containsKey(input[0])
                    && NUMBERS.containsKey(input[1])
                    && NUMBERS.containsKey(input[3])
                    && NUMBERS.containsKey(input[4])) {
                this.first = NUMBERS.get(input[0]) + NUMBERS.get(input[1]);
                this.operator = OPERATORS.get(input[2]);
                this.second = NUMBERS.get(input[3]) + NUMBERS.get(input[4]);
            } else {
                throw new Exception("Unexpected input\n");
            }
        }

    }

    public int parse() throws Exception {
        switch (this.operator) {
            case '+':
                return this.first + this.second;
            case '-':
                return this.first - this.second;
            case '*':
                return this.first * this.second;
            default:
                throw new Exception("Unexpected action\n");
        }
    }

    public void reportInput() {
        System.out.println(
                (new StringBuilder()).append("\nInterpreted expression: ")
                        .append(this.first).append(" ").append(this.operator)
                        .append(" ").append(this.second).append("\n"));
    }

}
