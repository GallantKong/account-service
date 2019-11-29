package org.gallant.account.controller.hateoas;

import org.gallant.account.controller.AccountInfoController;
import org.gallant.account.domain.dto.result.AccountInfoDTO;
import org.springframework.hateoas.server.core.AnnotationLinkRelationProvider;
import org.springframework.stereotype.Component;

/**
 * @author kongyong
 * @date 2019/11/30
 */
@Component
public class AccountInfoResourceAssembler extends SimpleIdentifiableRepresentationModelAssembler<AccountInfoDTO> {

    public AccountInfoResourceAssembler() {
        super(AccountInfoController.class, new AnnotationLinkRelationProvider());
    }
}
