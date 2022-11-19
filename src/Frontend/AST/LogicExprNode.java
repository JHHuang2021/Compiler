package Frontend.AST;

import Util.Type;
import Util.position;

public class LogicExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum LogicOpType {
        eq, neq, le, ge, leq, geq, not, andand, oror
    }

    public LogicOpType op_code;

    public LogicExprNode(ExprNode lhs, ExprNode rhs, LogicOpType op_code, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op_code = op_code;
        this.type = new Type("bool");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
