package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.frameImpl.*;
import log.MyLog;
import utils.CompareUtil;

/**
 * @author 22454
 */
public interface Frame {
    /**
     * same_frame interval
     */
    int SAME_MIN = 0;
    int SAME_MAX = 63;
    /**
     * same_locals_1_stack_item interval
     */
    int SAME_LOCALS_ONE_STACK_ITEM_MIN = 64;
    int SAME_LOCALS_ONE_STACK_ITEM_MAX = 127;

    /**
     * Reserved interval ( 预留区间 )
     */
    int RESERVED_MIN = 128;
    int RESERVED_MAX = 246;

    /**
     * same_locals_1_stack_item_extended interval
     */
    int SAME_LOCALS_ONE_STACK_ITEM_EXTENDED = 247;

    /**
     * chop_frame interval
     */
    int CHOP_MIN = 248;
    int CHOP_MAX = 250;
    /**
     * same_frame_extended interval
     */
    int SAME_FRAME_EXTENDED = 251;
    /**
     * append_frame interval
     */
    int APPEND_MIN = 252;
    int APPEND_MAX = 254;
    /**
     * full_frame interval
     */
    int FULL_FRAME = 255;

    /**
     * 读取信息
     *
     * @param byteCodeFile 字节码文件
     * @param frameType    frame类型
     */
    void read(ByteCodeFile byteCodeFile, int frameType);

    /**
     * 创建一个 Frame
     *
     * @param byteCodeFile 字节码文件
     * @return Frame实例
     * @throws Exception ex
     */
    static Frame createFrame(ByteCodeFile byteCodeFile) throws Exception {
        int frameType = byteCodeFile.readOneUint();
        Frame frame;
        //same_frame
        if (CompareUtil.between(SAME_MIN, SAME_MAX, frameType)) {
            MyLog.debug("frameType=" + frameType + ", is same_frame");
            frame = new SameFrame();
        }
        //same_locals_1_stack_item
        else if (CompareUtil.between(SAME_LOCALS_ONE_STACK_ITEM_MIN, SAME_LOCALS_ONE_STACK_ITEM_MAX, frameType)) {
            MyLog.debug("frameType=" + frameType + ", is same_locals_1_stack_item");
            frame = new SameLocalsOneStackItemFrame();
        }
        // [128,246]该区间留给未来的需要
        else if (CompareUtil.between(RESERVED_MIN, RESERVED_MAX, frameType)) {
            throw new Exception("Interval Never Used");
            //MyLog.error("frameType=" + frameType + ", is never used interval,exception");

        }
        //same_locals_1_stack_item_extended
        else if (frameType == SAME_LOCALS_ONE_STACK_ITEM_EXTENDED) {
            MyLog.debug("frameType=" + frameType + ", is same_locals_1_stack_item_extended");
            frame = new SameLocalsOneStackItemFrameExtended();
        }
        //chop_frame
        else if (CompareUtil.between(CHOP_MIN, CHOP_MAX, frameType)) {
            MyLog.debug("frameType=" + frameType + ", is chop_frame");
            frame = new ChopFrame();
        }
        //same_frame_extended
        else if (frameType == SAME_FRAME_EXTENDED) {
            MyLog.debug("frameType=" + frameType + ", is same_frame_extended");
            frame = new SameFrameExtended();
        }
        //append_frame
        else if (CompareUtil.between(APPEND_MIN, APPEND_MAX, frameType)) {
            MyLog.debug("frameType=" + frameType + ", is append_frame");
            frame = new AppendFrame();
        }
        //full_frame
        else if (frameType == FULL_FRAME) {
            MyLog.debug("frameType=" + frameType + ", is full_frame");
            frame = new FullFrame();
        }
        //there may be some exception,
        // for example frameType > 255 or frameType < 0,it will raise some exception
        else {
            MyLog.warn("frameType=" + frameType + ", is error number");
            throw new Exception("Read Stack Map Frame Fail");
        }
        frame.read(byteCodeFile, frameType);
        return frame;
    }
}
