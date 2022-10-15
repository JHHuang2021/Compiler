package Frontend;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.globalScope;
import Util.position;

import java.util.ArrayList;

import javax.lang.model.type.NullType;

import org.antlr.v4.runtime.ParserRuleContext;

import AST.*;
import AST.binaryExprNode.binaryOpType;
import AST.logicExprNode.logicOpType;
import AST.unaryExprNode.unaryOpType;
import AST.varDefStmtNode.Var;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private globalScope gScope;

    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        ctx.funcDef().forEach(fd -> root.funcDefs.add((FnNode) visit(fd)));
        ctx.classDef().forEach(cd -> root.classDefs.add((classDefNode) visit(cd)));
        ctx.varDef().forEach(vd -> root.varDefs.add((varDefStmtNode) visit(vd)));
        return root;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        ArrayList<Var> varList = new ArrayList<>();

        int varNum = ctx.Identifier().size();
        TypeNode type = (TypeNode) visit(ctx.type());
        for (int i = 0; i < varNum; i++) {
            String name = ctx.Identifier(i).toString();
            ExprNode expr = null;
            if (ctx.expression(i) != null)
                expr = (ExprNode) visit(ctx.expression(i));
            varList.add(new Var(type, name, expr));
        }

        return new varDefStmtNode(new position(ctx), varList);
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        classDefNode classDef = new classDefNode(new position(ctx), ctx.Identifier().toString());
        ctx.varDef().forEach(vd -> classDef.varDefs.add((varDefStmtNode) visit(vd)));
        return classDef;
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        suiteStmtNode node = new suiteStmtNode(new position(ctx));

        if (!ctx.statement().isEmpty()) {
            for (ParserRuleContext stmt : ctx.statement()) {
                StmtNode tmp = (StmtNode) visit(stmt);
                if (tmp != null)
                    node.stmts.add(tmp);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitVardefStmt(MxParser.VardefStmtContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        suiteStmtNode thenSuite = null, elseSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());
        if (ctx.thensent.statement() != null)
            thenSuite = new suiteStmtNode((StmtNode) visit(ctx.thensent.statement()), new position(ctx));
        else
            thenSuite = new suiteStmtNode((StmtNode) visit(ctx.thensent.suite()), new position(ctx));
        if (ctx.elsesent != null && ctx.elsesent.statement() != null)
            elseSuite = new suiteStmtNode((StmtNode) visit(ctx.elsesent.statement()), new position(ctx));
        else if (ctx.elsesent != null && ctx.elsesent.statement() == null)
            elseSuite = new suiteStmtNode((StmtNode) visit(ctx.elsesent.suite()), new position(ctx));

        return new ifStmtNode(condition, thenSuite, elseSuite, new position(ctx));
    }

    @Override

    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        suiteStmtNode forSuite = null;
        ExprNode forexpr1 = null, forexpr2 = null, forexpr3 = null;
        if (ctx.forexpr1().varDef() != null)
            forexpr1 = (ExprNode) visit(ctx.forexpr1().varDef());
        else
            forexpr1 = (ExprNode) visit(ctx.forexpr1().expression());

        if (ctx.forexpr2 != null)
            forexpr2 = (ExprNode) visit(ctx.forexpr2);

        if (ctx.forexpr3 != null)
            forexpr3 = (ExprNode) visit(ctx.forexpr3);

        if (ctx.statement() != null)
            forSuite = new suiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            forSuite = new suiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new forStmtNode(forexpr1, forexpr2, forexpr3, forSuite, new position(ctx));
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        suiteStmtNode whileSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());

        if (ctx.statement() != null)
            whileSuite = new suiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            whileSuite = new suiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new whileStmtNode(condition, whileSuite, new position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ExprNode ret = null;

        if (ctx.expression() != null)
            ret = (ExprNode) visit(ctx.expression());

        return new returnStmtNode(ret, new position(ctx));
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new breakStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new continueStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        return new exprStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return new newExprNode((TypeNode) visit(ctx.typewitharg()), new position(ctx));
    }

    @Override
    public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        ExprNode expr = (ExprNode) visit(ctx.expression());
        if (ctx.beforeop != null) {
            if (ctx.PlusPlus() != null)
                return new unaryExprNode(expr, unaryOpType.laddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new unaryExprNode(expr, unaryOpType.lsubsub, new position(ctx));
            if (ctx.Tilde() != null)
                return new unaryExprNode(expr, unaryOpType.tilde, new position(ctx));
            if (ctx.Minus() != null)
                return new unaryExprNode(expr, unaryOpType.neg, new position(ctx));
        } else {
            if (ctx.PlusPlus() != null)
                return new unaryExprNode(expr, unaryOpType.raddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new unaryExprNode(expr, unaryOpType.rsubsub, new position(ctx));
        }
        return null;
    }

    @Override
    public ASTNode visitFunctionCallExpr(MxParser.FunctionCallExprContext ctx) {
        return visit(ctx.funcCall());
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visit(ctx.primary());
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        return new assignExprNode(lhs, rhs, new position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.Identifier() != null) {
            if (ctx.expression().isEmpty())
                return new varExprNode(ctx.Identifier().toString(), new position(ctx.Identifier()));
            else {
                int dim = ctx.expression().size();
                ArrayList<ExprNode> dimArgs = new ArrayList<>();
                for (int i = 0; i < dim; i++)
                    dimArgs.add((ExprNode) visit(ctx.expression(i)));
                return new varExprNode(ctx.Identifier().toString(), dim, dimArgs, new position(ctx.Identifier()));
            }
        }
        if (ctx.This() != null)
            return new varExprNode(new position(ctx));
        if (ctx.expression(0) != null)
            return visit(ctx.expression(0));
        if (ctx.literal() != null)
            return visit(ctx.literal());
        return null;
    }

    @Override
    public ASTNode visitArgDef(MxParser.ArgDefContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArg(MxParser.ArgContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFuncCall(MxParser.FuncCallContext ctx) {
        funcCallExprNode node = new funcCallExprNode(new position(ctx), ctx.Identifier().toString());
        if (ctx.classPref() != null) {
        } else
            for (int i = 0; i < ctx.arg().expression().size(); i++)
                node.args.add((ExprNode) visit(ctx.arg().expression(i)));

        return node;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        TypeNode type = null;
        if (ctx.type() != null)
            type = (TypeNode) visit(ctx.type());
        FnNode node = new FnNode(new position(ctx), type, ctx.Identifier().toString());
        for (int i = 0; i < ctx.suite().statement().size(); i++)
            node.stmts.add((StmtNode) visit(ctx.suite().statement(i)));
        for (int i = 0; i < ctx.argDef().Identifier().size(); i++) {
            ArrayList<Var> var = new ArrayList<>();
            var.add(new Var((TypeNode) visit(ctx.argDef().type(i)), ctx.argDef().Identifier(i).toString(), null));
            node.argsDef.add(new varDefStmtNode(new position(ctx), var));
        }
        return node;
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.DecimalInteger() != null)
            return new constExprNode<Integer>(Integer.parseInt(ctx.DecimalInteger().toString()), new position(ctx));
        else if (ctx.BoolConstant() != null) {
            String boolConstant = ctx.BoolConstant().toString();
            boolean value = false;
            if (boolConstant == "true")
                value = true;
            return new constExprNode<Boolean>(value, new position(ctx));
        } else if (ctx.StringConstant() != null) {
            return new constExprNode<String>(ctx.StringConstant().toString(), new position(ctx));
        } else
            return new constExprNode<NullType>(null, new position(ctx));
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        Type type = new Type();
        if (ctx.basicType() != null) {
            type.typeName = ctx.basicType().getText();
        } else {
            type.typeName = ctx.array().getText();
            type.array = true;
            type.dim = ctx.array().Dim().size();
        }
        return new TypeNode(new position(ctx), type);
    }

    @Override
    public ASTNode visitTypewitharg(MxParser.TypewithargContext ctx) {
        Type type = new Type();
        if (ctx.basicType() != null) {
            type.typeName = ctx.basicType().toString();
        } else {
            type.typeName = ctx.arraywitharg().toString();
            type.array = true;
            type.dim = ctx.arraywitharg().Int().size();
            for (int i = 0; i < type.dim; i++)
                type.dimArgs.add((ExprNode) visit(ctx.arraywitharg().Int(i)));
        }
        return new TypeNode(new position(ctx), type);
    }

    // @Override
    // public ASTNode visitBasicType(MxParser.BasicTypeContext ctx) {
    // return visitChildren(ctx);
    // }

    @Override
    public ASTNode visitArray(MxParser.ArrayContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLogicExpr(MxParser.LogicExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        logicOpType logicOp = null;
        switch (ctx.op.getText()) {
            case "==":
                logicOp = logicOpType.eq;
                break;
            case "!=":
                logicOp = logicOpType.neq;
                break;
            case "<":
                logicOp = logicOpType.le;
                break;
            case ">":
                logicOp = logicOpType.ge;
                break;
            case "<=":
                logicOp = logicOpType.leq;
                break;
            case ">=":
                logicOp = logicOpType.geq;
                break;
            case "!":
                logicOp = logicOpType.not;
                break;
            case "&&":
                logicOp = logicOpType.andand;
                break;
            case "||":
                logicOp = logicOpType.oror;
                break;
            default:
                break;
        }
        return new logicExprNode(lhs, rhs, logicOp, new position(ctx));
    }

    @Override
    public ASTNode visitArithExpr(MxParser.ArithExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        binaryOpType logicOp = null;
        // System.out.println(ctx.op.getText());
        switch (ctx.op.getText()) {
            case "+":
                logicOp = binaryOpType.add;
                break;
            case "-":
                logicOp = binaryOpType.sub;
                break;
            case "*":
                logicOp = binaryOpType.star;
                break;
            case "/":
                logicOp = binaryOpType.div;
                break;
            case "<<":
                logicOp = binaryOpType.lshift;
                break;
            case ">>":
                logicOp = binaryOpType.rshift;
                break;
            case "^":
                logicOp = binaryOpType.xor;
                break;
            case "&":
                logicOp = binaryOpType.and;
                break;
            case "|":
                logicOp = binaryOpType.or;
                break;
            default:
                break;
        }
        return new binaryExprNode(lhs, rhs, logicOp, new position(ctx));
    }

    // @Override
    // public ASTNode visitArraywitharg(MxParser.ArraywithargContext ctx) {
    // return visitChildren(ctx);
    // }

    @Override
    public ASTNode visitLambda(MxParser.LambdaContext ctx) {
        return visitChildren(ctx);
    }

}