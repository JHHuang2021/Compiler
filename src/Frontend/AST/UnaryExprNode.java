package Frontend.AST;

import Util.Type;
import Util.position;

public class UnaryExprNode extends ExprNode {
    public ExprNode expr;

    public enum UnaryOpType {
        laddadd, raddadd, lsubsub, rsubsub, tilde, neg
    }

    public UnaryOpType opCode;

    public UnaryExprNode(ExprNode expr, UnaryOpType opCode, position pos) {
        super(pos);
        this.expr = expr;
        this.opCode = opCode;
        this.type = new Type("int");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        if (opCode == UnaryOpType.laddadd || opCode == UnaryOpType.lsubsub)
            return expr.isAssignable();
        else
            return false;
    }
}