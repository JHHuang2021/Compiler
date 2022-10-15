package AST;

import Util.Type;
import Util.position;

public class TypeNode extends ASTNode {
    Type type = null;

    public TypeNode(position pos, Type type) {
        super(pos);
        this.type = type;
    }

    public String ToString() {
        return type.typeName;
    }

    public Type GetType() {
        return type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
