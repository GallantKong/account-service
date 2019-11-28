package org.gallant.account.manager;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.gallant.account.domain.dto.AccountInfoDTO;
import org.gallant.account.domain.dto.AccountInfoQueryDTO;
import org.gallant.account.domain.dto.AccountInfoSaveDTO;
import org.gallant.account.domain.dto.AccountInfoUpdateDTO;
import org.gallant.account.entity.AccountInfo;
import org.gallant.account.entity.AccountInfo.Column;
import org.gallant.account.entity.AccountInfoExample;
import org.gallant.account.entity.AccountInfoExample.Criteria;
import org.gallant.account.mapper.AccountInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author kongyong
 * @date 2019/11/28
 */
@Component
public class AccountInfoManager {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    public List<AccountInfoDTO> queryByPage(AccountInfoQueryDTO accountInfoQueryDTO){
        AccountInfoExample example = new AccountInfoExample();
        example.page(accountInfoQueryDTO.getPageNum() - 1, accountInfoQueryDTO.getPageSize());
        example.setOrderByClause(Column.id.desc());
        Criteria criteria = example.createCriteria();
        if (accountInfoQueryDTO.getStartDate() != null && accountInfoQueryDTO.getEndDate() != null) {
            criteria.andCreateTimeBetween(accountInfoQueryDTO.getStartDate(), accountInfoQueryDTO.getEndDate());
        }
        List<AccountInfo> accountInfos = accountInfoMapper.selectByExampleSelective(example);
        List<AccountInfoDTO> accountInfoDTOS = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(accountInfos)) {
            accountInfos.forEach(accountInfo -> accountInfoDTOS.add(convert2DTO(accountInfo)));
        }
        return accountInfoDTOS;
    }

    public AccountInfoDTO queryByPrimaryKey(Integer id) {
        return convert2DTO(accountInfoMapper.selectByPrimaryKey(id));
    }

    public AccountInfoDTO save(AccountInfoSaveDTO accountInfoSaveDTO) {
        AccountInfoDTO accountInfoDTO = null;
        AccountInfo accountInfo = convert(accountInfoSaveDTO);
        int count = accountInfoMapper.insertSelective(accountInfo);
        if (count == 1) {
            accountInfoDTO = convert2DTO(accountInfo);
        }
        return accountInfoDTO;
    }

    public AccountInfoDTO update(AccountInfoUpdateDTO accountInfoUpdateDTO) {
        AccountInfoDTO accountInfoDTO = null;
        AccountInfo accountInfo = convert(accountInfoUpdateDTO);
        int count = accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
        if (count == 1) {
            accountInfoDTO = convert2DTO(accountInfo);
        }
        return accountInfoDTO;
    }

    public int delete(Integer id) {
        return accountInfoMapper.deleteByPrimaryKey(id);
    }

    private AccountInfoDTO convert2DTO(AccountInfo accountInfo) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        BeanUtils.copyProperties(accountInfo, accountInfoDTO);
        return accountInfoDTO;
    }

    private AccountInfo convert(AccountInfoSaveDTO accountInfoSaveDTO) {
        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountInfoSaveDTO, accountInfo);
        return accountInfo;
    }

}
