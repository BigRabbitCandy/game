package cn.bluewhale.core.dao;

import cn.bluewhale.core.entity.GameContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public interface GameContentMapper extends BaseMapper<GameContent> {
    List<GameContent> selectListByGid(Integer gid);
}