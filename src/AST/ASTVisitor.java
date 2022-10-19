package AST;

public interface ASTVisitor {
    void visit(RootNode it);

    void visit(classDefNode it);

    void visit(FnNode it);

    void visit(suiteStmtNode it);

    void visit(varDefStmtNode it);

    void visit(exprStmtNode it);

    void visit(ifStmtNode it);

    void visit(forStmtNode it);

    void visit(whileStmtNode it);

    void visit(returnStmtNode it);

    void visit(breakStmtNode it);

    void visit(continueStmtNode it);

    void visit(newExprNode it);

    void visit(assignExprNode it);

    void visit(unaryExprNode it);

    void visit(binaryExprNode it);

    <T> void visit(constExprNode<T> it);

    void visit(logicExprNode it);

    void visit(varExprNode it);

    void visit(funcCallExprNode it);

    void visit(thisExprNode it);

    void visit(TypeNode it);

    void visit(visitExprNode it);

    void visit(exprArrayNode it);
}
