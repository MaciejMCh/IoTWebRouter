/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import requestOperations.Admin.InterpretedOperation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author maciej
 */
public class InterpretedTestOperation extends InterpretedOperation {
    
    public String firstArgument;
    public String secondArgument;
    public String thirdArgument;
    public boolean optionA;
    public boolean optionB;
    public boolean optionC;
    
    @Override
    public ArrayList<Argument> arguments() {
        return new ArrayList<Argument>() {{
            add(new Argument("firstArgument", "firstArgument"));
            add(new Argument("secondArgument", "secondArgument"));
            add(new Argument("thirdArgument", "thirdArgument"));
        }};
    }

    @Override
    public ArrayList<Option> options() {
        return new ArrayList<Option>(){{
            add(new Option("is_A", "is it A?", "optionA", "a"));
            add(new Option("is_B", "is it B?", "optionB", "b"));
            add(new Option("is_C", "is it C?", "optionC", "c"));
        }};
    }

    @Override
    public void performOperation() {
        
    }

    @Override
    public String description() {
        return "Just a test operation";
    }
    
}
