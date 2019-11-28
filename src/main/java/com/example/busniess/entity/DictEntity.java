package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 数据字典表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-10 09:41:28
 */
@Data
public class DictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典条目id
     */
	@NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    /**
     * 字典名称
     */
	@NotNull(message = "名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String name;
    /**
     * 字典类型
     */
	@NotNull(message = "类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String type;
    /**
     * 字典码
     */
	@NotNull(message = "字典code不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String code;
    /**
     * 字典值
     */
	@NotNull(message = "值不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String value;
    /**
     * 子标签
     */
	private List<DictEntity> children;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标记  -1：已删除  0：正常
     */
    private Integer delFlag;
    /**
     * 父目录id
     */
    private Integer parentId;

}
