package AST;

import Util.position;
import Util.Type;

public class newExprNode extends ExprNode {
    public newExprNode(Type newType, position pos) {
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
