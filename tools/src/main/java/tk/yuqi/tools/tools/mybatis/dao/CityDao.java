
package tk.yuqi.tools.tools.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.yuqi.tools.tools.mybatis.domain.City;

/**
 * 类 CityDao 的实现描述：CityDao
 *
 * @since 2018/7/19
 */
@Mapper
public interface CityDao {
    @Select("SELECT * FROM city WHERE state = #{state}")
    City findByState(String state);
}
