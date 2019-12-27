package org.gallant.account.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.gallant.account.controller.hateoas.FundOrderResourceAssembler;
import org.gallant.account.domain.dto.result.FundOrderDTO;
import org.gallant.account.domain.dto.param.FundOrderQueryDTO;
import org.gallant.account.domain.dto.param.FundOrderBaseDTO;
import org.gallant.account.domain.dto.param.FundOrderUpdateDTO;
import org.gallant.account.domain.dto.SaveGroup;
import org.gallant.account.exception.AccountServiceException;
import org.gallant.account.manager.FundOrderManager;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kongyong
 * @date 2019/11/30
 */
@Slf4j
@RestController
@RequestMapping("/fund/order")
public class FundOrderController extends BaseController {

    @Resource
    private FundOrderManager fundOrderManager;
    @Resource
    private FundOrderResourceAssembler fundOrderResourceAssembler;

    @GetMapping
    public CollectionModel<EntityModel<FundOrderDTO>> query(
            @ModelAttribute FundOrderQueryDTO fundOrderQueryDTO) {
        log.debug("fundOrderQueryDTO:{}", fundOrderQueryDTO);
        return fundOrderResourceAssembler
                .toCollectionModel(fundOrderManager.queryByPage(fundOrderQueryDTO));
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<EntityModel<FundOrderDTO>> query(@PathVariable Integer id) {
        log.debug("id:{}", id);
        FundOrderDTO fundOrderDTO = fundOrderManager.queryByPrimaryKey(id);
        if (fundOrderDTO == null) {
            throw new AccountServiceException("对象不存在,id=" + id);
        }
        return ResponseEntity.ok(fundOrderResourceAssembler.toModel(fundOrderDTO));
    }

    @PostMapping
    public ResponseEntity<EntityModel<FundOrderDTO>> save(
            @Validated(SaveGroup.class) @RequestBody FundOrderBaseDTO fundOrderBaseDTO,
            BindingResult errors) {
        processBindingResult(errors);
        FundOrderDTO fundOrderDTO = fundOrderManager.save(fundOrderBaseDTO);
        return ResponseEntity.created(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(FundOrderController.class)
                        .query(fundOrderDTO.getId())).toUri())
                .body(fundOrderResourceAssembler.toModel(fundOrderDTO));
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<EntityModel<FundOrderDTO>> update(@PathVariable Integer id,
            @RequestBody FundOrderBaseDTO fundOrderBaseDTO, BindingResult errors) {
        processBindingResult(errors);
        FundOrderDTO fundOrderOld = fundOrderManager.queryByPrimaryKey(id);
        if (fundOrderOld == null) {
            throw new AccountServiceException("对象不存在,更新失败,id=" + id + ",fundOrderBaseDTO=" + fundOrderBaseDTO);
        }
        FundOrderUpdateDTO fundOrderUpdateDTO = new FundOrderUpdateDTO();
        BeanUtils.copyProperties(fundOrderBaseDTO, fundOrderUpdateDTO);
        fundOrderUpdateDTO.setId(id);
        FundOrderDTO fundOrderDTO = fundOrderManager.update(fundOrderUpdateDTO);
        return ResponseEntity.created(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(FundOrderController.class)
                        .query(fundOrderDTO.getId())).toUri())
                .body(fundOrderResourceAssembler.toModel(fundOrderDTO));
    }

    @DeleteMapping("/{id:\\d+}")
    public int delete(@PathVariable Integer id) {
        return fundOrderManager.delete(id);
    }

}
