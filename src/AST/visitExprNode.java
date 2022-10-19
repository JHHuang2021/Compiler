package AST;

import Util.position;

public class visitExprNode extends ExprNode {
    public ExprNode visitor = null, visitee = null;

    public visitExprNode(position pos, ExprNode visitor, ExprNode visitee) {
        super(pos);
        this.visitor = visitor;
        this.visitee = visitee;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
