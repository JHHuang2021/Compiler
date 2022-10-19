package AST;

import Util.position;

public class returnStmtNode extends StmtNode {
    public ExprNode value;
    public boolean ifthis = false;

    public returnStmtNode(position pos) {
        super(pos);
        this.ifthis = true;
    }

    public returnStmtNode(ExprNode value, position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
