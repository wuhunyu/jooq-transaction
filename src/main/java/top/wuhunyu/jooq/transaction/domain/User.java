package top.wuhunyu.jooq.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author wuhunyu
 * @date 2025-01-21 14:35
 */

@TableName("t_user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -2032611999035504700L;

    /**
     * 主键
     */
    @TableId(type = IdType.NONE)
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

}
