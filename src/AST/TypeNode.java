package AST;

import Util.Type;
import Util.position;

public class TypeNode extends ASTNode {
    Type type = null;

    public TypeNode(position pos, Type type) {
        super(pos);
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
