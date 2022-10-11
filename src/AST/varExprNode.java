package AST;

import java.util.ArrayList;

import Util.position;

public class varExprNode extends ExprNode {
    public boolean ifthis = false;
    public String name;
    public int dim = 0;
    public ArrayList<ExprNode> dimArgs = null;

    public varExprNode(position pos) {
        super(pos);
        this.ifthis = true;
    }

    public varExprNode(String name, position pos) {
        super(pos);
        this.name = name;
    }

    public varExprNode(String name, int dim, ArrayList<ExprNode> dimArgs, position pos) {
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
