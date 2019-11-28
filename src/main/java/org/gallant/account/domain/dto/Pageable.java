package org.gallant.account.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@Data
public class Pageable implements Serializable {

    private static final long serialVersionUID = -1933388285497219282L;
    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
