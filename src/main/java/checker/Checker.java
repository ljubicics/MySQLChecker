package checker;

import checker.rules.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

@Getter
@Setter
public class Checker{

    Stack<String> mistakes = new Stack<>();
    List<Rule> rules = new ArrayList<>();

    Stack<String> stack = new Stack<>();

    PriorityRule priorityRule = new PriorityRule();
    WhereRule whereRule = new WhereRule();
    AsRule asRule = new AsRule();
    RequiredWordRule requiredWordRule = new RequiredWordRule();
    GroupByRule groupByRule = new GroupByRule();
    CSVFileRule csvFileRule = new CSVFileRule();
    ForeignKeyRule foreignKeyRule = new ForeignKeyRule();
    CheckColumnsTablesRule checkColumnsTablesRule = new CheckColumnsTablesRule();
    DescriptorRepository descriptorRepository = new DescriptorRepository();

    public Checker() {
        //rules.add(checkColumnsTablesRule);
        rules.add(priorityRule);
        rules.add(whereRule);
        rules.add(asRule);
        rules.add(requiredWordRule);
        rules.add(groupByRule);
        //rules.add(foreignKeyRule);
    }

    public Stack<String> check(String query) {

        String str = "";

        for(int i = 0; i < rules.size(); i++) {
            str = rules.get(i).check(query);
            if(!Objects.equals(str, "null")) {
                this.mistakes.push(str);
            }
        }

        return mistakes;
    }
}
