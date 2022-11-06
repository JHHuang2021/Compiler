package Frontend.AST;

import Util.position;

public class ThisExprNode extends ExprNode {

    public ThisExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean IfThis() {
        return true;
    }
}
