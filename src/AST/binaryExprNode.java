package AST;

import Util.Type;
import Util.position;

public class binaryExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum binaryOpType {
        add, sub, star, div, mod, lshift, rshift, and, or, xor
    }

    public binaryOpType opCode;

    public binaryExprNode(ExprNode lhs, ExprNode rhs, binaryOpType opCode, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
        this.type = new TypeNode(pos, new Type("int"));

    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
