package AST;

import java.util.ArrayList;

import Util.position;

public class varExprNode extends ExprNode {

    public String name;
    public int dim = 0;
    public ArrayList<ExprNode> dimArgs = null;

    public varExprNode(position pos, String name, int dim, ArrayList<ExprNode> dimArgs) {
        super(pos);
        this.name = name;
        this.dim = dim;
        this.dimArgs = dimArgs;
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
