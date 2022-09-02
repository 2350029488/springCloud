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
 * @since 2022-09-02
 */
@TableName("t_storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

    public Storage() {
    }

    public Storage(Integer id, Integer productId, Integer total, Integer used, Integer residue) {
        this.id = id;
        this.productId = productId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        return "Storage{" +
            "id=" + id +
            ", productId=" + productId +
            ", total=" + total +
            ", used=" + used +
            ", residue=" + residue +
        "}";
    }
}
