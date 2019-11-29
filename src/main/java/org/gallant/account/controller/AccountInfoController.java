package org.gallant.account.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.gallant.account.controller.hateoas.AccountInfoResourceAssembler;
import org.gallant.account.domain.dto.AccountInfoDTO;
import org.gallant.account.domain.dto.AccountInfoQueryDTO;
import org.gallant.account.domain.dto.AccountInfoSaveDTO;
import org.gallant.account.domain.dto.AccountInfoUpdateDTO;
import org.gallant.account.domain.dto.SaveGroup;
import org.gallant.account.exception.AccountServiceException;
import org.gallant.account.manager.AccountInfoManager;
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
 * @date 2019/11/27
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountInfoController extends BaseController {

    @Resource
    private AccountInfoManager accountInfoManager;
    @Resource
    private AccountInfoResourceAssembler accountInfoResourceAssembler;

    @GetMapping
    public CollectionModel<EntityModel<AccountInfoDTO>> query(
            @ModelAttribute AccountInfoQueryDTO accountInfoQueryDTO) {
        log.debug("accountInfoQueryDTO:{}", accountInfoQueryDTO);
        return accountInfoResourceAssembler
                .toCollectionModel(accountInfoManager.queryByPage(accountInfoQueryDTO));
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<EntityModel<AccountInfoDTO>> query(@PathVariable Integer id) {
        log.debug("id:{}", id);
        AccountInfoDTO accountInfoDTO = accountInfoManager.queryByPrimaryKey(id);
        if (accountInfoDTO == null) {
            throw new AccountServiceException("对象不存在,id=" + id);
        }
        return ResponseEntity.ok(accountInfoResourceAssembler.toModel(accountInfoDTO));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AccountInfoDTO>> save(
            @Validated(SaveGroup.class) @RequestBody AccountInfoSaveDTO accountInfoSaveDTO,
            BindingResult errors) {
        processBindingResult(errors);
        AccountInfoDTO accountInfoDTO = accountInfoManager.save(accountInfoSaveDTO);
        return ResponseEntity.created(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(AccountInfoController.class)
                        .query(accountInfoDTO.getId())).toUri())
                .body(accountInfoResourceAssembler.toModel(accountInfoDTO));
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<EntityModel<AccountInfoDTO>> update(@PathVariable Integer id,
            @RequestBody AccountInfoSaveDTO accountInfoSaveDTO, BindingResult errors) {
        processBindingResult(errors);
        AccountInfoDTO accountInfoDTOOld = accountInfoManager.queryByPrimaryKey(id);
        if (accountInfoDTOOld == null) {
            throw new AccountServiceException("对象不存在,更新失败,id=" + id + ",accountInfoSaveDTO=" + accountInfoSaveDTO);
        }
        AccountInfoUpdateDTO accountInfoUpdateDTO = new AccountInfoUpdateDTO();
        BeanUtils.copyProperties(accountInfoSaveDTO, accountInfoUpdateDTO);
        accountInfoUpdateDTO.setId(id);
        AccountInfoDTO accountInfoDTO = accountInfoManager.update(accountInfoUpdateDTO);
        return ResponseEntity.created(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(AccountInfoController.class)
                        .query(accountInfoDTO.getId())).toUri())
                .body(accountInfoResourceAssembler.toModel(accountInfoDTO));
    }

    @DeleteMapping("/{id:\\d+}")
    public int delete(@PathVariable Integer id) {
        return accountInfoManager.delete(id);
    }

}
