package org.gallant.account.controller;

import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.gallant.account.entity.AccountInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kongyong
 * @date 2019/11/27
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountInfoController {

    @RequestMapping("/test")
    public List<Integer> test(){
        return Lists.newArrayList(1, 2);
    }

    @GetMapping
    public List<AccountInfo> query(@PageableDefault(value = 15, sort = {
            "id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "startDate", required = false) Date startDate,
            @RequestParam(name = "endDate", required = false) Date endDate) {
        List<AccountInfo> accountInfos = Lists.newLinkedList();
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setCreateTime(startDate);
        accountInfo.setModifyTime(endDate);
        accountInfo.setId((int) pageable.getOffset());
        accountInfo.setAccountBankName(String.valueOf(pageable.getPageNumber()));
        accountInfo.setAccountCardCode(String.valueOf(pageable.getPageSize()));
        accountInfos.add(accountInfo);
        log.info("queryByPage:{}", accountInfo);
        return accountInfos;
    }

    @GetMapping(value="/{id:\\d+}")
    public AccountInfo query(@PathVariable Integer id) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(id);
        log.info("query:{}", id);
        return accountInfo;
    }

    @PostMapping
    public AccountInfo create(@Valid @RequestBody AccountInfo accountInfo, BindingResult errors) {
        if(errors.hasErrors()) {
            //有错误返回true
            errors.getAllErrors().stream().forEach(error -> log.error(error.getDefaultMessage()));
            //may not be empty
        }
        log.info("create:{}", accountInfo.toString());
        return accountInfo;
    }

    @PutMapping
    public AccountInfo update(@Valid @RequestBody AccountInfo accountInfo, BindingResult errors) {
        if(errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> log.info(error.getDefaultMessage()));
        }
        log.info("update:{}", accountInfo);
        return accountInfo;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Integer id) {
        log.info("delete:{}", id);
    }

}
