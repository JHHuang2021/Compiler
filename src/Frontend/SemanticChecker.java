package Frontend;

import java.util.HashMap;

import AST.*;
import AST.binaryExprNode.binaryOpType;
import AST.varDefStmtNode.Var;
import Util.CheckName;
import Util.Func;
import Util.Scope;
import Util.Type;
import Util.error.semanticError;
import Util.globalScope;
import Util.Scope.ScopeType;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private globalScope gScope;
    private Type currentClass = null;
    private ScopeType nxtScopeType = null;
    private CheckName check = new CheckName();
    private boolean ifthis = false;
    private boolean ifinclass = false;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
    }

    @Override
    public void visit(RootNode it) {
        it.varDefs.forEach(vd -> vd.accept(this));
        it.classDefs.forEach(cd -> cd.accept(this));
        // it.funcDefs.forEach(fd -> fd.accept(this));
        for (int i = 0; i < it.funcDefs.size(); i++)
            it.funcDefs.get(i).accept(this);
        Func main = gScope.containFunc("main", it.pos);
        if (main == null)
            throw new semanticError("no main function", it.pos);
        if (!main.retType.typeName.equals("int") || main.retType.dim > 0)
            throw new semanticError("main function must return int value", it.pos);
        if (main.args != null && main.args.size() > 0)
            throw new semanticError("main function should not have parameters", null);
        // we SHOULD check struct definitions first
    }

    @Override
    public void visit(classDefNode it) {
        // gScope.addType(it.name, new Type(it.name), it.pos);
        if (!check.Check(it.name))
            throw new semanticError("reserved keyword as class name", it.pos);
        currentClass = gScope.containType(it.name, it.pos);
        it.varDefs.forEach(vd -> {
            // currentClass = gScope.containType(it.name, it.pos);
            vd.accept(this);
        });
        it.funcDefs.forEach(fd -> {
            // currentClass = gScope.containType(it.name, it.pos);
            fd.accept(this);
        });
        gScope.defineVarible(it.name, currentClass, it.pos);
        currentClass = null;
    }

    @Override
    public void visit(FnNode it) {
        if (!check.Check(it.funcName))
            throw new semanticError("reserved keyword as function name", it.pos);
        if (currentScope != gScope && currentClass == null)
            throw new semanticError("wrong position of function definition", it.pos);
        if (it.retType.GetType().typeName == "void" && it.retType.GetType().dim > 0)
            throw new semanticError("void[] can't be return type", it.pos);

        if (currentClass != null) {
            // if (currentClass.funcs == null)
            // currentClass.funcs = new HashMap<>();
            // if (currentClass.containFunc(it.funcName, it.pos) != null)
            // throw new semanticError("redefinition of function " + it.funcName, it.pos);
            // currentClass.defineFunc(it.funcName, it.retType.GetType(), it.argsDef,
            // it.pos);
            nxtScopeType = ScopeType.FUNC;
            currentScope = new Scope(currentScope, ScopeType.FUNC);
            currentScope.returnType = it.retType.GetType();
            for (varDefStmtNode node : it.argsDef)
                node.accept(this);
            for (StmtNode stmt : it.stmts)
                stmt.accept(this);
            nxtScopeType = null;
            currentScope = gScope;
            return;
        }

        // if (gScope.containFunc(it.funcName, it.pos) != null)
        // throw new semanticError("redefinition of function " + it.funcName, it.pos);
        // gScope.defineFunc(it.funcName, it.retType.GetType(), it.argsDef, it.pos);
        nxtScopeType = ScopeType.FUNC;
        currentScope = new Scope(currentScope, ScopeType.FUNC);
        currentScope.returnType = it.retType.GetType();
        for (varDefStmtNode node : it.argsDef)
            node.accept(this);
        for (StmtNode stmt : it.stmts)
            if (stmt != null)
                stmt.accept(this);
        nxtScopeType = null;
        currentScope = gScope;
    }

    @Override
    public void visit(ifStmtNode it) {
        nxtScopeType = ScopeType.IF;
        it.condition.accept(this);
        if (!it.condition.type.GetType().typeName.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.thenSuite.accept(this);
        if (it.elseSuite != null)
            it.elseSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(varDefStmtNode it) {
        if (currentClass != null && currentScope == gScope) {
            if (currentClass.members == null)
                currentClass.members = new HashMap<>();
            for (Var var : it.var) {
                if (!check.Check(var.name))
                    throw new semanticError("reserved keyword as varible name", it.pos);

                if (var.type.typeName.equals("void"))
                    throw new semanticError("void can't be varible's type", it.pos);
                if (var.init != null)
                    var.init.accept(this);

                // if (var.init != null)
                // throw new semanticError("Yx does not support default init of members",
                // var.init.pos);
            }
            return;
        }

        // if (it.init != null) {
        for (Var var : it.var) {
            if (!check.Check(var.name))
                throw new semanticError("reserved keyword as varible name", it.pos);
            if (var.type.typeName.equals("void"))
                throw new semanticError("void can't be varible's type", it.pos);
            if (var.init != null)
                var.init.accept(this);
            if (var.init != null && !var.init.type.Equal(var.type) && !var.init.type.GetType().typeName.equals("Null"))
                throw new semanticError("type not match.",
                        var.init.pos);
            // }
            Type t = gScope.containType(var.type.typeName, it.pos);
            if (t == null)
                throw new semanticError("type " + var.type.typeName + " not defined", it.pos);
            var.type.members = t.members;
            var.type.funcs = t.funcs;
            currentScope.defineVarible(var.name, var.type, it.pos);
        }
    }

    @Override
    public void visit(returnStmtNode it) {
        Type retType = currentScope.returnType();
        if (currentScope == gScope || retType == null)
            throw new semanticError("return doesn't exist in function.", it.pos);
        if (it.ifthis) {
            if (currentScope == gScope || currentClass == null)
                throw new semanticError("wrong position of this", it.pos);
        } else if (it.value != null) {
            it.value.accept(this);
            if (!it.value.type.Equal(retType))
                throw new semanticError("type not match.",
                        it.value.pos);
        } else if (!retType.typeName.equals("void"))
            throw new semanticError("type not match.",
                    null);
    }

    @Override
    public void visit(suiteStmtNode it) {
        if (!it.stmts.isEmpty()) {
            currentScope = new Scope(currentScope, nxtScopeType);
            for (StmtNode stmt : it.stmts)
                if (stmt != null)
                    stmt.accept(this);
            currentScope = currentScope.parentScope();
        }
    }

    @Override
    public void visit(exprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new semanticError("not assignable", it.lhs.pos);
        it.rhs.accept(this);
        Type l = it.lhs.type.GetType();
        if (!it.rhs.type.Equal(it.lhs.type) && !it.rhs.type.GetType().typeName.equals("Null"))
            throw new semanticError("type not match. ", it.pos);
        if (it.rhs.type.GetType().typeName.equals("Null") && (l.dim == 0
                && (l.typeName.equals("int") || l.typeName.equals("bool") || l.typeName.equals("string"))))
            throw new semanticError("null cannot be assigned to primitive type variable", it.pos);
        it.type = it.rhs.type;
    }

    @Override
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.GetType().typeName.equals("int")
                && (!it.lhs.type.GetType().typeName.equals("string") || it.opCode != binaryOpType.add))
            throw new semanticError("Operator " + it.opCode.name() + " cannot be applied to "
                    + it.lhs.type.GetType().typeName + " objects.", it.pos);
        if (!it.rhs.type.Equal(it.lhs.type))
            throw new semanticError("type not match.",
                    it.lhs.pos);
        it.type = it.lhs.type;
    }

    @Override
    public <T> void visit(constExprNode<T> it) {
        String typeName = null;
        if (it.value == null) {
            it.type = new TypeNode(it.pos, new Type("Null"));
            return;
        }
        switch (it.value.getClass().getName()) {
            case "java.lang.String":
                typeName = "string";
                break;
            case "java.lang.Long":
                typeName = "int";
                break;
            case "java.lang.Boolean":
                typeName = "bool";
                break;
        }
        it.type = new TypeNode(it.pos, new Type(typeName));
    }

    // @Override
    // public void visit(cmpExprNode it) {
    // it.lhs.accept(this);
    // it.rhs.accept(this);
    // if (it.rhs.type != it.lhs.type)
    // throw new semanticError("type not match. ", it.pos);
    // }

    @Override
    public void visit(varExprNode it) {
        Type varType = null;
        // if (!currentScope.contains(it.var.get(0).name, true)
        // && (currentClass == null || currentClass.containsVarible(it.var.get(0).name)
        // == null))
        // throw new semanticError("variable not defined. ", it.pos);
        // currentClass = currentScope.getType(it.var.get(0).name, true);
        if (!ifthis) {
            if (!ifinclass) {
                if (varType == null && currentScope.containVarible(it.name, false))
                    varType = currentScope.getVaribleType(it.name, false);
                if (varType == null && currentClass != null)
                    varType = currentClass.containVarible(it.name);
                if (varType == null && currentScope.containVarible(it.name, true))
                    varType = currentScope.getVaribleType(it.name, true);
            } else {
                if (varType == null && currentClass != null)
                    varType = currentClass.containVarible(it.name);
            }
        } else {
            ifthis = false;
            if (varType == null && currentClass != null)
                varType = currentClass.containVarible(it.name);
        }

        if (varType == null)
            throw new semanticError("variable not defined. ", it.pos);

        it.type = new TypeNode(it.pos, varType, it.dim);
    }

    @Override
    public void visit(forStmtNode it) {
        nxtScopeType = ScopeType.FOR;
        if (it.var != null)
            it.var.accept(this);
        if (it.step != null)
            it.step.accept(this);
        if (it.condition != null)
            it.condition.accept(this);
        if (it.condition != null && !it.condition.type.GetType().typeName.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.forSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(whileStmtNode it) {
        nxtScopeType = ScopeType.WHILE;
        it.condition.accept(this);
        if (!it.condition.type.GetType().typeName.equals("bool"))
            throw new semanticError("type not match. It should be bool",
                    it.condition.pos);
        it.whileSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(breakStmtNode it) {
        if (currentScope == null || (!currentScope.containScopeType(ScopeType.FOR, true)
                && !currentScope.containScopeType(ScopeType.WHILE, true)))
            throw new semanticError("break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(continueStmtNode it) {
        if (currentScope == null || (!currentScope.containScopeType(ScopeType.FOR, true)
                && !currentScope.containScopeType(ScopeType.WHILE, true)))
            throw new semanticError("break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(newExprNode it) {
        if (it.type.GetType().typeName.equals("void"))
            throw new semanticError("new expression cannot apply to void", it.pos);
    }

    @Override
    public void visit(unaryExprNode it) {
        it.expr.accept(this);
        if (!it.type.GetType().typeName.equals("int"))
            throw new semanticError("type not match. It should be int",
                    it.pos);
        if (!it.expr.isAssignable())
            throw new semanticError("left value operation is invalid", it.pos);
    }

    @Override
    public void visit(logicExprNode it) {
        it.lhs.accept(this);
        if (it.rhs != null)
            it.rhs.accept(this);
        if (it.opCode.ordinal() >= 6 && (!it.lhs.type.GetType().typeName.equals("bool")
                || (it.rhs != null && !it.rhs.type.GetType().typeName.equals("bool"))))
            throw new semanticError("type not match. ", it.pos);
        else if (it.opCode.ordinal() < 6 && !it.lhs.type.Equal(it.rhs.type))
            throw new semanticError("type not match. ", it.pos);
        it.type = new TypeNode(it.pos, new Type("bool"));
    }

    @Override
    public void visit(funcCallExprNode it) {
        Func calledFunc = null;

        if (currentClass != null && (ifthis || ifinclass)) {
            if (ifthis)
                ifthis = false;
            calledFunc = currentClass.containFunc(it.name, it.pos);
            if (calledFunc == null)
                throw new semanticError("function " + it.name + " not exists", it.pos);
            it.type = new TypeNode(it.pos, gScope.containType(calledFunc.retType.typeName, it.pos));
            return;
        }
        if (currentClass != null) {
            calledFunc = currentClass.containFunc(it.name, it.pos);
            if (calledFunc == null)
                calledFunc = gScope.containFunc(it.name, it.pos);
            if (calledFunc == null && (!it.name.equals("size") || currentClass.dim == 0))
                throw new semanticError("function " + it.name + " not exists", it.pos);
            if (calledFunc != null && (!it.name.equals("size") || currentClass.dim == 0)) {
                if ((calledFunc.args == null && it.args.size() > 0)
                        || (calledFunc.args != null && it.args.size() != calledFunc.args.size()))
                    throw new semanticError("function " + it.name + " requires " + calledFunc.args.size()
                            + " but has " + it.args.size(), it.pos);
                for (int i = 0; i < it.args.size(); i++) {
                    it.args.get(i).accept(this);
                    if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type))
                        throw new semanticError("type not match", it.pos);
                }
                it.type = new TypeNode(it.pos, gScope.containType(calledFunc.retType.typeName, it.pos));
            } else {
                it.type = new TypeNode(it.pos, gScope.containType("int", it.pos));
            }
            // currentClass = null;
            return;
        }
        calledFunc = gScope.containFunc(it.name, it.pos);
        if (calledFunc == null)
            throw new semanticError("function " + it.name + " not exists", it.pos);
        if ((calledFunc.args == null && it.args.size() > 0)
                || (calledFunc.args != null && it.args.size() != calledFunc.args.size()))
            throw new semanticError("function " + it.name + " requires " + calledFunc.args.size()
                    + " but has " + it.args.size(), it.pos);
        for (int i = 0; i < it.args.size(); i++) {
            it.args.get(i).accept(this);
            if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type))
                throw new semanticError("type not match", it.pos);
        }
        it.type = new TypeNode(it.pos, gScope.containType(calledFunc.retType.typeName, it.pos));
    }

    @Override
    public void visit(thisExprNode it) {
        if (currentScope == gScope || currentClass == null)
            throw new semanticError("wrong position of this", it.pos);
        it.type = new TypeNode(it.pos, currentClass);
    }

    @Override
    public void visit(TypeNode it) {

    }

    @Override
    public void visit(visitExprNode it) {
        it.visitor.accept(this);
        ifinclass = true;
        String currentClassName = null;
        if (currentClass != null)
            currentClassName = currentClass.typeName;
        currentClass = gScope.containType(it.visitor.type.GetType().typeName, it.pos);
        if (it.visitor.ifThis())
            ifthis = true;
        it.visitee.accept(this);
        ifthis = false;
        ifinclass = false;
        it.type = it.visitee.type;
        if (currentClassName != null)
            currentClass = gScope.containType(currentClassName, it.pos);
        else
            currentClass = null;
    }

    @Override
    public void visit(exprArrayNode it) {
        for (int i = 0; i < it.expr.size(); i++)
            it.expr.get(i).accept(this);
        it.type = new TypeNode(it.pos, it.expr.get(0).type.GetType(), it.expr.size() - 1);
    }

}
