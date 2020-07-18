package com.liuhuiyu.scaffold.socket;

import com.liuhuiyu.scaffold.utils.BytesUtil;
import com.liuhuiyu.scaffold.utils.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 基础报文结构
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-18 8:58
 */
public class ReceiveMessage {
    private final static Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);
    //    DataInputStream dataInputStream;
    //region 报表头通用长度定义
    protected static final int HEAD_LEN_HEAD_LEN = 2; //报表头长度
    protected static final int HEAD_LEN_MAIN_VER = 1; //报表主版本长度
    protected static final int HEAD_LEN_MINOR_VER = 1; //报表次版本长度
    protected static final int HEAD_LEN_BODY_LEN = 4; //报表体长度
    //endregion
    //region 解析后的数据
    protected int headLen;//头部长度
    protected int mainVer;//主版本号
    protected int minorVer;//次版本号
    protected int bodyLen;//报文体长度
    //endregion
    //region 原始数据
    protected byte[] headBytes; //报文头字节码
    protected byte[] bodyBytes; //报文体字节码

    //endregion
    public ReceiveMessage(DataInputStream dataInputStream) throws IOException {
        this.initialParseMessage(dataInputStream);
    }

    /**
     * 报文初步解析
     */
    private void initialParseMessage(DataInputStream dataInputStream) throws IOException {
        int index = 0;
        byte[] headLenBytes = new byte[ReceiveMessage.HEAD_LEN_HEAD_LEN];
        for (int i = 0; i < ReceiveMessage.HEAD_LEN_HEAD_LEN; i++) {
            headLenBytes[i] = dataInputStream.readByte();
        }
        logger.debug("报文头字节：" + BytesUtil.bytesToString(headLenBytes));
        this.headLen = BytesUtil.byteArrayToInt(headLenBytes);
        this.headBytes = new byte[this.headLen];
        System.arraycopy(headLenBytes, index, this.headBytes, 0, ReceiveMessage.HEAD_LEN_HEAD_LEN);
        index += ReceiveMessage.HEAD_LEN_HEAD_LEN;
        for (int i = ReceiveMessage.HEAD_LEN_HEAD_LEN; i < this.headLen; i++) {
            this.headBytes[i] = dataInputStream.readByte();
        }
        logger.debug("报文头：" + BytesUtil.bytesToString(this.headBytes));
        byte[] mainVerBytes = new byte[ReceiveMessage.HEAD_LEN_MAIN_VER];
        System.arraycopy(headBytes, index, mainVerBytes, 0, mainVerBytes.length);
        this.mainVer = BytesUtil.byteArrayToInt(mainVerBytes);
        byte[] minorVerBytes = new byte[ReceiveMessage.HEAD_LEN_MINOR_VER];
        System.arraycopy(headBytes, index, minorVerBytes, 0, minorVerBytes.length);
        this.minorVer = BytesUtil.byteArrayToInt(minorVerBytes);
        byte[] bodyLenBytes = new byte[ReceiveMessage.HEAD_LEN_BODY_LEN];
        System.arraycopy(headBytes, index, bodyLenBytes, 0, bodyLenBytes.length);
        this.bodyLen = BytesUtil.byteArrayToInt(minorVerBytes);
        this.bodyBytes = new byte[this.bodyLen];
        for (int i = 0; i < this.bodyBytes.length; i++) {
            this.bodyBytes[i] = dataInputStream.readByte();
        }
    }

    public int getHeadLen() {
        return headLen;
    }

    public int getMainVer() {
        return mainVer;
    }

    public int getMinorVer() {
        return minorVer;
    }

    public int getBodyLen() {
        return bodyLen;
    }

    public byte[] getHeadBytes() {
        return headBytes;
    }

    public byte[] getBodyBytes() {
        return bodyBytes;
    }
}
