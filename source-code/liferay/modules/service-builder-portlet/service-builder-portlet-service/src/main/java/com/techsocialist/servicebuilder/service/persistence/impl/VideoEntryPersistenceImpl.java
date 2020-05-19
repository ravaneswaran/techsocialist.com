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

package com.techsocialist.servicebuilder.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;

import com.techsocialist.servicebuilder.exception.NoSuchVideoEntryException;
import com.techsocialist.servicebuilder.model.VideoEntry;
import com.techsocialist.servicebuilder.model.impl.VideoEntryImpl;
import com.techsocialist.servicebuilder.model.impl.VideoEntryModelImpl;
import com.techsocialist.servicebuilder.service.persistence.VideoEntryPersistence;
import com.techsocialist.servicebuilder.service.persistence.impl.constants.TSPersistenceConstants;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the video entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = VideoEntryPersistence.class)
public class VideoEntryPersistenceImpl
	extends BasePersistenceImpl<VideoEntry> implements VideoEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VideoEntryUtil</code> to access the video entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VideoEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public VideoEntryPersistenceImpl() {
		setModelClass(VideoEntry.class);

		setModelImplClass(VideoEntryImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the video entry in the entity cache if it is enabled.
	 *
	 * @param videoEntry the video entry
	 */
	@Override
	public void cacheResult(VideoEntry videoEntry) {
		entityCache.putResult(
			entityCacheEnabled, VideoEntryImpl.class,
			videoEntry.getPrimaryKey(), videoEntry);

		videoEntry.resetOriginalValues();
	}

	/**
	 * Caches the video entries in the entity cache if it is enabled.
	 *
	 * @param videoEntries the video entries
	 */
	@Override
	public void cacheResult(List<VideoEntry> videoEntries) {
		for (VideoEntry videoEntry : videoEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, VideoEntryImpl.class,
					videoEntry.getPrimaryKey()) == null) {

				cacheResult(videoEntry);
			}
			else {
				videoEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all video entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VideoEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the video entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VideoEntry videoEntry) {
		entityCache.removeResult(
			entityCacheEnabled, VideoEntryImpl.class,
			videoEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VideoEntry> videoEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VideoEntry videoEntry : videoEntries) {
			entityCache.removeResult(
				entityCacheEnabled, VideoEntryImpl.class,
				videoEntry.getPrimaryKey());
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, VideoEntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new video entry with the primary key. Does not add the video entry to the database.
	 *
	 * @param id the primary key for the new video entry
	 * @return the new video entry
	 */
	@Override
	public VideoEntry create(long id) {
		VideoEntry videoEntry = new VideoEntryImpl();

		videoEntry.setNew(true);
		videoEntry.setPrimaryKey(id);

		return videoEntry;
	}

	/**
	 * Removes the video entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the video entry
	 * @return the video entry that was removed
	 * @throws NoSuchVideoEntryException if a video entry with the primary key could not be found
	 */
	@Override
	public VideoEntry remove(long id) throws NoSuchVideoEntryException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the video entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the video entry
	 * @return the video entry that was removed
	 * @throws NoSuchVideoEntryException if a video entry with the primary key could not be found
	 */
	@Override
	public VideoEntry remove(Serializable primaryKey)
		throws NoSuchVideoEntryException {

		Session session = null;

		try {
			session = openSession();

			VideoEntry videoEntry = (VideoEntry)session.get(
				VideoEntryImpl.class, primaryKey);

			if (videoEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVideoEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(videoEntry);
		}
		catch (NoSuchVideoEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected VideoEntry removeImpl(VideoEntry videoEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(videoEntry)) {
				videoEntry = (VideoEntry)session.get(
					VideoEntryImpl.class, videoEntry.getPrimaryKeyObj());
			}

			if (videoEntry != null) {
				session.delete(videoEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (videoEntry != null) {
			clearCache(videoEntry);
		}

		return videoEntry;
	}

	@Override
	public VideoEntry updateImpl(VideoEntry videoEntry) {
		boolean isNew = videoEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (videoEntry.isNew()) {
				session.save(videoEntry);

				videoEntry.setNew(false);
			}
			else {
				session.evict(videoEntry);
				session.saveOrUpdate(videoEntry);
			}

			session.flush();
			session.clear();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, VideoEntryImpl.class,
			videoEntry.getPrimaryKey(), videoEntry, false);

		videoEntry.resetOriginalValues();

		return videoEntry;
	}

	/**
	 * Returns the video entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the video entry
	 * @return the video entry
	 * @throws NoSuchVideoEntryException if a video entry with the primary key could not be found
	 */
	@Override
	public VideoEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVideoEntryException {

		VideoEntry videoEntry = fetchByPrimaryKey(primaryKey);

		if (videoEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVideoEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return videoEntry;
	}

	/**
	 * Returns the video entry with the primary key or throws a <code>NoSuchVideoEntryException</code> if it could not be found.
	 *
	 * @param id the primary key of the video entry
	 * @return the video entry
	 * @throws NoSuchVideoEntryException if a video entry with the primary key could not be found
	 */
	@Override
	public VideoEntry findByPrimaryKey(long id)
		throws NoSuchVideoEntryException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the video entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the video entry
	 * @return the video entry, or <code>null</code> if a video entry with the primary key could not be found
	 */
	@Override
	public VideoEntry fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the video entries.
	 *
	 * @return the video entries
	 */
	@Override
	public List<VideoEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video entries
	 * @param end the upper bound of the range of video entries (not inclusive)
	 * @return the range of video entries
	 */
	@Override
	public List<VideoEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the video entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video entries
	 * @param end the upper bound of the range of video entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of video entries
	 */
	@Override
	public List<VideoEntry> findAll(
		int start, int end, OrderByComparator<VideoEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video entries
	 * @param end the upper bound of the range of video entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of video entries
	 */
	@Override
	public List<VideoEntry> findAll(
		int start, int end, OrderByComparator<VideoEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<VideoEntry> list = null;

		if (useFinderCache) {
			list = (List<VideoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VIDEOENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIDEOENTRY;

				sql = sql.concat(VideoEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<VideoEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the video entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VideoEntry videoEntry : findAll()) {
			remove(videoEntry);
		}
	}

	/**
	 * Returns the number of video entries.
	 *
	 * @return the number of video entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VIDEOENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VIDEOENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VideoEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the video entry persistence.
	 */
	@Activate
	public void activate() {
		VideoEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		VideoEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, VideoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, VideoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(VideoEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = TSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.techsocialist.servicebuilder.model.VideoEntry"),
			true);
	}

	@Override
	@Reference(
		target = TSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = TSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_VIDEOENTRY =
		"SELECT videoEntry FROM VideoEntry videoEntry";

	private static final String _SQL_COUNT_VIDEOENTRY =
		"SELECT COUNT(videoEntry) FROM VideoEntry videoEntry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "videoEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No VideoEntry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		VideoEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id", "type"});

	static {
		try {
			Class.forName(TSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}