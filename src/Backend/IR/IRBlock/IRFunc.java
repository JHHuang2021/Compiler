package Backend.IR.IRBlock;

import java.util.ArrayList;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRFunc {
    public ArrayList<IRBlock> blocks = new ArrayList<>();
    public ArrayList<IRValue> args = new ArrayList<>();
    public String func_name;
    public IRType ret_type;
    public Register this_ptr = null;
    public boolean is_builtin = false;

    public IRFunc(String func_name, IRType ret_type) {
        this.func_name = func_name;
        this.ret_type = ret_type;
    }
}
