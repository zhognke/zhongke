package com.example.busniess.controller.manager;

import com.example.busniess.entity.ProductionInformationEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ProductionInformationService;
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
@RestController("productionInformation")
@RequestMapping("/manager/productionInformation")
public class ProductionInformationController {

    @Autowired
    private ProductionInformationService productionInformationService;

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult deleteById(Integer id) {
        if (productionInformationService.deleteById(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     *
     * @param productionInformationEntity
     * @return
     */
    @PostMapping("/update")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) ProductionInformationEntity productionInformationEntity) {
        if (productionInformationService.update(productionInformationEntity)) {
            return ReturnResult.success("修改成功");
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
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(ProductionInformationEntity productionInformationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
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
