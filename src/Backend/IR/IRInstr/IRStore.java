package Backend.IR.IRInstr;

import Backend.IR.IRValue.IRValue;

public class IRStore extends IRInstr {
    IRValue val, dest;

    public IRStore(IRValue val, IRValue dest) {
        this.val = val;
        this.dest = dest;
    }

    @Override
    public String ToString() {
        String str = "";
        str += "store " + val.type.ToString() + " " + val.ToString() + ", " + dest.type.ToString() + " "
                + dest.ToString();
        return str;
    }
}
