package Util;

import java.util.ArrayList;
import java.util.HashMap;

import Frontend.AST.ExprNode;
import Frontend.AST.VarDefStmtNode;
import Util.error.semanticError;

public class Type {
    public String type_name;
    public int dim = 0;
    public ArrayList<ExprNode> dim_args = null;
    public HashMap<String, Type> members = null;
    public HashMap<String, Func> funcs = null;

    public Type() {
    }

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public Type(Type type, int dim) {
        this.type_name = type.type_name;
        this.dim_args = type.dim_args;
        this.funcs = type.funcs;
        this.members = type.members;
        this.dim = type.dim - dim;
    }

    public boolean Equal(Type rhs) {
        if (this.type_name.equals(rhs.type_name) && this.dim == rhs.dim)
            return true;
        else
            return false;
    }

    public Type ContainVarible(String v) {
        if (members != null && members.containsKey(v))
            return members.get(v);
        else
            return null;
    }

    public void DefineFunc(String name, Type retType, ArrayList<VarDefStmtNode> args, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        funcs.put(name, new Func(retType, args));
    }

    public Func ContainFunc(String name, position pos) {
        if (funcs != null && funcs.containsKey(name))
            return funcs.get(name);
        else
            return null;
    }
}
