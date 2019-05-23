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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.model.CommerceWarehouseItemModel;
import com.liferay.commerce.model.CommerceWarehouseItemSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceWarehouseItem service. Represents a row in the &quot;CommerceWarehouseItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceWarehouseItemModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceWarehouseItemImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceWarehouseItemImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceWarehouseItemModelImpl
	extends BaseModelImpl<CommerceWarehouseItem>
	implements CommerceWarehouseItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce warehouse item model instance should use the <code>CommerceWarehouseItem</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceWarehouseItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceWarehouseItemId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commerceWarehouseId", Types.BIGINT}, {"CProductId", Types.BIGINT},
		{"CPInstanceUuid", Types.VARCHAR}, {"quantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceWarehouseItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceWarehouseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CProductId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPInstanceUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceWarehouseItem (commerceWarehouseItemId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceWarehouseId LONG,CProductId LONG,CPInstanceUuid VARCHAR(75) null,quantity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceWarehouseItem";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceWarehouseItem.commerceWarehouseItemId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceWarehouseItem.commerceWarehouseItemId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceWarehouseItem"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceWarehouseItem"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceWarehouseItem"),
		true);

	public static final long CPINSTANCEUUID_COLUMN_BITMASK = 1L;

	public static final long CPRODUCTID_COLUMN_BITMASK = 2L;

	public static final long COMMERCEWAREHOUSEID_COLUMN_BITMASK = 4L;

	public static final long COMMERCEWAREHOUSEITEMID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceWarehouseItem toModel(
		CommerceWarehouseItemSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceWarehouseItem model = new CommerceWarehouseItemImpl();

		model.setCommerceWarehouseItemId(
			soapModel.getCommerceWarehouseItemId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceWarehouseId(soapModel.getCommerceWarehouseId());
		model.setCProductId(soapModel.getCProductId());
		model.setCPInstanceUuid(soapModel.getCPInstanceUuid());
		model.setQuantity(soapModel.getQuantity());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceWarehouseItem> toModels(
		CommerceWarehouseItemSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceWarehouseItem> models =
			new ArrayList<CommerceWarehouseItem>(soapModels.length);

		for (CommerceWarehouseItemSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceWarehouseItem"));

	public CommerceWarehouseItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceWarehouseItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceWarehouseItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceWarehouseItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceWarehouseItem.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceWarehouseItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceWarehouseItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceWarehouseItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceWarehouseItem, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceWarehouseItem)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceWarehouseItem, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceWarehouseItem, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceWarehouseItem)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceWarehouseItem, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceWarehouseItem, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CommerceWarehouseItem, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceWarehouseItem, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceWarehouseItem, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceWarehouseItem, Object>>();
		Map<String, BiConsumer<CommerceWarehouseItem, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceWarehouseItem, ?>>();

		attributeGetterFunctions.put(
			"commerceWarehouseItemId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCommerceWarehouseItemId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceWarehouseItemId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object commerceWarehouseItemId) {

					commerceWarehouseItem.setCommerceWarehouseItemId(
						(Long)commerceWarehouseItemId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object groupId) {

					commerceWarehouseItem.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object companyId) {

					commerceWarehouseItem.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object userId) {

					commerceWarehouseItem.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object userName) {

					commerceWarehouseItem.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object createDate) {

					commerceWarehouseItem.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object modifiedDate) {

					commerceWarehouseItem.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"commerceWarehouseId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCommerceWarehouseId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceWarehouseId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object commerceWarehouseId) {

					commerceWarehouseItem.setCommerceWarehouseId(
						(Long)commerceWarehouseId);
				}

			});
		attributeGetterFunctions.put(
			"CProductId",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCProductId();
				}

			});
		attributeSetterBiConsumers.put(
			"CProductId",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object CProductId) {

					commerceWarehouseItem.setCProductId((Long)CProductId);
				}

			});
		attributeGetterFunctions.put(
			"CPInstanceUuid",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getCPInstanceUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"CPInstanceUuid",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object CPInstanceUuid) {

					commerceWarehouseItem.setCPInstanceUuid(
						(String)CPInstanceUuid);
				}

			});
		attributeGetterFunctions.put(
			"quantity",
			new Function<CommerceWarehouseItem, Object>() {

				@Override
				public Object apply(
					CommerceWarehouseItem commerceWarehouseItem) {

					return commerceWarehouseItem.getQuantity();
				}

			});
		attributeSetterBiConsumers.put(
			"quantity",
			new BiConsumer<CommerceWarehouseItem, Object>() {

				@Override
				public void accept(
					CommerceWarehouseItem commerceWarehouseItem,
					Object quantity) {

					commerceWarehouseItem.setQuantity((Integer)quantity);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceWarehouseItemId() {
		return _commerceWarehouseItemId;
	}

	@Override
	public void setCommerceWarehouseItemId(long commerceWarehouseItemId) {
		_commerceWarehouseItemId = commerceWarehouseItemId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
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
	public long getCommerceWarehouseId() {
		return _commerceWarehouseId;
	}

	@Override
	public void setCommerceWarehouseId(long commerceWarehouseId) {
		_columnBitmask |= COMMERCEWAREHOUSEID_COLUMN_BITMASK;

		if (!_setOriginalCommerceWarehouseId) {
			_setOriginalCommerceWarehouseId = true;

			_originalCommerceWarehouseId = _commerceWarehouseId;
		}

		_commerceWarehouseId = commerceWarehouseId;
	}

	public long getOriginalCommerceWarehouseId() {
		return _originalCommerceWarehouseId;
	}

	@JSON
	@Override
	public long getCProductId() {
		return _CProductId;
	}

	@Override
	public void setCProductId(long CProductId) {
		_columnBitmask |= CPRODUCTID_COLUMN_BITMASK;

		if (!_setOriginalCProductId) {
			_setOriginalCProductId = true;

			_originalCProductId = _CProductId;
		}

		_CProductId = CProductId;
	}

	public long getOriginalCProductId() {
		return _originalCProductId;
	}

	@JSON
	@Override
	public String getCPInstanceUuid() {
		if (_CPInstanceUuid == null) {
			return "";
		}
		else {
			return _CPInstanceUuid;
		}
	}

	@Override
	public void setCPInstanceUuid(String CPInstanceUuid) {
		_columnBitmask |= CPINSTANCEUUID_COLUMN_BITMASK;

		if (_originalCPInstanceUuid == null) {
			_originalCPInstanceUuid = _CPInstanceUuid;
		}

		_CPInstanceUuid = CPInstanceUuid;
	}

	public String getOriginalCPInstanceUuid() {
		return GetterUtil.getString(_originalCPInstanceUuid);
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceWarehouseItem.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceWarehouseItem toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceWarehouseItem)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceWarehouseItemImpl commerceWarehouseItemImpl =
			new CommerceWarehouseItemImpl();

		commerceWarehouseItemImpl.setCommerceWarehouseItemId(
			getCommerceWarehouseItemId());
		commerceWarehouseItemImpl.setGroupId(getGroupId());
		commerceWarehouseItemImpl.setCompanyId(getCompanyId());
		commerceWarehouseItemImpl.setUserId(getUserId());
		commerceWarehouseItemImpl.setUserName(getUserName());
		commerceWarehouseItemImpl.setCreateDate(getCreateDate());
		commerceWarehouseItemImpl.setModifiedDate(getModifiedDate());
		commerceWarehouseItemImpl.setCommerceWarehouseId(
			getCommerceWarehouseId());
		commerceWarehouseItemImpl.setCProductId(getCProductId());
		commerceWarehouseItemImpl.setCPInstanceUuid(getCPInstanceUuid());
		commerceWarehouseItemImpl.setQuantity(getQuantity());

		commerceWarehouseItemImpl.resetOriginalValues();

		return commerceWarehouseItemImpl;
	}

	@Override
	public int compareTo(CommerceWarehouseItem commerceWarehouseItem) {
		long primaryKey = commerceWarehouseItem.getPrimaryKey();

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

		if (!(obj instanceof CommerceWarehouseItem)) {
			return false;
		}

		CommerceWarehouseItem commerceWarehouseItem =
			(CommerceWarehouseItem)obj;

		long primaryKey = commerceWarehouseItem.getPrimaryKey();

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
		CommerceWarehouseItemModelImpl commerceWarehouseItemModelImpl = this;

		commerceWarehouseItemModelImpl._setModifiedDate = false;

		commerceWarehouseItemModelImpl._originalCommerceWarehouseId =
			commerceWarehouseItemModelImpl._commerceWarehouseId;

		commerceWarehouseItemModelImpl._setOriginalCommerceWarehouseId = false;

		commerceWarehouseItemModelImpl._originalCProductId =
			commerceWarehouseItemModelImpl._CProductId;

		commerceWarehouseItemModelImpl._setOriginalCProductId = false;

		commerceWarehouseItemModelImpl._originalCPInstanceUuid =
			commerceWarehouseItemModelImpl._CPInstanceUuid;

		commerceWarehouseItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceWarehouseItem> toCacheModel() {
		CommerceWarehouseItemCacheModel commerceWarehouseItemCacheModel =
			new CommerceWarehouseItemCacheModel();

		commerceWarehouseItemCacheModel.commerceWarehouseItemId =
			getCommerceWarehouseItemId();

		commerceWarehouseItemCacheModel.groupId = getGroupId();

		commerceWarehouseItemCacheModel.companyId = getCompanyId();

		commerceWarehouseItemCacheModel.userId = getUserId();

		commerceWarehouseItemCacheModel.userName = getUserName();

		String userName = commerceWarehouseItemCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceWarehouseItemCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceWarehouseItemCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceWarehouseItemCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceWarehouseItemCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceWarehouseItemCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceWarehouseItemCacheModel.commerceWarehouseId =
			getCommerceWarehouseId();

		commerceWarehouseItemCacheModel.CProductId = getCProductId();

		commerceWarehouseItemCacheModel.CPInstanceUuid = getCPInstanceUuid();

		String CPInstanceUuid = commerceWarehouseItemCacheModel.CPInstanceUuid;

		if ((CPInstanceUuid != null) && (CPInstanceUuid.length() == 0)) {
			commerceWarehouseItemCacheModel.CPInstanceUuid = null;
		}

		commerceWarehouseItemCacheModel.quantity = getQuantity();

		return commerceWarehouseItemCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceWarehouseItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceWarehouseItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceWarehouseItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommerceWarehouseItem)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceWarehouseItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceWarehouseItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceWarehouseItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceWarehouseItem)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		CommerceWarehouseItem.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		CommerceWarehouseItem.class, ModelWrapper.class
	};

	private long _commerceWarehouseItemId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceWarehouseId;
	private long _originalCommerceWarehouseId;
	private boolean _setOriginalCommerceWarehouseId;
	private long _CProductId;
	private long _originalCProductId;
	private boolean _setOriginalCProductId;
	private String _CPInstanceUuid;
	private String _originalCPInstanceUuid;
	private int _quantity;
	private long _columnBitmask;
	private CommerceWarehouseItem _escapedModel;

}