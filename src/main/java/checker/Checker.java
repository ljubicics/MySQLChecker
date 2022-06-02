package checker;

import checker.rules.PriorityRule;
import checker.rules.WhereRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Checker implements Rule{

    List<Rule> rules = new ArrayList<>();

    Stack<String> stack = new Stack<>();

    public Checker() {
        rules.add(new PriorityRule());
        rules.add(new WhereRule());
    }

    @Override
    public String check(String query) {

        String str = rules.get(1).check(query);
        return str;
    }
}
