package Frontend.AST;

import Util.position;

public class ForStmtNode extends StmtNode {
    public StmtNode var;
    public ExprNode condition, step;
    public SuiteStmtNode for_suite;

    public ForStmtNode(StmtNode var, ExprNode condition, ExprNode step, SuiteStmtNode for_suite, position pos) {
        super(pos);
        this.var = var;
        this.condition = condition;
        this.step = step;
        this.for_suite = for_suite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
