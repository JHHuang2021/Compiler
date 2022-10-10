package AST;

import Util.position;

import java.util.ArrayList;

public class suiteStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public suiteStmtNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
