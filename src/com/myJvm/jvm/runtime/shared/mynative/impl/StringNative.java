package com.myJvm.jvm.runtime.shared.mynative.impl;

import com.myJvm.jvm.beancenter.annotations.MyNativeObject;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.shared.mynative.NativeClass;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@MyNativeObject
public class StringNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/String";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("intern", "()Ljava/lang/String;");
            put("registerNatives", "()V");
        }
    };

    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void intern(StackFrame frame) {
        MyLog.nativeLog("intern");
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            MyObject intern = StringCache.intern(thisObject);
            frame.getOperandStack().pushRef(intern);
        } catch (Exception e) {
            MyLog.error("Failed To Intern String Native");
            e.printStackTrace();
        }
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public Map<String, String> getNativeMethodDescriptorMap() {
        return NATIVE_METHOD_DESCRIPTOR_MAP;
    }
}
