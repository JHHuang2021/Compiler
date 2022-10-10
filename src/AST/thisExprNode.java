package AST;

import Util.position;

public class thisExprNode extends ExprNode {

    public thisExprNode(position pos) {
        super(pos);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
