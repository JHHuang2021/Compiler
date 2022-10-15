package AST;

import Util.position;

import java.util.ArrayList;

public class FnNode extends ASTNode {
    public ArrayList<StmtNode> stmts;
    public ArrayList<varDefStmtNode> argsDef;
    public TypeNode retType;
    public String funcName;

    public FnNode(position pos, TypeNode retType, String funcName) {
        super(pos);
        stmts = new ArrayList<>();
        argsDef = new ArrayList<>();
        this.retType = retType;
        this.funcName = funcName;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
