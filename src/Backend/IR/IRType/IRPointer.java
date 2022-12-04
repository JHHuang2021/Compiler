package Backend.IR.IRType;

public class IRPointer extends IRType {
    public int dim;
    public IRType father_type;
    public String s;

    public IRPointer(int dim, IRType basic_type) {
        // super(basic_type.name, 32);
        super(basic_type.name, 64);
        if (basic_type instanceof IRPointer)
            this.dim = ((IRPointer) basic_type).dim + dim;
        else
            this.dim = dim;
        this.father_type = basic_type;

        s = this.GetBasicType().ToString();
        for (int i = 0; i < this.dim; i++)
            s += "*";
    }

    public IRPointer(int dim, IRType basic_type, int reg_cnt) {
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

    public String ToString() {
        return s;
    }

    @Override
    public String GetType() {
        String str = "";
        str += GetBasicType().ToString();
        for (int i = 0; i < dim; i++)
            str += '*';
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
}
