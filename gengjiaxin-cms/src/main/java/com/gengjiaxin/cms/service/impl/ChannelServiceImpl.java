package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.ChannelDao;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channelDao;

	@Override
	public List<Channel> channelList() {
		return channelDao.channelList();
	}
	
	
}
