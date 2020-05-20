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

package com.techsocialist.servicebuilder.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.sql.Blob;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Video service. Represents a row in the &quot;video_register&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.techsocialist.servicebuilder.model.impl.VideoModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.techsocialist.servicebuilder.model.impl.VideoImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Video
 * @generated
 */
@ProviderType
public interface VideoModel extends BaseModel<Video> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a video model instance should use the {@link Video} interface instead.
	 */

	/**
	 * Returns the primary key of this video.
	 *
	 * @return the primary key of this video
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this video.
	 *
	 * @param primaryKey the primary key of this video
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this video.
	 *
	 * @return the ID of this video
	 */
	public long getId();

	/**
	 * Sets the ID of this video.
	 *
	 * @param id the ID of this video
	 */
	public void setId(long id);

	/**
	 * Returns the user ID of this video.
	 *
	 * @return the user ID of this video
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this video.
	 *
	 * @param userId the user ID of this video
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this video.
	 *
	 * @return the user uuid of this video
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this video.
	 *
	 * @param userUuid the user uuid of this video
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the production house ID of this video.
	 *
	 * @return the production house ID of this video
	 */
	public long getProductionHouseId();

	/**
	 * Sets the production house ID of this video.
	 *
	 * @param productionHouseId the production house ID of this video
	 */
	public void setProductionHouseId(long productionHouseId);

	/**
	 * Returns the name of this video.
	 *
	 * @return the name of this video
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this video.
	 *
	 * @param name the name of this video
	 */
	public void setName(String name);

	/**
	 * Returns the thumbnail of this video.
	 *
	 * @return the thumbnail of this video
	 */
	public Blob getThumbnail();

	/**
	 * Sets the thumbnail of this video.
	 *
	 * @param thumbnail the thumbnail of this video
	 */
	public void setThumbnail(Blob thumbnail);

	/**
	 * Returns the content of this video.
	 *
	 * @return the content of this video
	 */
	public Blob getContent();

	/**
	 * Sets the content of this video.
	 *
	 * @param content the content of this video
	 */
	public void setContent(Blob content);

	/**
	 * Returns the type of this video.
	 *
	 * @return the type of this video
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this video.
	 *
	 * @param type the type of this video
	 */
	public void setType(String type);

	/**
	 * Returns the status of this video.
	 *
	 * @return the status of this video
	 */
	@AutoEscape
	public String getStatus();

	/**
	 * Sets the status of this video.
	 *
	 * @param status the status of this video
	 */
	public void setStatus(String status);

	/**
	 * Returns the ticket price of this video.
	 *
	 * @return the ticket price of this video
	 */
	public double getTicketPrice();

	/**
	 * Sets the ticket price of this video.
	 *
	 * @param ticketPrice the ticket price of this video
	 */
	public void setTicketPrice(double ticketPrice);

	/**
	 * Returns the publish date time of this video.
	 *
	 * @return the publish date time of this video
	 */
	public long getPublishDateTime();

	/**
	 * Sets the publish date time of this video.
	 *
	 * @param publishDateTime the publish date time of this video
	 */
	public void setPublishDateTime(long publishDateTime);

	/**
	 * Returns the created by of this video.
	 *
	 * @return the created by of this video
	 */
	@AutoEscape
	public String getCreatedBy();

	/**
	 * Sets the created by of this video.
	 *
	 * @param createdBy the created by of this video
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Returns the modified by of this video.
	 *
	 * @return the modified by of this video
	 */
	@AutoEscape
	public String getModifiedBy();

	/**
	 * Sets the modified by of this video.
	 *
	 * @param modifiedBy the modified by of this video
	 */
	public void setModifiedBy(String modifiedBy);

	/**
	 * Returns the created date of this video.
	 *
	 * @return the created date of this video
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this video.
	 *
	 * @param createdDate the created date of this video
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the modified date of this video.
	 *
	 * @return the modified date of this video
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this video.
	 *
	 * @param modifiedDate the modified date of this video
	 */
	public void setModifiedDate(Date modifiedDate);

}