package Backend.IR;

import java.util.HashMap;
import java.util.Stack;

import javax.lang.model.type.NullType;

import Backend.IR.IRBlock.*;
import Backend.IR.IRInstr.*;
import Backend.IR.IRType.*;
import Backend.IR.IRValue.*;
import Frontend.AST.ASTVisitor;
import Frontend.AST.AssignExprNode;
import Frontend.AST.BinaryExprNode;
import Frontend.AST.BreakStmtNode;
import Frontend.AST.ClassDefNode;
import Frontend.AST.ConstExprNode;
import Frontend.AST.ContinueStmtNode;
import Frontend.AST.ExprArrayNode;
import Frontend.AST.ExprNode;
import Frontend.AST.ExprStmtNode;
import Frontend.AST.ForStmtNode;
import Frontend.AST.FuncCallExprNode;
import Frontend.AST.FunctionNode;
import Frontend.AST.IfStmtNode;
import Frontend.AST.LambdaExprNode;
import Frontend.AST.LogicExprNode;
import Frontend.AST.NewExprNode;
import Frontend.AST.ReturnStmtNode;
import Frontend.AST.RootNode;
import Frontend.AST.StmtNode;
import Frontend.AST.SuiteStmtNode;
import Frontend.AST.ThisExprNode;
import Frontend.AST.TypeNode;
import Frontend.AST.UnaryExprNode;
import Frontend.AST.VarDefStmtNode;
import Frontend.AST.VarExprNode;
import Frontend.AST.VisitExprNode;
import Frontend.AST.WhileStmtNode;
import Frontend.AST.BinaryExprNode.BinaryOpType;
import Frontend.AST.LogicExprNode.LogicOpType;
import Frontend.AST.VarDefStmtNode.Var;

public class IRBuilder implements ASTVisitor {
    public IRBlock cur_block = null;
    public IRClass cur_class = null;
    public Register cur_visit_class = null;
    public IRFunc cur_func = null;
    public HashMap<String, IRType> types = new HashMap<>();
    public HashMap<String, IRFunc> funcs = new HashMap<>();
    public IRGlobal global = null;
    public Stack<IRBlock> break_to = new Stack<>();
    public Stack<IRBlock> continue_to = new Stack<>();
    public HashMap<String, IRValue> val_recoder = new HashMap<>();
    public int str_cnt = 0;
    public int reg_cnt = 0;

    void LoadBuiltinFunc() {
    }

    String GetFuncName(FunctionNode it) {
        String func_name = null;
        if (cur_class != null) {
            if (!cur_class.name.equals("string"))
                func_name = cur_class.name + ".";
            else
                func_name = "__builtin_";
        }
        return func_name;
    }

    public IRBuilder() {
        this.global = new IRGlobal();
    }

    @Override
    public void visit(AssignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        cur_block.instr.add(new IRStore(it.lhs.val, it.rhs.val));
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        cur_block.instr.add(new IRBin(it.val, it.opCode, it.lhs.val, it.rhs.val));
    }

