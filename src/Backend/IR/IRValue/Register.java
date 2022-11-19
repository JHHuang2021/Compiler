package Backend.IR.IRValue;

import Backend.IR.IRType.IRType;

public class Register extends IRValue {
    public String name;
    public int reg_cnt;

    public Register(IRType type, String name, int reg_cnt) {
        super(type);
        this.name = name;
        this.reg_cnt = reg_cnt;
    }

}
