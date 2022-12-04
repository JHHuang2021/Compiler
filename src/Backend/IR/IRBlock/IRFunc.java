package Backend.IR.IRBlock;

import java.util.ArrayList;

import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.Register;

public class IRFunc {
    public ArrayList<IRBlock> blocks = new ArrayList<>();
    public ArrayList<Register> args = new ArrayList<>();
    public String func_name;
    public IRType ret_type;
    public Register this_ptr = null;
    public boolean is_builtin = false;
    public boolean has_return = false;

    public IRFunc(String func_name, IRType ret_type) {
        this.func_name = func_name;
        this.ret_type = ret_type;
    }

    public boolean IfEmpty() {
        for (IRBlock i : blocks) {
            if (!i.instr.isEmpty())
                return false;
        }
        return true;
    }

    public String Declare(boolean flag) {
        String str = flag ? "declare " : "define ";
        str += ret_type.ToString() + " @" + func_name + '(';
        for (int i = 0; i < args.size(); i++)
            str += args.get(i).type.ToString() + " "
                    + args.get(i).ToString()
                    + (i != args.size() - 1 ? ", " : "");
        str += ")";
        return str;
    }
}