    @Override
    public void visit(BreakStmtNode it) {
        cur_block.instr.add(new IRBr(break_to.peek()));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(ClassDefNode it) {
        cur_class = (IRClass) ((IRPointer) types.get(it.name)).basic_type;
        for (FunctionNode i : it.func_defs)
            i.accept(this);
        cur_class = null;
    }

    @Override
    public <T> void visit(ConstExprNode<T> it) {
        if (it.value instanceof Long)
            it.val = new Constant(types.get("int"), (int) it.value);
        else if (it.value instanceof Boolean)
            it.val = new Constant(types.get("bool"), (int) it.value);
        else if (it.value instanceof NullType)
            it.val = new Constant(types.get("null"), 0);
        else if (it.value instanceof String) {
            StringConst str;
            if (val_recoder.containsKey(it.value))
                str = (StringConst) val_recoder.get(it.value);
            else {
                str = new StringConst(new IRString(((String) it.value).length() - 2),
                        (String) it.value, str_cnt++);
                global.strs.add(str);
                val_recoder.put((String) it.value, str);
            }
            it.val = str;
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        cur_block.instr.add(new IRBr(continue_to.peek()));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(ExprArrayNode it) {
        it.expr.accept(this);
        it.offset.accept(this);
        IRType type = types.get(it.type.type_name);
        if (it.type.dim != 0)
            type = new IRPointer(it.type.dim, type);

        it.val = new Register(type, "expr_array", reg_cnt++);
        IRGetElementPtr inst = new IRGetElementPtr((Register) it.val, type, it.expr.val, it.offset.val);
        cur_block.instr.add(inst);
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.accept(this);
    }

    @Override
    public void visit(ForStmtNode it) {
        IRBlock cond_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cond_block);

        IRBlock body_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(body_block);

        IRBlock next_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(next_block);

        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(next_block);

        if (it.var != null)
            it.var.accept(this);
        cur_block.instr.add(new IRBr(next_block));
        cur_block = cond_block;

        if (it.condition != null) {
            it.condition.accept(this);
            cur_block.instr.add(new IRBr(it.condition.val, body_block, new_block));
        } else
            cur_block.instr.add(new IRBr(body_block));
        cur_block = next_block;

        if (it.step != null)
            it.step.accept(this);
        cur_block.instr.add(new IRBr(cond_block));
        cur_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

    @Override
    public void visit(FuncCallExprNode it) {
        String func_name = null;
        if (cur_visit_class != null)
            func_name = cur_visit_class.type.name + ".";

        func_name += it.name;
        IRCall func_call = new IRCall(func_name);
        if (cur_visit_class != null)
            func_call.args.add(cur_visit_class);

        for (ExprNode i : it.args) {
            i.accept(this);
            func_call.args.add(i.val);
        }

        cur_block.instr.add(func_call);
    }

    @Override
    public void visit(FunctionNode it) {
        cur_func = funcs.get(GetFuncName(it));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);

        if (cur_class != null)
            cur_func.args.add(cur_func.this_ptr);

        for (VarDefStmtNode i : it.args_def) {
            i.accept(this);
            cur_func.args.add(i.var.get(0).init.val);
        }

        for (StmtNode i : it.stmts)
            i.accept(this);

        cur_block = null;
        cur_func = null;
    }

    @Override
    public void visit(IfStmtNode it) {
        IRBlock true_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(true_block);
        IRBlock false_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(false_block);
        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        it.condition.accept(this);
        cur_block.instr.add(new IRBr(it.condition.val, true_block, false_block));
        cur_block = true_block;

        it.then_suite.accept(this);
        cur_block.instr.add(new IRBr(new_block));
        cur_block = false_block;

        if (it.else_suite != null)
            it.else_suite.accept(this);

        cur_block.instr.add(new IRBr(new_block));
        cur_block = new_block;
    }

    @Override
    public void visit(LambdaExprNode it) {

    }

    @Override
    public void visit(LogicExprNode it) {
        it.lhs.accept(this);
        IRBlock next_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(next_block);
        IRValue lval = it.lhs.val;

        if (it.op_code == LogicOpType.andand) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 1) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
            } else {
                it.val = new Register(types.get("bool"), "and_and", reg_cnt++);
                IRBlock block1 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block1);
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                cur_block.instr.add(new IRBr(lval, block2, block1));

                cur_block = block1;
                cur_block.instr.add(new IRStore(it.val, new Constant(types.get("bool"), 0)));
                cur_block.instr.add(new IRBr(next_block));

                cur_block = block2;
                it.rhs.accept(this);
                cur_block.instr.add(new IRStore(it.val, it.rhs.val));
                cur_block.instr.add(new IRBr(next_block));
            }
            cur_block = next_block;
        } else if (it.op_code == LogicOpType.oror) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 0) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
            } else {
                it.val = new Register(types.get("bool"), "or_or", reg_cnt++);
                IRBlock block1 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block1);
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                cur_block.instr.add(new IRBr(lval, block1, block2));

                cur_block = block1;
                cur_block.instr.add(new IRStore(it.val, new Constant(types.get("bool"), 1)));
                cur_block.instr.add(new IRBr(next_block));

                cur_block = block2;
                it.rhs.accept(this);
                cur_block.instr.add(new IRStore(it.val, it.rhs.val));
                cur_block.instr.add(new IRBr(next_block));
            }
            cur_block = next_block;
        } else if (it.op_code == LogicOpType.not) {
            IRType type = types.get("bool");
            if (lval instanceof Constant)
                it.val = new Constant(type, ((Constant) lval).val);
            else {
                it.val = new Register(type, "not", reg_cnt++);
                cur_block.instr.add(new IRBin(it.val, BinaryOpType.xor, new Constant(type, 1), lval));
            }
            cur_func.blocks.remove(cur_func.blocks.size() - 1);
        } else {
            it.lhs.accept(this);
            it.rhs.accept(this);
            cur_block.instr.add(new IRCmp(it.val, it.op_code, it.lhs.val, it.rhs.val));
            cur_func.blocks.remove(cur_func.blocks.size() - 1);
        }

    }

    @Override
    public void visit(NewExprNode it) {
        for (ExprNode i : it.args)
            i.accept(this);
    }

    @Override
    public void visit(RootNode it) {
        types.put("void", new IRVoid());
        types.put("null", new IRVoid());
        types.put("bool", new IRBool());
        types.put("int", new IRInt());
        types.put("string", new IRString(-1));

        for (ClassDefNode i : it.class_defs) {
            IRClass new_class = new IRClass(i.name);
            IRPointer class_ptr = new IRPointer(0, new_class);
            types.put(i.name, class_ptr);
            global.classes.add(new_class);
        }

        for (ClassDefNode i : it.class_defs) {
            IRClass curr_class = (IRClass) ((IRPointer) types.get(i.name)).basic_type;
            for (VarDefStmtNode j : i.var_defs) {
                IRType type = types.get(j.var.get(0).type.type_name);
                for (Var k : j.var)
                    if (k.type.dim != 0)
                        type = new IRPointer(k.type.dim, type);
                curr_class.var_type.add(type);
            }
        }

        LoadBuiltinFunc();

        for (ClassDefNode i : it.class_defs) {
            cur_class = (IRClass) types.get(i.name);
            for (FunctionNode j : i.func_defs) {
                IRType func_type = types.get(j.ret_type.type_name);
                if (j.ret_type.dim != 0)
                    func_type = new IRPointer(j.ret_type.dim, func_type);
                String func_name = GetFuncName(j);
                IRFunc new_func = new IRFunc(func_name, func_type);
                new_func.this_ptr = new Register(cur_class, "this_ptr", reg_cnt++);
                funcs.put(func_name, new_func);
                global.funcs.add(new_func);
            }
            cur_class = null;
        }

        for (FunctionNode i : it.func_defs) {
            IRType func_type = types.get(i.ret_type.type_name);
            if (i.ret_type.dim != 0)
                func_type = new IRPointer(i.ret_type.dim, func_type);
            String func_name = GetFuncName(i);
            IRFunc new_func = new IRFunc(func_name, func_type);
            funcs.put(func_name, new_func);
            global.funcs.add(new_func);
        }

    }

    @Override
    public void visit(ReturnStmtNode it) {
        cur_block.is_returned = true;
        if (it.value != null) {
            it.value.accept(this);
            it.value.val.type = cur_func.ret_type;
            cur_block.instr.add(new IRRet(it.value.val));
        } else
            cur_block.instr.add(new IRRet(new Constant(types.get("void"), 0)));

        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(SuiteStmtNode it) {
        for (StmtNode i : it.stmts)
            i.accept(this);
    }

    @Override
    public void visit(ThisExprNode it) {
        it.val = cur_func.this_ptr;
    }

    @Override
    public void visit(TypeNode it) {

    }

    @Override
    public void visit(UnaryExprNode it) {

    }

    @Override
    public void visit(VarDefStmtNode it) {
        for (Var i : it.var) {
            i.init.accept(this);
            IRType type = types.get(i.init.type.type_name);
            if (i.init.type.dim != 0)
                type = new IRPointer(i.init.type.dim, type);
            i.val = new Register(type, "var_def", reg_cnt++);
            val_recoder.put(i.name, i.val);
            cur_block.instr.add(new IRAlloc(type, i.val));
        }
    }

    @Override
    public void visit(VarExprNode it) {
        it.val = val_recoder.get(it.name);
    }

    @Override
    public void visit(VisitExprNode it) {
        it.visitor.accept(this);
        cur_visit_class = (Register) it.val;
        it.visitee.accept(this);
        cur_visit_class = null;
        it.val = it.visitee.val;
    }

    @Override
    public void visit(WhileStmtNode it) {
        IRBlock cond_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cond_block);

        IRBlock body_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(body_block);

        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(body_block);

        it.condition.accept(this);
        cur_block.instr.add(new IRBr(it.condition.val, body_block, new_block));
        cur_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

}