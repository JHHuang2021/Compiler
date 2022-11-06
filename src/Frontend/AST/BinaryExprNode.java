package Frontend.AST;

import Util.Type;
import Util.position;

public class BinaryExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum BinaryOpType {
        add, sub, star, div, mod, lshift, rshift, and, or, xor
    }

    public BinaryOpType opCode;

    public BinaryExprNode(ExprNode lhs, ExprNode rhs, BinaryOpType opCode, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
        this.type = new Type("int");

    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
