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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionRelModel;
import com.liferay.commerce.product.model.CPDefinitionOptionRelSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the CPDefinitionOptionRel service. Represents a row in the &quot;CPDefinitionOptionRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CPDefinitionOptionRelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDefinitionOptionRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionOptionRelImpl
 * @see CPDefinitionOptionRel
 * @see CPDefinitionOptionRelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CPDefinitionOptionRelModelImpl extends BaseModelImpl<CPDefinitionOptionRel>
	implements CPDefinitionOptionRelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition option rel model instance should use the {@link CPDefinitionOptionRel} interface instead.
	 */
	public static final String TABLE_NAME = "CPDefinitionOptionRel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "CPDefinitionOptionRelId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "CPDefinitionId", Types.BIGINT },
			{ "CPOptionId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "DDMFormFieldTypeName", Types.VARCHAR },
			{ "priority", Types.DOUBLE },
			{ "facetable", Types.BOOLEAN },
			{ "required", Types.BOOLEAN },
			{ "skuContributor", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPDefinitionOptionRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("DDMFormFieldTypeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("facetable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("required", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("skuContributor", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CPDefinitionOptionRel (uuid_ VARCHAR(75) null,CPDefinitionOptionRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPDefinitionId LONG,CPOptionId LONG,name STRING null,description STRING null,DDMFormFieldTypeName VARCHAR(75) null,priority DOUBLE,facetable BOOLEAN,required BOOLEAN,skuContributor BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CPDefinitionOptionRel";
	public static final String ORDER_BY_JPQL = " ORDER BY cpDefinitionOptionRel.priority ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CPDefinitionOptionRel.priority ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CPDefinitionOptionRel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CPDefinitionOptionRel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CPDefinitionOptionRel"),
			true);
	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long SKUCONTRIBUTOR_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long PRIORITY_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CPDefinitionOptionRel toModel(
		CPDefinitionOptionRelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CPDefinitionOptionRel model = new CPDefinitionOptionRelImpl();

		model.setUuid(soapModel.getUuid());
		model.setCPDefinitionOptionRelId(soapModel.getCPDefinitionOptionRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPDefinitionId(soapModel.getCPDefinitionId());
		model.setCPOptionId(soapModel.getCPOptionId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setDDMFormFieldTypeName(soapModel.getDDMFormFieldTypeName());
		model.setPriority(soapModel.getPriority());
		model.setFacetable(soapModel.isFacetable());
		model.setRequired(soapModel.isRequired());
		model.setSkuContributor(soapModel.isSkuContributor());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CPDefinitionOptionRel> toModels(
		CPDefinitionOptionRelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CPDefinitionOptionRel> models = new ArrayList<CPDefinitionOptionRel>(soapModels.length);

		for (CPDefinitionOptionRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.product.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.product.model.CPDefinitionOptionRel"));

	public CPDefinitionOptionRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPDefinitionOptionRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPDefinitionOptionRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPDefinitionOptionRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionOptionRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionOptionRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CPDefinitionOptionRelId", getCPDefinitionOptionRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPDefinitionId", getCPDefinitionId());
		attributes.put("CPOptionId", getCPOptionId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("DDMFormFieldTypeName", getDDMFormFieldTypeName());
		attributes.put("priority", getPriority());
		attributes.put("facetable", isFacetable());
		attributes.put("required", isRequired());
		attributes.put("skuContributor", isSkuContributor());

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

		Long CPDefinitionOptionRelId = (Long)attributes.get(
				"CPDefinitionOptionRelId");

		if (CPDefinitionOptionRelId != null) {
			setCPDefinitionOptionRelId(CPDefinitionOptionRelId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long CPDefinitionId = (Long)attributes.get("CPDefinitionId");

		if (CPDefinitionId != null) {
			setCPDefinitionId(CPDefinitionId);
		}

		Long CPOptionId = (Long)attributes.get("CPOptionId");

		if (CPOptionId != null) {
			setCPOptionId(CPOptionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String DDMFormFieldTypeName = (String)attributes.get(
				"DDMFormFieldTypeName");

		if (DDMFormFieldTypeName != null) {
			setDDMFormFieldTypeName(DDMFormFieldTypeName);
		}

		Double priority = (Double)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Boolean facetable = (Boolean)attributes.get("facetable");

		if (facetable != null) {
			setFacetable(facetable);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}

		Boolean skuContributor = (Boolean)attributes.get("skuContributor");

		if (skuContributor != null) {
			setSkuContributor(skuContributor);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
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
	public long getCPDefinitionOptionRelId() {
		return _CPDefinitionOptionRelId;
	}

	@Override
	public void setCPDefinitionOptionRelId(long CPDefinitionOptionRelId) {
		_CPDefinitionOptionRelId = CPDefinitionOptionRelId;
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
	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		_columnBitmask |= CPDEFINITIONID_COLUMN_BITMASK;

		if (!_setOriginalCPDefinitionId) {
			_setOriginalCPDefinitionId = true;

			_originalCPDefinitionId = _CPDefinitionId;
		}

		_CPDefinitionId = CPDefinitionId;
	}

	public long getOriginalCPDefinitionId() {
		return _originalCPDefinitionId;
	}

	@JSON
	@Override
	public long getCPOptionId() {
		return _CPOptionId;
	}

	@Override
	public void setCPOptionId(long CPOptionId) {
		_CPOptionId = CPOptionId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(LocalizationUtil.updateLocalization(nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		setDescription(LocalizationUtil.updateLocalization(descriptionMap,
				getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDDMFormFieldTypeName() {
		if (_DDMFormFieldTypeName == null) {
			return "";
		}
		else {
			return _DDMFormFieldTypeName;
		}
	}

	@Override
	public void setDDMFormFieldTypeName(String DDMFormFieldTypeName) {
		_DDMFormFieldTypeName = DDMFormFieldTypeName;
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		_columnBitmask = -1L;

		_priority = priority;
	}

	@JSON
	@Override
	public boolean getFacetable() {
		return _facetable;
	}

	@JSON
	@Override
	public boolean isFacetable() {
		return _facetable;
	}

	@Override
	public void setFacetable(boolean facetable) {
		_facetable = facetable;
	}

	@JSON
	@Override
	public boolean getRequired() {
		return _required;
	}

	@JSON
	@Override
	public boolean isRequired() {
		return _required;
	}

	@Override
	public void setRequired(boolean required) {
		_required = required;
	}

	@JSON
	@Override
	public boolean getSkuContributor() {
		return _skuContributor;
	}

	@JSON
	@Override
	public boolean isSkuContributor() {
		return _skuContributor;
	}

	@Override
	public void setSkuContributor(boolean skuContributor) {
		_columnBitmask |= SKUCONTRIBUTOR_COLUMN_BITMASK;

		if (!_setOriginalSkuContributor) {
			_setOriginalSkuContributor = true;

			_originalSkuContributor = _skuContributor;
		}

		_skuContributor = skuContributor;
	}

	public boolean getOriginalSkuContributor() {
		return _originalSkuContributor;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				CPDefinitionOptionRel.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CPDefinitionOptionRel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(CPDefinitionOptionRel.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public CPDefinitionOptionRel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CPDefinitionOptionRel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CPDefinitionOptionRelImpl cpDefinitionOptionRelImpl = new CPDefinitionOptionRelImpl();

		cpDefinitionOptionRelImpl.setUuid(getUuid());
		cpDefinitionOptionRelImpl.setCPDefinitionOptionRelId(getCPDefinitionOptionRelId());
		cpDefinitionOptionRelImpl.setGroupId(getGroupId());
		cpDefinitionOptionRelImpl.setCompanyId(getCompanyId());
		cpDefinitionOptionRelImpl.setUserId(getUserId());
		cpDefinitionOptionRelImpl.setUserName(getUserName());
		cpDefinitionOptionRelImpl.setCreateDate(getCreateDate());
		cpDefinitionOptionRelImpl.setModifiedDate(getModifiedDate());
		cpDefinitionOptionRelImpl.setCPDefinitionId(getCPDefinitionId());
		cpDefinitionOptionRelImpl.setCPOptionId(getCPOptionId());
		cpDefinitionOptionRelImpl.setName(getName());
		cpDefinitionOptionRelImpl.setDescription(getDescription());
		cpDefinitionOptionRelImpl.setDDMFormFieldTypeName(getDDMFormFieldTypeName());
		cpDefinitionOptionRelImpl.setPriority(getPriority());
		cpDefinitionOptionRelImpl.setFacetable(isFacetable());
		cpDefinitionOptionRelImpl.setRequired(isRequired());
		cpDefinitionOptionRelImpl.setSkuContributor(isSkuContributor());

		cpDefinitionOptionRelImpl.resetOriginalValues();

		return cpDefinitionOptionRelImpl;
	}

	@Override
	public int compareTo(CPDefinitionOptionRel cpDefinitionOptionRel) {
		int value = 0;

		if (getPriority() < cpDefinitionOptionRel.getPriority()) {
			value = -1;
		}
		else if (getPriority() > cpDefinitionOptionRel.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionOptionRel)) {
			return false;
		}

		CPDefinitionOptionRel cpDefinitionOptionRel = (CPDefinitionOptionRel)obj;

		long primaryKey = cpDefinitionOptionRel.getPrimaryKey();

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
		CPDefinitionOptionRelModelImpl cpDefinitionOptionRelModelImpl = this;

		cpDefinitionOptionRelModelImpl._originalUuid = cpDefinitionOptionRelModelImpl._uuid;

		cpDefinitionOptionRelModelImpl._originalGroupId = cpDefinitionOptionRelModelImpl._groupId;

		cpDefinitionOptionRelModelImpl._setOriginalGroupId = false;

		cpDefinitionOptionRelModelImpl._originalCompanyId = cpDefinitionOptionRelModelImpl._companyId;

		cpDefinitionOptionRelModelImpl._setOriginalCompanyId = false;

		cpDefinitionOptionRelModelImpl._setModifiedDate = false;

		cpDefinitionOptionRelModelImpl._originalCPDefinitionId = cpDefinitionOptionRelModelImpl._CPDefinitionId;

		cpDefinitionOptionRelModelImpl._setOriginalCPDefinitionId = false;

		cpDefinitionOptionRelModelImpl._originalSkuContributor = cpDefinitionOptionRelModelImpl._skuContributor;

		cpDefinitionOptionRelModelImpl._setOriginalSkuContributor = false;

		cpDefinitionOptionRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CPDefinitionOptionRel> toCacheModel() {
		CPDefinitionOptionRelCacheModel cpDefinitionOptionRelCacheModel = new CPDefinitionOptionRelCacheModel();

		cpDefinitionOptionRelCacheModel.uuid = getUuid();

		String uuid = cpDefinitionOptionRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpDefinitionOptionRelCacheModel.uuid = null;
		}

		cpDefinitionOptionRelCacheModel.CPDefinitionOptionRelId = getCPDefinitionOptionRelId();

		cpDefinitionOptionRelCacheModel.groupId = getGroupId();

		cpDefinitionOptionRelCacheModel.companyId = getCompanyId();

		cpDefinitionOptionRelCacheModel.userId = getUserId();

		cpDefinitionOptionRelCacheModel.userName = getUserName();

		String userName = cpDefinitionOptionRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpDefinitionOptionRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpDefinitionOptionRelCacheModel.createDate = createDate.getTime();
		}
		else {
			cpDefinitionOptionRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpDefinitionOptionRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpDefinitionOptionRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpDefinitionOptionRelCacheModel.CPDefinitionId = getCPDefinitionId();

		cpDefinitionOptionRelCacheModel.CPOptionId = getCPOptionId();

		cpDefinitionOptionRelCacheModel.name = getName();

		String name = cpDefinitionOptionRelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpDefinitionOptionRelCacheModel.name = null;
		}

		cpDefinitionOptionRelCacheModel.description = getDescription();

		String description = cpDefinitionOptionRelCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			cpDefinitionOptionRelCacheModel.description = null;
		}

		cpDefinitionOptionRelCacheModel.DDMFormFieldTypeName = getDDMFormFieldTypeName();

		String DDMFormFieldTypeName = cpDefinitionOptionRelCacheModel.DDMFormFieldTypeName;

		if ((DDMFormFieldTypeName != null) &&
				(DDMFormFieldTypeName.length() == 0)) {
			cpDefinitionOptionRelCacheModel.DDMFormFieldTypeName = null;
		}

		cpDefinitionOptionRelCacheModel.priority = getPriority();

		cpDefinitionOptionRelCacheModel.facetable = isFacetable();

		cpDefinitionOptionRelCacheModel.required = isRequired();

		cpDefinitionOptionRelCacheModel.skuContributor = isSkuContributor();

		return cpDefinitionOptionRelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", CPDefinitionOptionRelId=");
		sb.append(getCPDefinitionOptionRelId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", CPDefinitionId=");
		sb.append(getCPDefinitionId());
		sb.append(", CPOptionId=");
		sb.append(getCPOptionId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", DDMFormFieldTypeName=");
		sb.append(getDDMFormFieldTypeName());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append(", facetable=");
		sb.append(isFacetable());
		sb.append(", required=");
		sb.append(isRequired());
		sb.append(", skuContributor=");
		sb.append(isSkuContributor());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.product.model.CPDefinitionOptionRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CPDefinitionOptionRelId</column-name><column-value><![CDATA[");
		sb.append(getCPDefinitionOptionRelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>CPDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getCPDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CPOptionId</column-name><column-value><![CDATA[");
		sb.append(getCPOptionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMFormFieldTypeName</column-name><column-value><![CDATA[");
		sb.append(getDDMFormFieldTypeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>facetable</column-name><column-value><![CDATA[");
		sb.append(isFacetable());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>required</column-name><column-value><![CDATA[");
		sb.append(isRequired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>skuContributor</column-name><column-value><![CDATA[");
		sb.append(isSkuContributor());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CPDefinitionOptionRel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CPDefinitionOptionRel.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _CPDefinitionOptionRelId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPDefinitionId;
	private long _originalCPDefinitionId;
	private boolean _setOriginalCPDefinitionId;
	private long _CPOptionId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _DDMFormFieldTypeName;
	private double _priority;
	private boolean _facetable;
	private boolean _required;
	private boolean _skuContributor;
	private boolean _originalSkuContributor;
	private boolean _setOriginalSkuContributor;
	private long _columnBitmask;
	private CPDefinitionOptionRel _escapedModel;
}