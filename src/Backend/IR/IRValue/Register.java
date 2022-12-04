package Backend.IR.IRValue;

import Backend.IR.IRType.IRPointer;
import Backend.IR.IRType.IRString;
import Backend.IR.IRType.IRType;

public class Register extends IRValue {
    public String name;
    public int reg_cnt;
    public boolean is_global;

    public Register(IRType type, String name, boolean is_global, int reg_cnt) {
        super(type);
        this.name = name;
        this.reg_cnt = reg_cnt;
        this.is_global = is_global;
    }

    public String ToString() {
        return (is_global ? "@" : "%") + name + "_" + reg_cnt;
    }

    public String Declare() {
        String str = ToString();
        if (is_global)
            str += " = global " + type.FatherType().ToString() + (type.FatherType() instanceof IRString ? " null" : " zeroinitializer");
        else
            str += " = alloca " + type.ToString();
        return str;
    }

}
