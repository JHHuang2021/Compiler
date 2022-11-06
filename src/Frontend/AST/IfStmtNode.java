package Frontend.AST;

import Util.position;

public class IfStmtNode extends StmtNode {
    public ExprNode condition;
    public SuiteStmtNode then_suite, else_suite;

    public IfStmtNode(ExprNode condition, SuiteStmtNode then_suite, SuiteStmtNode else_suite, position pos) {
        super(pos);
        this.condition = condition;
        this.then_suite = then_suite;
        this.else_suite = else_suite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
