package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IDiv extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("idiv");
        OperandStack operandStack = frame.getOperandStack();
        int val1 = operandStack.popInteger();
        int val2 = operandStack.popInteger();
        int mulResult = val1 / val2;
        operandStack.pushInteger(mulResult);
    }
}
