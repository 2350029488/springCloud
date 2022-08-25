package com.huanglong.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-24
 */
@TableName("payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pay_id", type = IdType.AUTO)
    private Long payId;

    /**
     * 顺序
     */
    private String serial;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Payment() {
    }

    public Payment(Long payId, String serial) {
        this.payId = payId;
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Payment{" +
            "payId=" + payId +
            ", serial=" + serial +
        "}";
    }
}
