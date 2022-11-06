package Frontend.AST;

import Util.position;

public class ConstExprNode<T> extends ExprNode {
    public T value;

    public ConstExprNode(T value, position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
