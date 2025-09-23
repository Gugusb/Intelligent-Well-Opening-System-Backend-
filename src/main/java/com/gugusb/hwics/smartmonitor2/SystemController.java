package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.smartmonitor2.entity.WellParams;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smart")
public class SystemController {
    @Autowired
    ProcessController processController;

    @PostMapping("/system-reset-process")
    public MessageRespnser<String> resetProcess() {
        processController.restartAllProcess();
        return MessageRespnser.success("重置所有进程");
    }

    @PostMapping("/system-start-check")
    public MessageRespnser<String> startSystemCheck() {
        processController.startSystemCheck();
        return MessageRespnser.success("智能系统尝试开启，正在自检...");
    }

    @PostMapping("/system-start-process")
    public MessageRespnser<String> startSystemProcess() {
        processController.startSystemProcess(new WellParams(3000, 1000, 0.062, 0.084));
        return MessageRespnser.success("智能系统成功开启");
    }

    @PostMapping("/edit-params")
    public MessageRespnser<String> editPms() {
        if(processController.editParams(new WellParams(3000, 1000, 0.062, 0.084)))
            return MessageRespnser.success("气井参数更新成功");
        return MessageRespnser.unsuccess("气井参数更新失败");
    }

    @PostMapping("/close-system")
    public MessageRespnser<String> closeSystem(){
        if(processController.isRunning()){
            if(processController.closeSystem())
                return MessageRespnser.success("系统关闭成功！");
        }
        return MessageRespnser.unsuccess("系统未开启，关闭失败");
    }
}
