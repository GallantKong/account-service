package org.gallant.account.domain.dto.result;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author kongyong
 * @date 2019/11/29
 */
@Data
@Relation(itemRelation = "/", collectionRelation = "/")
public class FundOrderDTO {
    private Integer id;

    private Date fundOrderDate;

    private Integer accountInfoId;

    private Integer productInfoId;

    private Integer fundOrderTypeId;

    private BigDecimal fundOrderAmount;

    private Boolean isActive;

    private Date createTime;

    private Date modifyTime;
}
