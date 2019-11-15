package xynu.shihang.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Attachment;
import xynu.shihang.service.project.AttachService;
import xynu.shihang.utils.OAResult;

@RequestMapping(value = "/attach")
@Controller
public class AttachFile{

    @Autowired
    private AttachService attachService;

@RequestMapping(value = "/{page}")
    public String forwardAttachmentFilePage(@PathVariable(value = "page") String page){

        return "project/"+page;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAttachment")
    public OAResult saveAttachmentFile(Attachment attachment, MultipartFile upLoadFile){

       System.out.println("你执行了么");
        OAResult oaResult = attachService.saveAttachmentFile(attachment, upLoadFile);
        return  oaResult;
    }

}
