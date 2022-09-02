package com.huanglong.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 黄隆
 * @since 2022-09-02
 */
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Integer money;

    /**
     * 订单状态：0：创建中；1：已完结
     */
    private Integer status;

    public Order() {
    }

    public Order(Integer id, Integer userId, Integer productId, Integer count, Integer money, Integer status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.money = money;
        this.status = status;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId=" + userId +
            ", productId=" + productId +
            ", count=" + count +
            ", money=" + money +
            ", status=" + status +
        "}";
    }
}
