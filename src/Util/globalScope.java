package Util;

import Util.error.semanticError;

import java.util.ArrayList;
import java.util.HashMap;

import AST.varDefStmtNode;
import AST.varDefStmtNode.Var;

public class globalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();
    private HashMap<String, Func> funcs = new HashMap<>();

    public globalScope(Scope parentScope) {
        super(parentScope);
        Type intType = new Type("int");
        Type stringType = new Type("string");
        Type voidType = new Type("void");
        Type boolType = new Type("bool");
        this.addType("int", intType, null);

        stringType.funcs = new HashMap<>();

        stringType.funcs.put("length", new Func(intType, null));

        ArrayList<varDefStmtNode> argSub = new ArrayList<>();
        argSub.add(new varDefStmtNode(null, new Var(intType, "left", null)));
        argSub.add(new varDefStmtNode(null, new Var(intType, "right", null)));
        stringType.funcs.put("substring", new Func(stringType, argSub));

        stringType.funcs.put("parseInt", new Func(intType, null));

        ArrayList<varDefStmtNode> argOrd = new ArrayList<>();
        argOrd.add(new varDefStmtNode(null, new Var(intType, "pos", null)));
        stringType.funcs.put("ord", new Func(intType, argOrd));

        this.addType("string", stringType, null);

        this.addType("bool", boolType, null);
        this.addType("void", voidType, null);

        ArrayList<varDefStmtNode> argStr = new ArrayList<>();
        argStr.add(new varDefStmtNode(null, new Var(stringType, "str", null)));
        this.defineFunc("print", voidType, argStr, null);

        this.defineFunc("println", voidType, argStr, null);

        ArrayList<varDefStmtNode> argInt = new ArrayList<>();
        argInt.add(new varDefStmtNode(null, new Var(intType, "int", null)));
        this.defineFunc("printInt", voidType, argInt, null);

        this.defineFunc("printlnInt", voidType, argInt, null);
        this.defineFunc("getString", stringType, null, null);
        this.defineFunc("getInt", intType, null, null);
        this.defineFunc("toString", stringType, argInt, null);

    }

    public void defineFunc(String name, Type retType, ArrayList<varDefStmtNode> args, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        funcs.put(name, new Func(retType, args));
    }

    public void delFunc(String name) {
        funcs.remove(name);
    }

    public Func containFunc(String name, position pos) {
        if (funcs.containsKey(name))
            return funcs.get(name);
        else
            return null;
    }

    public void addType(String name, Type t, position pos) {
        if (types.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        t.typeName = name;
        types.put(name, t);
    }

    public Type containType(String name, position pos) {
        if (types.containsKey(name))
            return types.get(name);
        else
            return null;
    }
}
