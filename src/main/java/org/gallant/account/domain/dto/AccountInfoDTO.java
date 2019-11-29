package org.gallant.account.domain.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@Data
@Relation(itemRelation = "/", collectionRelation = "/")
public class AccountInfoDTO {
    private Integer id;

    private String accountTypeName;

    private String accountBankName;

    private String accountCardCode;

    private Date repaymentDate;

    private String owner;

    private Boolean isActive;

    private Date createTime;

    private Date modifyTime;
}
