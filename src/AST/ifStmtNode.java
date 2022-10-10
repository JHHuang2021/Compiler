package AST;

import Util.position;

public class ifStmtNode extends StmtNode {
    public ExprNode condition;
    public suiteStmtNode thenSuite, elseSuite;

    public ifStmtNode(ExprNode condition, suiteStmtNode thenSuite, suiteStmtNode elseSuite, position pos) {
        super(pos);
        this.condition = condition;
        this.thenSuite = thenSuite;
        this.elseSuite = elseSuite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
