package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoDouble implements VariableInfo {
    public int tag = VariableItemType.ITEM_DOUBLE;

    @Override
    public int getTag() {
        return tag;
    }
}
