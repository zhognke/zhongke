package com.example.busniess.controller;

import com.example.busniess.entity.DictEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DictService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 数据字典表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-10 09:41:28
 */
@Validated
@RestController
@RequestMapping("/dict")
public class DictController {
    
    @Autowired
    private DictService dictService;

    /**
     * 新增数据字典
     * @param entity    实体类
     * @return ReturnResult
     */
    @PostMapping("/addDict")
    public ReturnResult addDict(@Validated({UserValidator.InSet.class})DictEntity entity){
        if(dictService.add(entity)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改数据字典
     * @param entity    实体类
     * @return ReturnResult
     */
    @PostMapping("/updateDict")
    public ReturnResult updateDict(@Validated({UserValidator.UpDate.class})DictEntity entity){
        if(dictService.update(entity)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 获取所有字典元素
     * @return ReturnResult
     */
    @GetMapping("/selectAll")
    public ReturnResult selectAll(){
        List<DictEntity> list = dictService.getAll();
        if(list.size()>0){
            return ReturnResult.success(list);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据父id获取字典元素
     *
     * @param parentId 父id
     * @return ReturnResult
     */
    @GetMapping("/getDictByParentId")
    public ReturnResult getByParentId(Integer parentId){
        List<DictEntity> list = dictService.getByParentId(parentId);
        if(list.size()>0){
            return ReturnResult.success(list);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据type获取字典元素
     *
     * @param type 类型
     * @return ReturnResult
     */
    @GetMapping("/getDictByType")
    public ReturnResult getByParentType(String type){
        List<DictEntity> list = dictService.getByType(type);
        if(list.size()>0){
            return ReturnResult.success(list);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据type获取字典树
     *
     * @param type 类型
     * @return ReturnResult
     */
    @GetMapping("/getTreeByType")
    public ReturnResult getTreeByType(String type){
        DictEntity entity = dictService.getTreeByType(type);
        if(entity!=null){
            return ReturnResult.success(entity);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 逻辑删除
     * @param id 主键id
     * @return ReturnResult
     */
    @GetMapping("/deleteById")
    public ReturnResult deleteById(Integer id){
        if(dictService.delById(id)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     * @param id 主键id
     * @return ReturnResult
     */
    @GetMapping("/realDeleteById")
    public ReturnResult realDeleteById(Integer id){
        if(dictService.realDeleteById(id)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

}
