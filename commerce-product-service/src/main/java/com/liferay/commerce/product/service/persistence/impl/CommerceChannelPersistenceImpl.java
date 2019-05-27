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

package com.liferay.commerce.product.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.exception.NoSuchChannelException;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.impl.CommerceChannelImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceChannelPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the commerce channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelPersistence
 * @see com.liferay.commerce.product.service.persistence.CommerceChannelUtil
 * @generated
 */
@ProviderType
public class CommerceChannelPersistenceImpl extends BasePersistenceImpl<CommerceChannel>
	implements CommerceChannelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceChannelUtil} to access the commerce channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceChannelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			CommerceChannelModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceChannelModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceChannel> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceChannel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<CommerceChannel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceChannel commerceChannel : list) {
					if ((companyId != commerceChannel.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByCompanyId_First(long companyId,
		OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {
		CommerceChannel commerceChannel = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (commerceChannel != null) {
			return commerceChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchChannelException(msg.toString());
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceChannel> orderByComparator) {
		List<CommerceChannel> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {
		CommerceChannel commerceChannel = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (commerceChannel != null) {
			return commerceChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchChannelException(msg.toString());
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceChannel> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceChannel> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce channels before and after the current commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param commerceChannelId the primary key of the current commerce channel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel[] findByCompanyId_PrevAndNext(
		long commerceChannelId, long companyId,
		OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {
		CommerceChannel commerceChannel = findByPrimaryKey(commerceChannelId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannel[] array = new CommerceChannelImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, commerceChannel,
					companyId, orderByComparator, true);

			array[1] = commerceChannel;

			array[2] = getByCompanyId_PrevAndNext(session, commerceChannel,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceChannel getByCompanyId_PrevAndNext(Session session,
		CommerceChannel commerceChannel, long companyId,
		OrderByComparator<CommerceChannel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceChannel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceChannel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce channels where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceChannel commerceChannel : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceChannel);
		}
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce channels
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECHANNEL_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "commerceChannel.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_ERC = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceChannelModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceChannelModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_ERC = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchChannelException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchChannelException {
		CommerceChannel commerceChannel = fetchByC_ERC(companyId,
				externalReferenceCode);

		if (commerceChannel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", externalReferenceCode=");
			msg.append(externalReferenceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchChannelException(msg.toString());
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_ERC,
					finderArgs, this);
		}

		if (result instanceof CommerceChannel) {
			CommerceChannel commerceChannel = (CommerceChannel)result;

			if ((companyId != commerceChannel.getCompanyId()) ||
					!Objects.equals(externalReferenceCode,
						commerceChannel.getExternalReferenceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				List<CommerceChannel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceChannelPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceChannel commerceChannel = list.get(0);

					result = commerceChannel;

					cacheResult(commerceChannel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CommerceChannel)result;
		}
	}

	/**
	 * Removes the commerce channel where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce channel that was removed
	 */
	@Override
	public CommerceChannel removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchChannelException {
		CommerceChannel commerceChannel = findByC_ERC(companyId,
				externalReferenceCode);

		return remove(commerceChannel);
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce channels
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_ERC;

		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECHANNEL_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 = "commerceChannel.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1 = "commerceChannel.externalReferenceCode IS NULL";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 = "commerceChannel.externalReferenceCode = ?";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 = "(commerceChannel.externalReferenceCode IS NULL OR commerceChannel.externalReferenceCode = '')";

	public CommerceChannelPersistenceImpl() {
		setModelClass(CommerceChannel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce channel in the entity cache if it is enabled.
	 *
	 * @param commerceChannel the commerce channel
	 */
	@Override
	public void cacheResult(CommerceChannel commerceChannel) {
		entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey(),
			commerceChannel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
			new Object[] {
				commerceChannel.getCompanyId(),
				commerceChannel.getExternalReferenceCode()
			}, commerceChannel);

		commerceChannel.resetOriginalValues();
	}

	/**
	 * Caches the commerce channels in the entity cache if it is enabled.
	 *
	 * @param commerceChannels the commerce channels
	 */
	@Override
	public void cacheResult(List<CommerceChannel> commerceChannels) {
		for (CommerceChannel commerceChannel : commerceChannels) {
			if (entityCache.getResult(
						CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelImpl.class,
						commerceChannel.getPrimaryKey()) == null) {
				cacheResult(commerceChannel);
			}
			else {
				commerceChannel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce channels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceChannelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce channel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceChannel commerceChannel) {
		entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceChannelModelImpl)commerceChannel, true);
	}

	@Override
	public void clearCache(List<CommerceChannel> commerceChannels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceChannel commerceChannel : commerceChannels) {
			entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelImpl.class, commerceChannel.getPrimaryKey());

			clearUniqueFindersCache((CommerceChannelModelImpl)commerceChannel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceChannelModelImpl commerceChannelModelImpl) {
		Object[] args = new Object[] {
				commerceChannelModelImpl.getCompanyId(),
				commerceChannelModelImpl.getExternalReferenceCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_ERC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC, args,
			commerceChannelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceChannelModelImpl commerceChannelModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceChannelModelImpl.getCompanyId(),
					commerceChannelModelImpl.getExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}

		if ((commerceChannelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_ERC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceChannelModelImpl.getOriginalCompanyId(),
					commerceChannelModelImpl.getOriginalExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}
	}

	/**
	 * Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	 *
	 * @param commerceChannelId the primary key for the new commerce channel
	 * @return the new commerce channel
	 */
	@Override
	public CommerceChannel create(long commerceChannelId) {
		CommerceChannel commerceChannel = new CommerceChannelImpl();

		commerceChannel.setNew(true);
		commerceChannel.setPrimaryKey(commerceChannelId);

		commerceChannel.setCompanyId(companyProvider.getCompanyId());

		return commerceChannel;
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(long commerceChannelId)
		throws NoSuchChannelException {
		return remove((Serializable)commerceChannelId);
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(Serializable primaryKey)
		throws NoSuchChannelException {
		Session session = null;

		try {
			session = openSession();

			CommerceChannel commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
					primaryKey);

			if (commerceChannel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceChannel);
		}
		catch (NoSuchChannelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommerceChannel removeImpl(CommerceChannel commerceChannel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceChannel)) {
				commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
						commerceChannel.getPrimaryKeyObj());
			}

			if (commerceChannel != null) {
				session.delete(commerceChannel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceChannel != null) {
			clearCache(commerceChannel);
		}

		return commerceChannel;
	}

	@Override
	public CommerceChannel updateImpl(CommerceChannel commerceChannel) {
		boolean isNew = commerceChannel.isNew();

		if (!(commerceChannel instanceof CommerceChannelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceChannel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceChannel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceChannel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceChannel implementation " +
				commerceChannel.getClass());
		}

		CommerceChannelModelImpl commerceChannelModelImpl = (CommerceChannelModelImpl)commerceChannel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceChannel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceChannel.setCreateDate(now);
			}
			else {
				commerceChannel.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceChannelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceChannel.setModifiedDate(now);
			}
			else {
				commerceChannel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceChannel.isNew()) {
				session.save(commerceChannel);

				commerceChannel.setNew(false);
			}
			else {
				commerceChannel = (CommerceChannel)session.merge(commerceChannel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceChannelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { commerceChannelModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceChannelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceChannelModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { commerceChannelModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey(),
			commerceChannel, false);

		clearUniqueFindersCache(commerceChannelModelImpl, false);
		cacheUniqueFindersCache(commerceChannelModelImpl);

		commerceChannel.resetOriginalValues();

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChannelException {
		CommerceChannel commerceChannel = fetchByPrimaryKey(primaryKey);

		if (commerceChannel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a {@link NoSuchChannelException} if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(long commerceChannelId)
		throws NoSuchChannelException {
		return findByPrimaryKey((Serializable)commerceChannelId);
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceChannel commerceChannel = (CommerceChannel)serializable;

		if (commerceChannel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
						primaryKey);

				if (commerceChannel != null) {
					cacheResult(commerceChannel);
				}
				else {
					entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel fetchByPrimaryKey(long commerceChannelId) {
		return fetchByPrimaryKey((Serializable)commerceChannelId);
	}

	@Override
	public Map<Serializable, CommerceChannel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceChannel> map = new HashMap<Serializable, CommerceChannel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceChannel commerceChannel = fetchByPrimaryKey(primaryKey);

			if (commerceChannel != null) {
				map.put(primaryKey, commerceChannel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceChannel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCECHANNEL_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CommerceChannel commerceChannel : (List<CommerceChannel>)q.list()) {
				map.put(commerceChannel.getPrimaryKeyObj(), commerceChannel);

				cacheResult(commerceChannel);

				uncachedPrimaryKeys.remove(commerceChannel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the commerce channels.
	 *
	 * @return the commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CommerceChannel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECHANNEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECHANNEL;

				if (pagination) {
					sql = sql.concat(CommerceChannelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce channels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceChannel commerceChannel : findAll()) {
			remove(commerceChannel);
		}
	}

	/**
	 * Returns the number of commerce channels.
	 *
	 * @return the number of commerce channels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECHANNEL);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
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
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceChannelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce channel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceChannelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMERCECHANNEL = "SELECT commerceChannel FROM CommerceChannel commerceChannel";
	private static final String _SQL_SELECT_COMMERCECHANNEL_WHERE_PKS_IN = "SELECT commerceChannel FROM CommerceChannel commerceChannel WHERE commerceChannelId IN (";
	private static final String _SQL_SELECT_COMMERCECHANNEL_WHERE = "SELECT commerceChannel FROM CommerceChannel commerceChannel WHERE ";
	private static final String _SQL_COUNT_COMMERCECHANNEL = "SELECT COUNT(commerceChannel) FROM CommerceChannel commerceChannel";
	private static final String _SQL_COUNT_COMMERCECHANNEL_WHERE = "SELECT COUNT(commerceChannel) FROM CommerceChannel commerceChannel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceChannel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceChannel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceChannel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceChannelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}