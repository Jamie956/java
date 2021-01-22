package ${class_path}.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ccr.qc.base.Query;
import com.ccr.qc.base.Result;
import ${class_path}.model.${entity}Model;
import ${class_path}.service.${entity}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "${entity}Controller", tags = "")
@RestController
@RequestMapping("/${Key?uncap_first}")
public class ${entity}Controller {
    @Resource
    private ${entity}Service ${Key?uncap_first}Service;

    @ApiOperation("分页查询")
    @ApiImplicitParam(name = "query", value = "查询封装对象", paramType = "body")
    @PostMapping("/list")
    public Result<IPage<${entity}Model>> list(@RequestBody Query<${entity}Model> query) {
        IPage<${entity}Model> list = ${Key?uncap_first}Service.list(query);
        if (list == null) {
            return new Result<>(10001, "未找到", null);
        }
        return new Result<>(0, null, list);
    }
}
