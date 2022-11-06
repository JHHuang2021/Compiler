package Frontend.AST;

import Util.position;

public class VisitExprNode extends ExprNode {
    public ExprNode visitor = null, visitee = null;

    public VisitExprNode(position pos, ExprNode visitor, ExprNode visitee) {
        super(pos);
        this.visitor = visitor;
        this.visitee = visitee;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable(){
        return visitee.isAssignable();
    }

}
