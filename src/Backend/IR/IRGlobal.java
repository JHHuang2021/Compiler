package Backend.IR;

import java.util.ArrayList;
import Backend.IR.IRBlock.IRFunc;
import Backend.IR.IRType.IRClass;
import Backend.IR.IRValue.Register;
import Backend.IR.IRValue.StringConst;

public class IRGlobal {
    public ArrayList<Register> vars = new ArrayList<>();
    public ArrayList<StringConst> strs = new ArrayList<>();
    public ArrayList<IRFunc> funcs = new ArrayList<>();
    public ArrayList<IRClass> classes = new ArrayList<>();

    public IRGlobal() {

    }

    // public void accept(ASMBuilder visitor) {
    //     visitor.visit(this);
    // }
}
