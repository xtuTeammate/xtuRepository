package com.xtu.DB.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Ilovezilian on 2017/4/22.
 */
@Data
public class StatusEntityVO {
    /**
     * 提交运行ID
     */
    private int runId;
    /**
     * 题目ID
     */
    private int problemId;
    /**
     * 比赛ID，非比赛提交为null
     */
    private Integer contestId;
    /**
     * 比赛题目序号，非比赛提交为null
     */
    private Byte no;
    /**
     * 源代码
     */
    private String code;
    /**
     * 源代码长度
     */
    private Long codeLength;
    /**
     * 语言
     */
    private String language;
    /**
     * 总运行时间
     */
    private int runTime;
    /**
     * 最大运行内存
     */
    private int runMemory;
    /**
     * 判题结果（第一个产生错误的地方的判题结果，否则为AC）
     */
    private byte resultCode;
    /**
     * 判题结果信息
     */
    private String resultMsg;
    /**
     * 提交时间
     */
    private Timestamp submitTime;

    /**
     * ID
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

}
