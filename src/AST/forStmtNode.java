package AST;

import Util.position;

public class forStmtNode extends StmtNode {
    public ExprNode var, condition, step;
    public suiteStmtNode forSuite;

    public forStmtNode(ExprNode var, ExprNode condition, ExprNode step, suiteStmtNode forSuite, position pos) {
        super(pos);
        this.var = var;
        this.condition = condition;
        this.step = step;
        this.forSuite = forSuite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
