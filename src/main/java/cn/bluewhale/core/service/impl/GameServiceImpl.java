package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.entity.Game;
import cn.bluewhale.core.dao.GameMapper;
import cn.bluewhale.core.service.IGameService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {
	
}
