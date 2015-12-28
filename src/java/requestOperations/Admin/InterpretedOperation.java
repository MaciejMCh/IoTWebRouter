/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public abstract class InterpretedOperation extends RequestOperation implements SerializableModel {

    protected String query;
    protected ArrayList<String> options;
    protected ArrayList<String> arguments;

    public abstract ArrayList<Argument> arguments();
    public abstract ArrayList<Option> options();
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {{
            put("query", "query");
        }};
    }
}
