package Backend.IR.IRValue;

import Backend.IR.IRType.IRType;

public class Constant extends IRValue {
    public int val;

    public Constant(IRType type, int val) {
        super(type);
        this.val = val;
    }

    @Override
    public String ToString() {
        return "i32 " + String.valueOf(val);
    }

}
