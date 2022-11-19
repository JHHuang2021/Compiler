package Frontend.AST;

import Util.position;

public class ExprArrayNode extends ExprNode {
    public ExprNode expr = null;
    public ExprNode offset = null;

    public ExprArrayNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return expr.isAssignable();
    }

}
