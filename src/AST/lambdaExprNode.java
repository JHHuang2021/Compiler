package AST;

import java.util.ArrayList;

import Util.position;

public class lambdaExprNode extends ExprNode {
    public ArrayList<varDefStmtNode> argsDef = new ArrayList<>();
    public ArrayList<ExprNode> args = new ArrayList<>();
    public ArrayList<StmtNode> stmts=new ArrayList<>();
    public boolean ifgScope = false;

    public lambdaExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
