package Backend.IR.IRInstr;

import Backend.IR.IRBlock.IRBlock;
import Backend.IR.IRValue.IRValue;

public class IRBr extends IRInstr {
    public IRValue cond = null;
    public IRBlock true_block = null, false_block = null;

    public IRBr(IRBlock true_block) {
        this.true_block = true_block;
    }

    public IRBr(IRValue cond, IRBlock true_block, IRBlock false_block) {
        this.cond = cond;
        this.true_block = true_block;
        this.false_block = false_block;
    }
}
