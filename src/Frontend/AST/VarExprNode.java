package Frontend.AST;

import Util.position;

public class VarExprNode extends ExprNode {

    public String name;

    public VarExprNode(position pos, String name) {
        super(pos);
        this.name = name;
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
