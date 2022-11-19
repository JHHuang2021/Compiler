package Backend.IR.IRInstr;

import Backend.Assembly.ASMBuilder;

abstract public class IRInstr {
    public IRInstr() {
    }

    public String ToString() {
        return null;
    }

    public void accept(ASMBuilder visitor) {
    }
}
