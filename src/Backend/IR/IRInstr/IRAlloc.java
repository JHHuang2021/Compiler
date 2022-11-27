package Backend.IR.IRInstr;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRAlloc extends IRInstr {
    public IRValue val;
    public IRType type;

    public IRAlloc(IRType type, IRValue val) {
        this.type = type;
        this.val = val;
    }

    @Override
    public String ToString() {
        if (((Register) val).is_global)
            return "";
        String str = "";
        str += val.ToString() + " = alloca " + type.FatherType().GetType();
        return str;
    }
}
