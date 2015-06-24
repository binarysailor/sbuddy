package bravura.sonata.buddy.navigsearch;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class NavigatorLocation {
    private Deque<String> path = new LinkedList<>(); // starts with top level option, ends with the searched option

    public void addParentOption(String optionName) {
        path.addFirst(optionName);
    }

    public String asPathString() {
        return path.stream().collect(Collectors.joining(" > "));
    }
}
