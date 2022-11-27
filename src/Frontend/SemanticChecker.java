package Frontend;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

import Frontend.AST.*;
import Frontend.AST.BinaryExprNode.BinaryOpType;
import Frontend.AST.UnaryExprNode.UnaryOpType;
import Frontend.AST.VarDefStmtNode.Var;
import Util.CheckName;
import Util.Func;
import Util.Scope;
import Util.Type;
import Util.error.semanticError;
import Util.GlobalScope;
import Util.position;
import Util.Scope.ScopeType;

public class SemanticChecker implements ASTVisitor {
    private Scope current_scope;
    private GlobalScope global_scope;
    private Type current_class = null;
    private ScopeType nxt_scope_type = null;
    private CheckName check = new CheckName();
    private boolean if_in_class = false;

    public SemanticChecker(GlobalScope global_scope) {
        current_scope = this.global_scope = global_scope;
    }

    @Override
    public void visit(RootNode it) {
        it.var_defs.forEach(vd -> vd.accept(this));
        it.class_defs.forEach(cd -> cd.accept(this));
        // it.func_defs.forEach(fd -> fd.accept(this));
        for (int i = 0; i < it.func_defs.size(); i++)
            it.func_defs.get(i).accept(this);
        Func main = global_scope.ContainFunc("main", it.pos);
        if (main == null)
            throw new semanticError("no main function", it.pos);
        if (!main.ret_type.type_name.equals("int") || main.ret_type.dim > 0)
            throw new semanticError("main function must return int value", it.pos);
        if (main.args != null && main.args.size() > 0)
            throw new semanticError("main function should not have parameters", null);
        // we SHOULD check struct definitions first
    }

    @Override
    public void visit(ClassDefNode it) {
        // global_scope.addType(it.name, new Type(it.name), it.pos);
        if (!check.Check(it.name))
            throw new semanticError("reserved keyword as class name", it.pos);
        current_class = global_scope.ContainType(it.name, it.pos);
        it.var_defs.forEach(vd -> {
            // current_class = global_scope.ContainType(it.name, it.pos);
            vd.accept(this);
        });
        it.func_defs.forEach(fd -> {
            // current_class = global_scope.ContainType(it.name, it.pos);
            fd.accept(this);
        });
        global_scope.DefineVarible(it.name, current_class, it.pos);
        current_class = null;
    }

    @Override
    public void visit(FunctionNode it) {
        if (!check.Check(it.func_name))
            throw new semanticError("reserved keyword as function name", it.pos);
        if (current_scope != global_scope && current_class == null)
            throw new semanticError("wrong position of function definition", it.pos);
        if (it.ret_type.type_name == "void" && it.ret_type.dim > 0)
            throw new semanticError("void[] can't be return type", it.pos);

        nxt_scope_type = ScopeType.FUNC;
        current_scope = new Scope(current_scope, ScopeType.FUNC);
        current_scope.return_type = it.ret_type;
        for (VarDefStmtNode node : it.args_def)
            node.accept(this);
        for (StmtNode stmt : it.stmts)
            if (stmt != null)
                stmt.accept(this);
        nxt_scope_type = null;
        current_scope = global_scope;
    }

