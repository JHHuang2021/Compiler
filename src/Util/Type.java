package Util;

import java.util.ArrayList;
import java.util.HashMap;

import AST.ExprNode;

public class Type {
    public String typeName;
    public boolean ifconst = false;
    public boolean array = false;
    public int dim = 0;
    public ArrayList<ExprNode> dimArgs;
    public HashMap<String, Type> members = null;

    public Type() {
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Type containsVarible(String v) {
        if (members != null && members.containsKey(v))
            return members.get(v);
        else
            return null;
    }

}
