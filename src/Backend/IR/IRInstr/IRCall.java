package Backend.IR.IRInstr;

import java.util.ArrayList;

import Backend.IR.IRValue.IRValue;

public class IRCall extends IRInstr {
    public String func_name;
    public IRValue val = null;
    public ArrayList<IRValue> args = new ArrayList<>();

    public IRCall(String func_name, IRValue val) {
        this.func_name = func_name;
        this.val = val;
    }

    @Override
    public String ToString() {
        String str = "";
        if (val != null)
            str += val.ToString() + " = ";
        str += "call ";
        str += val != null ? val.type.ToString() : "void";
        str += " " + func_name + "(";
        for (int i = 0; i < args.size(); i++) {
            IRValue val = args.get(i);
            str += val.type.ToString() + " " + val.ToString() + val.type.PrintArgs();
            if (i != args.size() - 1)
                str += ", ";
        }
        str += ")";
        return str;
    }
}
