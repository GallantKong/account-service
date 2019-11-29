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
public class FundOrderQueryDTO extends Pageable {

    private static final long serialVersionUID = -1264708420241563947L;
    private Date startDate;
    private Date endDate;

}
