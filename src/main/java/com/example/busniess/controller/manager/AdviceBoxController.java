package com.example.busniess.controller.manager;

import com.example.busniess.entity.AdviceBoxEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.AdviceBoxService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 意见信箱表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-06 09:01:24
 */
@Validated
@RestController("adviceBox")
@RequestMapping("/manager/adviceBox")
public class AdviceBoxController {

    @Autowired
    private AdviceBoxService adviceBoxService;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "id不能为空")Integer id) {
        if (adviceBoxService.deleteByID(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteByBatch", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空") String ids) {
        if (adviceBoxService.deleteBatch(ids)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 指派
     *
     * @param id                 主键id
     * @param assignmentUsername 指派人员用户名
     * @return
     */
    @PostMapping("/assignment")
    public ReturnResult assignment(@NotNull(message = "id不能为空")Integer id, @NotNull(message = "指派人员不能为空") String assignmentUsername) {
        AdviceBoxEntity entity = new AdviceBoxEntity();
        entity.setId(id);
        entity.setAssignmentUsername(assignmentUsername);
        if (adviceBoxService.assignment(entity)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 回信
     *
     * @param id    主键id
     * @param reply 回信内容
     * @return
     */
    @PostMapping("/reply")
    public ReturnResult reply(@NotNull(message = "id不能为空")Integer id, @NotNull(message = "回信内容不能为空") String reply) {
        AdviceBoxEntity entity = new AdviceBoxEntity();
        entity.setId(id);
        entity.setReply(reply);
        if (adviceBoxService.reply(entity)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 回信
     *
     * @param id     主键id
     * @param isShow 是否显示:0不显示,1显示
     * @return
     */
    @PostMapping("/isShow")
    public ReturnResult isShow(@NotNull(message = "id不能为空")Integer id, @NotNull(message = "回信内容不能为空") int isShow) {
        AdviceBoxEntity entity = new AdviceBoxEntity();
        entity.setId(id);
        entity.setIsShow(isShow);
        if (adviceBoxService.isShow(entity)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param adviceBoxEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(AdviceBoxEntity adviceBoxEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = adviceBoxService.showByPage(adviceBoxEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空") Integer id) {
        AdviceBoxEntity obj = adviceBoxService.selectByID(id);
        if (obj != null) {
            return ReturnResult.success(obj);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
