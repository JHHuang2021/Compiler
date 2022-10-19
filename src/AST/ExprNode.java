package AST;

import Util.position;

public abstract class ExprNode extends ASTNode {
    public TypeNode type;
    // public entity val;

    public ExprNode(position pos) {
        super(pos);
    }

    public boolean isAssignable() {
        return false;
    }
}
