package com.verbalcalculator;

import java.util.Map;
import java.util.HashMap;

public class Parser {

    private final int first;
    private final int second;
    private final char operator;
    private static final Map<String, Character> OPERATORS;
    private static final Map<String, Integer> NUMBERS;

    static{
        OPERATORS = new HashMap<>();
        OPERATORS.put("times", '*');
        OPERATORS.put("plus", '+');
        OPERATORS.put("minus", '-');
    }

    static{
        NUMBERS = new HashMap<>();
        NUMBERS.put("zero", 0);
        NUMBERS.put("one", 1);
        NUMBERS.put("two", 2);
        NUMBERS.put("three", 3);
        NUMBERS.put("four", 4);
        NUMBERS.put("five", 5);
        NUMBERS.put("six", 6);
        NUMBERS.put("seven", 7);
        NUMBERS.put("eight", 8);
        NUMBERS.put("nine", 9);
    }

    public Parser(String[] input) throws Exception {
        if(input.length > 3) throw new Exception("Unexpected array size\n");
        if(NUMBERS.containsKey(input[0]) && NUMBERS.containsKey(input[2]) &&
            OPERATORS.containsKey(input[1])) {
            first = NUMBERS.get(input[0]);
            second = NUMBERS.get(input[2]);
            operator = OPERATORS.get(input[1]);
        } else {
            throw new Exception("Unexpected input\n");
        }
    }

    public int parse() throws Exception {
        switch(operator) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            default:
                throw new Exception("Unexpected action\n");
        }
    }

    public void reportInput() {
        System.out.println(
                (new StringBuilder())
                        .append("\nInterpreted expression: ")
                        .append(first)
                        .append(" ")
                        .append(operator)
                        .append(" ")
                        .append(second)
                        .append("\n")
        );
    }

}
