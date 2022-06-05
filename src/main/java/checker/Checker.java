package checker;

import checker.rules.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Getter
@Setter
public class Checker implements Rule{

    List<Rule> rules = new ArrayList<>();

    Stack<String> stack = new Stack<>();

    PriorityRule priorityRule = new PriorityRule();
    WhereRule whereRule = new WhereRule();
    AsRule asRule = new AsRule();
    RequiredWordRule requiredWordRule = new RequiredWordRule();
    GroupByRule groupByRule = new GroupByRule();
    CSVFileRule csvFileRule = new CSVFileRule();

    public Checker() {
        rules.add(priorityRule);
        rules.add(whereRule);
        rules.add(asRule);
        rules.add(requiredWordRule);
        rules.add(groupByRule);
    }

    @Override
    public String check(String query) {

        String str = rules.get(4).check(query);
        return str;
    }
}
