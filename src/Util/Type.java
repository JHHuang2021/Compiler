package Util;

import java.util.ArrayList;
import java.util.HashMap;

import AST.ExprNode;
import AST.varDefStmtNode;
import Util.error.semanticError;

public class Type {
    public String typeName;
    public int dim = 0;
    public ArrayList<ExprNode> dimArgs = null;
    public HashMap<String, Type> members = null;
    public HashMap<String, Func> funcs = null;

    public Type() {
    }

    // public Type(int i) {
    //     this.typeName = "int";
    // }

    public Type(String typeName) {
        this.typeName = typeName;
        // funcs = new HashMap<>();
        // funcs.put("size", new Func(new Type(0), null));
    }

    public Type containVarible(String v) {
        if (members != null && members.containsKey(v))
            return members.get(v);
        else
            return null;
    }

    public void defineFunc(String name, Type retType, ArrayList<varDefStmtNode> args, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        funcs.put(name, new Func(retType, args));
    }

    public Func containFunc(String name, position pos) {
        if (funcs != null && funcs.containsKey(name))
            return funcs.get(name);
        else
            return null;
    }
}
