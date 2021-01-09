package jvm.interpreter.instructions.logic;

import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import jvm.runtimeDataArea.threadDependent.VariableSlot;

/**
 * @author 22454
 */
public class MethodInvokeLogic {

    public static void invokeMethod(StackFrame invokeFrame, MyMethod method) {
        MyThread thread = invokeFrame.getThread();
        StackFrame newStackFrame = thread.createNewStackFrame(method);
        thread.pushStackFrame(newStackFrame);
        int argsCount = method.getArgsCount();
        if (argsCount > 0) {
            for (int i = argsCount - 1; i >= 0; i--) {
                VariableSlot variableSlot = invokeFrame.getOperandStack().popVariableSlot();
                newStackFrame.getLocalVariableTable().putSlot(i, variableSlot);
            }
        }
    }
}
