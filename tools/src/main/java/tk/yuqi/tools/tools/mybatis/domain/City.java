
package tk.yuqi.tools.tools.mybatis.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 类 City 的实现描述：City
 *
 * @since 2018/7/19
 */

@Data
@ToString(callSuper = true)
public class City extends BaseFeatrue{
    private Long id;
    private String name;
    private String state;
    private String country;
}
