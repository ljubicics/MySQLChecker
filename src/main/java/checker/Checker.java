package checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Checker implements Rule{

    List<Rule> rules = new ArrayList<>();

    Stack<String> stack = new Stack<>();

    public Checker() {

    }

    @Override
    public String check(String query) {
        return null;
    }
}