    @Override
    public void visit(IfStmtNode it) {
        nxt_scope_type = ScopeType.IF;
        it.condition.accept(this);
        if (!it.condition.type.type_name.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.then_suite.accept(this);
        if (it.else_suite != null)
            it.else_suite.accept(this);
        nxt_scope_type = null;
    }

    @Override
    public void visit(VarDefStmtNode it) {
        if (current_class != null && current_scope == global_scope) {
            if (current_class.members == null)
                current_class.members = new HashMap<>();
            for (Var var : it.var) {
                if (!check.Check(var.name))
                    throw new semanticError("reserved keyword as varible name", it.pos);
                if (var.type.type_name.equals("void"))
                    throw new semanticError("void can't be varible's type", it.pos);
                if (var.init != null)
                    var.init.accept(this);
            }
            return;
        }

        for (Var var : it.var) {
            if (!check.Check(var.name))
                throw new semanticError("reserved keyword as varible name", it.pos);
            if (var.type.type_name.equals("void"))
                throw new semanticError("void can't be varible's type", it.pos);
            if (var.init != null)
                var.init.accept(this);
            if (var.init != null && !var.init.type.Equal(var.type) && !var.init.type.type_name.equals("Null"))
                throw new semanticError("type not match.",
                        var.init.pos);

            Type t = global_scope.ContainType(var.type.type_name, it.pos);
            if (t == null)
                throw new semanticError("type " + var.type.type_name + " not defined", it.pos);
            var.type.members = t.members;
            var.type.funcs = t.funcs;
            current_scope.DefineVarible(var.name, var.type, it.pos);
            if (current_scope == global_scope)
                global_scope.PutDefineVariblePos(var.name, it.pos);
        }
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (current_scope.if_in_lambda) {
            if (current_scope.return_type == null)
                if (it.value != null) {
                    it.value.accept(this);
                    current_scope.return_type = it.value.type;
                } else
                    current_scope.return_type = new Type("void");
            else if (it.value != null) {
                it.value.accept(this);
                if (!it.value.type.Equal(current_scope.return_type))
                    throw new semanticError("different return type", it.pos);
                else if (!current_scope.return_type.type_name.equals("void"))
                    throw new semanticError("different return type", it.pos);
            }
            return;
        }
        Type ret_type = current_scope.ReturnType();
        if (current_scope == global_scope || ret_type == null)
            throw new semanticError("return doesn't exist in function.", it.pos);
        if (it.if_this) {
            if (current_scope == global_scope || current_class == null)
                throw new semanticError("wrong position of this", it.pos);
        } else if (it.value != null) {
            it.value.accept(this);
            if (it.value.type.type_name.equals("Null")) {
                Type l = ret_type;
                if (l.dim == 0
                        && (l.type_name.equals("int") || l.type_name.equals("bool")
                                || l.type_name.equals("string"))) {
                    throw new semanticError("type not match. ", it.pos);
                }
            } else if (!it.value.type.Equal(ret_type))
                throw new semanticError("type not match.",
                        it.value.pos);
        } else if (!ret_type.type_name.equals("void") && !ret_type.type_name.equals("Create"))
            throw new semanticError("type not match.",
                    null);
    }

    @Override
    public void visit(SuiteStmtNode it) {
        if (!it.stmts.isEmpty()) {
            current_scope = new Scope(current_scope, nxt_scope_type);
            for (StmtNode stmt : it.stmts)
                if (stmt != null)
                    stmt.accept(this);
            current_scope = current_scope.ParentScope();
        }
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new semanticError("not assignable", it.lhs.pos);
        it.rhs.accept(this);
        Type l = it.lhs.type;
        if (!it.rhs.type.Equal(it.lhs.type) && !it.rhs.type.type_name.equals("Null"))
            throw new semanticError("type not match. ", it.pos);
        if (it.rhs.type.type_name.equals("Null") && (l.dim == 0
                && (l.type_name.equals("int") || l.type_name.equals("bool") || l.type_name.equals("string"))))
            throw new semanticError("null cannot be assigned to primitive type variable", it.pos);
        it.type = it.rhs.type;
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.type_name.equals("int")
                && (!it.lhs.type.type_name.equals("string") || it.opCode != BinaryOpType.add))
            throw new semanticError("Operator " + it.opCode.name() + " cannot be applied to "
                    + it.lhs.type.type_name + " objects.", it.pos);
        if (!it.rhs.type.Equal(it.lhs.type))
            throw new semanticError("type not match.",
                    it.lhs.pos);
        it.type = it.lhs.type;
    }

