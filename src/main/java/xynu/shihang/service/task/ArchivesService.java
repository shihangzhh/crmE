package xynu.shihang.service.task;

import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Archives;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

public interface ArchivesService {

    /**
     * 查询所有员工的档案
     * @return
     */
    public PageView<Archives> getAllArchives(int currentPage,int pageSize);


    public OAResult importBatchEmployeeArchives(MultipartFile files);


    /**
     * 删除档案
     * @param dnum
     * @return
     */
    public OAResult deleteOneOrMoreArchives(String dnum);

    /**
     * 员工id
     * @param id
     * @return
     */
    public Archives getArchivesByEid(int id);

}
