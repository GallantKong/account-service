package org.gallant.account.domain.dto.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kongyong
 * @date 2019/11/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FundOrderUpdateDTO extends FundOrderBaseDTO {

    private static final long serialVersionUID = -8989762105866563741L;
    private Integer id;
}