    @Override
    public <T> void visit(ConstExprNode<T> it) {
        String type_name = null;
        if (it.value == null) {
            it.type = new Type("Null");
            return;
        }
        switch (it.value.getClass().getName()) {
            case "java.lang.String":
                type_name = "string";
                break;
            case "java.lang.Long":
                type_name = "int";
                break;
            case "java.lang.Boolean":
                type_name = "bool";
                break;
        }
        it.type = new Type(type_name);
    }

    @Override
    public void visit(VarExprNode it) {
        Pair<Type, Boolean> ret = null;
        Type var_type = null;
        boolean flag = false;

        if (!if_in_class) {
            if (var_type == null && current_scope.ContainVarible(it.name, false)) {
                ret = current_scope.GetVaribleType(it.name, false);
                if (ret != null) {
                    var_type = ret.a;
                    flag = ret.b;
                }
            }
            if (var_type == null && current_class != null)
                var_type = current_class.ContainVarible(it.name);
            if (var_type == null && current_scope.ContainVarible(it.name, true)) {
                ret = current_scope.GetVaribleType(it.name, true);
                if (ret != null) {
                    var_type = ret.a;
                    flag = ret.b;
                }
            }
            if (flag) {
                position define_pos = global_scope.GetDefineVariblePos(it.name);
                if (define_pos.row() > it.pos.row())
                    throw new semanticError("Variable is not available for back reference.", it.pos);
            }

        } else {
            if (var_type == null && current_class != null)
                var_type = current_class.ContainVarible(it.name);
            if_in_class = false;
        }

        if (var_type == null)
            throw new semanticError("variable not defined. ", it.pos);
        // if (it.dim_args != null)
        // for (int i = 0; i < it.dim_args.size(); i++) {
        // it.dim_args.get(i).accept(this);
        // if (it.dim_args.get(i).type.dim != 0)
        // throw new semanticError("assignment with different dimension", it.pos);
        // if (!it.dim_args.get(i).type.type_name.equals("int"))
        // throw new semanticError("dimension should be int", it.pos);
        // }
        it.type = new Type(var_type, 0);
        // it.type = new Type(var_type, it.dim);
    }

