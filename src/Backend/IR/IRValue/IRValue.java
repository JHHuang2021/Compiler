package Backend.IR.IRValue;

import Backend.IR.IRType.IRType;

abstract public class IRValue {
    public IRType type;

    public IRValue(IRType type) {
        this.type = type;
    }

    public String ToString() {
        return null;
    }
}
