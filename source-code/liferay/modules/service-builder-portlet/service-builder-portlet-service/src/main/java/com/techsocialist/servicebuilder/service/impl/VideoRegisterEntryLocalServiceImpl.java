/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.techsocialist.servicebuilder.service.impl;

import com.liferay.portal.aop.AopService;

import com.techsocialist.servicebuilder.service.base.VideoRegisterEntryLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the video register entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.techsocialist.servicebuilder.service.VideoRegisterEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VideoRegisterEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.techsocialist.servicebuilder.model.VideoRegisterEntry",
	service = AopService.class
)
public class VideoRegisterEntryLocalServiceImpl
	extends VideoRegisterEntryLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.techsocialist.servicebuilder.service.VideoRegisterEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.techsocialist.servicebuilder.service.VideoRegisterEntryLocalServiceUtil</code>.
	 */
}