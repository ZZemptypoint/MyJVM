package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class DStore1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dstore_1");
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(1, doubleVal);
    }
}
