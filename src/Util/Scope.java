package Util;

// import MIR.register;
import Util.error.semanticError;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

public class Scope {

    private HashMap<String, Type> members;
    // public HashMap<String, register> entities = new HashMap<>();
    public Type returnType = null;
    public boolean ifinlambda = false;
    private Scope parentScope;

    public enum ScopeType {
        FOR, WHILE, IF, FUNC
    };

    public ScopeType scopeType = null;

    public Scope(Scope parentScope) {
        members = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope(Scope parentScope, ScopeType scopeType) {
        members = new HashMap<>();
        this.parentScope = parentScope;
        this.scopeType = scopeType;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public boolean containScopeType(ScopeType scopeType, boolean lookUpon) {
        if (this.scopeType == scopeType)
            return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containScopeType(scopeType, lookUpon);
        else
            return false;
    }

    public Type returnType() {
        if (this.returnType != null)
            return this.returnType;
        else if (parentScope != null)
            return parentScope.returnType();
        else
            return null;
    }

    public void defineVarible(String name, Type t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("redefine", pos);
        members.put(name, t);
    }

    public boolean containVarible(String name, boolean lookUpon) {
        if (members.containsKey(name))
            return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containVarible(name, true);
        else
            return false;
    }

    public Pair<Type, Boolean> getVaribleType(String name, boolean lookUpon) {
        if (members.containsKey(name)) {
            if (parentScope == null)
                return new Pair<>(members.get(name), true);
            else
                return new Pair<>(members.get(name), false);
        } else if (parentScope != null && lookUpon)
            return parentScope.getVaribleType(name, true);
        return null;
    }

    // public register getEntity(String name, boolean lookUpon) {
    // if (entities.containsKey(name))
    // return entities.get(name);
    // else if (parentScope != null && lookUpon)
    // return parentScope.getEntity(name, true);
    // return null;
    // }
}
