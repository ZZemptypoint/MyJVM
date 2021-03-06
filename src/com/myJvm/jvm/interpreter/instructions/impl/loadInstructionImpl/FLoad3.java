package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class FLoad3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fload_3");
        Float floatVal = frame.getLocalVariableTable().getFloat(3);
        frame.getOperandStack().pushFloat(floatVal);
    }
}
