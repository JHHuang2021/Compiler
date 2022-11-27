package Backend.IR.IRInstr;

import Backend.IR.IRValue.IRValue;

public class IRStore extends IRInstr {
    IRValue lhs, rhs;

    public IRStore(IRValue lhs, IRValue rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String ToString() {
        String str = "";
        str += "store " + rhs.type.ToString() + " " + rhs.ToString() + ", " + lhs.type.ToString() + " "
                + lhs.ToString();
        return str;
    }
}
