package net.prsv.udfa;

import java.util.HashMap;
import java.util.HashSet;

public class StateMachine {
    private final HashSet<String> states;
    private final HashSet<Character> alphabet;
    private final HashSet<String> acceptStates;
    private final HashMap<Pair, String> transitions;

    private final String startState;

    public StateMachine(HashSet<String> states,
                        HashSet<Character> alphabet,
                        HashSet<String> acceptStates,
                        HashMap<Pair, String> transitions,
                        String startState) {
        this.states = states;
        this.alphabet = alphabet;
        this.acceptStates = acceptStates;
        this.transitions = transitions;
        this.startState = startState;
    }

    public void printConfig() {
        System.out.println("===== DFA configuration =====");
        System.out.print("States: ");
        states.forEach(state -> System.out.print(state + " "));
        System.out.println("\nStart state: " + startState);
        System.out.print("Accept states: ");
        acceptStates.forEach(state -> System.out.print(state + " "));
        System.out.print("\nAlphabet: ");
        alphabet.forEach(symbol -> System.out.print(symbol + " "));
        System.out.println("\nTransitions: ");
        transitions.keySet().forEach(key -> System.out.println("(" + key + ") -> " + transitions.get(key)));
    }

    public void printSummary() {
        System.out.println("===== DFA summary =====");
        System.out.println("States: " + states.size() + ", accept states: " +
                acceptStates.size() + ", transitions: " +
                transitions.size());
        System.out.print("Alphabet: ");
        alphabet.forEach(symbol -> System.out.print(symbol + " "));
        System.out.println();
    }

    public boolean run(String input) {
        System.out.println("Start state: " + startState);
        String currentState = startState;
        if(!input.equals("")) {
            for (int i = 0; i < input.length(); i++) {
                char symbol = input.charAt(i);
                System.out.print("Current symbol: '" + symbol + "'. ");
                if (!alphabet.contains(symbol)) {
                    System.out.println("Symbol not defined");
                    return false;
                }
                currentState = transitions.get(new Pair(currentState, symbol));
                System.out.println("Moving to state " + currentState);
            }
        }
        return acceptStates.contains(currentState);
    }

}
