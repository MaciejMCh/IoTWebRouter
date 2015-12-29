/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;

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
            add(new Argument("first_argument", "first argument", "firstArgument", true));
            add(new Argument("second_argument", "second argument", "secondArgument", true));
            add(new Argument("third_argument", "third argument", "thirdArgument", false));
        }};
    }

    @Override
    public ArrayList<Option> options() {
        return new ArrayList<Option>(){{
            add(new Option("is_a", "is it A?", "optionA", "a"));
            add(new Option("is_b", "is it B?", "optionB", "b"));
            add(new Option("is_c", "is it C?", "optionC", "c"));
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
