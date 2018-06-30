package cn.bluewhale.core.service;

import cn.bluewhale.core.entity.GameProp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public interface IGamePropService extends IService<GameProp> {
    int selectCount();
}
