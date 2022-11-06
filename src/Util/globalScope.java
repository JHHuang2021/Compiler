package Util;

import Util.error.semanticError;

import java.util.ArrayList;
import java.util.HashMap;

import Frontend.AST.VarDefStmtNode;
import Frontend.AST.VarDefStmtNode.Var;

public class GlobalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();
    private HashMap<String, Func> funcs = new HashMap<>();
    private HashMap<String, position> global_varibles = new HashMap<>();

    public GlobalScope(Scope parent_scope) {
        super(parent_scope);
        Type int_type = new Type("int");
        Type string_type = new Type("string");
        Type void_type = new Type("void");
        Type bool_type = new Type("bool");
        this.AddType("int", int_type, null);

        string_type.funcs = new HashMap<>();

        string_type.funcs.put("length", new Func(int_type, null));

        ArrayList<VarDefStmtNode> arg_sub = new ArrayList<>();
        arg_sub.add(new VarDefStmtNode(null, new Var(int_type, "left", null)));
        arg_sub.add(new VarDefStmtNode(null, new Var(int_type, "right", null)));
        string_type.funcs.put("substring", new Func(string_type, arg_sub));

        string_type.funcs.put("parseInt", new Func(int_type, null));

        ArrayList<VarDefStmtNode> arg_ord = new ArrayList<>();
        arg_ord.add(new VarDefStmtNode(null, new Var(int_type, "pos", null)));
        string_type.funcs.put("ord", new Func(int_type, arg_ord));

        this.AddType("string", string_type, null);

        this.AddType("bool", bool_type, null);
        this.AddType("void", void_type, null);

        ArrayList<VarDefStmtNode> arg_str = new ArrayList<>();
        arg_str.add(new VarDefStmtNode(null, new Var(string_type, "str", null)));
        this.DefineFunc("print", void_type, arg_str, null);

        this.DefineFunc("println", void_type, arg_str, null);

        ArrayList<VarDefStmtNode> arg_int = new ArrayList<>();
        arg_int.add(new VarDefStmtNode(null, new Var(int_type, "int", null)));
        this.DefineFunc("printInt", void_type, arg_int, null);

        this.DefineFunc("printlnInt", void_type, arg_int, null);
        this.DefineFunc("getString", string_type, null, null);
        this.DefineFunc("getInt", int_type, null, null);
        this.DefineFunc("toString", string_type, arg_int, null);

    }

    public void DefineFunc(String name, Type retType, ArrayList<VarDefStmtNode> args, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        funcs.put(name, new Func(retType, args));
    }

    public void DelFunc(String name) {
        funcs.remove(name);
    }

    public Func ContainFunc(String name, position pos) {
        if (funcs.containsKey(name))
            return funcs.get(name);
        else
            return null;
    }

    public void AddType(String name, Type t, position pos) {
        if (types.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        t.type_name = name;
        types.put(name, t);
    }

    public Type ContainType(String name, position pos) {
        if (types.containsKey(name))
            return types.get(name);
        else
            return null;
    }

    public void PutDefineVariblePos(String name, position pos) {
        global_varibles.put(name, pos);
    }

    public position GetDefineVariblePos(String name) {
        return global_varibles.get(name);
    }
}
