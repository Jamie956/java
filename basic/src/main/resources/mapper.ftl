package ${class_path}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${class_path}.entity.${entity};
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${entity}Mapper extends BaseMapper<${entity}> {
}
