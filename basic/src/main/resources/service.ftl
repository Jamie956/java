package ${class_path}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccr.qc.base.Query;
import com.ccr.qc.base.Utils;
import ${class_path}.entity.${entity};
import ${class_path}.mapper.${entity}Mapper;
import ${class_path}.model.${entity}Model;
import org.springframework.stereotype.Service;

@Service
public class ${entity}Service extends ServiceImpl<${entity}Mapper, ${entity}> {
public IPage<${entity}Model> list(Query<${entity}Model> tempQuery) {
    Query<${entity}> query = Utils.copyQuery(tempQuery);
    Page<${entity}> srcResult = this.page(query.initPager(), query.getWrapper());
        if (srcResult == null) {
            return null;
        }
        return Utils.copyResult(srcResult, ${entity}Model::new);
    }
}
