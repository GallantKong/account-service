package org.gallant.account.domain.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountInfoUpdateDTO extends AccountInfoSaveDTO {

    private static final long serialVersionUID = 5332623238945579244L;
    @NotNull(groups = UpdateGroup.class, message = "主键不能为空")
    private Integer id;
}
