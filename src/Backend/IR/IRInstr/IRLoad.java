package Backend.IR.IRInstr;

// import Backend.IR.IRType.IRType;
import Backend.IR.IRValue.Register;

public class IRLoad extends IRInstr {
    // public IRType type;
    public Register reg_addr;
    public Register dest;

    public IRLoad(/* IRType type, */ Register dest, Register reg_addr) {
        // this.type = type;
        this.dest = dest;
        this.reg_addr = reg_addr;
    }

    @Override
    public String ToString() {
        String str = "";
        str += dest.ToString() + " = load " + dest.type.ToString() + ", " + reg_addr.type.ToString() + " "
                + reg_addr.ToString();
        return str;
    }
}
