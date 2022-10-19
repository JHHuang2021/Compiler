package AST;

import Util.Type;
import Util.position;

public class TypeNode extends ASTNode {
    Type type = null;
    boolean assignable = false;

    public TypeNode(position pos, Type type) {
        super(pos);
        this.type = type;
    }

    public TypeNode(position pos, Type type, int dim) {
        super(pos);
        this.type = new Type(type.typeName);
        this.type.dimArgs = type.dimArgs;
        this.type.funcs = type.funcs;
        this.type.members = type.members;
        this.type.dim = type.dim - dim;
    }

    public String ToString() {
        return type.typeName;
    }

    public Type GetType() {
        return type;
    }

    public boolean Equal(TypeNode rhs) {
        if (this.type.typeName.equals(rhs.type.typeName) && this.type.dim == rhs.type.dim)
            return true;
        else
            return false;
    }

    public boolean Equal(Type rhs) {
        if (this.type.typeName.equals(rhs.typeName) && this.type.dim == rhs.dim)
            return true;
        else
            return false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
