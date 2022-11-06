package Frontend.AST;

import Util.position;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public SuiteStmtNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    public SuiteStmtNode(ArrayList<StmtNode> stmts, position pos) {
        super(pos);
        this.stmts = stmts;
    }

    public SuiteStmtNode(StmtNode stmt, position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
        this.stmts.add(stmt);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
