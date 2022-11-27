package Util;

// import MIR.register;
import Util.error.semanticError;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

import Backend.IR.IRValue.Register;

public class Scope {

    private HashMap<String, Type> members;
    private HashMap<String, Register> entities = new HashMap<>();
    public Type return_type = null;
    public boolean if_in_lambda = false;
    private Scope parent_scope;

    public enum ScopeType {
        FOR, WHILE, IF, FUNC
    };

    public ScopeType scopeType = null;

    public Scope(Scope parent_scope) {
        members = new HashMap<>();
        this.parent_scope = parent_scope;
    }

    public Scope(Scope parent_scope, ScopeType scopeType) {
        members = new HashMap<>();
        this.parent_scope = parent_scope;
        this.scopeType = scopeType;
    }

    public Scope ParentScope() {
        return parent_scope;
    }

    public boolean ContainScopeType(ScopeType scopeType, boolean lookUpon) {
        if (this.scopeType == scopeType)
            return true;
        else if (parent_scope != null && lookUpon)
            return parent_scope.ContainScopeType(scopeType, lookUpon);
        else
            return false;
    }

    public Type ReturnType() {
        if (this.return_type != null)
            return this.return_type;
        else if (parent_scope != null)
            return parent_scope.ReturnType();
        else
            return null;
    }

    public void DefineVarible(String name, Type t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("redefine", pos);
        members.put(name, t);
    }

    public boolean ContainVarible(String name, boolean lookUpon) {
        if (members.containsKey(name))
            return true;
        else if (parent_scope != null && lookUpon)
            return parent_scope.ContainVarible(name, true);
        else
            return false;
    }

    public Pair<Type, Boolean> GetVaribleType(String name, boolean lookUpon) {
        if (members.containsKey(name)) {
            if (parent_scope == null)
                return new Pair<>(members.get(name), true);
            else
                return new Pair<>(members.get(name), false);
        } else if (parent_scope != null && lookUpon)
            return parent_scope.GetVaribleType(name, true);
        return null;
    }

    public void DefineVarible(String name, Register val) {
        entities.put(name, val);
    }

    public Pair<Register, Boolean> GetVaribleVal(String name, boolean lookUpon) {
        if (entities.containsKey(name)) {
            return new Pair<>(entities.get(name), true);
        } else if (parent_scope != null && (lookUpon || this.scopeType != ScopeType.FUNC))
            return parent_scope.GetVaribleVal(name, true);
        return new Pair<>(null, false);
    }

    public boolean IfInFunc() {
        if (scopeType == ScopeType.FUNC)
            return true;
        else if (parent_scope != null)
            return parent_scope.IfInFunc();
        return false;
    }
}
