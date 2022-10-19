package AST;

import java.util.ArrayList;

import Util.position;

public class varExprNode extends ExprNode {

    public static class Layer {
        public boolean ifthis = false;
        public String name;
        public int dim = 0;
        public ArrayList<ExprNode> dimArgs = null;

        public Layer() {
            this.ifthis = true;
        }

        public Layer(String name) {
            this.name = name;
        }

        public Layer(String name, int dim, ArrayList<ExprNode> dimArgs) {
            this.name = name;
            this.dim = dim;
            this.dimArgs = dimArgs;
        }
    }

    public boolean ifthis = false;
    public ArrayList<Layer> var = null;

    public varExprNode(position pos) {
        super(pos);
        var = new ArrayList<>();
        var.add(new Layer());
    }

    public varExprNode(position pos, ArrayList<Layer> var, boolean ifthis) {
        super(pos);
        this.var = var;
        this.ifthis = ifthis;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}
