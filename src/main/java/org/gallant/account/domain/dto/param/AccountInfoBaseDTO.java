package org.gallant.account.domain.dto.param;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.gallant.account.domain.dto.SaveGroup;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@Data
public class AccountInfoBaseDTO implements Serializable {

    private static final long serialVersionUID = 8000618299725133657L;
    @NotBlank(groups = SaveGroup.class, message = "账户类型名称不能为空")
    private String accountTypeName;

    @NotBlank(groups = SaveGroup.class, message = "账户银行名称不能为空")
    private String accountBankName;

    @NotBlank(groups = SaveGroup.class, message = "账户银行卡号不能为空")
    private String accountCardCode;

    @NotNull(groups = SaveGroup.class, message = "还款日期不能为空")
    private Date repaymentDate;

    @NotBlank(groups = SaveGroup.class, message = "账户归属不能为空")
    private String owner;
}
