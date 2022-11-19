package Backend.IR.IRInstr;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.IRValue;

public class IRAlloc extends IRInstr {
    public IRValue val;
    public IRType type;

    public IRAlloc(IRType type, IRValue val) {
        this.type = type;
        this.val = val;
    }
}
