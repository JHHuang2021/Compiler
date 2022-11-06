package Util;

// import MIR.register;
import Util.error.semanticError;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

public class Scope {

    private HashMap<String, Type> members;
    // public HashMap<String, register> entities = new HashMap<>();
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
}
