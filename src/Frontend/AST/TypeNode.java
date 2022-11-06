package Frontend.AST;

import Util.Type;
import Util.position;

public class TypeNode extends ASTNode {
    public Type type = null;
    // boolean assignable = false;

    public TypeNode(position pos, Type type) {
        super(pos);
        this.type = type;
    }

    public String ToString() {
        return type.type_name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
