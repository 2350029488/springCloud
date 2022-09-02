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
@TableName("t_account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 总额度
     */
    private Integer total;

    /**
     * 已用余额
     */
    private Integer used;

    /**
     * 剩余可用额度
     */
    private Integer residue;

    public Account() {
    }

    public Account(Integer id, Integer userId, Integer total, Integer used, Integer residue) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.used = used;
        this.residue = residue;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", userId=" + userId +
            ", total=" + total +
            ", used=" + used +
            ", residue=" + residue +
        "}";
    }
}
