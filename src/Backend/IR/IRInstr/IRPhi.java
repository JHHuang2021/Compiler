package Backend.IR.IRInstr;

import Backend.IR.IRBlock.IRBlock;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRPhi extends IRInstr {
    public Register reg;
    public IRValue val1, val2;
    public IRBlock predecessor1, predecessor2;

    public IRPhi(Register reg, IRValue val1, IRValue val2, IRBlock predecessor1, IRBlock predecessor2) {
        this.reg = reg;
        this.val1 = val1;
        this.val2 = val2;
        this.predecessor1 = predecessor1;
        this.predecessor2 = predecessor2;
    }

    @Override
    public String ToString() {
        String str = "";
        str += reg.ToString() + " = phi " + reg.type.ToString() + " [ " + val1.ToString() + ", "
                + predecessor1.PrintInSentence() + " ], [ " + val2.ToString() + ", " + predecessor2.PrintInSentence() + " ]";
        return str;
    }
}
