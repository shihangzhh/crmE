package xynu.shihang.service.message;

import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Email;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {

    public OAResult sendMail(Email email, String reemp, MultipartFile file, HttpServletRequest request) throws Exception;


}
