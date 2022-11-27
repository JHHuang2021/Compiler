package Backend.IR.IRInstr;

import Backend.IR.IRValue.IRValue;
import Frontend.AST.LogicExprNode.LogicOpType;

public class IRCmp extends IRInstr {
    public IRValue dest;
    public IRValue lhs, rhs;
    public LogicOpType op_type;

    public IRCmp(IRValue dest, LogicOpType op_type, IRValue lhs, IRValue rhs) {
        this.dest = dest;
        this.op_type = op_type;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String ToString() {
        String str = dest.ToString() + " = icmp " + op_type.toString();
        str += lhs.type.ToString() + " " + lhs.ToString() + ", " + rhs.ToString();
        return str;
    }
}
