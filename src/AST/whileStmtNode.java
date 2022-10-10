package AST;

import Util.position;

public class whileStmtNode extends StmtNode {
    public ExprNode condition;
    public suiteStmtNode whileSuite;

    public whileStmtNode(ExprNode condition, suiteStmtNode whileSuite, position pos) {
        super(pos);
        this.condition = condition;
        this.whileSuite = whileSuite;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
