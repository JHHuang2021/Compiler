package Backend.IR.IRInstr;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRGetElementPtr extends IRInstr {
    public Register reg;
    public IRType type;
    public IRValue expr;
    public IRValue offset;

    public IRGetElementPtr(Register reg, IRType type, IRValue expr, IRValue offset) {
        this.reg = reg;
        this.type = type;
        this.expr = expr;
        this.offset = offset;
    }
}
