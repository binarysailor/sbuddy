package bravura.sonata.buddy.navigsearch;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isUpperCase;


public class NavigatorLocation {
    private final Deque<String> path = new LinkedList<>(); // starts with top level option, ends with the searched option

    public void addParentOption(String optionName) {
        path.addFirst(optionName);
    }

    public String asPathString() {
        return path.stream().collect(Collectors.joining("  >  "));
    }

    public boolean isClassicMenu() {
        // purely heuristic! to be investigated for a real solution
        String topLevel = path.getFirst();
        return !isStringUpperCase(topLevel);
    }

    private boolean isStringUpperCase(String topLevel) {
        char[] topLevelChars = topLevel.toCharArray();
        for (char c : topLevelChars) {
            if (isAlphabetic(c) && !isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
}
