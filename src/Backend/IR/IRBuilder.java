package Backend.IR;

import java.util.HashMap;
import java.util.Stack;

import javax.lang.model.type.NullType;

import org.antlr.v4.runtime.misc.Pair;

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
import Frontend.AST.UnaryExprNode.UnaryOpType;
import Frontend.AST.VarDefStmtNode.Var;
import Util.GlobalScope;
import Util.Scope;
import Util.Scope.ScopeType;

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
    public boolean args_define = false;

    private Scope current_scope;
    private GlobalScope global_scope;

    void LoadBuiltinFunc() {
        IRFunc print = new IRFunc("__builtin_print", types.get("void"));
        print.args.add(new Register(types.get("string"), "s", false, 0));
        print.is_builtin = true;
        funcs.put("print", print);
        global.funcs.add(print);

        IRFunc println = new IRFunc("__builtin_println", types.get("void"));
        println.args.add(new Register(types.get("string"), "s", false, 0));
        println.is_builtin = true;
        funcs.put("println", println);
        global.funcs.add(println);

        IRFunc printInt = new IRFunc("__builtin_printInt", types.get("void"));
        printInt.args.add(new Register(types.get("int"), "int", false, 0));
        printInt.is_builtin = true;
        funcs.put("printInt", printInt);
        global.funcs.add(printInt);

        IRFunc printlnInt = new IRFunc("__builtin_printlnInt", types.get("void"));
        printlnInt.args.add(new Register(types.get("int"), "int", false, 0));
        printlnInt.is_builtin = true;
        funcs.put("printlnInt", printlnInt);
        global.funcs.add(printlnInt);

        IRFunc getInt = new IRFunc("__builtin_getInt", types.get("int"));
        getInt.is_builtin = true;
        funcs.put("getInt", getInt);
        global.funcs.add(getInt);

        IRFunc getString = new IRFunc("__builtin_getString", types.get("string"));
        getString.is_builtin = true;
        funcs.put("getString", getString);
        global.funcs.add(getString);

        IRFunc toString = new IRFunc("__builtin_toString", types.get("string"));
        toString.args.add(new Register(types.get("int"), "int", false, 0));
        toString.is_builtin = true;
        funcs.put("toString", toString);
        global.funcs.add(toString);

        IRFunc malloc = new IRFunc("__builtin_malloc", types.get("string"));
        malloc.args.add(new Register(types.get("int"), "size", false, 0));
        malloc.is_builtin = true;
        funcs.put(malloc.func_name, malloc);
        global.funcs.add(malloc);

        IRFunc str_add = new IRFunc("__builtin_str_add", types.get("string"));
        str_add.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_add.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_add.is_builtin = true;
        funcs.put(str_add.func_name, str_add);
        global.funcs.add(str_add);

        IRFunc str_eq = new IRFunc("__builtin_str_eq", types.get("bool"));
        str_eq.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_eq.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_eq.is_builtin = true;
        funcs.put(str_eq.func_name, str_eq);
        global.funcs.add(str_eq);

        IRFunc str_ne = new IRFunc("__builtin_str_ne", types.get("bool"));
        str_ne.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_ne.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_ne.is_builtin = true;
        funcs.put(str_ne.func_name, str_ne);
        global.funcs.add(str_ne);

        IRFunc str_gt = new IRFunc("__builtin_str_gt", types.get("bool"));
        str_gt.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_gt.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_gt.is_builtin = true;
        funcs.put(str_gt.func_name, str_gt);
        global.funcs.add(str_gt);

        IRFunc str_ge = new IRFunc("__builtin_str_ge", types.get("bool"));
        str_ge.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_ge.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_ge.is_builtin = true;
        funcs.put(str_ge.func_name, str_ge);
        global.funcs.add(str_ge);

        IRFunc str_lt = new IRFunc("__builtin_str_lt", types.get("bool"));
        str_lt.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_lt.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_lt.is_builtin = true;
        funcs.put(str_lt.func_name, str_lt);
        global.funcs.add(str_lt);

        IRFunc str_le = new IRFunc("__builtin_str_le", types.get("bool"));
        str_le.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_le.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_le.is_builtin = true;
        funcs.put(str_le.func_name, str_le);
        global.funcs.add(str_le);

        IRFunc str_len = new IRFunc("__builtin_length", types.get("int"));
        str_len.args.add(new Register(types.get("string"), "this", false, 0));
        str_len.is_builtin = true;
        funcs.put(str_len.func_name, str_len);
        global.funcs.add(str_len);

        IRFunc str_substring = new IRFunc("__builtin_substring", types.get("string"));
        str_substring.args.add(new Register(types.get("string"), "this", false, 0));
        str_substring.args.add(new Register(types.get("int"), "left", false, 1));
        str_substring.args.add(new Register(types.get("int"), "right", false, 2));
        str_substring.is_builtin = true;
        funcs.put(str_substring.func_name, str_substring);
        global.funcs.add(str_substring);

        IRFunc str_parseInt = new IRFunc("__builtin_parseInt", types.get("int"));
        str_parseInt.args.add(new Register(types.get("string"), "this", false, 0));
        str_parseInt.is_builtin = true;
        funcs.put(str_parseInt.func_name, str_parseInt);
        global.funcs.add(str_parseInt);

        IRFunc str_ord = new IRFunc("__builtin_ord", types.get("int"));
        str_ord.args.add(new Register(types.get("string"), "this", false, 0));
        str_ord.args.add(new Register(types.get("int"), "pos", false, 1));
        str_ord.is_builtin = true;
        funcs.put(str_ord.func_name, str_ord);
        global.funcs.add(str_ord);

        // IRFunc init_func = new IRFunc("__builtin_init", types.get("void"));
        // funcs.put("__builtin_init", init_func);
        // global.funcs.add(init_func);
    }

    String GetFuncName(FunctionNode it) {
        String func_name = "";
        if (cur_class != null) {
            if (!cur_class.name.equals("string"))
                func_name = cur_class.name + ".";
            else
                func_name = "__builtin_";
        }
        return func_name + it.func_name;
    }

    public IRBuilder(GlobalScope global_scope) {
        this.global = new IRGlobal();
        current_scope = this.global_scope = global_scope;
    }

    @Override
    public void visit(AssignExprNode it) {
        it.rhs.accept(this);
        it.lhs.accept(this);
        IRValue store_val = it.rhs.val;
        if (it.rhs instanceof NewExprNode) {

            cur_block.instr.add(new IRAlloc(new IRPointer(1, it.rhs.val.type), it.lhs.val));
            it.val = it.lhs.val;
        } else if (store_val.type instanceof IRPointer) {
            Register reg = new Register(store_val.type.FatherType(), "load", false,
                    reg_cnt++);
            cur_block.instr.add(new IRLoad(reg, (Register) store_val));
            store_val = reg;
            cur_block.instr.add(new IRStore(it.lhs.val, store_val));
        }

    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        it.val = new Register(it.lhs.val.type, "binary_expr", false, reg_cnt++);
        IRValue vlhs = it.lhs.val, vrhs = it.rhs.val;
        if (vlhs.type instanceof IRPointer) {
            IRType basic_type = ((IRPointer) vlhs.type).father_type;
            it.val.type = basic_type;
            Register load_reg = new Register(basic_type, "load", false, reg_cnt++);
            cur_block.instr.add(new IRLoad(load_reg, (Register) vlhs));
            vlhs = load_reg;
        }
        if (vrhs.type instanceof IRPointer) {
            Register load_reg = new Register(((IRPointer) vrhs.type).father_type, "load", false, reg_cnt++);
            cur_block.instr.add(new IRLoad(load_reg, (Register) vrhs));
            vrhs = load_reg;
        }
        cur_block.instr.add(new IRBin(it.val, it.opCode, vlhs, vrhs));
    }

    @Override
    public void visit(BreakStmtNode it) {
        cur_block.instr.add(new IRBr(break_to.peek()));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(ClassDefNode it) {
        // cur_class = (IRClass) ((IRPointer) types.get(it.name)).basic_type;
        current_scope = new Scope(current_scope);
        cur_class = (IRClass) types.get(it.name);
        for (VarDefStmtNode i : it.var_defs)
            i.accept(this);
        for (FunctionNode i : it.func_defs)
            i.accept(this);
        cur_class = null;
        current_scope = current_scope.ParentScope();
    }

    @Override
    public <T> void visit(ConstExprNode<T> it) {
        if (it.value instanceof Long)
            it.val = new Constant(types.get("int"), ((Long) it.value).intValue());
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
            str.s = str.s.substring(1, str.s.length() - 1);
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
        IRType type = it.expr.val.type.FatherType();
        it.val = new Register(type, "expr_array", false, reg_cnt++);
        IRGetElementPtr inst = new IRGetElementPtr((Register) it.val, type, it.expr.val, it.offset.val);
        cur_block.instr.add(inst);
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
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

        current_scope = new Scope(current_scope);

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

        cur_block = body_block;
        it.for_suite.accept(this);
        cur_block.instr.add(new IRBr(next_block));

        cur_block = new_block;
        current_scope = current_scope.ParentScope();

        break_to.pop();
        continue_to.pop();
    }

    @Override
    public void visit(FuncCallExprNode it) {
        if (cur_visit_class != null && (cur_visit_class.type.FatherType() instanceof IRPointer)
                && it.name.equals("size")) {
            it.val = ((IRPointer) cur_visit_class.type.FatherType()).max_dim;
            return;
        }

        String func_name = "";
        if (cur_visit_class != null)
            func_name = cur_visit_class.type.name + ".";

        func_name += it.name;
        IRFunc called_func = null;
        if (cur_class != null && cur_visit_class == null)
            called_func = funcs.get(cur_class.name + '.' + func_name);
        if (called_func == null)
            called_func = funcs.get(func_name);
        if (called_func == null)
            called_func = funcs.get("__builtin_" + func_name);
        it.val = new Register(called_func.ret_type, func_name, false, reg_cnt++);
        IRCall func_call = new IRCall(func_name, it.val);
        if (cur_visit_class != null)
            func_call.args.add(cur_visit_class);
        cur_visit_class = null;

        for (ExprNode i : it.args) {
            i.accept(this);
            func_call.args.add(i.val);
        }

        cur_block.instr.add(func_call);
    }

    @Override
    public void visit(FunctionNode it) {
        current_scope = new Scope(current_scope, ScopeType.FUNC);
        cur_func = funcs.get(GetFuncName(it));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);

        if (cur_class != null)
            cur_func.args.add(cur_func.this_ptr);

        args_define = true;
        for (VarDefStmtNode i : it.args_def) {
            i.accept(this);
            cur_func.args.add((Register) i.var.get(0).val);
        }
        args_define = false;

        for (StmtNode i : it.stmts)
            i.accept(this);

        cur_block = null;
        cur_func = null;
        current_scope = current_scope.ParentScope();
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
        IRBlock next_block = new IRBlock(cur_func.func_name + "_" + (cur_func.blocks.size() + 1));
        IRValue lval = it.lhs.val;

        if (it.op_code == LogicOpType.andand) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 1) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
                cur_block = next_block;
            } else {
                IRBlock block1 = cur_block;
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                IRValue cond_val = lval;
                if (cond_val.type instanceof IRPointer) {
                    Register reg = new Register(types.get("bool"), "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) cond_val));
                    cond_val = reg;
                }

                cur_block.instr.add(new IRBr(cond_val, block2, next_block));

                cur_block = block2;
                it.rhs.accept(this);
                IRValue vrhs = it.rhs.val;
                if (vrhs.type instanceof IRPointer) {
                    Register reg = new Register(types.get("bool"), "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) vrhs));
                    vrhs = reg;
                }

                cur_block.instr.add(new IRBr(next_block));

                it.val = new Register(types.get("bool"), "and_and", false, reg_cnt++);
                cur_block = next_block;
                cur_block.instr.add(
                        new IRPhi((Register) it.val, new Constant(types.get("bool"), 0), vrhs, block1, block2));
            }
            cur_func.blocks.add(next_block);
        } else if (it.op_code == LogicOpType.oror) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 0) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
                cur_block = next_block;
            } else {
                IRBlock block1 = cur_block;
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                IRValue cond_val = lval;
                if (cond_val.type instanceof IRPointer) {
                    Register reg = new Register(types.get("bool"), "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) cond_val));
                    cond_val = reg;
                }

                cur_block.instr.add(new IRBr(cond_val, next_block, block2));

                cur_block = block2;
                it.rhs.accept(this);
                IRValue vrhs = it.rhs.val;
                if (vrhs.type instanceof IRPointer) {
                    Register reg = new Register(types.get("bool"), "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) vrhs));
                    vrhs = reg;
                }

                cur_block.instr.add(new IRBr(next_block));

                it.val = new Register(types.get("bool"), "or_or", false, reg_cnt++);
                cur_block = next_block;
                cur_block.instr.add(
                        new IRPhi((Register) it.val, new Constant(types.get("bool"), 1), vrhs, block1, block2));
            }
            cur_func.blocks.add(next_block);
        } else if (it.op_code == LogicOpType.not) {
            IRType type = types.get("bool");
            if (lval instanceof Constant)
                it.val = new Constant(type, ((Constant) lval).val);
            else {
                it.val = new Register(type, "not", false, reg_cnt++);
                cur_block.instr.add(new IRBin(it.val, BinaryOpType.xor, new Constant(type, 1), lval));
            }
        } else {
            it.val = new Register(types.get("bool"), "cmp", false, reg_cnt++);
            it.lhs.accept(this);
            it.rhs.accept(this);
            cur_block.instr.add(new IRCmp(it.val, it.op_code, it.lhs.val, it.rhs.val));
        }
    }

    @Override
    public void visit(NewExprNode it) {
        IRType type = types.get(it.type.type_name);
        for (int i = 0; i < it.type.dim; i++)
            type = new IRPointer(1, type);
        it.val = new Register(type, "new_expr", false, reg_cnt++);

        IRType tmp = type;
        if (it.type.dim_args != null)
            for (ExprNode i : it.type.dim_args) {
                i.accept(this);
                ((IRPointer) tmp).max_dim = i.val;
                tmp = tmp.FatherType();
            }
        while (tmp instanceof IRPointer) {
            ((IRPointer) tmp).max_dim = new Register(types.get("int"), "max_dim", false, reg_cnt++);
            tmp = tmp.FatherType();
        }
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
            types.put(i.name, new_class);
            global.classes.add(new_class);
        }

        for (ClassDefNode i : it.class_defs) {
            IRClass curr_class = (IRClass) types.get(i.name);
            for (int m = 0; m < i.var_defs.size(); m++) {
                VarDefStmtNode j = i.var_defs.get(m);
                IRType type = types.get(j.var.get(0).type.type_name);
                for (Var k : j.var) {
                    for (int l = 0; l < k.type.dim; l++) {
                        type = new IRPointer(1, type, reg_cnt++, types.get("int"));
                    }
                    curr_class.idt_to_rank.put(k.name, m);
                }
                curr_class.var_type.add(type);
            }
        }

        LoadBuiltinFunc();

        for (ClassDefNode i : it.class_defs) {
            cur_class = (IRClass) types.get(i.name);
            for (FunctionNode j : i.func_defs) {
                IRType func_type = null;
                if (j.ret_type.type_name.equals("Create"))
                    func_type = types.get("void");
                else
                    func_type = types.get(j.ret_type.type_name);
                for (int k = 0; k < j.ret_type.dim; k++)
                    func_type = new IRPointer(1, func_type, reg_cnt++, types.get("int"));
                String func_name = GetFuncName(j);
                IRFunc new_func = new IRFunc(func_name, func_type);
                new_func.this_ptr = new Register(new IRPointer(1, cur_class), "this_ptr", false, reg_cnt++);
                funcs.put(func_name, new_func);
                global.funcs.add(new_func);
            }
            cur_class = null;
        }

        for (FunctionNode i : it.func_defs) {
            IRType func_type = types.get(i.ret_type.type_name);
            for (int j = 0; j < i.ret_type.dim; j++)
                func_type = new IRPointer(1, func_type, reg_cnt++, types.get("int"));
            String func_name = GetFuncName(i);
            IRFunc new_func = new IRFunc(func_name, func_type);
            funcs.put(func_name, new_func);
            global.funcs.add(new_func);
        }

        IRFunc global_init_func = new IRFunc("__global_var_init", types.get("void"));
        funcs.put(global_init_func.func_name, global_init_func);
        global.funcs.add(global_init_func);
        cur_func = global_init_func;
        cur_block = new IRBlock("__global_var_init_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
        for (VarDefStmtNode i : it.var_defs) {
            i.accept(this);
            global.vars.add((Register) i.var.get(0).val);
        }
        cur_block = null;
        cur_func = null;

        for (ClassDefNode i : it.class_defs)
            i.accept(this);

        for (FunctionNode i : it.func_defs)
            i.accept(this);
    }

    @Override
    public void visit(ReturnStmtNode it) {
        cur_block.is_returned = true;
        if (it.value != null) {
            it.value.accept(this);
            if (it.value.val.type instanceof IRPointer) {
                Register reg = new Register(((IRPointer) it.value.val.type).father_type, "load", false, reg_cnt++);
                cur_block.instr.add(new IRLoad(reg, (Register) it.value.val));
                cur_block.instr.add(new IRRet(reg));
            } else
                cur_block.instr.add(new IRRet(it.value.val));
        } else
            cur_block.instr.add(new IRRet(new Constant(types.get("void"), 0)));

        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(SuiteStmtNode it) {
        current_scope = new Scope(current_scope);
        for (StmtNode i : it.stmts)
            i.accept(this);
        current_scope = current_scope.ParentScope();
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
        it.expr.accept(this);
        it.val = new Register(types.get("int"), "unary", false, reg_cnt++);
        IRType type = types.get("int");
        if (it.opCode == UnaryOpType.raddadd || it.opCode == UnaryOpType.rsubsub) {
            cur_block.instr.add(new IRLoad((Register) it.val, (Register) it.expr.val));
            Register reg = new Register(type, "tmp", false, reg_cnt++);
            cur_block.instr.add(
                    new IRBin(reg, BinaryOpType.add, it.val, (IRValue) new Constant(type, 1)));
            cur_block.instr.add(new IRStore(it.expr.val, reg));
        } else if (it.opCode == UnaryOpType.laddadd || it.opCode == UnaryOpType.lsubsub) {
            Register reg = new Register(type, "tmp", false, reg_cnt++);
            cur_block.instr.add(new IRLoad((Register) reg, (Register) it.expr.val));
            cur_block.instr.add(new IRBin(it.val, BinaryOpType.sub, reg, (IRValue) new Constant(type, 1)));
            cur_block.instr.add(new IRStore(it.expr.val, it.val));
        } else if (it.opCode == UnaryOpType.neg)
            cur_block.instr.add(new IRBin(it.expr.val, BinaryOpType.sub, (IRValue) new Constant(type, 0), it.expr.val));
        else if (it.opCode == UnaryOpType.tilde)
            cur_block.instr
                    .add(new IRBin(it.expr.val, BinaryOpType.xor, (IRValue) new Constant(type, -1), it.expr.val));

    }

    @Override
    public void visit(VarDefStmtNode it) {
        if ((cur_class != null && !current_scope.IfInFunc()))
            return;
        boolean is_global = false;
        is_global = cur_func.func_name == "__global_var_init" ? true : false;
        for (Var i : it.var) {
            IRType type = types.get(i.type.type_name);
            for (int j = 0; j < i.type.dim; j++) {
                type = new IRPointer(1, type, reg_cnt++, types.get("int"));
            }

            i.val = new Register(type, "var_def", is_global, reg_cnt++);
            if (!args_define) {
                i.val.type = new IRPointer(1, i.val.type);
                current_scope.DefineVarible(i.name, (Register) i.val);
            } else {
                Register reg = new Register(new IRPointer(1, type), "arg_define", false, reg_cnt++);
                cur_block.instr.add(new IRAlloc(reg.type, reg));
                cur_block.instr.add(new IRStore(reg, i.val));
                current_scope.DefineVarible(i.name, reg);
            }

            if (i.init != null) {
                i.init.accept(this);
                if (i.init instanceof NewExprNode) {
                    NewExprNode new_expr = (NewExprNode) i.init;
                    String type_name = new_expr.type.type_name;
                    if (new_expr.type.dim == 0) {
                        IRCall func_call = new IRCall(type_name + '.' + type_name, null);
                        func_call.args.add(i.val);
                        if (new_expr.args != null)
                            for (ExprNode j : new_expr.args)
                                func_call.args.add(j.val);
                        cur_block.instr.add(func_call);
                    } else {
                        IRType new_type = i.init.val.type;
                        boolean contain_varible = false;
                        ((IRPointer) i.val.type).father_type = new_type;
                        while (new_type instanceof IRPointer) {
                            if (((IRPointer) new_type).max_dim instanceof Register) {
                                contain_varible = true;
                                break;
                            } else
                                new_type = new_type.FatherType();
                        }
                        if (contain_varible) {
                            int arg_dim = new_expr.type.dim_args.size();
                            IRType basic_type = new_type;
                            Register reg = new Register(types.get("int"), "size_calc", false, reg_cnt++);
                            Register dest = new Register(types.get("int"), "size_calc", false, reg_cnt++);
                            cur_block.instr.add(new IRBin(reg, BinaryOpType.add, new Constant(types.get("int"), 0),
                                    new Constant(types.get("int"), 1)));
                            for (int j = 0; j < arg_dim; j++) {
                                cur_block.instr
                                        .add(new IRBin(dest, BinaryOpType.mul, reg, ((IRPointer) basic_type).max_dim));
                                reg = dest;
                                dest = new Register(types.get("int"), "size_calc", false, reg_cnt++);
                                basic_type = basic_type.FatherType();
                            }
                            cur_block.instr.add(new IRBin(dest, BinaryOpType.mul, reg,
                                    new Constant(types.get("int"), basic_type.GetSize())));

                            IRFunc called_func=funcs.get("__builtin_malloc");

                            Register ptr_reg = new Register(called_func.ret_type, "ptr_reg", false, reg_cnt++);
                            IRCall func_call = new IRCall("__builtin_malloc", ptr_reg);
                            func_call.args.add(dest);
                            cur_block.instr.add(func_call);
                            cur_block.instr.add(new IRBitCast(ptr_reg, (Register) i.val));
                        } else {
                            cur_block.instr.add(new IRAlloc(i.val.type, i.val));
                        }

                    }
                } else {
                    IRValue store_val = i.init.val;
                    if (store_val.type instanceof IRPointer) {
                        Register reg = new Register(((IRPointer) store_val.type).father_type, "load", false, reg_cnt++);
                        cur_block.instr.add(new IRLoad(reg, (Register) store_val));
                        store_val = reg;
                    }
                    cur_block.instr.add(new IRAlloc(i.val.type, i.val));
                    cur_block.instr.add(new IRStore(i.val, store_val));
                }
            }

        }
    }

    @Override
    public void visit(VarExprNode it) {
        IRType type = types.get(it.type.type_name);
        for (int i = 0; i < it.type.dim; i++) {
            type = new IRPointer(1, type, reg_cnt++, types.get("int"));
        }

        it.val = new Register(type, "var_expr", false, reg_cnt++);
        if (cur_visit_class != null) {
            IRClass clas = (IRClass) types.get(cur_visit_class.type.name);
            int ind = clas.idt_to_rank.get(it.name);
            Register get_ptr = new Register(new IRPointer(1, type), "get_ptr", false, reg_cnt++);
            it.val = get_ptr;
            cur_block.instr.add(new IRGetElementPtr(get_ptr, cur_visit_class.type, cur_visit_class, ind));
            return;
        }

        // cur_visit_class = null
        if (cur_class != null) {
            Pair<Register, Boolean> ret = current_scope.GetVaribleVal(it.name, false);
            if (ret.b) {
                it.val = ret.a;
                return;
            }
            if (cur_class.idt_to_rank.containsKey(it.name)) {
                it.val.type = new IRPointer(1, it.val.type);
                cur_block.instr.add(new IRGetElementPtr((Register) it.val,
                        ((IRPointer) cur_func.this_ptr.type).father_type, cur_func.this_ptr,
                        cur_class.idt_to_rank.get(it.name)));
                return;
            }
        }

        Pair<Register, Boolean> ret = current_scope.GetVaribleVal(it.name, true);
        it.val = ret.a;

    }

    @Override
    public void visit(VisitExprNode it) {
        it.visitor.accept(this);
        cur_visit_class = (Register) it.visitor.val;
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

        cur_block = cond_block;
        it.condition.accept(this);
        cur_block.instr.add(new IRBr(it.condition.val, body_block, new_block));

        cur_block = body_block;
        it.while_suite.accept(this);
        cur_block.instr.add(new IRBr(cond_block));

        cur_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

}