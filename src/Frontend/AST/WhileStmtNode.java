package Frontend.AST;

import Util.position;

public class WhileStmtNode extends StmtNode {
    public ExprNode condition;
    public SuiteStmtNode while_suite;

    public WhileStmtNode(ExprNode condition, SuiteStmtNode while_suite, position pos) {
        super(pos);
        this.condition = condition;
        this.while_suite = while_suite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
