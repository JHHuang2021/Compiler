package AST;

import Util.position;

import java.util.ArrayList;

public class suiteStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public suiteStmtNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    public suiteStmtNode(ArrayList<StmtNode> stmts, position pos) {
        super(pos);
        this.stmts = stmts;
    }

    public suiteStmtNode(StmtNode stmt, position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
        this.stmts.add(stmt);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
