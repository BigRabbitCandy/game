package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.dao.GameContentMapper;
import cn.bluewhale.core.entity.GameContent;
import cn.bluewhale.core.service.IGameContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@Service
public class GameContentServiceImpl extends ServiceImpl<GameContentMapper, GameContent> implements IGameContentService {
    @Autowired
    private GameContentMapper gameContentMapper;

    @Override
    public List<GameContent> selectListByGid(Integer gid) {
        return gameContentMapper.selectListByGid(gid);
    }
}
