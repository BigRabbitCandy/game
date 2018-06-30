package cn.bluewhale.core.dao;

import cn.bluewhale.core.entity.GameProp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public interface GamePropMapper extends BaseMapper<GameProp> {

    int selectCount();
}