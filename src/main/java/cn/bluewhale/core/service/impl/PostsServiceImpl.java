package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.entity.Posts;
import cn.bluewhale.core.dao.PostsMapper;
import cn.bluewhale.core.service.IPostsService;
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
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {
	
}
