package AST;

import Util.position;

public class thisExprNode extends ExprNode {

    public thisExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean ifThis() {
        return true;
    }
}
