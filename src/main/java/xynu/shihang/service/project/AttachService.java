package xynu.shihang.service.project;

import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Attachment;
import xynu.shihang.utils.OAResult;

public interface AttachService {

    /**
     * 保存附件
     * @param attachment
     * @return
     */
    public OAResult saveAttachmentFile(Attachment attachment, MultipartFile upLoadFile);

}
