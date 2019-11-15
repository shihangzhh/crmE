        package xynu.shihang.action.project;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import xynu.shihang.entity.Function;
        import xynu.shihang.service.project.FunctionService;
        import xynu.shihang.utils.OAResult;
        import java.util.List;

        @Controller
        @RequestMapping(value = "/function")
        public class FunctionAction {

        @Autowired
        private FunctionService functionService;

            @ResponseBody
            @RequestMapping(value = "/saveFunction")
            public OAResult saveFunction(Function function){

                return functionService.saveProjectFunction(function);
            }

            @ResponseBody
             @RequestMapping(value = "/module/functions")
            public List<Function> getFunctionsByModuleId(int id){

                return functionService.getFunctionsByModuleId(id);
            }
        }
