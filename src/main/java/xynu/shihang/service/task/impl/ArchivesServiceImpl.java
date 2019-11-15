package xynu.shihang.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Archives;
import xynu.shihang.mapper.ArchivesMapper;
import xynu.shihang.service.task.ArchivesService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesMapper archivesMapper;

    @Override
    public PageView<Archives> getAllArchives(int currentPage,int pageSize) {

        PageView<Archives> pageView = new PageView<Archives>(currentPage,pageSize);

        //设置开始页码和一页显示多少条数据
        List<Archives> archivesList = archivesMapper.getAllArchives();

        PageHelper.startPage(currentPage,pageSize);

        PageInfo pageInfo = new PageInfo(archivesList);

        pageView.setTotalRecoreds(pageInfo.getTotal());
      pageView.setDataList(pageInfo.getList());

        return pageView;
    }

    @Override
    public OAResult importBatchEmployeeArchives(MultipartFile files) {

        try{
            List<Archives> archivesList=new ArrayList<Archives>();
            //获取上传文件的输入流
            InputStream inputStream = files.getInputStream();
            //构造一个Excel文档对象
            HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
            //获取工作薄的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            //循环每个工作薄
            for(int i=0;i<numberOfSheets;i++){
                //获取指定的工作薄
                HSSFSheet sheetAt = workbook.getSheetAt(i);
                if(sheetAt==null){
                    continue;
                }
                //获取最后一行的行数
                int lastRowNum = sheetAt.getLastRowNum();
                for(int j=1;j<=lastRowNum;j++){
                    //获取指定行
                    HSSFRow row = sheetAt.getRow(j);
                    HSSFCell dnum = row.getCell(0);
                    HSSFCell landline = row.getCell(1);
                    HSSFCell school = row.getCell(2);
                    HSSFCell zhuanye = row.getCell(3);
                    HSSFCell sosperson = row.getCell(4);
                    HSSFCell biyedate = row.getCell(5);
                    HSSFCell zzmm = row.getCell(6);
                    HSSFCell minzu = row.getCell(7);
                    HSSFCell xueli = row.getCell(8);
                    HSSFCell email = row.getCell(9);
                    HSSFCell empFk = row.getCell(10);

                    //创建一个档案类对象
                    Archives archives=new Archives();
                    archives.setDnum(dnum.getStringCellValue());
                    archives.setLandline(landline.getStringCellValue());
                    archives.setSchool(school.getStringCellValue());
                    archives.setZhuanye(zhuanye.getStringCellValue());
                    archives.setSosperson(sosperson.getStringCellValue());
                    archives.setBiyedate(biyedate.getDateCellValue());
                    archives.setZzmm(zzmm.getStringCellValue());
                    archives.setMinzu(minzu.getStringCellValue());
                    archives.setXueli(xueli.getStringCellValue());
                    archives.setEmail(email.getStringCellValue());
                    archives.setEmpFk((int)empFk.getNumericCellValue());
                    //每一行对应的archives添加到集合
                    archivesList.add(archives);
                }
            }
            System.out.println("============>"+archivesList.size());
            //保存集合到archives表中
            int rows = archivesMapper.addBatchArchives(archivesList);
            if(rows>=1){
                return OAResult.ok(200,"档案数据导入成功");
            }else{
                return OAResult.ok(400,"档案数据导入失败");
            }

        }catch(Exception e){
            e.printStackTrace();
            return OAResult.ok(400,"档案数据导入失败");
        }

    }

    @Override
    public OAResult deleteOneOrMoreArchives(String dnum) {

        int row = archivesMapper.deleteByPrimaryKey(dnum);

        if (row >=1){
            return  OAResult.ok(200,"删除档案成功");
        }

        return  OAResult.ok(200,"删除档案失败");
    }

    @Override
    public Archives getArchivesByEid(int id) {

        Archives archives = archivesMapper.getArchivesByEid(id);

        return archives;
    }
}
