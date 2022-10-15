package AST;

import Util.Type;
import Util.position;

public class unaryExprNode extends ExprNode {
    public ExprNode expr;

    public enum unaryOpType {
        laddadd, raddadd, lsubsub, rsubsub, tilde, neg
    }

    public unaryOpType opCode;

    public unaryExprNode(ExprNode expr, unaryOpType opCode, position pos) {
        super(pos);
        this.expr = expr;
        this.opCode = opCode;
        this.type = new TypeNode(pos, new Type("int"));
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}