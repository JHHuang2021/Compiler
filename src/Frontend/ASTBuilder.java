package Frontend;

import Frontend.Parser.MxBaseVisitor;
import Frontend.Parser.MxParser;
import Util.Type;
import Util.GlobalScope;
import Util.position;
import Util.error.semanticError;

import java.util.ArrayList;

import javax.lang.model.type.NullType;

import org.antlr.v4.runtime.ParserRuleContext;

import Frontend.AST.*;
import Frontend.AST.BinaryExprNode.BinaryOpType;
import Frontend.AST.LogicExprNode.LogicOpType;
import Frontend.AST.UnaryExprNode.UnaryOpType;
import Frontend.AST.VarDefStmtNode.Var;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope global_scope;

    public ASTBuilder(GlobalScope global_scope) {
        this.global_scope = global_scope;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        ctx.funcDef().forEach(fd -> root.func_defs.add((FunctionNode) visit(fd)));
        ctx.classDef().forEach(cd -> root.class_defs.add((ClassDefNode) visit(cd)));
        ctx.varDef().forEach(vd -> root.var_defs.add((VarDefStmtNode) visit(vd)));
        return root;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        ArrayList<Var> varList = new ArrayList<>();

        int varNum = ctx.Identifier().size();
        TypeNode type = (TypeNode) visit(ctx.type());
        for (int i = 0; i < varNum; i++) {
            String name = ctx.Identifier(i).getText();
            ExprNode expr = null;
            if (ctx.expression(i) != null)
                expr = (ExprNode) visit(ctx.expression(i));
            varList.add(new Var(type.type, name, expr));
        }

        return new VarDefStmtNode(new position(ctx), varList);
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        ClassDefNode class_def = new ClassDefNode(new position(ctx), ctx.Identifier().getText());
        ctx.varDef().forEach(vd -> class_def.var_defs.add((VarDefStmtNode) visit(vd)));
        ctx.funcDef().forEach(fd -> class_def.func_defs.add((FunctionNode) visit(fd)));
        return class_def;
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        SuiteStmtNode node = new SuiteStmtNode(new position(ctx));

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
        SuiteStmtNode thenSuite = null, elseSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());
        if (ctx.thensent.statement() != null)
            thenSuite = new SuiteStmtNode((StmtNode) visit(ctx.thensent.statement()), new position(ctx));
        else
            thenSuite = new SuiteStmtNode((StmtNode) visit(ctx.thensent.suite()), new position(ctx));
        if (ctx.elsesent != null && ctx.elsesent.statement() != null)
            elseSuite = new SuiteStmtNode((StmtNode) visit(ctx.elsesent.statement()), new position(ctx));
        else if (ctx.elsesent != null && ctx.elsesent.statement() == null)
            elseSuite = new SuiteStmtNode((StmtNode) visit(ctx.elsesent.suite()), new position(ctx));

        return new IfStmtNode(condition, thenSuite, elseSuite, new position(ctx));
    }

    @Override

    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        SuiteStmtNode forSuite = null;
        StmtNode forexpr1 = null;
        ExprNode forexpr2 = null, forexpr3 = null;
        if (ctx.forexpr1().varDef() != null)
            forexpr1 = (StmtNode) visit(ctx.forexpr1().varDef());
        else if (ctx.forexpr1().expression() != null)
            forexpr1 = new ExprStmtNode((ExprNode) visit(ctx.forexpr1().expression()), new position(ctx));

        if (ctx.forexpr2 != null)
            forexpr2 = (ExprNode) visit(ctx.forexpr2);

        if (ctx.forexpr3 != null)
            forexpr3 = (ExprNode) visit(ctx.forexpr3);

        if (ctx.statement() != null)
            forSuite = new SuiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            forSuite = new SuiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new ForStmtNode(forexpr1, forexpr2, forexpr3, forSuite, new position(ctx));
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        SuiteStmtNode whileSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());

        if (ctx.statement() != null)
            whileSuite = new SuiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            whileSuite = new SuiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new WhileStmtNode(condition, whileSuite, new position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ExprNode ret = null;

        if (ctx.expression() != null)
            ret = (ExprNode) visit(ctx.expression());

        return new ReturnStmtNode(ret, new position(ctx));
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new BreakStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new ContinueStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        return new ExprStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        if (ctx.typewitharg() != null)
            return new NewExprNode(((TypeNode) visit(ctx.typewitharg())).type, new position(ctx));
        else {
            NewExprNode node = new NewExprNode(
                    new Type(ctx.createFuncCall().Identifier().getText()),
                    new position(ctx));
            node.args = new ArrayList<>();
            for (int i = 0; i < ctx.createFuncCall().arg().expression().size(); i++)
                node.args.add((ExprNode) visit(ctx.createFuncCall().arg().expression(i)));
            return node;
        }
    }

    @Override
    public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        ExprNode expr = (ExprNode) visit(ctx.expression());
        if (ctx.beforeop != null) {
            if (ctx.PlusPlus() != null)
                return new UnaryExprNode(expr, UnaryOpType.laddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new UnaryExprNode(expr, UnaryOpType.lsubsub, new position(ctx));
            if (ctx.Tilde() != null)
                return new UnaryExprNode(expr, UnaryOpType.tilde, new position(ctx));
            if (ctx.Minus() != null)
                return new UnaryExprNode(expr, UnaryOpType.neg, new position(ctx));
        } else {
            if (ctx.PlusPlus() != null)
                return new UnaryExprNode(expr, UnaryOpType.raddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new UnaryExprNode(expr, UnaryOpType.rsubsub, new position(ctx));
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
        return new AssignExprNode(lhs, rhs, new position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.Identifier() != null) {
            // int dim = ctx.expression().size();
            // ArrayList<ExprNode> dim_args = null;
            // if (dim > 0) {
            // dim_args = new ArrayList<>();
            // for (int i = 0; i < dim; i++)
            // dim_args.add((ExprNode) visit(ctx.expression(i)));
            // }

            return new VarExprNode(new position(ctx), ctx.Identifier().getText());
        }
        if (ctx.literal() != null)
            return visit(ctx.literal());
        if (ctx.expression() != null)
            return (ExprNode) visit(ctx.expression());
        if (ctx.This() != null)
            return new ThisExprNode(new position(ctx));
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
    public ASTNode visitFuncCall(MxParser.FuncCallContext ctx) {
        FuncCallExprNode node = new FuncCallExprNode(new position(ctx), ctx.Identifier().getText());
        // Todo
        for (int i = 0; i < ctx.arg().expression().size(); i++)
            node.args.add((ExprNode) visit(ctx.arg().expression(i)));

        return node;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        TypeNode type = null;
        if (ctx.type() != null)
            type = (TypeNode) visit(ctx.type());
        Type t = null;
        if (type != null)
            t = type.type;
        FunctionNode node = new FunctionNode(new position(ctx), t, ctx.Identifier().getText());
        for (int i = 0; i < ctx.suite().statement().size(); i++)
            node.stmts.add((StmtNode) visit(ctx.suite().statement(i)));
        for (int i = 0; i < ctx.argDef().Identifier().size(); i++) {
            ArrayList<Var> var = new ArrayList<>();
            var.add(new Var(((TypeNode) visit(ctx.argDef().type(i))).type, ctx.argDef().Identifier(i).getText(),
                    null));
            node.args_def.add(new VarDefStmtNode(new position(ctx), var));
        }
        return node;
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.DecimalInteger() != null)
            return new ConstExprNode<Long>(Long.parseLong(ctx.DecimalInteger().getText()), new position(ctx));
        else if (ctx.BoolConstant() != null) {
            String bool_constant = ctx.BoolConstant().getText();
            boolean value = false;
            if (bool_constant.equals("true"))
                value = true;
            return new ConstExprNode<Boolean>(value, new position(ctx));
        } else if (ctx.StringConstant() != null) {
            return new ConstExprNode<String>(ctx.StringConstant().getText(), new position(ctx));
        } else
            return new ConstExprNode<NullType>(null, new position(ctx));
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        Type type = new Type();
        if (ctx.basicType() != null) {
            type.type_name = ctx.basicType().getText();
        } else {
            type.type_name = ctx.array().basicType().getText();
            type.dim = ctx.array().LeftBracket().size();
        }
        return new TypeNode(new position(ctx), type);
    }

    @Override
    public ASTNode visitTypewitharg(MxParser.TypewithargContext ctx) {
        Type type = new Type();
        if (ctx.wrongarraywitharg() != null)
            throw new semanticError("wrong type of array", new position(ctx));
        else if (ctx.basicType() != null) {
            type.type_name = ctx.basicType().getText();
        } else {
            type.type_name = ctx.arraywitharg().basicType().getText();
            type.dim = ctx.arraywitharg().LeftBracket().size();
            if (type.dim_args == null)
                type.dim_args = new ArrayList<>();
            for (int i = 0; i < ctx.arraywitharg().expression().size(); i++)
                type.dim_args.add((ExprNode) visit(ctx.arraywitharg().expression(i)));
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
                rhs = null;
        if (ctx.expression(1) != null)
            rhs = (ExprNode) visit(ctx.expression(1));
        LogicOpType logicOp = null;
        switch (ctx.op.getText()) {
            case "==":
                logicOp = LogicOpType.eq;
                break;
            case "!=":
                logicOp = LogicOpType.ne;
                break;
            case "<":
                logicOp = LogicOpType.slt;
                break;
            case ">":
                logicOp = LogicOpType.sgt;
                break;
            case "<=":
                logicOp = LogicOpType.sle;
                break;
            case ">=":
                logicOp = LogicOpType.sge;
                break;
            case "!":
                logicOp = LogicOpType.not;
                break;
            case "&&":
                logicOp = LogicOpType.andand;
                break;
            case "||":
                logicOp = LogicOpType.oror;
                break;
            default:
                break;
        }
        return new LogicExprNode(lhs, rhs, logicOp, new position(ctx));
    }

    @Override
    public ASTNode visitArithExpr(MxParser.ArithExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        BinaryOpType logicOp = null;
        // System.out.println(ctx.op.getText());
        switch (ctx.op.getText()) {
            case "+":
                logicOp = BinaryOpType.add;
                break;
            case "-":
                logicOp = BinaryOpType.sub;
                break;
            case "*":
                logicOp = BinaryOpType.mul;
                break;
            case "/":
                logicOp = BinaryOpType.sdiv;
                break;
            case "%":
                logicOp = BinaryOpType.srem;
                break;
            case "<<":
                logicOp = BinaryOpType.shl;
                break;
            case ">>":
                logicOp = BinaryOpType.ashr;
                break;
            case "^":
                logicOp = BinaryOpType.xor;
                break;
            case "&":
                logicOp = BinaryOpType.and;
                break;
            case "|":
                logicOp = BinaryOpType.or;
                break;
            default:
                break;
        }
        return new BinaryExprNode(lhs, rhs, logicOp, new position(ctx));
    }

    // @Override
    // public ASTNode visitArraywitharg(MxParser.ArraywithargContext ctx) {
    // return visitChildren(ctx);
    // }

    @Override
    public ASTNode visitLambda(MxParser.LambdaContext ctx) {
        return visit(ctx.lambdaExpression());
    }

    @Override
    public ASTNode visitLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        LambdaExprNode node = new LambdaExprNode(new position(ctx));
        if (ctx.And() != null)
            node.if_global_scope = true;
        node.stmts = ((SuiteStmtNode) visit(ctx.suite())).stmts;
        for (int i = 0; i < ctx.argDef().Identifier().size(); i++) {
            ArrayList<Var> var = new ArrayList<>();
            var.add(new Var(((TypeNode) visit(ctx.argDef().type(i))).type, ctx.argDef().Identifier(i).getText(),
                    null));
            node.args_def.add(new VarDefStmtNode(new position(ctx), var));
        }
        for (int i = 0; i < ctx.arg().expression().size(); i++)
            node.args.add((ExprNode) visit(ctx.arg().expression(i)));
        return node;
    }

    @Override
    public ASTNode visitExprArray(MxParser.ExprArrayContext ctx) {
        ExprArrayNode node = new ExprArrayNode(new position(ctx));
        node.expr = (ExprNode) visit(ctx.expression(0));
        node.offset = (ExprNode) visit(ctx.expression(1));
        return node;
    }

    @Override
    public ASTNode visitVisitExpr(MxParser.VisitExprContext ctx) {
        VisitExprNode node = new VisitExprNode(new position(ctx), (ExprNode) visit(ctx.expression(0)),
                (ExprNode) visit(ctx.expression(1)));
        return node;
    }

}