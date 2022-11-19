package Backend.IR.IRInstr;

import Backend.IR.IRValue.IRValue;

public class IRRet extends IRInstr {
    public IRValue ret_val;

    public IRRet(IRValue val) {
        this.ret_val = val;
    }
}
