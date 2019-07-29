
package com.yuhao.business.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhao.dao.domain.model.TUserEntity;
import com.yuhao.dao.domain.vo.UserVo;
import com.yuhao.dao.repository.IUserRepository;


@Service
public class UserService{

	@Autowired
	IUserRepository userRepository;
	
	public UserVo findById(String userid) {
		UserVo uv = new UserVo();
		TUserEntity ue = this.userRepository.findByUserid(userid);
		BeanUtils.copyProperties(ue, uv);
		return uv;
	}

}

      
