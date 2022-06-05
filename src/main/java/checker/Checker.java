package checker;

import checker.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Checker implements Rule{

    List<Rule> rules = new ArrayList<>();

    Stack<String> stack = new Stack<>();

    public Checker() {
        rules.add(new PriorityRule());
        rules.add(new WhereRule());
        rules.add(new AsRule());
        rules.add(new RequiredWordRule());
        rules.add(new GroupByRule());
    }

    @Override
    public String check(String query) {

        String str = rules.get(4).check(query);
        return str;
    }
}
