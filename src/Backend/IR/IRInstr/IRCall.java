package Backend.IR.IRInstr;

import java.util.ArrayList;

import Backend.IR.IRValue.IRValue;

public class IRCall extends IRInstr {
    public String func_name;
    public ArrayList<IRValue> args = new ArrayList<>();

    public IRCall(String func_name) {
        this.func_name=func_name;
    }
}
