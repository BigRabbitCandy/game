package cn.bluewhale.core.service;

import cn.bluewhale.core.entity.GameContent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public interface IGameContentService extends IService<GameContent> {
    List<GameContent> selectListByGid(Integer gid);
}
