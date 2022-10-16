package Util;

import Util.error.semanticError;

import java.util.ArrayList;
import java.util.HashMap;

import AST.varDefStmtNode;

public class globalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();
    private HashMap<String, Func> funcs = new HashMap<>();

    public globalScope(Scope parentScope) {
        super(parentScope);
        this.addType("int", new Type("int"), null);
        this.addType("string", new Type("string"), null);
        this.addType("bool", new Type("bool"), null);

    }

    public void defineFunc(String name, Type retType, ArrayList<varDefStmtNode> args, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        funcs.put(name, new Func(retType, args));
    }

    public Func containFunc(String name, position pos) {
        if (funcs.containsKey(name))
            return funcs.get(name);
        else
            throw new semanticError("multiple definition of " + name, pos);
    }

    public void addType(String name, Type t, position pos) {
        if (types.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        types.put(name, t);
    }

    public Type getTypeFromName(String name, position pos) {
        if (types.containsKey(name))
            return types.get(name);
        throw new semanticError("no such type: " + name, pos);
    }
}
