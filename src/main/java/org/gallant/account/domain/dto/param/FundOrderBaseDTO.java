package org.gallant.account.domain.dto.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.gallant.account.domain.dto.SaveGroup;

/**
 * @author kongyong
 * @date 2019/11/29
 */
@Data
public class FundOrderBaseDTO implements Serializable {

    private static final long serialVersionUID = 7125522971532674212L;
    private Date fundOrderDate;
    @NotNull(groups = SaveGroup.class, message = "账户信息不能为空")
    private Integer accountInfoId;
    @NotNull(groups = SaveGroup.class, message = "产品信息不能为空")
    private Integer productInfoId;
    @NotNull(groups = SaveGroup.class, message = "账单类型不能为空")
    private Integer fundOrderTypeId;
    @NotNull(groups = SaveGroup.class, message = "账单金额不能为空")
    private BigDecimal fundOrderAmount;
}
