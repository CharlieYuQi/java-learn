package tk.yuqi.tools.tools.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import tk.yuqi.tools.tools.model.NewStudent;
import tk.yuqi.tools.tools.model.Student;

@Mapper
public interface StudentConverter {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    @Mappings({
            @Mapping(target = "studentName", source = "name")
    })
    NewStudent int2Pub(Student student);
}