    @Override
    public void visit(ForStmtNode it) {
        nxt_scope_type = ScopeType.FOR;
        if (it.var != null)
            it.var.accept(this);
        if (it.step != null)
            it.step.accept(this);
        if (it.condition != null)
            it.condition.accept(this);
        if (it.condition != null && !it.condition.type.type_name.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.for_suite.accept(this);
        nxt_scope_type = null;
    }

    @Override
    public void visit(WhileStmtNode it) {
        nxt_scope_type = ScopeType.WHILE;
        it.condition.accept(this);
        if (!it.condition.type.type_name.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.while_suite.accept(this);
        nxt_scope_type = null;
    }

    @Override
    public void visit(BreakStmtNode it) {
        if (current_scope == null || (!current_scope.ContainScopeType(ScopeType.FOR, true)
                && !current_scope.ContainScopeType(ScopeType.WHILE, true)))
            throw new semanticError("break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(ContinueStmtNode it) {
        if (current_scope == null || (!current_scope.ContainScopeType(ScopeType.FOR, true)
                && !current_scope.ContainScopeType(ScopeType.WHILE, true)))
            throw new semanticError("break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(NewExprNode it) {
        if (it.type.type_name.equals("void"))
            throw new semanticError("new expression cannot apply to void", it.pos);
        if (it.type.dim_args != null)
            for (int i = 0; i < it.type.dim_args.size(); i++) {
                it.type.dim_args.get(i).accept(this);
                if (!it.type.dim_args.get(i).type.type_name.equals("int"))
                    throw new semanticError("dimension should be int", it.pos);
            }
        Type t = global_scope.ContainType(it.type.type_name, it.pos);
        it.type.funcs = t.funcs;
        it.type.members = t.members;
    }

    @Override
    public void visit(UnaryExprNode it) {
        it.expr.accept(this);
        if (!it.expr.type.type_name.equals("int"))
            throw new semanticError("type not match. It should be int",
                    it.pos);
        if (!it.expr.isAssignable() && it.opCode != UnaryOpType.tilde && it.opCode != UnaryOpType.neg)
            throw new semanticError("left value operation is invalid", it.pos);
        it.type = it.expr.type;
    }

    @Override
    public void visit(LogicExprNode it) {
        it.lhs.accept(this);
        if (it.rhs != null)
            it.rhs.accept(this);
        if (it.op_code.ordinal() >= 6 && (!it.lhs.type.type_name.equals("bool")
                || (it.rhs != null && !it.rhs.type.type_name.equals("bool"))))
            throw new semanticError("type not match. ", it.pos);
        else if (it.op_code.ordinal() < 6) {
            Type l = it.rhs.type;
            if (it.rhs.type.type_name.equals("Null") && it.op_code.ordinal() <= 1) {
                if (l.dim == 0
                        && (l.type_name.equals("int") || l.type_name.equals("bool")
                                || l.type_name.equals("string"))) {
                    throw new semanticError("type not match. ", it.pos);
                }
            } else if (!it.lhs.type.Equal(it.rhs.type))
                throw new semanticError("type not match. ", it.pos);
        }
        it.type = new Type("bool");
    }

    @Override
    public void visit(FuncCallExprNode it) {
        Func calledFunc = null;

        if (current_class != null && if_in_class) {
            calledFunc = current_class.ContainFunc(it.name, it.pos);
            if (calledFunc == null && (!it.name.equals("size") || current_class.dim == 0))
                throw new semanticError("function " + it.name + " not exists", it.pos);
            if (calledFunc != null)
                it.type = global_scope.ContainType(calledFunc.ret_type.type_name, it.pos);
            else
                it.type = global_scope.ContainType("int", it.pos);
            if (calledFunc != null) {
                it.type = new Type(calledFunc.ret_type, 0);
                it.type.dim = calledFunc.ret_type.dim;
            }
            if_in_class = false;
            for (int i = 0; i < it.args.size(); i++) {
                it.args.get(i).accept(this);
                if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type)) {
                    Type l = calledFunc.args.get(i).var.get(0).type;
                    if (it.args.get(i).type.type_name.equals("Null") && (l.dim == 0
                            && (l.type_name.equals("int") || l.type_name.equals("bool")
                                    || l.type_name.equals("string"))))
                        throw new semanticError("null cannot be assigned to primitive type variable", it.pos);
                    else if (!it.args.get(i).type.type_name.equals("Null"))
                        throw new semanticError("type not match", it.pos);
                }
            }
            return;
        }
        if (current_class != null) {
            calledFunc = current_class.ContainFunc(it.name, it.pos);
            if (calledFunc == null)
                calledFunc = global_scope.ContainFunc(it.name, it.pos);
            if (calledFunc == null && (!it.name.equals("size") || current_class.dim == 0))
                throw new semanticError("function " + it.name + " not exists", it.pos);
            if (calledFunc != null && (!it.name.equals("size") || current_class.dim == 0)) {
                if ((calledFunc.args == null && it.args.size() > 0)
                        || (calledFunc.args != null && it.args.size() != calledFunc.args.size()))
                    throw new semanticError("function " + it.name + " requires " + calledFunc.args.size()
                            + " but has " + it.args.size(), it.pos);
                for (int i = 0; i < it.args.size(); i++) {
                    it.args.get(i).accept(this);
                    if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type)) {
                        Type l = calledFunc.args.get(i).var.get(0).type;
                        if (it.args.get(i).type.type_name.equals("Null") && (l.dim == 0
                                && (l.type_name.equals("int") || l.type_name.equals("bool")
                                        || l.type_name.equals("string"))))
                            throw new semanticError("null cannot be assigned to primitive type variable", it.pos);
                        else if (!it.args.get(i).type.type_name.equals("Null"))
                            throw new semanticError("type not match", it.pos);
                    }
                }
                it.type = global_scope.ContainType(calledFunc.ret_type.type_name, it.pos);
            } else {
                it.type = global_scope.ContainType("int", it.pos);
            }
            if (calledFunc != null) {
                it.type = new Type(calledFunc.ret_type, 0);
                it.type.dim = calledFunc.ret_type.dim;
            }

            // current_class = null;
            return;
        }
        calledFunc = global_scope.ContainFunc(it.name, it.pos);
        if (calledFunc == null)
            throw new semanticError("function " + it.name + " not exists", it.pos);
        if ((calledFunc.args == null && it.args.size() > 0)
                || (calledFunc.args != null && it.args.size() != calledFunc.args.size()))
            throw new semanticError("function " + it.name + " requires " + calledFunc.args.size()
                    + " but has " + it.args.size(), it.pos);
        for (int i = 0; i < it.args.size(); i++) {
            it.args.get(i).accept(this);
            if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type)) {
                Type l = calledFunc.args.get(i).var.get(0).type;
                if (it.args.get(i).type.type_name.equals("Null") && (l.dim == 0
                        && (l.type_name.equals("int") || l.type_name.equals("bool") || l.type_name.equals("string"))))
                    throw new semanticError("null cannot be assigned to primitive type variable", it.pos);
                else if (!it.args.get(i).type.type_name.equals("Null"))
                    throw new semanticError("type not match", it.pos);
            }
        }
        it.type = global_scope.ContainType(calledFunc.ret_type.type_name, it.pos);
        it.type = new Type(calledFunc.ret_type, 0);
        it.type.dim = calledFunc.ret_type.dim;
    }

    @Override
    public void visit(ThisExprNode it) {
        if (current_scope == global_scope || current_class == null)
            throw new semanticError("wrong position of this", it.pos);
        it.type = current_class;
    }

    @Override
    public void visit(TypeNode it) {
        if (it.type.dim_args != null)
            for (int i = 0; i < it.type.dim_args.size(); i++) {
                it.type.dim_args.get(i).accept(this);
                if (!it.type.dim_args.get(i).type.type_name.equals("int"))
                    throw new semanticError("dimension should be int", it.pos);
            }
    }

    @Override
    public void visit(VisitExprNode it) {
        it.visitor.accept(this);
        if_in_class = true;
        String current_className = null;
        if (current_class != null)
            current_className = current_class.type_name;
        Type t = global_scope.ContainType(it.visitor.type.type_name, it.pos);
        current_class = it.visitor.type;
        current_class.funcs = t.funcs;
        current_class.members = t.members;
        it.visitee.accept(this);
        if_in_class = false;
        it.type = it.visitee.type;
        if (current_className != null)
            current_class = global_scope.ContainType(current_className, it.pos);
        else
            current_class = null;
    }

    @Override
    public void visit(ExprArrayNode it) {
        it.expr.accept(this);
        it.offset.accept(this);
        it.type = new Type(it.expr.type, 1);
    }

    @Override
    public void visit(LambdaExprNode it) {
        nxt_scope_type = ScopeType.FUNC;
        current_scope = new Scope(current_scope, ScopeType.FUNC);
        current_scope.if_in_lambda = true;
        if (it.args.size() != it.args_def.size())
            throw new semanticError("lambda function requires " + it.args.size()
                    + " but has " + it.args.size(), it.pos);
        for (VarDefStmtNode node : it.args_def)
            node.accept(this);
        for (int i = 0; i < it.args.size(); i++) {
            it.args.get(i).accept(this);
            if (!it.args.get(i).type.Equal(it.args_def.get(i).var.get(0).type))
                throw new semanticError("type not match", it.pos);
        }
        for (StmtNode stmt : it.stmts)
            if (stmt != null)
                stmt.accept(this);
        if (current_scope.return_type == null)
            it.type = new Type("void");
        else
            it.type = current_scope.return_type;
        nxt_scope_type = null;
        current_scope = current_scope.ParentScope();
    }

}
