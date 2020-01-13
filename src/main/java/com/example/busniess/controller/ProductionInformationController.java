package com.example.busniess.controller;

import com.example.busniess.entity.ProductionInformationEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ProductionInformationService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 企业生产信息
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-09 08:13:22
 */
@Validated
@RestController
@RequestMapping("/productionInformation")
public class ProductionInformationController {

    @Autowired
    private ProductionInformationService productionInformationService;

    /**
     * 新增
     *
     * @param productionInformationEntity
     * @return
     */
    @PostMapping("/add")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) ProductionInformationEntity productionInformationEntity) {
        if(productionInformationService.selectByDate(productionInformationEntity)!=null){
            return ReturnResult.erro(CodeMsg.REPEAT_SUBMIT);
        }
        if (productionInformationService.add(productionInformationEntity)) {
            return ReturnResult.success("添加成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param productionInformationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPageForCenter", method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(ProductionInformationEntity productionInformationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.LOGIN_TIME_OUT);
        }else{
            productionInformationEntity.setUsername(userName);
        }
        PageInfo pageInfo = productionInformationService.showByPage(productionInformationEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ReturnResult getById(Integer id) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        } else {
            ProductionInformationEntity obj = productionInformationService.selectById(id);
            if (obj != null) {
                return ReturnResult.success(obj);
            } else {
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }
}
