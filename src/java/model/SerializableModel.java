package model;

import java.util.HashMap;

public abstract class SerializableModel {
    public abstract HashMap<String, String> JSONKeyPathsByPropertyKey();
}