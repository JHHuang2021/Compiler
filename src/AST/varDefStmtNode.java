package AST;

import java.util.ArrayList;

import Util.position;

public class varDefStmtNode extends StmtNode {
    public static class Var {
        // Type type;
        TypeNode type;
        String name;
        ExprNode init;

        public Var(TypeNode type, String name, ExprNode init) {
            this.type = type;
            this.name = name;
            this.init = init;
        }
    };

    ArrayList<Var> var;

    public varDefStmtNode(position pos, ArrayList<Var> var) {
        super(pos);
        this.var = var;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
