package com.qst.system.controller;

import java.util.List;

import com.qst.system.service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qst.common.annotation.Log;
import com.qst.common.core.controller.BaseController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.common.enums.BusinessType;
import com.qst.system.domain.Message;
import com.qst.system.service.IMessageService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 留言管理Controller
 *
 * @author qst
 * @date 2022-06-14
 */
@Api(tags = "留言管理类")
@RestController
@RequestMapping("/system/message")
public class MessageController extends BaseController
{
    @Autowired
    private IMessageService messageService;

    @Autowired
    private ICompanyService companyService;
    /**
     * 查询留言管理列表
     */
    @ApiOperation(value = "留言管理列表",notes = "显示全部的留言数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Message",name = "message",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(Message message)
    {
        startPage();
        List<Message> list = messageService.selectMessageList(message);
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @ApiOperation(value = "导出留言数据管理列表",notes = "导出全部的留言数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Message",name = "message",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:export')")
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Message message)
    {
        List<Message> list = messageService.selectMessageList(message);
        ExcelUtil<Message> util = new ExcelUtil<Message>(Message.class);
        return util.exportExcel(list, "留言管理数据");
    }

    /**
     * 获取留言管理详细信息
     */
    @ApiOperation(value = "查看留言数据信息",notes = "查看留言数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "mId",value="mId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:query')")
    @GetMapping(value = "/{mId}")
    public AjaxResult getInfo(@PathVariable("mId") Long mId)
    {
        return AjaxResult.success(messageService.selectMessageByMId(mId));
    }

    /**
     * 新增留言管理
     */
    @ApiOperation(value = "新增留言数据",notes = "新增留言数据")
    @ApiImplicitParam(paramType = "query",dataType = "Message",name = "message",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:add')")
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Message message)
    {
        return toAjax(messageService.insertMessage(message));
    }

    /**
     * 修改留言管理
     */
    @ApiOperation(value = "修改留言数据",notes = "修改留言数据")
    @ApiImplicitParam(paramType = "query",dataType = "Message",name = "message",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:edit')")
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Message message)
    {
        return toAjax(messageService.updateMessage(message));
    }

    /**
     * 删除留言管理
     */
    @ApiOperation(value = "删除留言数据",notes = "删除留言数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "mIds",value="mIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:message:remove')")
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{mIds}")
    public AjaxResult remove(@PathVariable Long[] mIds)
    {
        return toAjax(messageService.deleteMessageByMIds(mIds));
    }

    /**
     * 查询资质列表
     */
    @ApiOperation(value = "查询资质列表",notes = "查询资质列表")
    @GetMapping("/comName")
    public AjaxResult listComName()
    {
        return AjaxResult.success(companyService.selectCompanyNameAutonym());
    }
}