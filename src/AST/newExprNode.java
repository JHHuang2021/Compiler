package AST;

import Util.position;

public class newExprNode extends ExprNode {
    public newExprNode(TypeNode newType, position pos) {
        super(pos);
        this.type = newType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}
