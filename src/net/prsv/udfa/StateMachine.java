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

    public String config() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== DFA configuration =====\n");
        sb.append("States: ");
        states.forEach(state -> sb.append(state).append(" "));
        sb.append("\nStart state: ").append(startState).append("\n");
        sb.append("Accept states: ");
        acceptStates.forEach(state -> sb.append(state).append(" "));
        sb.append("\nAlphabet: ");
        alphabet.forEach(symbol -> sb.append(symbol).append(" "));
        sb.append("\nTransitions: \n");
        transitions.keySet().forEach(key -> sb.append("(").append(key).append(") -> ").append(transitions.get(key)).append("\n"));
        return sb.toString();
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== DFA summary =====\n");
        sb.append("States: ").
                append(states.size()).
                append(", accept states: ").
                append(acceptStates.size()).
                append(", transitions: ").
                append(transitions.size()).
                append("\n");
        sb.append("Alphabet: ");
        alphabet.forEach(symbol -> sb.append(symbol).append(" "));
        sb.append("\n");
        return sb.toString();
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
