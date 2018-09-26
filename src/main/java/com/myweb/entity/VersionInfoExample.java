package com.myweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VersionInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VersionInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andVidIsNull() {
            addCriterion("vid is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("vid is not null");
            return (Criteria) this;
        }

        public Criteria andVidEqualTo(Integer value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(Integer value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(Integer value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(Integer value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(Integer value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<Integer> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<Integer> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(Integer value1, Integer value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(Integer value1, Integer value2) {
            addCriterion("vid not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVersiontypeIsNull() {
            addCriterion("versionType is null");
            return (Criteria) this;
        }

        public Criteria andVersiontypeIsNotNull() {
            addCriterion("versionType is not null");
            return (Criteria) this;
        }

        public Criteria andVersiontypeEqualTo(String value) {
            addCriterion("versionType =", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeNotEqualTo(String value) {
            addCriterion("versionType <>", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeGreaterThan(String value) {
            addCriterion("versionType >", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeGreaterThanOrEqualTo(String value) {
            addCriterion("versionType >=", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeLessThan(String value) {
            addCriterion("versionType <", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeLessThanOrEqualTo(String value) {
            addCriterion("versionType <=", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeLike(String value) {
            addCriterion("versionType like", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeNotLike(String value) {
            addCriterion("versionType not like", value, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeIn(List<String> values) {
            addCriterion("versionType in", values, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeNotIn(List<String> values) {
            addCriterion("versionType not in", values, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeBetween(String value1, String value2) {
            addCriterion("versionType between", value1, value2, "versiontype");
            return (Criteria) this;
        }

        public Criteria andVersiontypeNotBetween(String value1, String value2) {
            addCriterion("versionType not between", value1, value2, "versiontype");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andReleasedateIsNull() {
            addCriterion("releaseDate is null");
            return (Criteria) this;
        }

        public Criteria andReleasedateIsNotNull() {
            addCriterion("releaseDate is not null");
            return (Criteria) this;
        }

        public Criteria andReleasedateEqualTo(Date value) {
            addCriterion("releaseDate =", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateNotEqualTo(Date value) {
            addCriterion("releaseDate <>", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateGreaterThan(Date value) {
            addCriterion("releaseDate >", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateGreaterThanOrEqualTo(Date value) {
            addCriterion("releaseDate >=", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateLessThan(Date value) {
            addCriterion("releaseDate <", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateLessThanOrEqualTo(Date value) {
            addCriterion("releaseDate <=", value, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateIn(List<Date> values) {
            addCriterion("releaseDate in", values, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateNotIn(List<Date> values) {
            addCriterion("releaseDate not in", values, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateBetween(Date value1, Date value2) {
            addCriterion("releaseDate between", value1, value2, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasedateNotBetween(Date value1, Date value2) {
            addCriterion("releaseDate not between", value1, value2, "releasedate");
            return (Criteria) this;
        }

        public Criteria andReleasenameIsNull() {
            addCriterion("releaseName is null");
            return (Criteria) this;
        }

        public Criteria andReleasenameIsNotNull() {
            addCriterion("releaseName is not null");
            return (Criteria) this;
        }

        public Criteria andReleasenameEqualTo(String value) {
            addCriterion("releaseName =", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameNotEqualTo(String value) {
            addCriterion("releaseName <>", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameGreaterThan(String value) {
            addCriterion("releaseName >", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameGreaterThanOrEqualTo(String value) {
            addCriterion("releaseName >=", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameLessThan(String value) {
            addCriterion("releaseName <", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameLessThanOrEqualTo(String value) {
            addCriterion("releaseName <=", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameLike(String value) {
            addCriterion("releaseName like", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameNotLike(String value) {
            addCriterion("releaseName not like", value, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameIn(List<String> values) {
            addCriterion("releaseName in", values, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameNotIn(List<String> values) {
            addCriterion("releaseName not in", values, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameBetween(String value1, String value2) {
            addCriterion("releaseName between", value1, value2, "releasename");
            return (Criteria) this;
        }

        public Criteria andReleasenameNotBetween(String value1, String value2) {
            addCriterion("releaseName not between", value1, value2, "releasename");
            return (Criteria) this;
        }

        public Criteria andForceupdateIsNull() {
            addCriterion("forceUpdate is null");
            return (Criteria) this;
        }

        public Criteria andForceupdateIsNotNull() {
            addCriterion("forceUpdate is not null");
            return (Criteria) this;
        }

        public Criteria andForceupdateEqualTo(String value) {
            addCriterion("forceUpdate =", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateNotEqualTo(String value) {
            addCriterion("forceUpdate <>", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateGreaterThan(String value) {
            addCriterion("forceUpdate >", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateGreaterThanOrEqualTo(String value) {
            addCriterion("forceUpdate >=", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateLessThan(String value) {
            addCriterion("forceUpdate <", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateLessThanOrEqualTo(String value) {
            addCriterion("forceUpdate <=", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateLike(String value) {
            addCriterion("forceUpdate like", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateNotLike(String value) {
            addCriterion("forceUpdate not like", value, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateIn(List<String> values) {
            addCriterion("forceUpdate in", values, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateNotIn(List<String> values) {
            addCriterion("forceUpdate not in", values, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateBetween(String value1, String value2) {
            addCriterion("forceUpdate between", value1, value2, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andForceupdateNotBetween(String value1, String value2) {
            addCriterion("forceUpdate not between", value1, value2, "forceupdate");
            return (Criteria) this;
        }

        public Criteria andUseversionIsNull() {
            addCriterion("useVersion is null");
            return (Criteria) this;
        }

        public Criteria andUseversionIsNotNull() {
            addCriterion("useVersion is not null");
            return (Criteria) this;
        }

        public Criteria andUseversionEqualTo(Integer value) {
            addCriterion("useVersion =", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionNotEqualTo(Integer value) {
            addCriterion("useVersion <>", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionGreaterThan(Integer value) {
            addCriterion("useVersion >", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionGreaterThanOrEqualTo(Integer value) {
            addCriterion("useVersion >=", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionLessThan(Integer value) {
            addCriterion("useVersion <", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionLessThanOrEqualTo(Integer value) {
            addCriterion("useVersion <=", value, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionIn(List<Integer> values) {
            addCriterion("useVersion in", values, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionNotIn(List<Integer> values) {
            addCriterion("useVersion not in", values, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionBetween(Integer value1, Integer value2) {
            addCriterion("useVersion between", value1, value2, "useversion");
            return (Criteria) this;
        }

        public Criteria andUseversionNotBetween(Integer value1, Integer value2) {
            addCriterion("useVersion not between", value1, value2, "useversion");
            return (Criteria) this;
        }

        public Criteria andTempIsNull() {
            addCriterion("temp is null");
            return (Criteria) this;
        }

        public Criteria andTempIsNotNull() {
            addCriterion("temp is not null");
            return (Criteria) this;
        }

        public Criteria andTempEqualTo(String value) {
            addCriterion("temp =", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotEqualTo(String value) {
            addCriterion("temp <>", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThan(String value) {
            addCriterion("temp >", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThanOrEqualTo(String value) {
            addCriterion("temp >=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThan(String value) {
            addCriterion("temp <", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThanOrEqualTo(String value) {
            addCriterion("temp <=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLike(String value) {
            addCriterion("temp like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotLike(String value) {
            addCriterion("temp not like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempIn(List<String> values) {
            addCriterion("temp in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotIn(List<String> values) {
            addCriterion("temp not in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempBetween(String value1, String value2) {
            addCriterion("temp between", value1, value2, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotBetween(String value1, String value2) {
            addCriterion("temp not between", value1, value2, "temp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}