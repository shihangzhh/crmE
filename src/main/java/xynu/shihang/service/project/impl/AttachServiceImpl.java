package xynu.shihang.service.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Attachment;
import xynu.shihang.entity.Project;
import xynu.shihang.mapper.AttachmentMapper;
import xynu.shihang.service.project.AttachService;
import xynu.shihang.utils.OAResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class AttachServiceImpl implements AttachService {


   // @Value("${upLoadFilePath}")
   // private String upLoadFilePath;
    @Autowired
    private AttachmentMapper attachmentMapper;


    @Override
    public OAResult saveAttachmentFile(Attachment attachment, MultipartFile upLoadFile) {

         String upLoadFilePath = "D:\\\\img\\\\images";
        String path = "";
        try {
            //设置文件的保存目录
            String saveDir = upLoadFilePath;
            //得到原始的文件名字
            String fileName = upLoadFile.getOriginalFilename();
            //设置文件的新名字
            String fileNewName = UUID.randomUUID().toString().replace('-', '1').concat(fileName.substring(fileName.indexOf(".")));

            int   fileDir1 = fileName.hashCode()&0xff;
            saveDir= saveDir+"\\"+fileDir1;
            File fileDir = new File(saveDir);
            if (!fileDir.exists()){
                fileDir.mkdirs();
            }

            File saveFile = new File(saveDir,fileNewName);
            upLoadFile.transferTo(saveFile);
            path = saveDir+"\\"+fileNewName;
        } catch (IOException e) {
            e.printStackTrace();
            return  OAResult.ok(400,"附件上传失败");
        }

        attachment.setPath(path);
        attachment.setUpdatetime(new Date());
        attachment.setUploadtime(new Date());

        int row = attachmentMapper.insert(attachment);

        if (row == 1){
            return OAResult.ok(200,"上传附件成功");

        }
        return OAResult.ok(400,"上传附件失败");
    }
}
