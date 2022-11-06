package Frontend.AST;

public interface ASTVisitor {
    void visit(AssignExprNode it);

    void visit(BinaryExprNode it);

    void visit(BreakStmtNode it);

    void visit(ClassDefNode it);

    <T> void visit(ConstExprNode<T> it);

    void visit(ContinueStmtNode it);

    void visit(ExprArrayNode it);

    void visit(ExprStmtNode it);

    void visit(ForStmtNode it);

    void visit(FuncCallExprNode it);

    void visit(FunctionNode it);

    void visit(IfStmtNode it);

    void visit(LambdaExprNode it);

    void visit(LogicExprNode it);

    void visit(NewExprNode it);

    void visit(RootNode it);

    void visit(ReturnStmtNode it);

    void visit(SuiteStmtNode it);

    void visit(ThisExprNode it);

    void visit(TypeNode it);

    void visit(UnaryExprNode it);

    void visit(VarDefStmtNode it);

    void visit(VarExprNode it);

    void visit(VisitExprNode it);

    void visit(WhileStmtNode it);

}
