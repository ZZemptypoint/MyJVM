package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoUnInitializedThis implements VariableInfo {
    public int tag = VariableItemType.ITEM_UNINITIALIZED_THIS;

    public VariableInfoUnInitializedThis() {
//        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "UNINITIALIZED_THIS\n";
    }
}
