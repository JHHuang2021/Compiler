package Backend.IR.IRInstr;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRGetElementPtr extends IRInstr {
    public Register reg;
    public IRType type;
    public IRValue expr;
    public IRValue offset;
    public int ind = -1;

    public IRGetElementPtr(Register reg, IRType type, IRValue expr, IRValue offset) {
        this.reg = reg;
        this.type = type;
        this.expr = expr;
        this.offset = offset;
    }

    public IRGetElementPtr(Register reg, IRType type, IRValue expr, int ind) {
        this.reg = reg;
        this.type = type;
        this.expr = expr;
        this.ind = ind;
    }

    @Override
    public String ToString() {
        String str = "";
        if (offset == null)
            str += reg.ToString() + " = getelementptr inbounds " + type.ToString() + ", " + expr.type.ToString() + " "
                    + expr.ToString() + ", i32 0, " + "i32 " + ind;
        else
            str += reg.ToString() + " = getelementptr inbounds " + type.GetType() + ", " + type.GetType() + "* "
                    + expr.ToString() + ", i32 0, " + offset.ToString();

        return str;
    }
}
