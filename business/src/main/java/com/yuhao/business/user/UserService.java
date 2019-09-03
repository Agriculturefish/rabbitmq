
package com.yuhao.business.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yuhao.dao.domain.model.TUserEntity;
import com.yuhao.dao.domain.vo.UserVo;
import com.yuhao.dao.repository.IUserRepository;


@Service

public class UserService{

	@Autowired
	IUserRepository userRepository;

	/**
	 * 如果没有指定key，则方法参数作为key保存到缓存中
	 * value缓存名字对应spring.cache.cache-names
	 * */
    @Cacheable(value = "myCache",key="#userid")
	public UserVo findById(String userid) {
		UserVo uv = new UserVo();
		TUserEntity ue = this.userRepository.findByUserid(userid);
		System.out.println("为id，key为"+ue.getUserid()+"做了缓存!");
		BeanUtils.copyProperties(ue, uv);
		return uv;
	}

}

      
