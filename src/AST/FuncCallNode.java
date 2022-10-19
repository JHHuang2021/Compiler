package AST;

import Util.position;

public class FuncCallNode extends ExprNode {
    public ExprNode exprBeforeCall = null;
    public funcCallExprNode funcCall = null;

    public FuncCallNode(position pos, ExprNode exprBeforeCall, funcCallExprNode funcCall) {
        super(pos);
        this.exprBeforeCall = exprBeforeCall;
        this.funcCall = funcCall;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
