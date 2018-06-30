package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.dao.GameContentMapper;
import cn.bluewhale.core.dao.GamePropMapper;
import cn.bluewhale.core.entity.GameProp;
import cn.bluewhale.core.service.IGamePropService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@Service
public class GamePropServiceImpl extends ServiceImpl<GamePropMapper, GameProp> implements IGamePropService {

    @Autowired
    private GamePropMapper gamePropMapper;

    @Override
    public int selectCount() {
        return gamePropMapper.selectCount();
    }
}
