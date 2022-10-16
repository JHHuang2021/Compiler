package Util;

import java.util.ArrayList;
import AST.varDefStmtNode;

public class Func {
    public Type retType;
    public ArrayList<varDefStmtNode> args = null;

    public Func(Type retType, ArrayList<varDefStmtNode> args) {
        this.retType = retType;
        this.args = args;
    }
}
