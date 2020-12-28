package cn.ajiehome.common.exception.entity.bo;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import lombok.Data;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:58
 * @Version: 1.0V
 */
@Data
public class ResultBO<T> {
    private Integer code;
    private T msg;

    public ResultBO(Integer code, T msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultBO<String> newResultBO(CodeType codeType){
        return new ResultBO<String>(codeType.getCode(), codeType.getMsg());
    }
    public static ResultBO<String> newResultBO(CodeType codeType,String message){
        return new ResultBO<String>(codeType.getCode(), message);
    }
    public static ResultBO<Object> newResultBO(CodeType codeType,Object message){
        return new ResultBO<Object>(codeType.getCode(), message);
    }
    public static ResultBO<String> newResultBO(ApplicationException e){
        return new ResultBO<String>(e.getCode(), e.getMsg());
    }

}
