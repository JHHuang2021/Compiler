package Frontend.AST;

import java.util.ArrayList;

import Util.position;

public class VarExprNode extends ExprNode {

    public String name;
    public int dim = 0;
    public ArrayList<ExprNode> dim_args = null;

    public VarExprNode(position pos, String name, int dim, ArrayList<ExprNode> dim_args) {
        super(pos);
        this.name = name;
        this.dim = dim;
        this.dim_args = dim_args;
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
