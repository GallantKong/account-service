package org.gallant.account.domain.dto;

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
    private Integer id;
}
