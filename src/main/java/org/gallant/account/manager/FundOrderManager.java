package org.gallant.account.manager;

import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.gallant.account.domain.dto.result.FundOrderDTO;
import org.gallant.account.domain.dto.param.FundOrderQueryDTO;
import org.gallant.account.domain.dto.param.FundOrderBaseDTO;
import org.gallant.account.domain.dto.param.FundOrderUpdateDTO;
import org.gallant.account.entity.FundOrder;
import org.gallant.account.entity.FundOrder.Column;
import org.gallant.account.entity.FundOrderExample;
import org.gallant.account.entity.FundOrderExample.Criteria;
import org.gallant.account.mapper.FundOrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author kongyong
 * @date 2019/11/29
 */
@Component
public class FundOrderManager {

    @Resource
    private FundOrderMapper fundOrderMapper;

    public List<FundOrderDTO> queryByPage(FundOrderQueryDTO fundOrderQueryDTO){
        FundOrderExample example = new FundOrderExample();
        example.page(fundOrderQueryDTO.getPageNum() - 1, fundOrderQueryDTO.getPageSize());
        example.setOrderByClause(Column.id.desc());
        Criteria criteria = example.createCriteria();
        if (fundOrderQueryDTO.getStartDate() != null && fundOrderQueryDTO.getEndDate() != null) {
            criteria.andCreateTimeBetween(fundOrderQueryDTO.getStartDate(), fundOrderQueryDTO.getEndDate());
        }
        List<FundOrder> fundOrders = fundOrderMapper.selectByExampleSelective(example);
        List<FundOrderDTO> fundOrderDTOS = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(fundOrders)) {
            fundOrders.forEach(fundOrder -> fundOrderDTOS.add(convert2DTO(fundOrder)));
        }
        return fundOrderDTOS;
    }

    public FundOrderDTO queryByPrimaryKey(Integer id) {
        FundOrder fundOrder = fundOrderMapper.selectByPrimaryKey(id);
        if (fundOrder != null) {
            return convert2DTO(fundOrder);
        }
        return null;
    }

    public FundOrderDTO save(FundOrderBaseDTO fundOrderBaseDTO) {
        FundOrderDTO fundOrderDTO = null;
        FundOrder fundOrder = convert(fundOrderBaseDTO);
        fundOrder.setIsActive(true);
        Date date = new Date();
        fundOrder.setCreateTime(date);
        fundOrder.setModifyTime(date);
        int count = fundOrderMapper.insertSelective(fundOrder);
        if (count == 1) {
            fundOrderDTO = convert2DTO(fundOrder);
        }
        return fundOrderDTO;
    }

    public FundOrderDTO update(FundOrderUpdateDTO fundOrderUpdateDTO) {
        FundOrderDTO fundOrderDTO = null;
        FundOrder fundOrder = convert(fundOrderUpdateDTO);
        Date date = new Date();
        fundOrder.setModifyTime(date);
        int count = fundOrderMapper.updateByPrimaryKeySelective(fundOrder);
        if (count == 1) {
            fundOrderDTO = convert2DTO(fundOrder);
        }
        return fundOrderDTO;
    }

    public int delete(Integer id) {
        FundOrder fundOrder = new FundOrder();
        fundOrder.setId(id);
        fundOrder.setIsActive(false);
        return fundOrderMapper.updateByPrimaryKeySelective(fundOrder);
    }

    private FundOrderDTO convert2DTO(FundOrder fundOrder) {
        FundOrderDTO fundOrderDTO = new FundOrderDTO();
        BeanUtils.copyProperties(fundOrder, fundOrderDTO);
        return fundOrderDTO;
    }

    private FundOrder convert(FundOrderBaseDTO fundOrderBaseDTO) {
        FundOrder fundOrder = new FundOrder();
        BeanUtils.copyProperties(fundOrderBaseDTO, fundOrder);
        return fundOrder;
    }

}
