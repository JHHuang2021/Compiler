package Backend.IR.IRInstr;

import Backend.IR.IRValue.IRValue;
import Frontend.AST.BinaryExprNode.BinaryOpType;

public class IRBin extends IRInstr {
    public IRValue val;

    public BinaryOpType op;
    public IRValue lhs, rhs;

    public IRBin(IRValue val,BinaryOpType op,IRValue lhs,IRValue rhs){
        super();
        this.val=val;
        this.op=op;
        this.lhs=lhs;
        this.rhs=rhs;
    }
}
