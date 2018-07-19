
package tk.yuqi.tools.tools.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.yuqi.tools.tools.mybatis.dao.CityDao;
import tk.yuqi.tools.tools.mybatis.domain.City;

/**
 * 类 CityController 的实现描述：CityController
 *
 * @since 2018/7/19
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityDao cityDao;

    @RequestMapping(value="/getCityByState/{state}")
    public City getCityByState( @PathVariable String state){
        return cityDao.findByState(state);
    }

    @RequestMapping(value="city",method = RequestMethod.POST)
    public long addCity(@RequestBody City city) throws Exception {
        cityDao.insert(city);
        return city.getId();
    }
}
