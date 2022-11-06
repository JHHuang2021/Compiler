package Frontend.AST;

import Util.position;

public class ReturnStmtNode extends StmtNode {
    public ExprNode value;
    public boolean if_this = false;

    public ReturnStmtNode(position pos) {
        super(pos);
        this.if_this = true;
    }

    public ReturnStmtNode(ExprNode value, position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
