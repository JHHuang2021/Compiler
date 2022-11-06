package Frontend;

import Frontend.AST.*;
import Frontend.AST.VarDefStmtNode.Var;
import Util.Type;
import Util.GlobalScope;
import Util.error.semanticError;

import java.util.HashMap;

public class SymbolCollector implements ASTVisitor {
    private GlobalScope global_scope;
    private Type current_class = null;

    public SymbolCollector(GlobalScope global_scope) {
        this.global_scope = global_scope;

    }

    @Override
    public void visit(RootNode it) {
        // it.strDefs.forEach(sd -> sd.accept(this));
        // it.class_defs.forEach(cd -> cd.accept(this));
        // for (int i = 0; i < it.var_defs.size(); i++)
        // it.var_defs.get(i).accept(this);
        for (int i = 0; i < it.class_defs.size(); i++)
            it.class_defs.get(i).accept(this);
        for (int i = 0; i < it.func_defs.size(); i++)
            it.func_defs.get(i).accept(this);
    }

    @Override
    public void visit(ClassDefNode it) {
        Type newClass = new Type(it.name);
        newClass.members = new HashMap<>();
        current_class = newClass;
        for (int i = 0; i < it.var_defs.size(); i++)
            it.var_defs.get(i).accept(this);
        for (int i = 0; i < it.func_defs.size(); i++)
            it.func_defs.get(i).accept(this);
        current_class = null;
        global_scope.AddType(it.name, newClass, it.pos);
    }

    @Override
    public void visit(FunctionNode it) {
        if (current_class != null) {
            if (current_class.funcs == null)
                current_class.funcs = new HashMap<>();
            if (current_class.ContainFunc(it.func_name, it.pos) != null)
                throw new semanticError("redefinition of function " + it.func_name, it.pos);
            if (it.ret_type == null) {
                if (!it.func_name.equals(current_class.type_name))
                    throw new semanticError("The name of class constructor must be the same as the class name.",
                            it.pos);
                it.ret_type =  new Type("Create");
            } else if (it.func_name.equals(current_class.type_name))
                throw new semanticError("Constructor Type Error.", it.pos);
            current_class.DefineFunc(it.func_name, it.ret_type, it.args_def, it.pos);
            return;
        }
        if (global_scope.ContainFunc(it.func_name, it.pos) != null || global_scope.ContainType(it.func_name, it.pos) != null)
            throw new semanticError("redefinition of function " + it.func_name, it.pos);
        global_scope.DefineFunc(it.func_name, it.ret_type, it.args_def, it.pos);
    }

    @Override
    public void visit(VarDefStmtNode it) {
        for (Var var : it.var) {
            if (current_class.members.containsKey(var.name))
                throw new semanticError("redefinition of member " + var.name, it.pos);
            Type t = global_scope.ContainType(var.type.type_name, it.pos);
            if (t != null) {
                var.type.members = t.members;
                var.type.funcs = t.funcs;
                current_class.members.put(var.name, var.type);
            } else {
                current_class.members.put(var.name, var.type);
            }
        }
    }

    @Override
    public void visit(ReturnStmtNode it) {
    }

    @Override
    public void visit(SuiteStmtNode it) {
    }

    @Override
    public void visit(ExprStmtNode it) {
    }

    @Override
    public void visit(IfStmtNode it) {
    }

    @Override
    public void visit(AssignExprNode it) {
    }

    @Override
    public void visit(BinaryExprNode it) {
    }

    @Override
    public <T> void visit(ConstExprNode<T> it) {
    }

    // @Override public void visit(cmpExprNode it) {}
    @Override
    public void visit(VarExprNode it) {
    }

    @Override
    public void visit(ForStmtNode it) {

    }

    @Override
    public void visit(WhileStmtNode it) {

    }

    @Override
    public void visit(BreakStmtNode it) {

    }

    @Override
    public void visit(ContinueStmtNode it) {

    }

    @Override
    public void visit(NewExprNode it) {

    }

    @Override
    public void visit(UnaryExprNode it) {

    }

    @Override
    public void visit(LogicExprNode it) {

    }

    @Override
    public void visit(FuncCallExprNode it) {

    }

    @Override
    public void visit(ThisExprNode it) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }

    @Override
    public void visit(VisitExprNode it) {

    }

    @Override
    public void visit(ExprArrayNode it) {

    }

    @Override
    public void visit(LambdaExprNode it) {

    }
}
