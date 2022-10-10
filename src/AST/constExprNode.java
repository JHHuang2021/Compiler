package AST;

import Util.position;

import Util.Type;

public class constExprNode<T> extends ExprNode {
    public T value;

    public constExprNode(T value, Type constType, position pos) {
        super(pos);
        this.value = value;
        type = constType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
