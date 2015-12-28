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
    public ArrayList<String> argumentPropertyNames() {
        return new ArrayList<String>() {{
            add("firstArgument");
            add("secondArgument");
            add("thirdArgument");
        }};
    }

    @Override
    public HashMap<String, String> optionByPropertyKey() {
        return new HashMap<String, String>(){{
            put("a", "optionA");
            put("b", "optionB");
            put("c", "optionC");
        }};
    }

    @Override
    public void performOperation() {
        
    }
    
}
