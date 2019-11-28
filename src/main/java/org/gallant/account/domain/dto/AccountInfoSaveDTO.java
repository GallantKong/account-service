package org.gallant.account.domain.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@Data
public class AccountInfoSaveDTO implements Serializable {

    private static final long serialVersionUID = 8000618299725133657L;
    @NotBlank(message = "账户类型名称不能为空")
    private String accountTypeName;

    @NotBlank(message = "账户银行名称不能为空")
    private String accountBankName;

    @NotBlank(message = "账户银行卡号不能为空")
    private String accountCardCode;

    @NotNull(message = "还款日期不能为空")
    private Date repaymentDate;

    @NotBlank(message = "账户归属不能为空")
    private String owner;
}
