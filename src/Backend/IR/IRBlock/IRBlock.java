package Backend.IR.IRBlock;

import java.util.ArrayList;

import Backend.Assembly.ASMBuilder;
import Backend.IR.IRInstr.IRInstr;

public class IRBlock {
    public ArrayList<IRInstr> instr = new ArrayList<>();
    public String tag;
    public boolean is_returned = false;

    public IRBlock(String tag) {
        this.tag = tag;
    }

    public String ToString() {
        return this.tag + ":";
    }

    public void accept(ASMBuilder visitor) {
        // visitor.accept(this);
    }
}
