package com.awb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoneyrecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MoneyrecordExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDealidIsNull() {
            addCriterion("dealid is null");
            return (Criteria) this;
        }

        public Criteria andDealidIsNotNull() {
            addCriterion("dealid is not null");
            return (Criteria) this;
        }

        public Criteria andDealidEqualTo(String value) {
            addCriterion("dealid =", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidNotEqualTo(String value) {
            addCriterion("dealid <>", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidGreaterThan(String value) {
            addCriterion("dealid >", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidGreaterThanOrEqualTo(String value) {
            addCriterion("dealid >=", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidLessThan(String value) {
            addCriterion("dealid <", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidLessThanOrEqualTo(String value) {
            addCriterion("dealid <=", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidLike(String value) {
            addCriterion("dealid like", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidNotLike(String value) {
            addCriterion("dealid not like", value, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidIn(List<String> values) {
            addCriterion("dealid in", values, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidNotIn(List<String> values) {
            addCriterion("dealid not in", values, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidBetween(String value1, String value2) {
            addCriterion("dealid between", value1, value2, "dealid");
            return (Criteria) this;
        }

        public Criteria andDealidNotBetween(String value1, String value2) {
            addCriterion("dealid not between", value1, value2, "dealid");
            return (Criteria) this;
        }

        public Criteria andAccountnumIsNull() {
            addCriterion("accountnum is null");
            return (Criteria) this;
        }

        public Criteria andAccountnumIsNotNull() {
            addCriterion("accountnum is not null");
            return (Criteria) this;
        }

        public Criteria andAccountnumEqualTo(String value) {
            addCriterion("accountnum =", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumNotEqualTo(String value) {
            addCriterion("accountnum <>", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumGreaterThan(String value) {
            addCriterion("accountnum >", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumGreaterThanOrEqualTo(String value) {
            addCriterion("accountnum >=", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumLessThan(String value) {
            addCriterion("accountnum <", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumLessThanOrEqualTo(String value) {
            addCriterion("accountnum <=", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumLike(String value) {
            addCriterion("accountnum like", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumNotLike(String value) {
            addCriterion("accountnum not like", value, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumIn(List<String> values) {
            addCriterion("accountnum in", values, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumNotIn(List<String> values) {
            addCriterion("accountnum not in", values, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumBetween(String value1, String value2) {
            addCriterion("accountnum between", value1, value2, "accountnum");
            return (Criteria) this;
        }

        public Criteria andAccountnumNotBetween(String value1, String value2) {
            addCriterion("accountnum not between", value1, value2, "accountnum");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIsNull() {
            addCriterion("arrivetime is null");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIsNotNull() {
            addCriterion("arrivetime is not null");
            return (Criteria) this;
        }

        public Criteria andArrivetimeEqualTo(String value) {
            addCriterion("arrivetime =", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotEqualTo(String value) {
            addCriterion("arrivetime <>", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeGreaterThan(String value) {
            addCriterion("arrivetime >", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeGreaterThanOrEqualTo(String value) {
            addCriterion("arrivetime >=", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeLessThan(String value) {
            addCriterion("arrivetime <", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeLessThanOrEqualTo(String value) {
            addCriterion("arrivetime <=", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeLike(String value) {
            addCriterion("arrivetime like", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotLike(String value) {
            addCriterion("arrivetime not like", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIn(List<String> values) {
            addCriterion("arrivetime in", values, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotIn(List<String> values) {
            addCriterion("arrivetime not in", values, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeBetween(String value1, String value2) {
            addCriterion("arrivetime between", value1, value2, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotBetween(String value1, String value2) {
            addCriterion("arrivetime not between", value1, value2, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPicture2IsNull() {
            addCriterion("picture2 is null");
            return (Criteria) this;
        }

        public Criteria andPicture2IsNotNull() {
            addCriterion("picture2 is not null");
            return (Criteria) this;
        }

        public Criteria andPicture2EqualTo(String value) {
            addCriterion("picture2 =", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2NotEqualTo(String value) {
            addCriterion("picture2 <>", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2GreaterThan(String value) {
            addCriterion("picture2 >", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2GreaterThanOrEqualTo(String value) {
            addCriterion("picture2 >=", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2LessThan(String value) {
            addCriterion("picture2 <", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2LessThanOrEqualTo(String value) {
            addCriterion("picture2 <=", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2Like(String value) {
            addCriterion("picture2 like", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2NotLike(String value) {
            addCriterion("picture2 not like", value, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2In(List<String> values) {
            addCriterion("picture2 in", values, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2NotIn(List<String> values) {
            addCriterion("picture2 not in", values, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2Between(String value1, String value2) {
            addCriterion("picture2 between", value1, value2, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture2NotBetween(String value1, String value2) {
            addCriterion("picture2 not between", value1, value2, "picture2");
            return (Criteria) this;
        }

        public Criteria andPicture3IsNull() {
            addCriterion("picture3 is null");
            return (Criteria) this;
        }

        public Criteria andPicture3IsNotNull() {
            addCriterion("picture3 is not null");
            return (Criteria) this;
        }

        public Criteria andPicture3EqualTo(String value) {
            addCriterion("picture3 =", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3NotEqualTo(String value) {
            addCriterion("picture3 <>", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3GreaterThan(String value) {
            addCriterion("picture3 >", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3GreaterThanOrEqualTo(String value) {
            addCriterion("picture3 >=", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3LessThan(String value) {
            addCriterion("picture3 <", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3LessThanOrEqualTo(String value) {
            addCriterion("picture3 <=", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3Like(String value) {
            addCriterion("picture3 like", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3NotLike(String value) {
            addCriterion("picture3 not like", value, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3In(List<String> values) {
            addCriterion("picture3 in", values, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3NotIn(List<String> values) {
            addCriterion("picture3 not in", values, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3Between(String value1, String value2) {
            addCriterion("picture3 between", value1, value2, "picture3");
            return (Criteria) this;
        }

        public Criteria andPicture3NotBetween(String value1, String value2) {
            addCriterion("picture3 not between", value1, value2, "picture3");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIsNull() {
            addCriterion("realmoney is null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIsNotNull() {
            addCriterion("realmoney is not null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyEqualTo(Double value) {
            addCriterion("realmoney =", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotEqualTo(Double value) {
            addCriterion("realmoney <>", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThan(Double value) {
            addCriterion("realmoney >", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("realmoney >=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThan(Double value) {
            addCriterion("realmoney <", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThanOrEqualTo(Double value) {
            addCriterion("realmoney <=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIn(List<Double> values) {
            addCriterion("realmoney in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotIn(List<Double> values) {
            addCriterion("realmoney not in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyBetween(Double value1, Double value2) {
            addCriterion("realmoney between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotBetween(Double value1, Double value2) {
            addCriterion("realmoney not between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyIsNull() {
            addCriterion("shouldmoney is null");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyIsNotNull() {
            addCriterion("shouldmoney is not null");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyEqualTo(Double value) {
            addCriterion("shouldmoney =", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyNotEqualTo(Double value) {
            addCriterion("shouldmoney <>", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyGreaterThan(Double value) {
            addCriterion("shouldmoney >", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("shouldmoney >=", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyLessThan(Double value) {
            addCriterion("shouldmoney <", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyLessThanOrEqualTo(Double value) {
            addCriterion("shouldmoney <=", value, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyIn(List<Double> values) {
            addCriterion("shouldmoney in", values, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyNotIn(List<Double> values) {
            addCriterion("shouldmoney not in", values, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyBetween(Double value1, Double value2) {
            addCriterion("shouldmoney between", value1, value2, "shouldmoney");
            return (Criteria) this;
        }

        public Criteria andShouldmoneyNotBetween(Double value1, Double value2) {
            addCriterion("shouldmoney not between", value1, value2, "shouldmoney");
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