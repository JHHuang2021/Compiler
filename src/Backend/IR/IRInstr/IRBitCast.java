package Backend.IR.IRInstr;

import Backend.IR.IRValue.Register;

public class IRBitCast extends IRInstr {
    public Register from;
    public Register to;

    public IRBitCast(Register from, Register to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String ToString() {
        String str = "";
        str += to.ToString() + " = bitcast " + from.type.ToString() + " " + from.ToString() + " to " + to.type.ToString();
        return str;
    }

}
