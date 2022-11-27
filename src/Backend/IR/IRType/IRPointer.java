package Backend.IR.IRType;

import Backend.IR.IRValue.Constant;
import Backend.IR.IRValue.IRValue;
import Backend.IR.IRValue.Register;

public class IRPointer extends IRType {
    public int dim;
    public IRValue max_dim = null;
    public IRType father_type;
    public String s;

    public IRPointer(int dim, IRType basic_type) {
        super(basic_type.name, 32);
        if (basic_type instanceof IRPointer)
            this.dim = ((IRPointer) basic_type).dim + dim;
        else
            this.dim = dim;
        this.father_type = basic_type;

        s = this.GetBasicType().ToString();
        for (int i = 0; i < this.dim; i++)
            s += "*";
    }

    public IRPointer(int dim, IRType basic_type, int reg_cnt, IRType int_type) {
        super(basic_type.name, 32);
        if (basic_type instanceof IRPointer)
            this.dim = ((IRPointer) basic_type).dim + dim;
        else
            this.dim = dim;
        this.father_type = basic_type;
        this.max_dim = new Register(int_type, "max_dim", false, reg_cnt);

        s = this.GetBasicType().ToString();
        for (int i = 0; i < this.dim; i++)
            s += "*";
    }

    public String ToString() {
        return s;
    }

    @Override
    public String GetType() {
        String str = "";
        if (max_dim instanceof Constant)
            str += "[" + ((Constant) max_dim).val + " x " + father_type.GetType() + "]";
        else {
            str += GetBasicType().ToString();
            for (int i = 0; i < dim; i++)
                str += '*';
        }
        return str;
    }

    @Override
    public IRType FatherType() {
        return father_type;
    }

    public IRType GetBasicType() {
        if (father_type instanceof IRPointer)
            return ((IRPointer) father_type).GetBasicType();
        else
            return father_type;
    }

    @Override
    public String PrintArgs() {
        if (max_dim == null)
            return "";
        String str = ", " + max_dim.type.ToString() + " " + max_dim.ToString();
        if (father_type instanceof IRPointer)
            str += ", " + ((IRPointer) father_type).PrintArgs();
        return str;
    }
}
