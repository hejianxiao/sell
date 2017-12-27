package com.hjx.util;

import com.hjx.vo.ResultVO;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code,String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
