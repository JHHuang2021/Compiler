package Backend.IR.IRInstr;

import java.io.Console;

import Backend.IR.IRValue.Constant;
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
        String str = dest.ToString() + " = icmp " + op_type.toString() + " ";
        // if (!(lhs instanceof Constant))
        str += lhs.type.ToString() + " ";
        str += lhs.ToString() + ", ";
        // if (!(rhs instanceof Constant))
        // str += rhs.type.ToString() + " ";
        str += rhs.ToString();
        return str;
    }
}
