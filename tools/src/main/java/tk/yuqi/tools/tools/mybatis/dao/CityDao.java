
package tk.yuqi.tools.tools.mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import tk.yuqi.tools.tools.mybatis.domain.City;

/**
 * 类 CityDao 的实现描述：CityDao
 * 更多注解见
 * <a href="http://www.mybatis.org/mybatis-3/zh/java-api.html">http://www.mybatis.org/mybatis-3/zh/java-api.html</a>
 *
 * @since 2018/7/19
 */
@Mapper
public interface CityDao {
    @Select("SELECT * FROM city WHERE state = #{state}")
    City findByState(String state);



    @Insert("insert into city (name,state,country) values (#{name},#{state},#{country})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", resultType=long.class, before=false)
    int insert(City city) throws Exception;
}
