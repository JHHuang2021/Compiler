package AST;

import Util.position;

public class binaryExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum binaryOpType {
        add, sub, star, div, lshift, rshift
    }

    public binaryOpType opCode;

    public binaryExprNode(ExprNode lhs, ExprNode rhs, binaryOpType opCode, TypeNode intType, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
        type = intType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
