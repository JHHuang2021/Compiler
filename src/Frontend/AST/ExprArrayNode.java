package Frontend.AST;

import java.util.ArrayList;

import Util.position;

public class ExprArrayNode extends ExprNode {
    public ArrayList<ExprNode> expr = new ArrayList<>();

    public ExprArrayNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return expr.get(0).isAssignable();
    }

}
