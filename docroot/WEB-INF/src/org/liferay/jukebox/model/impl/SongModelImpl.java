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

package org.liferay.jukebox.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil;

import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.model.SongModel;
import org.liferay.jukebox.model.SongSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Song service. Represents a row in the &quot;jukebox_Song&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SongModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SongImpl}.
 * </p>
 *
 * @author Julio Camarero
 * @see SongImpl
 * @see Song
 * @see SongModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SongModelImpl extends BaseModelImpl<Song> implements SongModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a song model instance should use the {@link Song} interface instead.
	 */
	public static final String TABLE_NAME = "jukebox_Song";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "songId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "artistId", Types.BIGINT },
			{ "albumId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("songId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("artistId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("albumId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table jukebox_Song (uuid_ VARCHAR(75) null,songId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,artistId LONG,albumId LONG,name VARCHAR(75) null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table jukebox_Song";
	public static final String ORDER_BY_JPQL = " ORDER BY song.songId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY jukebox_Song.songId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.liferay.jukebox.model.Song"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.liferay.jukebox.model.Song"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.liferay.jukebox.model.Song"),
			true);
	public static final long ALBUMID_COLUMN_BITMASK = 1L;
	public static final long ARTISTID_COLUMN_BITMASK = 2L;
	public static final long COMPANYID_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long NAME_COLUMN_BITMASK = 16L;
	public static final long STATUS_COLUMN_BITMASK = 32L;
	public static final long USERID_COLUMN_BITMASK = 64L;
	public static final long UUID_COLUMN_BITMASK = 128L;
	public static final long SONGID_COLUMN_BITMASK = 256L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Song toModel(SongSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Song model = new SongImpl();

		model.setUuid(soapModel.getUuid());
		model.setSongId(soapModel.getSongId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setArtistId(soapModel.getArtistId());
		model.setAlbumId(soapModel.getAlbumId());
		model.setName(soapModel.getName());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Song> toModels(SongSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Song> models = new ArrayList<Song>(soapModels.length);

		for (SongSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.liferay.jukebox.model.Song"));

	public SongModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _songId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSongId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _songId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Song.class;
	}

	@Override
	public String getModelClassName() {
		return Song.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("songId", getSongId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("artistId", getArtistId());
		attributes.put("albumId", getAlbumId());
		attributes.put("name", getName());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long songId = (Long)attributes.get("songId");

		if (songId != null) {
			setSongId(songId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long artistId = (Long)attributes.get("artistId");

		if (artistId != null) {
			setArtistId(artistId);
		}

		Long albumId = (Long)attributes.get("albumId");

		if (albumId != null) {
			setAlbumId(albumId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getSongId() {
		return _songId;
	}

	@Override
	public void setSongId(long songId) {
		_songId = songId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getArtistId() {
		return _artistId;
	}

	@Override
	public void setArtistId(long artistId) {
		_columnBitmask |= ARTISTID_COLUMN_BITMASK;

		if (!_setOriginalArtistId) {
			_setOriginalArtistId = true;

			_originalArtistId = _artistId;
		}

		_artistId = artistId;
	}

	public long getOriginalArtistId() {
		return _originalArtistId;
	}

	@JSON
	@Override
	public long getAlbumId() {
		return _albumId;
	}

	@Override
	public void setAlbumId(long albumId) {
		_columnBitmask |= ALBUMID_COLUMN_BITMASK;

		if (!_setOriginalAlbumId) {
			_setOriginalAlbumId = true;

			_originalAlbumId = _albumId;
		}

		_albumId = albumId;
	}

	public long getOriginalAlbumId() {
		return _originalAlbumId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Song.class.getName()));
	}

	@Override
	public TrashEntry getTrashEntry() throws PortalException {
		if (!isInTrash()) {
			return null;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return trashEntry;
		}

		TrashHandler trashHandler = getTrashHandler();

		if (!Validator.isNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			ContainerModel containerModel = null;

			try {
				containerModel = trashHandler.getParentContainerModel(this);
			}
			catch (NoSuchModelException nsme) {
				return null;
			}

			while (containerModel != null) {
				if (containerModel instanceof TrashedModel) {
					TrashedModel trashedModel = (TrashedModel)containerModel;

					return trashedModel.getTrashEntry();
				}

				trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashHandler.getContainerModelClassName(
							containerModel.getContainerModelId()));

				if (trashHandler == null) {
					return null;
				}

				containerModel = trashHandler.getContainerModel(containerModel.getParentContainerModelId());
			}
		}

		return null;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	@Override
	public TrashHandler getTrashHandler() {
		return TrashHandlerRegistryUtil.getTrashHandler(getModelClassName());
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInTrashContainer() {
		TrashHandler trashHandler = getTrashHandler();

		if ((trashHandler == null) ||
				Validator.isNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			return false;
		}

		try {
			ContainerModel containerModel = trashHandler.getParentContainerModel(this);

			if (containerModel == null) {
				return false;
			}

			if (containerModel instanceof TrashedModel) {
				return ((TrashedModel)containerModel).isInTrash();
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isInTrashExplicitly() {
		if (!isInTrash()) {
			return false;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isInTrashImplicitly() {
		if (!isInTrash()) {
			return false;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Song.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Song toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Song)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SongImpl songImpl = new SongImpl();

		songImpl.setUuid(getUuid());
		songImpl.setSongId(getSongId());
		songImpl.setCompanyId(getCompanyId());
		songImpl.setGroupId(getGroupId());
		songImpl.setUserId(getUserId());
		songImpl.setUserName(getUserName());
		songImpl.setCreateDate(getCreateDate());
		songImpl.setModifiedDate(getModifiedDate());
		songImpl.setArtistId(getArtistId());
		songImpl.setAlbumId(getAlbumId());
		songImpl.setName(getName());
		songImpl.setStatus(getStatus());
		songImpl.setStatusByUserId(getStatusByUserId());
		songImpl.setStatusByUserName(getStatusByUserName());
		songImpl.setStatusDate(getStatusDate());

		songImpl.resetOriginalValues();

		return songImpl;
	}

	@Override
	public int compareTo(Song song) {
		long primaryKey = song.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Song)) {
			return false;
		}

		Song song = (Song)obj;

		long primaryKey = song.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SongModelImpl songModelImpl = this;

		songModelImpl._originalUuid = songModelImpl._uuid;

		songModelImpl._originalCompanyId = songModelImpl._companyId;

		songModelImpl._setOriginalCompanyId = false;

		songModelImpl._originalGroupId = songModelImpl._groupId;

		songModelImpl._setOriginalGroupId = false;

		songModelImpl._originalUserId = songModelImpl._userId;

		songModelImpl._setOriginalUserId = false;

		songModelImpl._setModifiedDate = false;

		songModelImpl._originalArtistId = songModelImpl._artistId;

		songModelImpl._setOriginalArtistId = false;

		songModelImpl._originalAlbumId = songModelImpl._albumId;

		songModelImpl._setOriginalAlbumId = false;

		songModelImpl._originalName = songModelImpl._name;

		songModelImpl._originalStatus = songModelImpl._status;

		songModelImpl._setOriginalStatus = false;

		songModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Song> toCacheModel() {
		SongCacheModel songCacheModel = new SongCacheModel();

		songCacheModel.uuid = getUuid();

		String uuid = songCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			songCacheModel.uuid = null;
		}

		songCacheModel.songId = getSongId();

		songCacheModel.companyId = getCompanyId();

		songCacheModel.groupId = getGroupId();

		songCacheModel.userId = getUserId();

		songCacheModel.userName = getUserName();

		String userName = songCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			songCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			songCacheModel.createDate = createDate.getTime();
		}
		else {
			songCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			songCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			songCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		songCacheModel.artistId = getArtistId();

		songCacheModel.albumId = getAlbumId();

		songCacheModel.name = getName();

		String name = songCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			songCacheModel.name = null;
		}

		songCacheModel.status = getStatus();

		songCacheModel.statusByUserId = getStatusByUserId();

		songCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = songCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			songCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			songCacheModel.statusDate = statusDate.getTime();
		}
		else {
			songCacheModel.statusDate = Long.MIN_VALUE;
		}

		return songCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", songId=");
		sb.append(getSongId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", artistId=");
		sb.append(getArtistId());
		sb.append(", albumId=");
		sb.append(getAlbumId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("org.liferay.jukebox.model.Song");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>songId</column-name><column-value><![CDATA[");
		sb.append(getSongId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>artistId</column-name><column-value><![CDATA[");
		sb.append(getArtistId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>albumId</column-name><column-value><![CDATA[");
		sb.append(getAlbumId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Song.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Song.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _songId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _artistId;
	private long _originalArtistId;
	private boolean _setOriginalArtistId;
	private long _albumId;
	private long _originalAlbumId;
	private boolean _setOriginalAlbumId;
	private String _name;
	private String _originalName;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _columnBitmask;
	private Song _escapedModel;
}