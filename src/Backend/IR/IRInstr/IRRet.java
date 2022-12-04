package Backend.IR.IRInstr;

import Backend.IR.IRType.IRVoid;
import Backend.IR.IRValue.IRValue;

public class IRRet extends IRInstr {
    public IRValue ret_val;

    public IRRet(IRValue val) {
        this.ret_val = val;
    }

    @Override
    public String ToString() {
        String str = "";
        str += "ret " + ret_val.type.ToString() + (ret_val.type instanceof IRVoid ? "" : " " + ret_val.ToString());
        return str;
    }
}
