package AST;

import java.util.ArrayList;

import Util.position;

public class newExprNode extends ExprNode {
    public ArrayList<ExprNode> args = null;

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
