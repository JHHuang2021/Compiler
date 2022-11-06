package Frontend.AST;

import Util.position;

public class AssignExprNode extends ExprNode{
    public ExprNode lhs, rhs;

    public AssignExprNode(ExprNode lhs, ExprNode rhs, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
