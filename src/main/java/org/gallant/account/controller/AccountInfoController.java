package org.gallant.account.controller;

import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.gallant.account.domain.dto.AccountInfoDTO;
import org.gallant.account.domain.dto.AccountInfoQueryDTO;
import org.gallant.account.domain.dto.AccountInfoSaveDTO;
import org.gallant.account.domain.dto.AccountInfoUpdateDTO;
import org.gallant.account.domain.dto.SaveGroup;
import org.gallant.account.domain.dto.UpdateGroup;
import org.gallant.account.exception.AccountServiceException;
import org.gallant.account.manager.AccountInfoManager;
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

    @GetMapping
    public List<AccountInfoDTO> query(@ModelAttribute AccountInfoQueryDTO accountInfoQueryDTO) {
        log.debug("accountInfoQueryDTO:{}", accountInfoQueryDTO);
        return accountInfoManager.queryByPage(accountInfoQueryDTO);
    }

    @GetMapping(value="/{id:\\d+}")
    public AccountInfoDTO query(@PathVariable Integer id) {
        log.debug("id:{}", id);
        if (id == null) {
            throw new AccountServiceException("主键不能为空");
        }
        return accountInfoManager.queryByPrimaryKey(id);
    }

    @PostMapping
    public AccountInfoDTO save(@Validated(SaveGroup.class) @RequestBody AccountInfoSaveDTO accountInfoSaveDTO, BindingResult errors) {
        processBindingResult(errors);
        return accountInfoManager.save(accountInfoSaveDTO);
    }

    @PutMapping
    public AccountInfoDTO update(@Validated(UpdateGroup.class) @RequestBody AccountInfoUpdateDTO accountInfoUpdateDTO, BindingResult errors) {
        processBindingResult(errors);
        return accountInfoManager.update(accountInfoUpdateDTO);
    }

    @DeleteMapping("/{id:\\d+}")
    public int delete(@PathVariable Integer id) {
        return accountInfoManager.delete(id);
    }

}