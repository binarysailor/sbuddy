package bravura.sonata.buddy.navigsearch;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        return !isUpperCase(topLevel);
    }

    private boolean isUpperCase(String topLevel) {
        char[] topLevelChars = topLevel.toCharArray();
        for (char c : topLevelChars) {
            if (Character.isAlphabetic(c) && !Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
}
