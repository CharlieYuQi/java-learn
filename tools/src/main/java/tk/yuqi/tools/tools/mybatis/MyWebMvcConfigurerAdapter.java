
package tk.yuqi.tools.tools.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 类 MyWebMvcConfigurerAdapter 的实现描述：MyWebMvcConfigurerAdapter
 *
 * @since 2018/7/19
 */
@Configuration
@EnableWebMvc
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(
        ContentNegotiationConfigurer configurer) {
        //      super.configureContentNegotiation(configurer);
        //设置是否忽略请求头的content-type,设置为TRUE后，按照请求后缀来返回对应的数据格式
        //      configurer.ignoreAcceptHeader(true);
        //自定义后缀返回的数据格式
        // 设置ext放回类型为json
        configurer.mediaType("ext", MediaType.APPLICATION_JSON);
        // 设置ext放回类型为json
        configurer.mediaType("in", MediaType.APPLICATION_JSON);
        /* 设置ext放回类型为json */
        configurer.mediaType("do", MediaType.APPLICATION_JSON);
        //是否是否使用后缀 来返回数据格式,优先级比 请求头要高
        configurer.favorPathExtension(true);
        configurer.favorParameter(true)
                  .parameterName("mediaType")
                  //设置默认的返回数据格式
                  .defaultContentType(MediaType.APPLICATION_JSON)
                  .mediaType("xml", MediaType.APPLICATION_XML)
                  .mediaType("json", MediaType.APPLICATION_JSON);
    }
}
