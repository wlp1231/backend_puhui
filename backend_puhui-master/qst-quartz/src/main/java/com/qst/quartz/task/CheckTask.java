package com.qst.quartz.task;

import com.qst.system.domain.Loans;
import com.qst.system.domain.Overdue;
import com.qst.system.domain.Refund;
import com.qst.system.service.ILoansService;
import com.qst.system.service.IOverdueService;
import com.qst.system.service.IRefundService;
import com.qst.system.util.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author qst
 */

@Component("checkTask")
public class CheckTask {

    @Autowired
    private ILoansService loansService;
    @Autowired
    private IOverdueService overdueService;
    @Autowired
    private IRefundService refundService;

    /**
     * 检查 还款表 结束 时间 ,是否 逾期
     */
    public void checkLoadEndTime(){
        System.out.println("---------定时任务执行----检查是否逾期----------开始--");

        // 获得 还款表 记录
        Refund refund = new Refund();
        refund.setRefState("1");
        List<Refund> list = refundService.selectRefundList(refund);
        System.out.println("----------未还款的记录共-------------------"+list.size());
        // 遍历 记录,获得 截止时间,并与当前系统时间比较
        for (Refund re:list) {
            Date endTime = re.getRefTime();// 获得 截止时间
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = change(endTime);
            if(end.isBefore(now)){


                // 为逾期表 增加 记录
                Overdue due = new Overdue();
                due.setOveComNo(re.getRefComNo());
                due.setOveLoaId(re.getRefLoId());
                due.setOveMoney(re.getRefMoney());
                due.setOveStartTime(new Date());
                due.setOveCutCount(ConstUtil.COUNT);
                due.setOveDel(ConstUtil.DEL_SIGN);
                due.setOveAddInt(ConstUtil.RATE); // 逾期利息
                overdueService.insertOverdue(due);
                System.out.println("---------已经为 oveduw 添加 记录-------------"+due.getOveId());

                // 更新 state 为 3 为 已逾期
                re.setRefState(ConstUtil.OVER_STATUS);
                re.setRefOveId(Long.valueOf(due.getOveId()));
                refundService.updateRefund(re);

            }

        }
        System.out.println("---------定时任务执行----检查是否逾期----------结束--");
    }

    /**
     * 将 Date 转换为 LocalDateTime
     * @param date
     * @return
     */
    public LocalDateTime change(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }


}