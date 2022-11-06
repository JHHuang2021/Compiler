package Util;

import java.util.ArrayList;
import Frontend.AST.VarDefStmtNode;

public class Func {
    public Type ret_type;
    public ArrayList<VarDefStmtNode> args = null;

    public Func(Type retType, ArrayList<VarDefStmtNode> args) {
        this.ret_type = retType;
        this.args = args;
    }
}
