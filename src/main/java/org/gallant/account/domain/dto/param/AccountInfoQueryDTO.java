package org.gallant.account.domain.dto.param;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountInfoQueryDTO extends Pageable {

    private static final long serialVersionUID = -5370319974234981535L;
    private Date startDate;
    private Date endDate;

}
