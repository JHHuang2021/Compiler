package AST;

import Util.position;

public class exprArrayExprNode extends ExprNode {
    public ExprNode exp1 = null, exp2 = null;

    public exprArrayExprNode(position pos, ExprNode exp1, ExprNode exp2) {
        super(pos);
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
