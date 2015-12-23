package model;

import java.util.HashMap;

public interface SerializableModel {
    public abstract HashMap<String, String> JSONKeyPathsByPropertyKey();

}