package AST;

import Util.position;

public class logicExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum logicOpType {
        eq, neq, le, ge, leq, geq, not, andand, oror
    }

    public logicOpType opCode;

    public logicExprNode(ExprNode lhs, ExprNode rhs, logicOpType opCode, TypeNode boolType, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
        type = boolType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
