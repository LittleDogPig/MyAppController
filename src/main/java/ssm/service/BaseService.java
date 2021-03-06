package ssm.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {

    public void save(T t);

    public void update(T t) ;

    public void delete(int id) ;

    public T get(int id) ;

    public List<T> list();

    /**
     * 统一返回成功格式
     * @param map
     * @param message
     * @param data
     * @return
     */
    public Map<String,Object> successRespMap (Map<String,Object> map , String message , Object data);

    /**
     * 统一返回参数错误格式
     * @param map
     * @param message
     * @return
     */
    public Map<String, Object> errorRespMap(Map<String,Object> map , String message);
//    public Map<String, Object> errorRespMap(Map<String,T> map , String message);
}
