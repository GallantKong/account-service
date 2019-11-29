package org.gallant.account.controller.hateoas;

import org.gallant.account.controller.FundOrderController;
import org.gallant.account.domain.dto.result.FundOrderDTO;
import org.springframework.hateoas.server.core.AnnotationLinkRelationProvider;
import org.springframework.stereotype.Component;

/**
 * @author kongyong
 * @date 2019/11/30
 */
@Component
public class FundOrderResourceAssembler extends SimpleIdentifiableRepresentationModelAssembler<FundOrderDTO> {

    public FundOrderResourceAssembler() {
        super(FundOrderController.class, new AnnotationLinkRelationProvider());
    }
}
