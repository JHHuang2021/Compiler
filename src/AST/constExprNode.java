package AST;

import Util.position;

public class constExprNode<T> extends ExprNode {
    public T value;

    public constExprNode(T value, position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
